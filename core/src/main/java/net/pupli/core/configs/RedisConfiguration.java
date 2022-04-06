package net.pupli.core.configs;

import net.pupli.core.libs.ConfigFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfiguration {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        ConfigFile configFile = new ConfigFile();
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(configFile.getRedisHost());
        configuration.setPassword(configFile.getRedisPassword());
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        return new StringRedisTemplate(redisConnectionFactory());
    }
}
