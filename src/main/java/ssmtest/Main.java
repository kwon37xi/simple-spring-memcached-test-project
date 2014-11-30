package ssmtest;

import java.util.Arrays;
import java.util.List;

import net.spy.memcached.compat.log.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ssmtest.service.CacheService;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		System.setProperty("net.spy.log.LoggerImpl", SLF4JLogger.class.getName());

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SsmTestApplicationContext.class);
		try {

			CacheService cacheService = context.getBean(CacheService.class);

			List<Integer> keys = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

			log.info("$$$ First results : {}", cacheService.getStringsFromCache(keys));
			log.info("$$$ Second results : {}", cacheService.getStringsFromCache(keys));
		} finally {
			context.close();
		}

	}
}
