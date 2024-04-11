package com.acme.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger

class BuildLogic implements Plugin<Project> {

    Project project
    Logger log

    void  apply( final Project target ) {
        project = target
        log = project.logger

        log.lifecycle("[BUILD LOGIC PLUGIN]  ### Applying BuildLogic to $project")
    }
}
