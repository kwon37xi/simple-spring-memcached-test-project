package ssmtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.code.ssm.api.CacheName;
import com.google.code.ssm.api.ReadThroughMultiCache;
import com.google.code.ssm.api.ReadThroughMultiCacheOption;

@Service
public class CacheService {
	private Logger log = LoggerFactory.getLogger(CacheService.class);

	private AtomicInteger callCount = new AtomicInteger(0);

	@CacheName("multitest")
	@ReadThroughMultiCache(namespace = "stringsFromCache", expiration = 300, option = @ReadThroughMultiCacheOption(skipNullsInResult = true))
	public List<String> getStringsFromCache(@ParameterValueKeyProvider List<Integer> keys) {
		int called = callCount.get();
		log.info("$$$ getStringsFromCache called : " + called);

		if (called > 0) {
			throw new IllegalStateException("Cannot be called twice in a row!");
		}

		List<String> results = new ArrayList<>();
		for (Integer key : keys) {
			results.add("Key : " + key);
		}

		callCount.incrementAndGet();
		log.info("$$$ results : " + results);
		return results;
	}
}
