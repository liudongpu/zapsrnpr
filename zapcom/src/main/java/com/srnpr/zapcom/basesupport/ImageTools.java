package com.srnpr.zapcom.basesupport;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.process.Pipe;
import org.im4java.process.ProcessExecutor;

public class ImageTools {
	
	
	/**
	 * * 获得图片文件大小[小技巧来获得图片大小] * * @param filePath * 文件路径 *
	 * 
	 * @return 文件大小
	 */

	public int getSize(String imagePath) {
	    int size = 0;
	    FileInputStream inputStream = null;
	    try {
	        inputStream = new FileInputStream(imagePath);
	        size = inputStream.available();
	        inputStream.close();
	        inputStream = null;
	    } catch (FileNotFoundException e) {
	        size = 0;
	        System.out.println("文件未找到!");
	    } catch (IOException e) {
	        size = 0;
	        System.out.println("读取文件大小错误!");
	    } finally {
	        // 可能异常为关闭输入流,所以需要关闭输入流
	        if (null != inputStream) {
	            try {
	                inputStream.close();
	            } catch (IOException e) {
	                System.out.println("关闭文件读入流异常");
	            }
	            inputStream = null;

	        }
	    }
	    return size;
	}

	/**
	 * 获得图片的宽度和高度 ，[0]:宽度，[1]高度 
	 * 
	 * @param stream
	 *            文件流
	 * @return 图片宽度
	 */
	public int[] getWidthAndHeight(InputStream stream) {
	    int[] line = new int[]{0,0};
	    try {
	        IMOperation op = new IMOperation();
	       
	        //op.ping().format("%[EXIF:ISOSpeedRatings]\n");
	        op.format("%w#%h"); // 设置获取宽度参数
	    	Pipe pipeIn = new Pipe(stream, null);
	        op.addImage("-");
	        IdentifyCmd identifyCmd = new IdentifyCmd(false);
	        identifyCmd.setInputProvider(pipeIn);
	        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
	        identifyCmd.setOutputConsumer(output);
	        identifyCmd.run(op);
	        ArrayList<String> cmdOutput = output.getOutput();
	        assert cmdOutput.size() == 1;
	        
	        String[] ary = cmdOutput.get(0).split("#");
	        if(ary == null || ary.length<2){
	        	System.out.println("图片的长度读取失败!");
	        	return line;
	        }else{
	        	line[0] = Integer.parseInt(ary[0]);
		        line[1] = Integer.parseInt(ary[1]);
	        }
	        	
	        
	    } catch (Exception e) {
	        System.out.println("运行指令出错!");
	        e.printStackTrace();
	        line = new int[]{-1,-1};
	    }
	    return line;
	}

	/**
	 * 获得图片的高度
	 * 
	 * @param stream
	 *            文件流
	 * @return 图片高度
	 */
	public int getHeight(InputStream stream) {
	    int line = 0;
	    try {
	    	 IMOperation op = new IMOperation();
		       
	        //op.ping().format("%[EXIF:ISOSpeedRatings]\n");
	    	 op.format("%h");// 设置获取宽度参数
	    	Pipe pipeIn = new Pipe(stream, null);
	        op.addImage("-");
	        IdentifyCmd identifyCmd = new IdentifyCmd(true);
	        identifyCmd.setInputProvider(pipeIn);
	        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
	        identifyCmd.setOutputConsumer(output);
	        identifyCmd.run(op);
	        ArrayList<String> cmdOutput = output.getOutput();
	        assert cmdOutput.size() == 1;
	        line = Integer.parseInt(cmdOutput.get(0));
	    } catch (Exception e) {
	        line = 0;
	        System.out.println("运行指令出错!"+e.toString());
	    }
	    return line;
	}
	
	
	/**
	 * 获得图片的高度11
	 * 
	 * @param stream
	 *            文件流
	 * @return 图片高度
	 */
	public int getHeightNew(String imagePath) {
	    int line = 0;
	    try {
	    	 IMOperation op = new IMOperation();
		       
	        //op.ping().format("%[EXIF:ISOSpeedRatings]\n");
	    	 op.format("%h");// 设置获取宽度参数
	    	
	        op.addImage(imagePath);
	        IdentifyCmd identifyCmd = new IdentifyCmd(false);
	       
	        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
	        identifyCmd.setOutputConsumer(output);
	        identifyCmd.run(op);
	        ArrayList<String> cmdOutput = output.getOutput();
	        assert cmdOutput.size() == 1;
	        line = Integer.parseInt(cmdOutput.get(0));
	    } catch (Exception e) {
	        line = 0;
	        System.out.println("运行指令出错!"+e.toString());
	    }
	    return line;
	}

	/**
	 * 图片信息
	 * 
	 * @param imagePath
	 * @return
	 */
	public static String getImageInfo(String imagePath) {
	    String line = null;
	    try {
	        IMOperation op = new IMOperation();
	        op.format("width:%w,height:%h,path:%d%f,size:%b%[EXIF:DateTimeOriginal]");
	        op.addImage(1);
	        IdentifyCmd identifyCmd = new IdentifyCmd(false);
	        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
	        identifyCmd.setOutputConsumer(output);
	        identifyCmd.run(op, imagePath);
	        ArrayList<String> cmdOutput = output.getOutput();
	        assert cmdOutput.size() == 1;
	        line = cmdOutput.get(0);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return line;
	}

	/**
	 * 裁剪图片
	 * 
	 * @param imagePath
	 *            源图片路径
	 * @param newPath
	 *            处理后图片路径
	 * @param x
	 *            起始X坐标
	 * @param y
	 *            起始Y坐标
	 * @param width
	 *            裁剪宽度
	 * @param height
	 *            裁剪高度
	 * @return 返回true说明裁剪成功,否则失败
	 */
	public boolean cutImage(String imagePath, String newPath, int x, int y,
	        int width, int height) {
	    boolean flag = false;
	    try {
	        IMOperation op = new IMOperation();
	        op.addImage(imagePath);
	        /** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
	        op.crop(width, height, x, y);
	        op.addImage(newPath);
	        ConvertCmd convert = new ConvertCmd(true);
	        convert.run(op);
	        flag = true;
	    } catch (IOException e) {
	        System.out.println("文件读取错误!");
	        flag = false;
	    } catch (InterruptedException e) {
	        flag = false;
	    } catch (IM4JavaException e) {
	        flag = false;
	    } finally {

	    }
	    return flag;
	}

	/**
	 * 根据尺寸缩放图片[等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放]
	 * 
	 * @param imagePath
	 *            源图片路径
	 * @param newPath
	 *            处理后图片路径
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @return 返回true说明缩放成功,否则失败
	 */
	public boolean zoomImage(String imagePath, String newPath, Integer width,
	        Integer height) {

	    boolean flag = false;
	    try {
	        IMOperation op = new IMOperation();
	        op.addImage(imagePath);
	        if (width == null) {// 根据高度缩放图片
	            op.resize(null, height);
	        } else if (height == null) {// 根据宽度缩放图片
	            op.resize(width);
	        } else {
	            op.resize(width, height);
	        }
	        op.addImage(newPath);
	        ConvertCmd convert = new ConvertCmd(true);
	        convert.run(op);
	        flag = true;
	    } catch (IOException e) {
	        System.out.println("文件读取错误!");
	        flag = false;
	    } catch (InterruptedException e) {
	        flag = false;
	    } catch (IM4JavaException e) {
	        flag = false;
	    } finally {

	    }
	    return flag;
	}

	/**
	 * 图片旋转
	 * 
	 * @param imagePath
	 *            源图片路径
	 * @param newPath
	 *            处理后图片路径
	 * @param degree
	 *            旋转角度
	 */
	public boolean rotate(String imagePath, String newPath, double degree) {
	    boolean flag = false;
	    try {
	        // 1.将角度转换到0-360度之间
	        degree = degree % 360;
	        if (degree <= 0) {
	            degree = 360 + degree;
	        }
	        IMOperation op = new IMOperation();
	        op.addImage(imagePath);
	        op.rotate(degree);
	        op.addImage(newPath);
	        ConvertCmd cmd = new ConvertCmd(true);
	        cmd.run(op);
	        flag = true;
	    } catch (Exception e) {
	        flag = false;
	        System.out.println("图片旋转失败!");
	    }
	    return flag;
	}

	public static void main(String[] args) throws Exception {
		ImageTools imageUtil = new ImageTools();

		InputStream is = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			is = new FileInputStream("d://app//123.jpg");
			int[] ary = imageUtil.getWidthAndHeight(is);
			
			System.out.println("width:"+ary[0]);
			System.out.println("height:"+ary[1]);
			
		}catch(Exception ex){
			
		}finally{
			if(is!=null)
				is.close();
		}
		
	   // System.out.println("原图片大小:" + imageUtil.getSize("d://app//121.jpg") + "Bit");
	   
	  /*  if (imageUtil.zoomImage("d://app//121.jpg", "d://app//test1.jpg", 500, null)) {
	        if (imageUtil.rotate("d://app//121.jpg", "d://app//test2.jpg", 15)) {
	            if (imageUtil.cutImage("d://app//121.jpg", "d://app//test3.jpg", 32,
	                    105, 200, 200)) {
	                System.out.println("编辑成功");
	            } else {
	                System.out.println("编辑失败03");
	            }
	        } else {
	            System.out.println("编辑失败02");
	        }
	    } else {
	        System.out.println("编辑失败01");
	    }*/

	}

	/**
	 * ImageMagick的路径
	 */
	public static String imageMagickPath = null;
	static {
		
	}
	
	
	public  byte[]  ImageMagicChange(InputStream Stream,
			final int rectw, final int recth, final String bgColor,int oldrectw,int oldrecth) throws IOException, InterruptedException{
		
		IMOperation op = new IMOperation();
		op.addImage("-");
		// 设置背景颜色
		op.background(bgColor);

		String raw = "";
		// 按像素
		// raw = 100+"x"+47+"!";
		op.addRawArgs("-quality", "90");
		// 缩放后不足的地方填充背景颜色
		
		int resizeWidth = 0;
		int resizeHeight = 0;
		
		resizeWidth = oldrectw>=rectw?rectw:oldrectw;
		resizeHeight = oldrecth>=recth?recth:oldrecth;
		
		op.resize(resizeWidth, resizeHeight).gravity("center").extent(rectw, recth);
		op.addImage("-");
		
		Pipe pipeIn = new Pipe(Stream, null);
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		Pipe pipeOut = new Pipe(null, fos);

		// set up command
		ConvertCmd convert = new ConvertCmd();
		
		convert.setInputProvider(pipeIn);
		convert.setOutputConsumer(pipeOut);
		try {
			convert.run(op);
		} catch (IM4JavaException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
		//Stream.close();
		return fos.toByteArray();
	}
	
	
	public void changeToStanderd(String infileName,int widthorheight,String ToFile){
		
		Info info;
		String bgColor = "white"; 
		try {
			info = new Info(infileName);
			int width = info.getImageWidth();
			int height = info.getImageHeight();
			
			InputStream is = null;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			try {
				is = new FileInputStream(infileName);
				
				try {
					
					byte[] ret = ImageMagicChange(is, widthorheight, widthorheight, bgColor, width, height);
					
					
					try { 
						// 创建文件输出流对象
						FileOutputStream os = new FileOutputStream(ToFile);

						os.write(ret);

						// 关闭输出流 
						os.close(); //System.out.println("已保存到" +dest);
					} catch (IOException ioe) {
						System.out.println(ioe);
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
					}
				}
			}
			
			
			
		} catch (InfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	

}
