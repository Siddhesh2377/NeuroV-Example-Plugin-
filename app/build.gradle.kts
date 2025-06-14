import java.io.FileNotFoundException
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "$PLUGIN_PACKAGE"
    compileSdk = 36

    defaultConfig {
        applicationId = "$PLUGIN_PACKAGE"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("plugin") {
            initWith(getByName("debug"))

            isDebuggable = false
            isMinifyEnabled = false

            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    compileOnly(files("libs/$PLUGIN_VERSION"))
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.1")
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.register("packagePluginZip") {
    group = "build"
    description = "Builds plugin APK, zips it as plugin.jar with manifest.json, uploads to FTP, and opens FTP folder."

    dependsOn("build", "assemblePlugin")

    doLast {
        val buildDir = layout.buildDirectory.get().asFile
        val apkDir = File(buildDir, "outputs/apk/plugin")
        val apkFile = apkDir.listFiles()?.firstOrNull { it.extension == "apk" }
            ?: throw FileNotFoundException("Plugin APK not found in $apkDir")

        val manifestFile = File(project.projectDir, "plugin_manifest/manifest.json")
        if (!manifestFile.exists()) throw FileNotFoundException("manifest.json not found at: $manifestFile")

        // STEP 1: Create ZIP locally
        val outputZip = File(buildDir, "list_applications_plugin.zip")

        ZipOutputStream(outputZip.outputStream()).use { zip ->
            listOf(
                apkFile to "plugin.jar",
                manifestFile to "manifest.json"
            ).forEach { (file, name) ->
                zip.putNextEntry(ZipEntry(name))
                file.inputStream().use { it.copyTo(zip) }
                zip.closeEntry()
            }
        }

        println("✅ Plugin ZIP created at: ${outputZip.absolutePath}")
    }
}
