package com.pichincha.backend.backendtest.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.client.RestTemplate;

class AppConfigTest {

    private final ApplicationContextRunner context = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(RestTemplateAutoConfiguration.class))
            .withUserConfiguration(AppConfig.class);

    @Test
    void shouldHaveOnlyOneInstanceOfRestTemplate(){
        context.run(ctx -> {
            Assertions.assertThat(ctx).hasSingleBean(RestTemplate.class);
        });
    }
}
