import ch.qos.logback.ext.spring.DelegatingLogbackAppender
import grails.plugins.*
import logback.appender.LogbackJmsAppender
import logback.appender.LogbackLoggerNameFilter
import org.apache.activemq.command.ActiveMQQueue

class JmsLogAppenderGrailsPlugin extends Plugin {

    def title = "Jms Log Appender" // Headline display name of the plugin
    def author = "Todd Hiles"
    def authorEmail = "2toddhiles@gmail.com"
    def description = '''\
Defines a bean named jmsLogbackAppender for use within the logback config. eg. appender('logbackJmsAppender', DelegatingLogbackAppender).
'''
    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/jms-log-appender"

    // Extra (optional) plugin metadata

    def dependsOn = [ "logback-ext-spring" : '0.1.2' ]
    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() {
        { ->
            loggingEventQueue(ActiveMQQueue, "loggingEvent")
            // Important: This just sets up the bean "logbackJmsAppender", the dependent application
            // must configure 'logback' with an appender of type DelegatingLogbackAppender.
            // The DelegatingLogbackAppender will cache log events until the spring application
            // context and it's beans are ready and will then delegate to "a bean with the same name"
            // as the appender!
            // eg. appender('logbackJmsAppender', DelegatingLogbackAppender)
            //todo: make this bean name configurable.
            logbackJmsAppender(LogbackJmsAppender) {
                filter(LogbackLoggerNameFilter) {
                    excludeLoggerNames = ["grails.app.services.grails.plugin.jms", "org.springframework.messaging.core"]
                }
            }
        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
