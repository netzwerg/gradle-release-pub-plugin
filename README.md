gradle-release-pub-plugin
=========================

Gradle plugin complementing the ['ch.netzwerg.release' plugin](https://github.com/netzwerg/gradle-release-plugin):
Supports publication of releases through configurable channels.

# Channel Type 'github'

Creates a GitHub release via [official REST API](https://developer.github.com/v3/repos/releases/#create-a-release).
Requires the [curl](http://curl.haxx.se) executable.

## Configuration
### JSON File

Requires a file named `release-github.json` whose contents will be passed to the GitHub API. Any tokens will be replaced
beforehand.

Example:

```json
{
 "tag_name" : "@tagName@",
 "name" : "Testing at @timeStamp@",
 "body" : "Test release body"
}
```

### Build File

```groovy
release {
    push = true // otherwise GitHub won't be able to link the release to its tag
    publish {
        github {
            repo = 'git-playground'
            user = 'netzwerg'
            password = "$GITHUB_PASSWORD"
            tokens = [tagName: release.tagName, timeStamp: new Date()]
        }
    }
}
```

# Channel Type 'mail'

_Coming soon_

# Applying the plugin

The plugin is hosted on [Bintray](https://bintray.com/netzwerg/gradle-plugins/gradle-release-pub-plugin) and can be
applied as follows:

## New plugin mechanism (as of Gradle 2.1):

```groovy
plugins {
  id 'ch.netzwerg.release.pub' version 'x.y.z'
}
```

## Older Gradle versions:

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'ch.netzwerg:gradle-release-pub-plugin:x.y.z'
    }
}
apply plugin: 'ch.netzwerg.release.pub'
```

# Acknowledgements

* [Etienne Studer](https://github.com/etiennestuder) (underlying [plugindev](https://github.com/etiennestuder/gradle-plugindev-plugin)
plugin, idea for this plugin)

# License

This plugin is available under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).

&copy; by Rahel Lüthy
