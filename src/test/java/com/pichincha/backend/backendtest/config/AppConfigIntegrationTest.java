package com.pichincha.backend.backendtest.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {AppConfig.class, RestTemplateAutoConfiguration.class}
)
class AppConfigIntegrationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void shouldResttemplateBeNotNull(){
        Assertions.assertThat(restTemplate).isNotNull();
    }
}
