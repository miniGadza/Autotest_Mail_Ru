package readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig(){
        return ConfigFactory.load("file_test_item.conf");
    }

    String URL = readConfig().getString("url");
    String USER1_LOGIN = readConfig().getString("users_parameters.user1.login");
    String USER1_PASSWORD = readConfig().getString("users_parameters.user1.password");
    String USER2_LOGIN = readConfig().getString("users_parameters.user2.login");
    String USER2_PASSWORD = readConfig().getString("users_parameters.user2.password");
}
