package common.config_reader;

import org.aeonbits.owner.ConfigCache;



public class ConfigurationManager {

    private ConfigurationManager() {
    }


    public static AppConfig getAppConfig() {
        return ConfigCache.getOrCreate(AppConfig.class);
    }

    public static CredentialsConfig getCredentials()   {
        return ConfigCache.getOrCreate(CredentialsConfig.class);
    }

}

