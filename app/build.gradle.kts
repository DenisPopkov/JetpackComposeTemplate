@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {

    namespace = "com.compose.template"
    compileSdk = Sdk.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = AppConfiguration.APPLICATION_ID
        minSdk = Sdk.MIN_SDK_VERSION
        targetSdk = Sdk.TARGET_SDK_VERSION
        versionCode = AppConfiguration.VERSION_CODE
        versionName = AppConfiguration.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            resValue("string", "app_name", "(D)ComposeTemplate")
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        release {
            resValue("string", "app_name", "ComposeTemplate")
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
        buildFeatures {
            kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material.ExperimentalMaterialApi"
            kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
            kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi"
            kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.animation.ExperimentalAnimationApi"
            kotlinOptions.freeCompilerArgs += "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
            kotlinOptions.freeCompilerArgs += "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi"
            kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = Versions.BUILD_TOOLS
}

kapt {
    correctErrorTypes = true
}

dependencies {
    platform(ComposeLibs.BOM)
        .also(::implementation)
        .also(::androidTestImplementation)

    implementation(AndroidXLibs.CORE)
    implementation(AndroidLibs.MATERIAL)
    implementation(AndroidLibs.SERIALIZATION)
    implementation(AndroidLibs.COROUTINES_CORE)
    implementation(AndroidLibs.COROUTINES)
    implementation(AndroidLibs.DATASTORE)
    implementation(AndroidLibs.LIVEDATA)

    implementation(ComposeLibs.UI)
    implementation(ComposeLibs.MATERIAL)
    implementation(ComposeLibs.TOOLING_PREVIEW)
    implementation(ComposeLibs.NAVIGATION)
    implementation(ComposeLibs.HILT_NAVIGATION)
    implementation(ComposeLibs.ACTIVITY)

    implementation(Acompanist.SYSTEM_UI_CONTROLLER_ACCOMPANIST)
    implementation(Acompanist.PAGER_ACCOMPANIST)
    implementation(Acompanist.PAGER_INDICATOR_ACCOMPANIST)
    implementation(Acompanist.SWIPE_REFRESHER_ACCOMPANIST)
    implementation(Acompanist.PLACEHOLDER_ACCOMPANIST)
    implementation(Acompanist.INSETS_ACCOMPANIST)

    implementation(AndroidXTestLibs.SPLASH)
    implementation(UILibs.COIL)
    implementation(HiltLibs.ANDROID)
    kapt(HiltLibs.ANDROID_COMPILER)

    testImplementation(TestLibs.JUNIT)
    androidTestImplementation(AndroidXTestLibs.JUNIT)
    androidTestImplementation(ComposeLibs.UI_TEST)

    debugImplementation(ComposeLibs.UI_TEST_MANIFEST)
    debugImplementation(ComposeLibs.TOOLING)
    debugImplementation(ComposeLibs.TOOLING_PREVIEW)
}
