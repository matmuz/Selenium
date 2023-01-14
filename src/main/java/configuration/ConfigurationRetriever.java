package configuration;

import org.aeonbits.owner.ConfigCache;

public final class ConfigurationRetriever {

    private ConfigurationRetriever() {
    }

    public static Configuration getConfiguration() {
        return ConfigCache.getOrCreate(Configuration.class, System.getProperties());
    }
}
