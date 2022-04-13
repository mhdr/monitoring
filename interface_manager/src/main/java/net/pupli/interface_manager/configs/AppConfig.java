package net.pupli.interface_manager.configs;

import net.pupli.interface_manager.services.MyCache;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public MyCache staticCache() {
        return new MyCache();
    }
}
