package com.alexa.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MyCustomSpeechlet implements Speechlet {

	private static BOTLogger LOG = BOTLogger.getLogger(HelloWorldPlatformContainerApplication.class);
	
	@Autowired
	private JsonHelper jsonHelper;
	
	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		return null;
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {

		LOG.debug("SpeechletResponse onIntent Start..");
		
		try {
			LOG.debug("IntentRequest " + jsonHelper.getJSONString(request));
			LOG.debug("Session Entry " + jsonHelper.getJSONString(session));
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		Intent intent = request.getIntent();
		if (intent == null)
			throw new SpeechletException("Unrecognized intent");

		SpeechletResponse response = new SpeechletResponse();
		
		if (intent.getName().equalsIgnoreCase("TellMeSomething")) {
			String speechText = "Hello, World.  I am a Spring Boot MyCustomSpeechlet. Explore more with me.. ";

			SimpleCard card = new SimpleCard();
			card.setTitle("Hello World Tell");
			card.setContent(speechText);

			PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
			speech.setText(speechText);

			response.setOutputSpeech(speech);
			
			session.setAttribute("SessionKey", new String("Working.."));
			
		} else if (intent.getName().equalsIgnoreCase("AskMeQuestion")) {
			String speechText = "Hello, World.  I am a Spring Boot MyCustomSpeechlet. What do you want ?";

			SimpleCard card = new SimpleCard();
			card.setTitle("Hello World Ask");
			card.setContent(speechText);

			PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
			speech.setText(speechText);

			Reprompt reprompt = new Reprompt();
			reprompt.setOutputSpeech(speech);

			response.setOutputSpeech(speech);
			
			response.setReprompt(reprompt);
			
			response.setShouldEndSession(false);
			
			session.setAttribute("SessionKey", new String("Working.."));
		}
		
		try {
			LOG.debug("SpeechletResponse " + jsonHelper.getJSONString(response));
			LOG.debug("Session Exit " + jsonHelper.getJSONString(session));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		LOG.debug("SpeechletResponse onIntent End");
		return response;
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

	}
}