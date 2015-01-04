/**
 * Copyright 2015 Rahel Lüthy
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
package ch.netzwerg.release.gradle.pub.mail

import ch.netzwerg.gradle.release.pub.PubChannelFactory
import org.slf4j.LoggerFactory

public class MailPubChannelFactory implements PubChannelFactory<MailPubChannel> {

    private static final LOGGER = LoggerFactory.getLogger(MailPubChannelFactory.class)

    private final Closure mailChannelHandler

    public MailPubChannelFactory(Closure mailChannelHandler) {
        this.mailChannelHandler = mailChannelHandler
    }

    @Override
    MailPubChannel create(String name) {
        LOGGER.debug("Creating MailPubChannel named '$name'")
        return new MailPubChannel(name)
    }

    @Override
    void onConfigurationComplete(MailPubChannel mailPubChannel) {
        mailChannelHandler mailPubChannel
    }

}