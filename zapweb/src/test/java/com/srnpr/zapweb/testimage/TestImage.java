package com.srnpr.zapweb.testimage;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.srnpr.zapcom.basehelper.ImageHelper;
import com.srnpr.zapcom.basehelper.TestHelper;

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
		try {
			BufferedImage bufferedImage=ImageIO.read(new File(sSourceFile));
			
			
			
			
			BufferedImage bSaveBufferedImage=bufferedImage.getSubimage(220, 0, 600, 1000);
			
			
			
			ImageIO.write(bSaveBufferedImage, "JPEG", new File(sSaveFile));
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

	}
}
