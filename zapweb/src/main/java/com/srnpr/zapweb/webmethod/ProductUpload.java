package com.srnpr.zapweb.webmethod;

import java.util.ArrayList;

import com.srnpr.zapcom.basesupport.ImageSupport;
import com.srnpr.zapweb.webface.IWebInput;
import com.srnpr.zapweb.webface.IWebNotice;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebUpload;

public class ProductUpload implements IWebNotice {

	public MWebResult noticeEvent(String sEventName, IWebInput input) {
		MWebResult mResult = new MWebResult();

		boolean bFlagTrue = false;

		if (sEventName.equals("upload_success")) {
			MWebUpload mUpload = (MWebUpload) input;

			WebUpload webUpload = new WebUpload();

			ImageSupport imageSupport = new ImageSupport(mUpload.getFile()
					.get());

			if (mResult.upFlagTrue()) {
				MWebResult mSourceResult = webUpload.remoteUpload(
						mUpload.getTarget(), mUpload.getFile().getName(),
						mUpload.getFile().get());

				mResult.setResultList(new ArrayList<Object>());

				if (mSourceResult.upFlagTrue()) {

					mResult.getResultList()
							.add(mSourceResult.getResultObject());

					imageSupport.cute(0, 0, 200, 200);

					MWebResult mBigFile = webUpload.remoteUpload(
							mUpload.getTarget(), mUpload.getFile().getName(),
							imageSupport.upTargetByte());
					
					mResult.setResultObject(mBigFile.getResultObject());

					if (mBigFile.upFlagTrue()) {

					}

				}
			}

		}

		return mResult;
	}

}
