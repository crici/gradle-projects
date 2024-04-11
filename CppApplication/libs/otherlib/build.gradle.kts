plugins {
    `cpp-application`
}

group = "com.acme.sample"
version = "1.0"

application {
    targetMachines.set(listOf(
        machines.linux.x86_64,
        machines.macOS.x86_64,
        machines.windows.x86_64
    ))
}