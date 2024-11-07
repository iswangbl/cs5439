package net.log4j;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JTest {

    private static final Logger logger = LogManager.getLogger(Log4JTest.class);

    public static void main(String[] args) {
        logger.error("wuya 666");
        logger.error("${java:runtime} - ${java:vm} - ${java:os}");
    }
}
