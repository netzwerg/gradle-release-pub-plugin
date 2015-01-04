/**
 * Copyright 2014 Rahel Lüthy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.netzwerg.release.gradle.pub

import ch.netzwerg.gradle.release.ReleaseExtension
import ch.netzwerg.gradle.release.ReleasePlugin
import ch.netzwerg.release.gradle.pub.github.GitHubPubChannel
import ch.netzwerg.release.gradle.pub.github.GitHubPubChannelFactory
import ch.netzwerg.release.gradle.pub.github.ReleaseGitHubTask
import ch.netzwerg.release.gradle.pub.mail.MailPubChannel
import ch.netzwerg.release.gradle.pub.mail.MailPubChannelFactory
import ch.netzwerg.release.gradle.pub.mail.MailTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.slf4j.LoggerFactory

class ReleasePublicationPlugin implements Plugin<Project> {

    private static final LOGGER = LoggerFactory.getLogger(ReleasePublicationPlugin.class)
    private static final String RELEASE_GIT_HUB_TASK_NAME = 'releasePubGitHub'

    @Override
    void apply(Project project) {
        project.plugins.apply ReleasePlugin
        ReleaseExtension releaseExtension = project.getExtensions().findByType(ReleaseExtension)

        releaseExtension.pubChannels.registerPubChannelFactory(GitHubPubChannel.PREFIX, new GitHubPubChannelFactory())

        def releasePubGitHubTask = project.tasks.create(RELEASE_GIT_HUB_TASK_NAME, ReleaseGitHubTask)
        def releaseTask = project.tasks.findByPath(ReleasePlugin.RELEASE_TASK_NAME)
        releaseTask.finalizedBy(releasePubGitHubTask)

        Closure<Task> mailChannelHandler = { MailPubChannel mailPubChannel ->
            LOGGER.debug("Handling channel config (type: '$MailPubChannel.PREFIX', name: '$mailPubChannel.name')")

            String capitalizedName = mailPubChannel.name.capitalize()
            String mailTaskName = "releasePubMail$capitalizedName"
            MailTask mailTask = project.tasks.create(mailTaskName, MailTask.class)
            mailTask.config = mailPubChannel
            releaseTask.finalizedBy(mailTask)
        }
        releaseExtension.pubChannels.registerPubChannelFactory(MailPubChannel.PREFIX, new MailPubChannelFactory(mailChannelHandler))
    }

}