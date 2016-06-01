package log

import org.apache.log4j.spi.LoggingEvent

class JmsLogAppenderService {
    static transactional = false
    def append(LoggingEvent event) {
        sendQueueJMSMessage('loggingEvent', event)
    }

}
