package ru.platypus.loggerstarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logging.http")
@Getter
@Setter
public class LoggingProperties {

    private boolean enabled = true;

    private DetailLevel detailLevel = DetailLevel.BASIC;

    public enum DetailLevel {
        BASIC, FULL
    }
}