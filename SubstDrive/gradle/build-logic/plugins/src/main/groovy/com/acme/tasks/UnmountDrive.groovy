package com.acme.tasks

import com.acme.internal.WindowsOnlyTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

abstract class UnmountDrive extends WindowsOnlyTask {

    @Input
    abstract Property<String> getDriveLetter()

    @Override
    void doWindowsTaskAction() {
        if (project.file("${driveLetter.get()}:/").exists()) {
            def exitResult = project.exec {
                commandLine("cmd.exe", "/c", "subst", "/d", "${driveLetter.get()}:")
            }
            logger.lifecycle("[CMD returned]  errorCode = ${exitResult.exitValue}")
        }
    }
}
