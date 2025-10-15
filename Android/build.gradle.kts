// DELETE THIS BLOCK
public fun falsealias(): Boolean {
    TODO("Not yet implemented")
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Note: The 'alias(...)' syntax is used for version catalog entries.
    // 'apply false' means the plugin is not applied to the root project itself,
    // but its version is declared for sub-projects (like the :app module) to use.

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}





