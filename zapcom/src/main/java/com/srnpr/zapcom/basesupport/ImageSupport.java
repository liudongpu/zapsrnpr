package com.srnpr.zapcom.basesupport;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

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

	/**
	 * 裁剪图片
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void cute(int x, int y, int w, int h) {
		targetImage = sourceImage.getSubimage(x, y, w, h);
	}

	/**
	 * 获取格式化图片类型
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
