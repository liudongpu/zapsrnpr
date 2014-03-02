package com.srnpr.zapweb.webclass;

import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/*
 * freemarker-config.properties
 * template_exception_handler=com.srnpr.zapweb.webclass.FreemarkerExceptionHandler
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {

	@Override
	public void handleTemplateException(TemplateException te, Environment env,
			Writer out) throws TemplateException {
		
	
	}

}
