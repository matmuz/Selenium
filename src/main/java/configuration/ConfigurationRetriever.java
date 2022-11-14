package configuration;

import org.aeonbits.owner.ConfigFactory;

public final class ConfigurationRetriever {

    private static Configuration configuration;

    private ConfigurationRetriever() {
    }

    public static Configuration getConfiguration() {
        if (configuration == null) {
            return ConfigFactory.create(Configuration.class);
        }
        return configuration;
    }
}
