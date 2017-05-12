/**
 * 
 */
package com.alexa.springboot;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;

/**
 * 
 * http://stackoverflow.com/questions/40556117/is-it-possible-to-create-a-custom-alexa-skill-with-spring-boot-https-endpoint
 *
 */
public class AlexaBootServlet extends SpeechletServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static BOTLogger LOG = BOTLogger.getLogger(AlexaBootServlet.class);
	
	private static final Set<String> supportedApplicationIds = new HashSet<String>();
	static {
		/*
		 * This Id can be found on https://developer.amazon.com/edw/home.html#/
		 * "Edit" the relevant Alexa Skill and put the relevant Application Ids
		 * in this Set.
		 */
		supportedApplicationIds.add("amzn1.ask.skill.4da3d4d1-1274-4c69-8c35-800a1b83e9fd");
	}

	private ApplicationContext appContext;
	private MyCustomSpeechlet myCustomSpeechlet;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		appContext = (ApplicationContext) config.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		myCustomSpeechlet = (MyCustomSpeechlet) appContext.getBean("myCustomSpeechlet");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getOutputStream().write("Hello !!".getBytes());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		LOG.debug("Executing doPost method ");
		
		try {
			this.setSpeechlet(myCustomSpeechlet);
		} catch (Exception exp) {
			LOG.error("Failed and got exception..");
			exp.printStackTrace();
		}
		
		super.doPost(request, response);
	}
}
