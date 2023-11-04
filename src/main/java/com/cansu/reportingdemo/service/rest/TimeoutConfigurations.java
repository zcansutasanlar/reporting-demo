package com.cansu.reportingdemo.service.rest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Setter
@Getter
public class TimeoutConfigurations {

    private Map<TimeoutLevel, TimeoutConfig> levels = new HashMap<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class TimeoutConfig {
        private int connectTimeout;
        private int readTimeout;
        private int connectionRequestTimeout;
    }

    public TimeoutConfig getTimeoutConfigForTimeoutLevel(TimeoutLevel timeoutLevel) {
        return levels.getOrDefault(timeoutLevel, getDefaultTiemoutConfigForLevel(timeoutLevel));
    }

    private TimeoutConfig getDefaultTiemoutConfigForLevel(TimeoutLevel timeoutLevel) {
        switch (timeoutLevel) {
            case QUICK:
                return new TimeoutConfig(3000, 3000, 3000);
            case INTERMEDIATE:
                return new TimeoutConfig(5000, 5000, 5000);
            case MEDIUM:
                return new TimeoutConfig(10000, 10000, 10000);
            case SLOW:
                return new TimeoutConfig(20000, 20000, 20000);
            case SLOTH:
                return new TimeoutConfig(120000, 120000, 120000);
        }

        throw new IllegalArgumentException();
    }

    @PostConstruct
    private void validateTimeouts() {
        Arrays.stream(TimeoutLevel.values()).forEach(timeoutLevel -> {
            TimeoutConfig timeoutConfig = getTimeoutConfigForTimeoutLevel(timeoutLevel);
            if (timeoutConfig.getConnectTimeout() <= 0 || timeoutConfig.getReadTimeout() <= 0 || timeoutConfig.getConnectionRequestTimeout() <= 0) {
                throw new IllegalArgumentException(String.format("timeouts cannot be infinite (i.e. < 0), check config for TimeoutLevel.%s", timeoutLevel));
            }
        });
    }
}