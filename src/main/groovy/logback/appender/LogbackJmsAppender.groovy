package logback.appender

import ch.qos.logback.core.AppenderBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.stereotype.Component

/*
Delegates the actual append(LogEvent) and close() to the delegate.
 */
@Component
class LogbackJmsAppender extends AppenderBase {
    @Autowired
    Queue loggingEventQueue

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate

    @Override
    protected void append(Object eventObject) {
        jmsMessagingTemplate.convertAndSend(loggingEventQueue, eventObject)
    }

}
