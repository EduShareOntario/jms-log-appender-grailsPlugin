package logback.appender;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.*;
import org.springframework.jms.listener.MessageListenerContainer;

/**
 * Created by thiles on 6/9/2016.
 */
public class Configurator implements JmsListenerConfigurer {
    /**
     * Callback allowing a {@link JmsListenerEndpointRegistry
     * JmsListenerEndpointRegistry} and specific {@link JmsListenerEndpoint
     * JmsListenerEndpoint} instances to be registered against the given
     * {@link JmsListenerEndpointRegistrar}. The default
     * {@link JmsListenerContainerFactory JmsListenerContainerFactory}
     * can also be customized.
     *
     * @param registrar the registrar to be configured
     */
    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        registrar.setContainerFactory(jmsListenerContainerFactory());
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory() {
        return new DefaultJmsListenerContainerFactory();
    }
}
