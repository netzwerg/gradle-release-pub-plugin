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

import ch.netzwerg.gradle.release.pub.PubChannel

class MailPubChannel extends PubChannel {

    public static final String PREFIX = "mail"

    String fromAddress
    String toAddress
    String subject
    String messageFileName
    String host
    String user
    String password

    MailPubChannel(String name) {
        super(name)
    }

}
