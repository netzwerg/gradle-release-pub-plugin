gradle-release-pub-plugin
=========================

*Work in progress!*

Preferred future DSL:

```groovy
release {

    // existing ch.netzwerg.release plugin extension

    push = false
    suffix = '.DEV'

    // new extensible container

    publications {

        github {
            repo = 'gradle-release-pub-plugin'
            user = 'netzwerg'
            password = 'password'
            json = 'release-github.json'
        }

        mail-users {
            address = 'users@domain.com'
            template = 'mail-users.html'
        }

        mail-devs {
            address = 'developers@domain.com'
            template = 'mail-devs.html'
        }

    }

}
```