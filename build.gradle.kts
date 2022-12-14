buildscript {
    rootProject.extra.set("composeVersion","1.2.1")
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("org.jetbrains.kotlin.kapt") version "1.7.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.30"
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
