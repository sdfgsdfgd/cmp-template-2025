plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
}

version = "1.0-SNAPSHOT"

kotlin {
    // Android
    androidTarget()

    // iOS
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    // Desktop
    jvm("desktop")

    // Web
    js {
        browser()
        useEsModules()
    }
    wasmJs { browser() }


    applyDefaultHierarchyTemplate()

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            // TODO: Submit a ticket on this ?  ( For Quest: Ultimate Flat & Most Minimal Structs )
            // xx-1 Found out the hard way that we can't ultimately `Flatten` the folder structure.
            //     The `res` folder requires a `src` root, otherwise @drawable can't resolve
            //  1- Setting kotlin src dir
            //  2- Setting resources src dir
            //  3- Setting manifest file
            //  4-   ? why still not working with @theme/... references when src/ dir removed from parent ?  submit the bug
            // kotlin.srcDirs("androidMain/kotlin")
            // kotlin.srcDirs("commonMain/kotlin")
            // resources.srcDirs(
            //     "/Users/x/Desktop/android/compose-multiplatform/examples/imageviewer/androidApp/androidMain/res",
            //     "androidMain/res",
            //     "src/main/res",
            //     "src/res",
            // )

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.components.resources)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
        }

        androidMain.dependencies {
            api("androidx.activity:activity-compose:1.9.1")
            api("androidx.appcompat:appcompat:1.7.0")
            api("androidx.core:core-ktx:1.13.1")
            implementation("androidx.camera:camera-camera2:1.3.4")
            implementation("androidx.camera:camera-lifecycle:1.3.4")
            implementation("androidx.camera:camera-view:1.3.4")
            implementation("com.google.accompanist:accompanist-permissions:0.34.0")
            implementation("com.google.android.gms:play-services-maps:19.0.0")
            implementation("com.google.android.gms:play-services-location:21.3.0")
            implementation("com.google.maps.android:maps-compose:2.11.2")
        }

        val jsWasmMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(npm("uuid", "^9.0.1"))
            }
        }

        val jsMain by getting {
            dependsOn(jsWasmMain)
        }

        val wasmJsMain by getting {
            dependsOn(jsWasmMain)
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.common)
            implementation(project(":mapview-desktop"))
        }

        val desktopTest by getting
        desktopTest.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(compose.desktop.uiTestJUnit4)
        }
    }
}

android {
    compileSdk = 34
    namespace = "template.x.shared"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }


    // xx-1    ( Same as above )
    //    sourceSets {
    //        getByName("main") {
    //            manifest.srcFile("androidMain/AndroidManifest.xml")
    //            resources.srcDir("androidMain/res")
    //            res.srcDirs(listOf("path/to/your/res"))
    //        }
    //    }
}
