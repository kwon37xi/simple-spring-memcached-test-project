Simple Sprint Memcached Learning test project

## Reproduce @ReadThroughMultiCache bug

```
./gradlew runMain
```

SSM 3.5.0 `@ReadThroughMultiCache` with `cacheConfiguration.setUseNameAsKeyPrefix(true);` does not work properly.

with `cacheConfiguration.setUseNameAsKeyPrefix(true);` CacheService.getStringsFromCache() method will throw IllegalStateException,
```
java.lang.IllegalStateException: Cannot be called twice in a row!
```
but with ``cacheConfiguration.setUseNameAsKeyPrefix(false);`, there will be no exception.
This configuration is in `ssmtest.SsmTestApplicationContext.cacheConfiguration` method.