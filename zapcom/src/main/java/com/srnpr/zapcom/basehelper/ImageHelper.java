package com.srnpr.zapcom.basehelper;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import com.srnpr.zapcom.baseface.IBaseHelper;

public class ImageHelper implements IBaseHelper {
	
	/**
     * 图片无损缩放 try { File fi = new File("c:/image2.jpg"); // 大图文件 File fo = new
     * File("c:/imgTest.jpg"); // 将要转换出的小图文件 BufferedImage bis =
     * ImageIO.read(fi); BufferedImage bid = null; bid = new
     * ImageScale().imageZoomOut(bis, 228, 96); ImageIO.write(bid, "jpeg", fo);
     * } catch (Exception e) { e.printStackTrace(); }
     */
    private int width;
    private int height;
    private int scaleWidth;

    double support = (double) 3.0;
    double PI = (double) 3.14159265358978;
    double[] contrib;
    double[] normContrib;
    double[] tmpContrib;
    int startContrib, stopContrib;
    int nDots;
    int nHalfDots;

    /**
     * Start: Use Lanczos filter to replace the original algorithm for image
     * scaling. Lanczos improves quality of the scaled image modify by :blade
     */
    public BufferedImage imageZoomOut(BufferedImage srcBufferImage, int w, int h) {
        width = srcBufferImage.getWidth();
        height = srcBufferImage.getHeight();
        scaleWidth = w;
        if (DetermineResultSize(w, h) == 1) {
            return srcBufferImage;
        }
        CalContrib();
        BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w);
        BufferedImage pbFinalOut = VerticalFiltering(pbOut, h);
        return pbFinalOut;
    }

    private int DetermineResultSize(int w, int h) {
        double scaleH, scaleV;
        scaleH = (double) w / (double) width;
        scaleV = (double) h / (double) height;

        if (scaleH >= 1.0 && scaleV >= 1.0) {
            return 1;
        }
        return 0;
    } 

    private double Lanczos(int i, int inWidth, int outWidth, double Support) {
        double x;
        x = (double) i * (double) outWidth / (double) inWidth;
        return Math.sin(x * PI) / (x * PI) * Math.sin(x * PI / Support)
                / (x * PI / Support);

    } // end of Lanczos()

    private void CalContrib() {
        nHalfDots = (int) ((double) width * support / (double) scaleWidth);
        nDots = nHalfDots * 2 + 1;
        try {
            contrib = new double[nDots];
            normContrib = new double[nDots];
            tmpContrib = new double[nDots];
        } catch (Exception e) {
            System.out.println("init   contrib,normContrib,tmpContrib" + e);
        }

        int center = nHalfDots;
        contrib[center] = 1.0;

        double weight = 0.0;
        int i = 0;
        for (i = 1; i <= center; i++) {
            contrib[center + i] = Lanczos(i, width, scaleWidth, support);
            weight += contrib[center + i];
        }

        for (i = center - 1; i >= 0; i--) {
            contrib[i] = contrib[center * 2 - i];
        }

        weight = weight * 2 + 1.0;

        for (i = 0; i <= center; i++) {
            normContrib[i] = contrib[i] / weight;
        }

        for (i = center + 1; i < nDots; i++) {
            normContrib[i] = normContrib[center * 2 - i];
        }
    } 

    private void CalTempContrib(int start, int stop) {
        double weight = 0;

        int i = 0;
        for (i = start; i <= stop; i++) {
            weight += contrib[i];
        }

        for (i = start; i <= stop; i++) {
            tmpContrib[i] = contrib[i] / weight;
        }

    } // end of CalTempContrib()

    private int GetRedValue(int rgbValue) {
        int temp = rgbValue & 0x00ff0000;
        return temp >> 16;
    }

    private int GetGreenValue(int rgbValue) {
        int temp = rgbValue & 0x0000ff00;
        return temp >> 8;
    }

    private int GetBlueValue(int rgbValue) {
        return rgbValue & 0x000000ff;
    }

    private int ComRGB(int redValue, int greenValue, int blueValue) {

        return (redValue << 16) + (greenValue << 8) + blueValue;
    }

    private int HorizontalFilter(BufferedImage bufImg, int startX, int stopX,
            int start, int stop, int y, double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startX, j = start; i <= stopX; i++, j++) {
            valueRGB = bufImg.getRGB(i, y);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
                Clip((int) valueBlue));
        return valueRGB;

    } // end of HorizontalFilter()

    private BufferedImage HorizontalFiltering(BufferedImage bufImage, int iOutW) {
        int dwInW = bufImage.getWidth();
        int dwInH = bufImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iOutW, dwInH,
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < iOutW; x++) {

            int startX;
            int start;
            int X = (int) (((double) x) * ((double) dwInW) / ((double) iOutW) + 0.5);
            int y = 0;

            startX = X - nHalfDots;
            if (startX < 0) {
                startX = 0;
                start = nHalfDots - X;
            } else {
                start = 0;
            }

            int stop;
            int stopX = X + nHalfDots;
            if (stopX > (dwInW - 1)) {
                stopX = dwInW - 1;
                stop = nHalfDots + (dwInW - 1 - X);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start,
                            stop, y, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start,
                            stop, y, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }
        }

        return pbOut;

    } // end of HorizontalFiltering()

    private int VerticalFilter(BufferedImage pbInImage, int startY, int stopY,
            int start, int stop, int x, double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startY, j = start; i <= stopY; i++, j++) {
            valueRGB = pbInImage.getRGB(x, i);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
                Clip((int) valueBlue));
        // System.out.println(valueRGB);
        return valueRGB;

    } // end of VerticalFilter()

    private BufferedImage VerticalFiltering(BufferedImage pbImage, int iOutH) {
        int iW = pbImage.getWidth();
        int iH = pbImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iW, iOutH,
                BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < iOutH; y++) {

            int startY;
            int start;
            int Y = (int) (((double) y) * ((double) iH) / ((double) iOutH) + 0.5);

            startY = Y - nHalfDots;
            if (startY < 0) {
                startY = 0;
                start = nHalfDots - Y;
            } else {
                start = 0;
            }

            int stop;
            int stopY = Y + nHalfDots;
            if (stopY > (int) (iH - 1)) {
                stopY = iH - 1;
                stop = nHalfDots + (iH - 1 - Y);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop,
                            x, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop,
                            x, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }

        }
        return pbOut;

    } // end of VerticalFiltering()

    int Clip(int x) {
        if (x < 0)
            return 0;
        if (x > 255)
            return 255;
        return x;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	public static BufferedImage doCut(BufferedImage source,int x,int y,int w,int h)
	{

		
		
		
		return source.getSubimage(x, y, w, h);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 几种常见的图片格式
	 */
	public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式
	public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组
	public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组
	public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
	public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形
	public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop

	/**
	 * 缩放图像（按比例缩放）
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param scale
	 *            缩放比例
	 * @param flag
	 *            缩放选择:true 放大; false 缩小;
	 */
	public final static void scale(String srcImageFile, String result,
			int scale, boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {// 放大
				width = width * scale;
				height = height * scale;
			} else {// 缩小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 缩放图像（按高度和宽度缩放）
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param height
	 *            缩放后的高度
	 * @param width
	 *            缩放后的宽度
	 * @param bb
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 */
	public final static void scale2(String srcImageFile, String result,
			int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
			File f = new File(srcImageFile);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue()
							/ bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(
						AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {// 补白
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图像切割(按指定起点坐标和宽高切割)
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param result
	 *            切片后的图像地址
	 * @param x
	 *            目标切片起点坐标X
	 * @param y
	 *            目标切片起点坐标Y
	 * @param width
	 *            目标切片宽度
	 * @param height
	 *            目标切片高度
	 */
	public final static void cut(String srcImageFile, String result, int x,
			int y, int width, int height) {
		try {
			// 读取源图像
			
			
			
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight,
						Image.SCALE_DEFAULT);
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				ImageFilter cropFilter = new CropImageFilter(x, y, width,
						height);
				Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
				g.dispose();
				
				
				
				// 输出为文件
				ImageIO.write(tag, "JPEG", new File(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图像切割（指定切片的行数和列数）
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param descDir
	 *            切片目标文件夹
	 * @param rows
	 *            目标切片行数。默认2，必须是范围 [1, 20] 之内
	 * @param cols
	 *            目标切片列数。默认2，必须是范围 [1, 20] 之内
	 */
	public final static void cut2(String srcImageFile, String descDir,
			int rows, int cols) {
		try {
			if (rows <= 0 || rows > 20)
				rows = 2; // 切片行数
			if (cols <= 0 || cols > 20)
				cols = 2; // 切片列数
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0) {
				Image img;
				ImageFilter cropFilter;
				Image image = bi.getScaledInstance(srcWidth, srcHeight,
						Image.SCALE_DEFAULT);
				int destWidth = srcWidth; // 每张切片的宽度
				int destHeight = srcHeight; // 每张切片的高度
				// 计算切片的宽度和高度
				if (srcWidth % cols == 0) {
					destWidth = srcWidth / cols;
				} else {
					destWidth = (int) Math.floor(srcWidth / cols) + 1;
				}
				if (srcHeight % rows == 0) {
					destHeight = srcHeight / rows;
				} else {
					destHeight = (int) Math.floor(srcWidth / rows) + 1;
				}
				// 循环建立切片
				// 改进的想法:是否可用多线程加快切割速度
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// 四个参数分别为图像起点坐标和宽高
						// 即: CropImageFilter(int x,int y,int width,int height)
						cropFilter = new CropImageFilter(j * destWidth, i
								* destHeight, destWidth, destHeight);
						img = Toolkit.getDefaultToolkit().createImage(
								new FilteredImageSource(image.getSource(),
										cropFilter));
						BufferedImage tag = new BufferedImage(destWidth,
								destHeight, BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null); // 绘制缩小后的图
						g.dispose();
						// 输出为文件
						ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i
								+ "_c" + j + ".jpg"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图像切割（指定切片的宽度和高度）
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param descDir
	 *            切片目标文件夹
	 * @param destWidth
	 *            目标切片宽度。默认200
	 * @param destHeight
	 *            目标切片高度。默认150
	 */
	public final static void cut3(String srcImageFile, String descDir,
			int destWidth, int destHeight) {
		try {
			if (destWidth <= 0)
				destWidth = 200; // 切片宽度
			if (destHeight <= 0)
				destHeight = 150; // 切片高度
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度
			if (srcWidth > destWidth && srcHeight > destHeight) {
				Image img;
				ImageFilter cropFilter;
				Image image = bi.getScaledInstance(srcWidth, srcHeight,
						Image.SCALE_DEFAULT);
				int cols = 0; // 切片横向数量
				int rows = 0; // 切片纵向数量
				// 计算切片的横向和纵向数量
				if (srcWidth % destWidth == 0) {
					cols = srcWidth / destWidth;
				} else {
					cols = (int) Math.floor(srcWidth / destWidth) + 1;
				}
				if (srcHeight % destHeight == 0) {
					rows = srcHeight / destHeight;
				} else {
					rows = (int) Math.floor(srcHeight / destHeight) + 1;
				}
				// 循环建立切片
				// 改进的想法:是否可用多线程加快切割速度
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// 四个参数分别为图像起点坐标和宽高
						// 即: CropImageFilter(int x,int y,int width,int height)
						cropFilter = new CropImageFilter(j * destWidth, i
								* destHeight, destWidth, destHeight);
						img = Toolkit.getDefaultToolkit().createImage(
								new FilteredImageSource(image.getSource(),
										cropFilter));
						BufferedImage tag = new BufferedImage(destWidth,
								destHeight, BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null); // 绘制缩小后的图
						g.dispose();
						// 输出为文件
						ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i
								+ "_c" + j + ".jpg"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图像类型转换：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param formatName
	 *            包含格式非正式名称的 String：如JPG、JPEG、GIF等
	 * @param destImageFile
	 *            目标图像地址
	 */
	public final static void convert(String srcImageFile, String formatName,
			String destImageFile) {
		try {
			File f = new File(srcImageFile);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, formatName, new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 彩色转为黑白
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 */
	public final static void gray(String srcImageFile, String destImageFile) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(destImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressText(String pressText, String srcImageFile,
			String destImageFile, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));// 输出到文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressText2(String pressText, String srcImageFile,
			String destImageFile, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param x
	 *            修正值。 默认在中间
	 * @param y
	 *            修正值。 默认在中间
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressImage(String pressImg, String srcImageFile,
			String destImageFile, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2,
					(height - height_biao) / 2, wideth_biao, height_biao, null);
			// 水印文件结束
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算text的长度（一个中文算两个字符）
	 * 
	 * @param text
	 * @return
	 */
	public final static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}
}
