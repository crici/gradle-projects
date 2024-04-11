package com.acme.tasks

import com.acme.internal.WindowsOnlyTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal

abstract class MountDrive extends WindowsOnlyTask {

    @Input
    abstract Property<String> getDriveLetter()

    @Internal
    abstract DirectoryProperty getSubstitutionDirectory()

    @Override
    void doWindowsTaskAction() {
        File substitutionDir = substitutionDirectory.get().asFile
        substitutionDir.exists() || substitutionDir.mkdirs()

        if (project.file("${driveLetter.get()}:/").exists()) {
            def exitResult = project.exec {
                commandLine("cmd.exe", "/c", "subst", "/d", "${driveLetter.get()}:")
            }
            logger.lifecycle("[CMD returned]  errorCode = ${exitResult.exitValue}")
        }
        def exitResult = project.exec {
            commandLine("cmd.exe", "/c", "subst", "${driveLetter.get()}:", "${substitutionDir.absolutePath}")
        }
        logger.lifecycle("[CMD returned]  errorCode = ${exitResult.exitValue}")
    }
}
