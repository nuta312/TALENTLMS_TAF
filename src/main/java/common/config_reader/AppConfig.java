package common.config_reader;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:app.properties"})
public interface AppConfig extends Config {
    @Key("base.url")
    String base_url();

    @Key("docker.remote")
    boolean remote();

    @Key("remote.url.docker")
    String dockerUrl();

}