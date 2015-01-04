package ch.netzwerg.release.gradle.pub.mail

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class MailTask extends DefaultTask {

    private static final String TASK_DESC = 'Sends release announcement via mail.'

    MailPubChannel config

    MailTask() {
        description = TASK_DESC
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    def send() {
        // TODO Implementation
//        def mailParams = [
//                mailhost       : config.host,
//                subject        : config.subject,
//                messagemimetype: inferMimeType(config.messageFileName),
//                user           : config.user,
//                password       : config.password
//        ]
//        ant.mail(mailParams) {
//            from(address: config.fromAddress)
//            to(address: config.toAddress)
//            message( "So far, so good" )
//        }
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    static def inferMimeType(String fileName) {
        if (fileName?.endsWith('html')) {
            return 'text/html'
        } else {
            return 'text/plain'
        }
    }

}