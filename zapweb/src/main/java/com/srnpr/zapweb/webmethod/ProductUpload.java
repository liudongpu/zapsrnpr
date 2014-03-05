package com.srnpr.zapweb.webmethod;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MKvdList;
import com.srnpr.zapcom.basemodel.MKvdModel;
import com.srnpr.zapcom.basesupport.ImageSupport;
import com.srnpr.zapcom.basesupport.ImageTools;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebInput;
import com.srnpr.zapweb.webface.IWebNotice;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebUpload;

public class ProductUpload extends BaseClass implements IWebNotice {
	
	
	public static boolean IsExistImageMagic = false;
	
	static {
		
		try{
			IMOperation op = new IMOperation();
			ConvertCmd convert = new ConvertCmd();
			convert.run(op);
			IsExistImageMagic = true;
			System.out.println("Install ImageMagic Is Good");
		}
		catch(Exception ex){
			IsExistImageMagic = false;
			System.out.println("Not Install ImageMagic ");
		}
		
	}

	public MWebResult noticeEvent(String sEventName, IWebInput input) {
		MWebResult mResult = new MWebResult();

		boolean bFlagTrue = true;

		if (sEventName.equals("upload_process")) {
			MWebUpload mUpload = (MWebUpload) input;

			if(!IsExistImageMagic){
				mResult = oldImageTool(mUpload);
			}else{
				try {
					mResult = newImageTool(mUpload);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mResult.inErrorMessage(969905062);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mResult.inErrorMessage(969905062);
				}
			}

		}

		return mResult;
	}
	
	
	private MWebResult newImageTool(MWebUpload mUpload) throws IOException, InterruptedException{
		
		MWebResult mResult = new MWebResult();
		WebUpload webUpload = new WebUpload();
		ImageTools imageUtil = new ImageTools();
		
		
		int[] widthAndHeight = imageUtil.getWidthAndHeight(mUpload.getFile().getInputStream());
		
		
		if(widthAndHeight == null || widthAndHeight[0] == -1 || widthAndHeight[1] == -1){
			mResult.inErrorMessage(969905061);
			return mResult;
		}else{
			
		}
		
		
		if(widthAndHeight == null || widthAndHeight[0] <600 || widthAndHeight[1] <600){
			mResult.inErrorMessage(969905060, "600", "600");
			return mResult;
		}else{
			
		}
		String bgColor = "white";
		
		//600*600
		//350*350
		//220*220
		//160*160
		//100*100
		//50*50
		boolean bFlagTrue = false;
		if (mResult.upFlagTrue()) {

			String sTarget = mUpload.getTarget();
			String sDate = FormatHelper.upDateHex();
			String sFileName =  WebHelper.upUuid();

			MWebResult mSourceResult = webUpload.remoteUploadCustom(mUpload
					.getFile().getName(), mUpload.getFile().get(), sTarget,
					sDate, "ps", sFileName);
			if (mSourceResult.upFlagTrue()) {
				//600*600
				int wOrH = 600;
				byte[] imageByte = imageUtil.ImageMagicChange(mUpload.getFile().getInputStream(), wOrH, wOrH, bgColor, widthAndHeight[0], widthAndHeight[1]);
				
				MWebResult mBigFile = webUpload.remoteUploadCustom(mUpload
						.getFile().getName(), imageByte,
						sTarget, sDate, "p0", sFileName);
				if (mBigFile.upFlagTrue()) {

					mResult.setResultObject(mBigFile.getResultObject());
					
					wOrH = 350;
					imageByte = imageUtil.ImageMagicChange(mUpload.getFile().getInputStream(), wOrH, wOrH, bgColor, widthAndHeight[0], widthAndHeight[1]);
					

					if(webUpload.remoteUploadCustom(mUpload
							.getFile().getName(),imageByte,
							sTarget, sDate, "p1", sFileName).upFlagTrue()){
						
						wOrH = 220;
						imageByte = imageUtil.ImageMagicChange(mUpload.getFile().getInputStream(), wOrH, wOrH, bgColor, widthAndHeight[0], widthAndHeight[1]);
						
						if(webUpload.remoteUploadCustom(mUpload
								.getFile().getName(),imageByte,
								sTarget, sDate, "p2", sFileName).upFlagTrue()){
							
							wOrH = 160;
							imageByte = imageUtil.ImageMagicChange(mUpload.getFile().getInputStream(), wOrH, wOrH, bgColor, widthAndHeight[0], widthAndHeight[1]);
							
							if(webUpload.remoteUploadCustom(mUpload
									.getFile().getName(),imageByte,
									sTarget, sDate, "p3", sFileName).upFlagTrue()){
								
								wOrH = 100;
								imageByte = imageUtil.ImageMagicChange(mUpload.getFile().getInputStream(), wOrH, wOrH, bgColor, widthAndHeight[0], widthAndHeight[1]);
								
								if(webUpload.remoteUploadCustom(mUpload
										.getFile().getName(),imageByte,
										sTarget, sDate, "p4", sFileName).upFlagTrue()){
									
									wOrH = 50;
									imageByte = imageUtil.ImageMagicChange(mUpload.getFile().getInputStream(), wOrH, wOrH, bgColor, widthAndHeight[0], widthAndHeight[1]);
									
									if(webUpload.remoteUploadCustom(mUpload
											.getFile().getName(),imageByte,
											sTarget, sDate, "p5", sFileName).upFlagTrue()){
										bFlagTrue = true;
									}
								}
							}
						}
					}
				}
			}
		}
		
		if (!bFlagTrue) {
			mResult.inErrorMessage(969905006);
		}

		return mResult;
	}
	
	
	private MWebResult oldImageTool(MWebUpload mUpload){
		
		MWebResult mResult = new MWebResult();

		boolean bFlagTrue = true;
		
		WebUpload webUpload = new WebUpload();

		ImageSupport imageSupport = new ImageSupport(mUpload.getFile()
				.get());
		
		if(imageSupport.getSourceImage() == null){
			mResult.inErrorMessage(969905061);
		}
		

		if (mResult.upFlagTrue()) {
			if (imageSupport.upSourceHeight() < 600
					|| imageSupport.upSourceWidth() < 600) {
				mResult.inErrorMessage(969905060, "600", "600");
			}
		}

		if (mResult.upFlagTrue()) {

			

			String sTarget = mUpload.getTarget();
			String sDate = FormatHelper.upDateHex();

			String sFileName =  WebHelper.upUuid();

			MWebResult mSourceResult = webUpload.remoteUploadCustom(mUpload
					.getFile().getName(), mUpload.getFile().get(), sTarget,
					sDate, "ps", sFileName);

			// mResult.setResultList(new ArrayList<Object>());

			if (mSourceResult.upFlagTrue()) {

				imageSupport.scaleWhite(600, 600);

				MWebResult mBigFile = webUpload.remoteUploadCustom(mUpload
						.getFile().getName(), imageSupport.upTargetByte(),
						sTarget, sDate, "p0", sFileName);
				if (mBigFile.upFlagTrue()) {

					mResult.setResultObject(mBigFile.getResultObject());
					
					//此地将留白后的图片重新设置为源
					imageSupport.setSourceImage(imageSupport.getTargetImage());
					

					imageSupport.scaleWhite(350, 350);

					if (webUpload.remoteUploadCustom(
							mUpload.getFile().getName(),
							imageSupport.upTargetByte(), sTarget, sDate,
							"p1", sFileName).upFlagTrue()) {
						imageSupport.setSourceImage(imageSupport.getTargetImage());
						imageSupport.scaleWhite(220, 220);
						if (webUpload.remoteUploadCustom(
								mUpload.getFile().getName(),
								imageSupport.upTargetByte(), sTarget,
								sDate, "p2", sFileName).upFlagTrue()) {
							imageSupport.setSourceImage(imageSupport.getTargetImage());imageSupport.scaleWhite(160, 160);
							if (webUpload.remoteUploadCustom(
									mUpload.getFile().getName(),
									imageSupport.upTargetByte(), sTarget,
									sDate, "p3", sFileName).upFlagTrue()) {
								imageSupport.setSourceImage(imageSupport.getTargetImage());imageSupport.scaleWhite(100, 100);
								if (webUpload.remoteUploadCustom(
										mUpload.getFile().getName(),
										imageSupport.upTargetByte(),
										sTarget, sDate, "p4", sFileName)
										.upFlagTrue()) {
									imageSupport.setSourceImage(imageSupport.getTargetImage());imageSupport.scaleWhite(50, 50);
									if (webUpload
											.remoteUploadCustom(
													mUpload.getFile()
															.getName(),
													imageSupport
															.upTargetByte(),
													sTarget, sDate, "p5",
													sFileName).upFlagTrue()) {
										bFlagTrue = true;

									}
								}
							}

						}

					}

				}

			}
		}

		if (!bFlagTrue) {

			mResult.inErrorMessage(969905006);

		}

		return mResult;
	}
}
