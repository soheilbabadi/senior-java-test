package ir.smartpath.senior.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MetricsConfig {


    private final  MeterRegistry meterRegistry;

    @Bean
    public Timer getMusicTimer() {
        return meterRegistry.timer("get_music_execution_time");
    }

    @Bean
    public Timer getBookTimer() {
        return meterRegistry.timer("get_book_execution_time");
    }
}