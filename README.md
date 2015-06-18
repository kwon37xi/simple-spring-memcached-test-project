Simple Sprint Memcached Learning test project

Test memcached server : `localhost:11211`

## Reproduce @ReadThroughMultiCache bug [#42](https://github.com/ragnor/simple-spring-memcached/issues/42)
_This bug has been fixed in Simple spring memcached 3.6.0._

```
./gradlew runMain
```

SSM configuration is in `SsmTestApplicationContext` class.

SSM 3.5.0 `@ReadThroughMultiCache` with `cacheConfiguration.setUseNameAsKeyPrefix(true);` does not work properly.

with `cacheConfiguration.setUseNameAsKeyPrefix(true);` CacheService.getStringsFromCache() method will throw IllegalStateException,
```
java.lang.IllegalStateException: Cannot be called twice in a row!
```
but with ``cacheConfiguration.setUseNameAsKeyPrefix(false);`, there will be no exception.
This configuration is in `ssmtest.SsmTestApplicationContext.cacheConfiguration` method.
