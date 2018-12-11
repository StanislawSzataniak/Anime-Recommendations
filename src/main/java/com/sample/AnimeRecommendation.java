package com.sample;

import org.kie.api.KieServices;
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
        	NewGUI gui = new NewGUI(kSession, kLogger);
        	kSession.setGlobal("AnimeGUI", gui);
        	kSession.insert("Start");
        	kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}
}
