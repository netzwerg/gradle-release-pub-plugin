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
package ch.netzwerg.release.gradle.pub.github

import ch.netzwerg.gradle.release.ReleaseExtension
import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.slf4j.LoggerFactory

class ReleaseGitHubTask extends DefaultTask {

    private static final LOGGER = LoggerFactory.getLogger(ReleaseGitHubTask.class)
    private static final String JSON_FILE_NAME = 'release-github.json'
    private static final String RELEASE_GIT_HUB_TASK_DESC = 'Publishes release(s) via GitHub REST API.'

    ReleaseGitHubTask() {
        description = RELEASE_GIT_HUB_TASK_DESC
    }

    @TaskAction
    def release() {
        ReleaseExtension releaseExtension = project.getExtensions().findByType(ReleaseExtension)
        Collection<GitHubPublication> publications = releaseExtension.getPublicationsByNamePrefix(GitHubPublication.PREFIX) as Collection<GitHubPublication>
        LOGGER.debug("Number of resolved GitHubPublications: " + publications.size())
        publications.each { publishToGitHub(it) }
    }

    def publishToGitHub(GitHubPublication publication) {
        project.copy {
            from JSON_FILE_NAME
            into getTemporaryDir()
            filter(ReplaceTokens, tokens: publication.tokens)
        }

        project.exec {
            executable 'curl'
            args '-X', 'POST',
                    '-u', "$publication.user:$publication.password",
                    '--data-binary', '@' + getTemporaryDir() + File.separator + JSON_FILE_NAME,
                    "https://api.github.com/repos/$publication.user/$publication.repo/releases"
        }
    }

}