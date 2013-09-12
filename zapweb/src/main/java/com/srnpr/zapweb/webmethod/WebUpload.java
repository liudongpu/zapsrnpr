package com.srnpr.zapweb.webmethod;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.springframework.util.FileCopyUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;

public class WebUpload extends BaseClass implements IBaseInstance {

	private static final WebUpload webUpload = new WebUpload();

	public static WebUpload getInstance() {
		return webUpload;
	}

	/**
	 * @param sTarget
	 * @param sFileName
	 * @param b
	 * @return
	 */
	public MWebResult remoteUpload(String sTarget, String sFileName, byte[] b) {

		MWebResult mResult = new MWebResult();

		try {

			HttpClient httpclient = new DefaultHttpClient();
			// httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
			// HttpVersion.HTTP_1_1);

			HttpPost httppost = new HttpPost(bConfig("zapweb.upload_remote")
					+ "?" + WebConst.CONST_WEB_FIELD_SET + "target=" + sTarget);

			MultipartEntityBuilder mb = MultipartEntityBuilder.create();

			mb.addBinaryBody("file", b, ContentType.MULTIPART_FORM_DATA,
					sFileName);

			httppost.setEntity(mb.build());

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				String sReturnString = EntityUtils.toString(resEntity);

				mResult = new JsonHelper<MWebResult>().StringToObj(
						sReturnString, mResult);

			}
			if (resEntity != null) {

				EntityUtils.consume(resEntity);

			}

			httpclient.getConnectionManager().shutdown();

		} catch (Exception e) {
			e.printStackTrace();

			mResult.inErrorMessage(969905005);

		}

		return mResult;

	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param sTarget
	 *            该参数如果为save则实际执行存储 否则远程异步调用
	 * @return
	 */
	public String uploadFile(HttpServletRequest request, String sTarget) {

		String sReturnString = "";

		MWebResult mResult = new MWebResult();

		// 开始执行上传逻辑
		{

			String sContentType = request.getContentType();
			if (StringUtils.contains(sContentType, "multipart/form-data")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}// 得到所有的文件
				Iterator<FileItem> i = items.iterator();
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();

					String fileName = fi.getName();

					if (fileName != null) {

						// 如果是实际保存
						if (sTarget
								.equals(WebConst.CONST_STATIC_WEB_UPLOAD_SAVE)) {

							// 初始化上传文件保存路径
							if (StringUtils
									.isEmpty(WebConst.Static_Web_Upload_Dir)) {
								String sDir = bConfig("zapweb.upload_path");
								sDir = request.getSession().getServletContext()
										.getRealPath(sDir)
										+ "/";
								WebConst.Static_Web_Upload_Dir = sDir;
								bLogInfo(969912001, sDir);

							}

							String sDirPath = WebConst.Static_Web_Upload_Dir;

							String sSubPathString = sTarget;
							if (StringUtils.isNotEmpty(request
									.getParameter(WebConst.CONST_WEB_FIELD_SET
											+ "target"))) {
								sSubPathString = request
										.getParameter(
												WebConst.CONST_WEB_FIELD_SET
														+ "target").toString();
							}

							mResult = uploadFile(sDirPath, sSubPathString,
									fileName, fi.get());
						} else {

							mResult = remoteUpload(sTarget, fileName, fi.get());

						}

					}
				}
			}
		}

		if (sTarget.equals("editor")) {
			if (mResult.upFlagTrue()) {

				String sEditorFuncNum = request.getParameter("CKEditorFuncNum");
				sReturnString = FormatHelper.formatString(
						bConfig("zapweb.editor_upload"), sEditorFuncNum,
						mResult.getResultObject().toString(),
						mResult.getResultMessage());

			} else {
				sReturnString = mResult.getResultMessage();
			}
		} else if (sTarget.equals("upload")) {

			MWebHtml mDivHtml = new MWebHtml("div");

			mDivHtml.addChild("css", "href", bConfig("zapweb.resources_path")
					+ "lib/bootstrap/css/bootstrap.min.css");
			mDivHtml.addChild("css", "href", bConfig("zapweb.resources_path")
					+ "zapadmin/css/zab_base.css");
			mDivHtml.addChild("css", "href", bConfig("zapweb.resources_path")
					+ "zapweb/css/w.css");
			mDivHtml.addChild("js", "src", bConfig("zapweb.resources_path")
					+ "zapjs/zapjs.zw.js");
			mDivHtml.addChild("js", "src", bConfig("zapweb.resources_path")
					+ "lib/jquery/jquery-last.min.js");

			mDivHtml.addChild("js", "src", bConfig("zapweb.resources_path")
					+ "lib/jquery/jquery-plugins-zap.min.js");

			MWebHtml mForm = mDivHtml.addChild("form");
			mForm.inAttributes("enctype", "multipart/form-data", "method",
					"post");

			MWebHtml mSpanHtml = mForm.addChild("span", "class",
					"btn btn-info fileinput-button");

			mSpanHtml.addChild("i", "class", "glyphicon glyphicon-plus");

			MWebHtml mTextHtml = mSpanHtml.addChild("span");
			mTextHtml.setHtml(bInfo(969901003));

			MWebHtml mFileHtml = mSpanHtml.addChild("file", "id", "file",
					"name", "file", "onchange", "zapjs.zw.upload_upload(this)");

			mForm.addChild("input", "type", "submit", "id", "formsubmit",
					"value", "", "class", "w_none");

			if (mResult != null) {

				MWebHtml mScriptHtml = mFileHtml.addChild("script");

				mScriptHtml.setHtml("zapjs.zw.upload_result("
						+ mResult.upJson() + ");");
			}

			sReturnString = mDivHtml.upString();

		} else {
			sReturnString = mResult.upJson();
		}

		return sReturnString;

	}

	/**
	 * 保存文件
	 * 
	 * @param sDirPath
	 * @param sFilePath
	 * @param sFileName
	 * @param bFile
	 * @return
	 */
	private MWebResult uploadFile(String sDirPath, String sFilePath,
			String sFileName, byte[] bFile) {
		MWebResult mResult = new MWebResult();
		try {

			String sFix = "";
			// 校验文件名称
			if (mResult.upFlagTrue()) {

				String sAllowName = bConfig("zapweb.allow_file");

				boolean bFlag = false;
				if (sFileName.length() > 1) {
					sFix = StringUtils.substringAfter(sFileName, ".")
							.toLowerCase();
					if (!StringUtils.isEmpty(sFix) && sFix.length() > 0) {
						if (StringUtils.contains(sAllowName, "." + sFix + ";")) {
							bFlag = true;
						}
					}
				}

				if (!bFlag) {
					mResult.inErrorMessage(969901002, sFileName);
				}

			}

			if (mResult.upFlagTrue()) {

				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String sDate = formatter.format(date);

				sFilePath = sFilePath + "/" + sDate + "/";

				FileUtils.forceMkdir(new File(sDirPath + sFilePath));

				String sNewFileNameString = WebHelper.upUuid() + "." + sFix;

				FileCopyUtils.copy(bFile, new File(sDirPath + sFilePath
						+ sNewFileNameString));

				// fileUrl = BConfig("zweb.upload_url") + filePath + fileName;

				// mResult.info(969909002);

				mResult.setResultObject(bConfig("zapweb.upload_url")
						+ sFilePath + sNewFileNameString);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mResult;
	}

}
