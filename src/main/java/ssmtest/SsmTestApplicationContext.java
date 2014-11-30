package ssmtest;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.Settings;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.spymemcached.MemcacheClientFactoryImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import ssmtest.service.CacheService;

import java.util.ArrayList;
import java.util.List;

@Configurable
@EnableAspectJAutoProxy
@ImportResource("classpath:simplesm-context.xml")
@ComponentScan(basePackageClasses = CacheService.class)
public class SsmTestApplicationContext {

	public static final String MEMCACHED_SERVER = "127.0.0.1:11211";

	@Bean
	public CacheFactory defaultMemcachedClient() {
		CacheFactory cacheFactory = new CacheFactory();
		cacheFactory.setCacheClientFactory(cacheClientFactory());
		cacheFactory.setAddressProvider(addressProvider());
		cacheFactory.setConfiguration(cacheConfiguration());
		cacheFactory.setDefaultSerializationType(SerializationType.JAVA);

		List<String> cacheAliases = new ArrayList<>();
		cacheAliases.add("multitest");
		cacheAliases.add("singletest");
		cacheFactory.setCacheAliases(cacheAliases);

		return cacheFactory;
	}

	@Bean
	public MemcacheClientFactoryImpl cacheClientFactory() {
		return new MemcacheClientFactoryImpl();
	}

	@Bean
	public DefaultAddressProvider addressProvider() {
		DefaultAddressProvider addressProvider = new DefaultAddressProvider();
		addressProvider.setAddress(MEMCACHED_SERVER);
		return addressProvider;
	}

	@Bean
	public CacheConfiguration cacheConfiguration() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setConsistentHashing(true);
		cacheConfiguration.setUseBinaryProtocol(true);
		cacheConfiguration.setOperationTimeout(10000);

		// !! UserNameAsKeyPrefix !! - comment out the following line to test.
		cacheConfiguration.setUseNameAsKeyPrefix(true);

		return cacheConfiguration;
	}

	@Bean
	public Settings ssmSettings() {
		Settings settings = new Settings();
		settings.setOrder(500);
		return settings;
	}
}
