package com.absurddevs.vespera.login


/**
 * UI State that represents LoginScreen
 **/
class LoginState

/**
 * Login Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginActions(
    val onClick: () -> Unit = {}
)