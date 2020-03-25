package com.github.a4ad.adapters.logging;

import com.github.a4ad.port.out.logging.LoggingPort;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggingAdapterConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LoggingPort createLoggingPort(InjectionPoint point) {
        return new LoggingAdapter(point.getMember().getDeclaringClass());
    }

}
