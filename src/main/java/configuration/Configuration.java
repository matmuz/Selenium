package configuration;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:configuration.properties"})
public interface Configuration extends Config {

    int timeout();

    boolean isLocal();
}