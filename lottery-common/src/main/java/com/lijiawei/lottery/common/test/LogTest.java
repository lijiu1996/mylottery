package com.lijiawei.lottery.common.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  测试了一下日志配置文件的使用
 */
@Slf4j
@SpringBootApplication
public class LogTest {

    public static void main(String[] args) {
        SpringApplication.run(LogTest.class);
//        System.out.println(log.getClass());
//        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//        Level level = root.getLevel();
//        System.out.println(level);
        log.trace("???");
        log.debug("aaa");
        log.error("bbb");
        log.info("ccc");
    }
}
