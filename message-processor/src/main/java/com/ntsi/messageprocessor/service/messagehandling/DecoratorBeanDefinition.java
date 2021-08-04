package com.ntsi.messageprocessor.service.messagehandling;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DecoratorBeanDefinition implements BeanDefinitionRegistryPostProcessor {
    private static final String DECORATED_HANDLER = "decoratedHandler";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder gpxMetadata = BeanDefinitionBuilder
                .rootBeanDefinition(TrackerMetadataHandlingDecorator.class);
        gpxMetadata.addPropertyReference(DECORATED_HANDLER,"gpxMessageHandler");
        registry.registerBeanDefinition(TrackerMessageTypeFactory.GPX_METADATA_DECORATOR, gpxMetadata.getBeanDefinition());


    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory){

    }
}
