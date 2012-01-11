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

import griffon.util.GriffonNameUtils
import griffon.util.Metadata

includeTargets << griffonScript('CreateMvc')

target(name: 'createApplicationProject',
       description: 'Creates a new application project',
       prehook: null, posthook: null) {
    createProjectWithDefaults()

    Metadata md = Metadata.getInstance(new File("${basedir}/application.properties"))
    installPluginExternal md, 'swing'

    argsMap.model      = 'MainModel'
    argsMap.view       = 'MainView'
    argsMap.controller = 'MainController'
    createMVC()

    baseMvcFullQualifiedClassName = mvcFullQualifiedClassName

    createArtifact(
        name:     baseMvcFullQualifiedClassName,
        suffix:   'Actions',
        type:     'Actions',
        template: 'MainActions',
        path:     'griffon-app/views')

    createArtifact(
        name:     baseMvcFullQualifiedClassName,
        suffix:   'MenuBar',
        type:     'MenuBar',
        template: 'MainMenuBar',
        path:     'griffon-app/views')

    createArtifact(
        name:     baseMvcFullQualifiedClassName,
        suffix:   'StatusBar',
        type:     'StatusBar',
        template: 'MainStatusBar',
        path:     'griffon-app/views')

    createArtifact(
        name:     baseMvcFullQualifiedClassName,
        suffix:   'Content',
        type:     'Content',
        template: 'MainContent',
        path:     'griffon-app/views')

    argsMap.model = ''
    argsMap.view = ''
    argsMap.controller = ''

    createArtifact(
        name:     qualify('AbstractDialogModel'),
        suffix:   '',
        type:     'Model',
        template: 'AbstractDialogModel',
        path:     'griffon-app/models')

    if(fileType == '.java') {
        createArtifact(
            name:     qualify('AbstractDialogView'),
            suffix:   '',
            type:     'View',
            template: 'AbstractDialogView',
            path:     'griffon-app/views')
    }

    createArtifact(
        name:     qualify('DialogController'),
        suffix:   '',
        type:     'Controller',
        template: 'DialogController',
        path:     'griffon-app/controllers')

    argsMap['with-controller'] = qualify('DialogController')
    argsMap.view      = 'AboutView'
    argsMap.model     = 'AboutModel'
    argsMap.params[0] = qualify('about')
    createMVC()

    argsMap.view      = 'CreditsView'
    argsMap.model     = 'CreditsModel'
    argsMap.params[0] = qualify('credits')
    createMVC()

    argsMap.view      = 'LicenseView'
    argsMap.model     = 'LicenseModel'
    argsMap.params[0] = qualify('license')
    createMVC()

    argsMap.view      = 'PreferencesView'
    argsMap.model     = 'PreferencesModel'
    argsMap.params[0] = qualify('preferences')
    createMVC()

    argsMap['skip-package-prompt'] = true

    createArtifact(
        name:     'Events',
        suffix:   '',
        type:     'Events',
        template: 'Events',
        path:     'griffon-app/conf')

    createArtifact(
        name:     baseMvcFullQualifiedClassName,
        suffix:   'WindowDisplayHandler',
        type:     'WindowDisplayHandler',
        template: 'WindowDisplayHandler',
        path:     'src/main')

    ant.copy(todir: "${basedir}/griffon-app/resources", overwrite: true, force: true) {
        fileset(dir: "${archetypeDirPath}/griffon-app/resources")
    }
    ant.copy(todir: "${basedir}/griffon-app/i18n", overwrite: true, force: true) {
        fileset(dir: "${archetypeDirPath}/griffon-app/i18n")
    }
    ant.replace(dir: "${basedir}/griffon-app/i18n") {
        replacefilter(token: "@griffon.project.name@", value: GriffonNameUtils.capitalize(griffonAppName))
    }

    File configFile = new File("${basedir}/griffon-app/conf/Config.groovy")
    configFile.append("""
swing {
    windowManager {
        defaultHandler = new ${baseMvcFullQualifiedClassName}WindowDisplayHandler()
    }
}
""")

    installPluginsLatest md, ['actions', 'glazedlists', 'miglayout']
}

qualify = { className ->
    (mvcPackageName? mvcPackageName + '.':'') + className
}

setDefaultTarget(createApplicationProject)
