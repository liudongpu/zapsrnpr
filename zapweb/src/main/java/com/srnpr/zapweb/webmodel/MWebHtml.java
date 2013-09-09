package com.srnpr.zapweb.webmodel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;

/**
 * 通用html操作类
 * 
 * @author srnpr
 * 
 */
public class MWebHtml {

	public MWebHtml(String sTarget) {

		// 特殊判断 如果是特殊定义则特殊操作
		if (StringUtils.indexOf("hidden,text,button,script,file,css,js,",
				sTarget + ",") > -1) {

			if (sTarget.equals("script")) {
				attribute.put("type", "text/javascript");

			} else if (sTarget.equals("css")) {
				attribute.put("type", "text/css");
				attribute.put("rel", "stylesheet");
				sTarget = "link";
			} else if (sTarget.equals("js")) {
				attribute.put("type", "text/javascript");
				sTarget = "script";
			}

			else {
				attribute.put("type", sTarget);
				sTarget = "input";
			}
		}
		target = sTarget;
	}

	/**
	 * 添加属性 如果参数为1个则自动输出到子内容下
	 * 
	 * @param sAttribute
	 * @return
	 */
	public MWebHtml inAttributes(String... sAttribute) {
		if (sAttribute.length == 1) {
			html = sAttribute[0];
		} else {
			attribute.inAllValues(sAttribute);
		}

		return this;

	}

	public MWebHtml addChild(String sTarget, String... sAttribute) {
		if (child == null) {
			child = new ArrayList<MWebHtml>();
		}

		child.add(new MWebHtml(sTarget).inAttributes(sAttribute));

		return child.get(child.size() - 1);
	}

	public String upString() {
		StringBuilder sbBuilder = new StringBuilder();

		sbBuilder.append("<" + target);

		if (attribute != null && attribute.size() > 0) {
			for (String sKey : attribute.keySet()) {
				if (StringUtils.isNotEmpty(sKey)) {
					sbBuilder.append(" " + sKey + "=\"" + attribute.get(sKey)
							+ "\"");
				}
			}
		}
		sbBuilder.append(">");

		if (child != null && child.size() > 0) {
		
			 for(int i=0,j=child.size();i<j;i++)
			 {
				 sbBuilder.append(child.get(i).upString());
			 }
			
			
		}

		sbBuilder.append(html);

		if ("input".indexOf(target) > -1) {
			sbBuilder.deleteCharAt(sbBuilder.length() - 1);
			sbBuilder.append("/>");
		} else {
			sbBuilder.append("</" + target + ">");
		}

		return sbBuilder.toString();

	}

	private String target = "";
	private List<MWebHtml> child = null;

	private String html = "";

	private MDataMap attribute = new MDataMap();

	public List<MWebHtml> getChild() {
		return child;
	}

	public void setChild(List<MWebHtml> child) {
		this.child = child;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public MDataMap getAttribute() {
		return attribute;
	}

	public void setAttribute(MDataMap attribute) {
		this.attribute = attribute;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
