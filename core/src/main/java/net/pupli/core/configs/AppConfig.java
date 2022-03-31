package net.pupli.core.configs;

import net.pupli.core.services.StaticCache;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    @DependsOnDatabaseInitialization
    public StaticCache staticCache() {
        return new StaticCache();
    }
}
