package common.config_reader;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:credentials.properties"})
public interface CredentialsConfig extends Config {

    @Config.Key("admin_username")
    String adminUsername();

    @Config.Key("admin_password")
    String adminPassword();
}
