package ch.netzwerg.release.gradle.pub.mail

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class MailTask extends DefaultTask {

    MailPubChannel config

    @SuppressWarnings("GroovyUnusedDeclaration")
    @TaskAction
    def send() {
        def mailParams = [
                mailhost       : config.smtpUrl,
                mailport       : config.smtpPort,
                messagemimetype: inferMimeType(config.messageFileName),
                user           : config.smtpUser,
                password       : config.smtpPassword,
                subject        : config.subject,
                ssl            : true
        ]
        ant.mail(mailParams) {
            from(address: config.fromAddress)
            to(address: config.toAddress)
            message("So far, so good") // TODO: Support message file
        }
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