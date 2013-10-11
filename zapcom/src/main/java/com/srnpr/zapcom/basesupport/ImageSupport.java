package com.srnpr.zapcom.basesupport;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.ImageHelper;

public class ImageSupport {

	/**
	 * 源图片
	 */
	private BufferedImage sourceImage = null;

	/**
	 * 图片类型
	 */
	private String imageType = null;

	/**
	 * 目标图片
	 */
	private BufferedImage targetImage = null;

	/**
	 * 初始化
	 * 
	 * @param b
	 */
	public ImageSupport(byte[] b) {
		ByteArrayInputStream in = new ByteArrayInputStream(b);

		try {
			sourceImage = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageSupport(BufferedImage bSource) {
		sourceImage = bSource;
	}

	public void cute(int w, int h) {
		cute(0, 0, w, h);
	}

	/**
	 * 裁剪图片
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void cute(int x, int y, int w, int h) {

		targetImage = sourceImage.getSubimage(Math.min(x, upSourceWidth()),
				Math.min(y, upSourceHeight()),
				Math.min(w, upSourceWidth() - x),
				Math.min(h, upSourceHeight() - y));
	}

	/**
	 * 缩放图片
	 * 
	 * @param dScale
	 *            大于1则变大 小于1则变小 等比例缩放
	 */
	public void scale(double dScale) {

		scale(dScale, Image.SCALE_SMOOTH);

	}

	/**
	 * @param dScale
	 * @param iScanleDeep
	 *            缩放级别
	 */
	public void scale(double dScale, int iScanleDeep) {

		int width = (int) (upSourceWidth() * dScale);
		int height = (int) (upSourceHeight() * dScale);

		Image image = sourceImage.getScaledInstance(width, height, iScanleDeep);
		targetImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = targetImage.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();

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

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
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
	public void pressText(String pressText, String fontName, int fontStyle,
			Color color, int fontSize, int x, int y, float alpha) {
		try {

			Graphics2D g = sourceImage.createGraphics();

			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText,
					(upSourceWidth() - (getLength(pressText) * fontSize)) / 2
							+ x, (upSourceHeight() - fontSize) / 2 + y);
			g.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调整大小 按原图片等比例调整
	 * 
	 * @param width
	 * @param height
	 */
	public void resize(int width, int height) {
		resize(width, height, false);
	}

	/**
	 * 调整图片大小
	 * 
	 * @param width
	 * @param height
	 * @param bb
	 */
	public void resize(int width, int height, boolean bb) {

		try {
			double ratio = 0.0; // 缩放比例

			BufferedImage bi = sourceImage;
			Image itemp = bi.getScaledInstance(width, height,
					BufferedImage.SCALE_SMOOTH);
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
				targetImage = op.filter(bi, null);
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
				targetImage = image;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int upSourceWidth() {
		return sourceImage.getWidth();
	}

	public int upSourceHeight() {
		return sourceImage.getHeight();
	}

	/**
	 * 获取格式化图片类型
	 * 
	 * @return
	 */
	private String upImageType() {
		if (StringUtils.isBlank(imageType)) {
			imageType = "JPEG";
		}

		return imageType;
	}

	/**
	 * 获取目标图片的字节流
	 * 
	 * @return
	 */
	public byte[] upTargetByte() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(targetImage, upImageType(), out);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return out.toByteArray();
	}

	public BufferedImage getTargetImage() {
		return targetImage;
	}

	public void setTargetImage(BufferedImage targetImage) {
		this.targetImage = targetImage;
	}

	public BufferedImage getSourceImage() {
		return sourceImage;
	}

	public void setSourceImage(BufferedImage sourceImage) {
		this.sourceImage = sourceImage;
	}

}
