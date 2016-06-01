package log

import org.apache.log4j.AppenderSkeleton
import org.apache.log4j.spi.LoggingEvent
/*
Delegates the actual append(LogEvent) and close() to the delegate.
 */

class DelegatingAppender extends AppenderSkeleton {
    def delegate

    @Override
    protected void append(LoggingEvent event) {
        try {
            delegate?.append(event)
        } catch (MissingMethodException e){
        }
    }

    @Override
    void close() {
        try {
            delegate?.close()
        } catch (MissingMethodException e) {
        }
    }

    @Override
    boolean requiresLayout() {
        return false
    }
}
