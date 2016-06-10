package logback.appender

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.filter.Filter
import ch.qos.logback.core.spi.FilterReply

/**
 * Created by thiles on 6/7/2016.
 */
class LogbackLoggerNameFilter extends Filter<ILoggingEvent> {
    def excludeLoggerNames = ["grails.app.services.grails.plugin.jms"]
    def includeLoggerNames
    /**
     * If the decision is <code>{@link FilterReply#DENY}</code>, then the event will be
     * dropped. If the decision is <code>{@link FilterReply#NEUTRAL}</code>, then the next
     * filter, if any, will be invoked. If the decision is
     * <code>{@link FilterReply#ACCEPT}</code> then the event will be logged without
     * consulting with other filters in the chain.
     *
     * @param event
     *                The event to decide upon.
     */
    @Override
    FilterReply decide(ILoggingEvent event) {
        excludeLoggerNames?.contains(event.loggerName) ? FilterReply.DENY : includeLoggerNames?.contains(event.loggerName) ? FilterReply.ACCEPT : FilterReply.NEUTRAL
    }
}
