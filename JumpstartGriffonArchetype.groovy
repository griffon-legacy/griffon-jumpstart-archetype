/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */
class JumpstartGriffonArchetype {
    String version = '1.0'
    String griffonVersion = '0.9.5-SNAPSHOT > *'
    String license = 'Apache Software License 2.0'
    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'Adds typical setup and dialogs'
    String description = '''
Inspired by [Ubuntu's Quickly][1] this archetype bootstraps an application by
adding common behavior found in many desktop applications, for example

 * main are separated in menu bar, content and status bar
 * typical set of menu actions (Open, Save, Quit, etc)
 * About, Credits, License and Preferences dialogs
 * centers all windows/dialogs on the screen
 * adds a quit confirmation dialog

Usage
----
Simply specify the name of the archetype (*jumpstart*) when invoking the `create-app`
command, like this

    griffon create-app sample -archetype=jumpstart

This will create the following artifacts

 **Configuration files**
 
 - griffon-app/conf/Events.groovy
 - griffon-app/i18n/messages.properties
 
 **Models**
 
 - griffon-app/models/sample/AboutModel.groovy
 - griffon-app/models/sample/AbstractDialogModel.groovy
 - griffon-app/models/sample/CreditsModel.groovy
 - griffon-app/models/sample/LicenseModel.groovy
 - griffon-app/models/sample/PreferencesModel.groovy
 
 **Views**
 
 - griffon-app/views/sample/AboutView.groovy
 - griffon-app/views/sample/CreditsView.groovy
 - griffon-app/views/sample/LicenseView.groovy
 - griffon-app/views/sample/PreferencesView.groovy
 - griffon-app/views/sample/SampleActions.groovy
 - griffon-app/views/sample/SampleContent.groovy
 - griffon-app/views/sample/SampleMenuBar.groovy
 - griffon-app/views/sample/SampleStatusBar.groovy
 - griffon-app/views/sample/SampleView.groovy
 
 **Controllers**
 
 - griffon-app/controllers/sample/DialogController.groovy
 - griffon-app/controllers/sample/SampleController.groovy

You can tweak all artifacts to match you're own needs.
Pay close attention to the conventions specified in the main MVC model. Controller actions
will be harvested using the *actions* plugin. All messages are internationalized thanks to
the *i18n* plugin, which means you only need to create a messages.properties file that follows
your own language conventions.
 
Configuration
-------------
The Jumpstart archetype supports Java files too.
Create a Java based application with the following command

    griffon create-app sample -archetype=jumpstart -fileType=java

[1]: https://wiki.ubuntu.com/Quickly
'''

    // URL to the archetype's documentation
    String documentation = 'http://griffon.codehaus.org/Jumpstart+Archetype'
}
