package com.GTCSoftware.wordGuess;



import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;



public class WordGuessApp {

    private static Log logger = LogFactory.getLog(WordGuessApp.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        logger.debug("Sample debug message");
        logger.info("Sample info message");
        logger.warn("Sample warn message");
        logger.error("Sample error message");
        logger.fatal("Sample fatal message");
        
        ApnsService service;
        service = APNS.newService()
                  .withCert("C:/Users/William/Workspace/wordGuess-project/src/main/resources/WordGuessCertificate.p12", "e777bBi9R22p")
                  .withSandboxDestination()
                  .build();
      
       String payload = APNS.newPayload().alertBody("Celebrate, celebrate...!!").build();
        String token = "98edadfeedf02035505327e39d5bdca64c0613d4393dc315342b598528e6ec34 ";
        service.push(token, payload);
     }
    private static final Logger LOG = Logger.getLogger(WordGuessApp.class.getName());
}
