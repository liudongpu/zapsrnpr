package com.srnpr.zapweb.webmethod;

import java.util.ArrayList;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MKvdList;
import com.srnpr.zapcom.basemodel.MKvdModel;
import com.srnpr.zapcom.basesupport.ImageSupport;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebInput;
import com.srnpr.zapweb.webface.IWebNotice;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebUpload;

public class ProductUpload extends BaseClass implements IWebNotice {

	public MWebResult noticeEvent(String sEventName, IWebInput input) {
		MWebResult mResult = new MWebResult();

		boolean bFlagTrue = true;

		if (sEventName.equals("upload_process")) {
			MWebUpload mUpload = (MWebUpload) input;

			WebUpload webUpload = new WebUpload();

			ImageSupport imageSupport = new ImageSupport(mUpload.getFile()
					.get());

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

		}

		return mResult;
	}

}
