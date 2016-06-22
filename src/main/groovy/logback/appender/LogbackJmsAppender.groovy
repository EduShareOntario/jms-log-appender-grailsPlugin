package logback.appender

import ch.qos.logback.core.AppenderBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.stereotype.Component

import javax.annotation.Resource
import javax.jms.Queue

/*
Delegates the actual append(LogEvent) and close() to the delegate.
 */
@Component
class LogbackJmsAppender extends AppenderBase {
    @Resource(name="loggingEventQueue")
    Queue loggingEventQueue

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate

    @Override
    protected void append(Object eventObject) {
        Map m = eventObject.properties
        jmsMessagingTemplate.convertAndSend(loggingEventQueue, eventObject)
    }

}
