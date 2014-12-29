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
import ch.netzwerg.release.gradle.pub.github.GitHubPublication
import ch.netzwerg.release.gradle.pub.github.GitHubPublicationFactory
import ch.netzwerg.release.gradle.pub.github.ReleaseGitHubTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class ReleasePublicationPlugin implements Plugin<Project> {

    private static final String RELEASE_GIT_HUB_TASK_NAME = 'releasePubGitHub'

    @Override
    void apply(Project project) {
        project.plugins.apply ReleasePlugin
        ReleaseExtension releaseExtension = project.getExtensions().findByType(ReleaseExtension)
        releaseExtension.getPublicationFactory().registerDelegateFactory(GitHubPublication.PREFIX, new GitHubPublicationFactory())
        project.tasks.create(RELEASE_GIT_HUB_TASK_NAME, ReleaseGitHubTask)
    }

}