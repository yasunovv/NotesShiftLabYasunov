// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.com.google.devtools.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
    alias(libs.plugins.jetbrains.kotlinx.serialization) apply false
    kotlin("kapt") version "2.0.21" apply false

}
buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin.v252)
    }
}