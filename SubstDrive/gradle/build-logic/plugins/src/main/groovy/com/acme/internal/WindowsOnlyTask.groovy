package com.acme.internal

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.os.OperatingSystem

import javax.inject.Inject

/**
 * Base class for windows family OS only task implementations.
 * The current operating system is checked at construction time.
 * If it does not belong to the Windows OS family, a {@link GradleException} is thrown.
 */
abstract class WindowsOnlyTask extends DefaultTask {

    static final String UNSUPPORTED_OS_ERROR_TEMPLATE = "This task class '%s' is not supported on operating system family '%s'."

    @Inject
    WindowsOnlyTask() {
        if ( !OperatingSystem.current().windows ) {
            throw new GradleException(String.format(UNSUPPORTED_OS_ERROR_TEMPLATE, this.class.name, OperatingSystem.current().familyName))
        }
    }

    /**
     * This MS Windows specific task action  must be implemented by the sub-class.
     */
    @TaskAction
    abstract void doWindowsTaskAction()
}
