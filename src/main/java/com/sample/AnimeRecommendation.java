package com.sample;

import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.definition.type.PropertyReactive;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class AnimeRecommendation {

	public static final void main(String[] args) {
		try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	KieRuntimeLogger kLogger = ks.getLoggers().newFileLogger(kSession, "test");
        	
        	Question question = new Question();
        	question.setStatus(1);
        	Answer answer = new Answer("Start");
        	kSession.insert(question);
        	kSession.insert(answer);
        	kSession.fireAllRules();
        	kLogger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}
	
	@PropertyReactive
	public static class Question {
		private String question;
		private ArrayList<Answer> answers;
		private int status;
		public void setQuestion(String question) {
			this.question = question;
		}
		public void setAnswers (ArrayList<Answer> answer) {
			this.answers = answer;
		}
		public ArrayList<Answer> getAnswers() {
			return this.answers;
		}
		public int getStatus() {
			return this.status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
	}
	
	@PropertyReactive
	public static class Answer {
		private String answer;
		public Answer (String answer) {
			this.answer = answer;
		}
		public void setAnswer (String answer) {
			this.answer = answer;
		}
		public String getAnswer() {
			return this.answer;
		}
	}
	
	@PropertyReactive
	public static class UsedAnswer {
		private Answer answer;
		public UsedAnswer (Answer answer) {
			this.answer = answer;
		}
		public void setAnswer(Answer answer) {
			this.answer = answer;
		}
		public Answer getAnswer() {
			return this.answer;
		}
	}
	
	public static class FinalAnswer {
		private ArrayList<String> answers;
		public FinalAnswer (ArrayList<String> answers) {
			this.answers = answers;
		}
		
	}

}
