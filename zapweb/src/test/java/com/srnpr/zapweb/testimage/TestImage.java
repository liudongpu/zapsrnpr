package com.srnpr.zapweb.testimage;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.srnpr.zapcom.basehelper.ImageHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basesupport.ImageSupport;

public class TestImage extends TestHelper {

	@Test
	public void test() {
/*
		ImageHelper
				.cut("C:\\Users/Administrator/Pictures/bigc/1c950a7b02087bf41c206ea0f3d3572c10dfcfae.jpg",
						"C:\\Users/Administrator/Pictures/cut/1.jpg", 0, 0,
						1800, 1000);
		
		*/
		
		String sSourceFile="C:\\Users/Administrator/Pictures/bigc/1c950a7b02087bf41c206ea0f3d3572c10dfcfae.jpg";
		
		String sSaveFile="C:\\Users/Administrator/Pictures/cut/1.jpg";
		String sSave3="C:\\Users/Administrator/Pictures/cut/3.jpg";
		String sSave2="C:\\Users/Administrator/Pictures/cut/2.jpg";
		
		try {
			//BufferedImage bufferedImage=ImageIO.read(new File(sSourceFile));
			
			
			BufferedImage bufferedImage=ImageIO.read(new URL("http://www.ohozaa.com/member/user_blog/s/sonnongnoom/files/1201874429.bmp"));
			
			
			ImageSupport iSupport=new ImageSupport(bufferedImage);
			
			
			//BufferedImage bSaveBufferedImage=bufferedImage.getSubimage(220, 0, 600, 1000);
			
			//iSupport.resize(3000, 300);
			
				iSupport.scale(0.6);
			
			ImageIO.write(iSupport.getTargetImage(), "JPEG", new File(sSave3));
			
			iSupport.scale(0.6,8);
			
			ImageIO.write(iSupport.getTargetImage(), "JPEG", new File(sSaveFile));
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

	}
}
