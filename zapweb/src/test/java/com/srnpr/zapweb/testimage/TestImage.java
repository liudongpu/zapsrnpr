package com.srnpr.zapweb.testimage;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.srnpr.zapcom.basehelper.ImageHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basesupport.ImageSupport;

public class TestImage extends TestHelper {

	
	public void testTime()
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String sDate = formatter.format(date);
		
		bLogTest(sDate);
		bLogTest(Integer.toHexString(Integer.valueOf(sDate)));
		
	}
	
	
	
	public void test() {
/*
		ImageHelper
				.cut("C:\\Users/Administrator/Pictures/bigc/1c950a7b02087bf41c206ea0f3d3572c10dfcfae.jpg",
						"C:\\Users/Administrator/Pictures/cut/1.jpg", 0, 0,
						1800, 1000);
		
		*/
		
		
		
		String sSourceFile="C:\\Users/Administrator/Pictures/bigc/1c950a7b02087bf41c206ea0f3d3572c10dfcfae.jpg";
		
		String sSave1="C:\\Users/Administrator/Pictures/cut/1.jpg";
		String sSave3="C:\\Users/Administrator/Pictures/cut/3.jpg";
		String sSave2="C:\\Users/Administrator/Pictures/cut/2.jpg";
		
		try {
			//BufferedImage bufferedImage=ImageIO.read(new File(sSourceFile));
			
			
			BufferedImage bufferedImage=ImageIO.read(new URL("http://p2.img.cctvpic.com/mall/images/20130830/83339/1377839869.jpg"));
			

			
			ImageHelper imageHelper=new ImageHelper();
			ImageIO.write(imageHelper.imageZoomOut(bufferedImage, 328, 328), "JPEG", new File(sSave1));
			
			
			
			ImageSupport iSupport=new ImageSupport(bufferedImage);
			
			
			//BufferedImage bSaveBufferedImage=bufferedImage.getSubimage(220, 0, 600, 1000);
			
			//iSupport.resize(3000, 300);
			
				iSupport.scaleSmall(328,328);
			
			ImageIO.write(iSupport.getTargetImage(), "JPEG", new File(sSave3));
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

	}
}
