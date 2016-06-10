package logback.appender

import grails.plugin.jms.JmsService
import spock.lang.Specification

class LogbackJmsAppenderSpec extends Specification {

    LogbackJmsAppender appender
    def setup() {
        appender = new LogbackJmsAppender()
    }

    def cleanup() {
    }

    void "test append sends a jms message to the loggingEvent queue"() {
        given:
            appender.jmsService = Mock(JmsService)
        when:
            appender.append("blah")
        then:"message to be queued"
            1 * appender.jmsService.send(['queue':'loggingEvent'], 'blah')
    }
}
