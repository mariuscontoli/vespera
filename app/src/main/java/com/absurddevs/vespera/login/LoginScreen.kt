package com.absurddevs.vespera.login

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.absurddevs.vespera.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    state: LoginState,
    actions: LoginActions,
) {

    var loginInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }
    var passwordInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }

    var isLoginInputError by remember { mutableStateOf(false) }
    var isPasswordInputError by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.vespera_logo),
                contentDescription = "Logo image"
            )

            Text(
                text = stringResource(id = R.string.app_name), Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            LoginTextField(value = loginInput,
                labelId = R.string.login_username,
                onValueChange = {
                    isLoginInputError = false
                    loginInput = it
                })

            LoginTextField(value = passwordInput,
                labelId = R.string.login_password,
                onValueChange = {
                    isPasswordInputError = false
                    passwordInput = it
                })

            ClickableLoginTextComponent(onSelect = { })
        }
    }
}

@Composable
fun LoginTextField(
    value: TextFieldValue,
    @StringRes labelId: Int,
    onValueChange: (TextFieldValue) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false
) {
    val focusManager = LocalFocusManager.current

    val textStyle =
        MaterialTheme.typography.titleMedium.merge(TextStyle(fontWeight = FontWeight.Normal))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .padding(bottom = 6.dp),
        textStyle = textStyle,
        singleLine = true,
        label = { Text(text = stringResource(id = labelId), style = textStyle) },
        visualTransformation = visualTransformation,
        isError = isError,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.None),
        shape = MaterialTheme.shapes.small,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = MaterialTheme.colorScheme.primary.copy(alpha = MaterialTheme.colorScheme.onSurface.alpha),
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(MaterialTheme.colorScheme.onSurfaceVariant.alpha),
            errorLabelColor = MaterialTheme.colorScheme.error,
        )

    )
}

@Composable
fun ClickableLoginTextComponent(
    isLoginIn: Boolean = true,
    onSelect: (String) -> Unit
) {
    val initialText = "New here?"
    val createAccountText = "Create an account"

    val annotedString = buildAnnotatedString {
        append(initialText)
        append(" ")
        pushStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.primaryContainer,
                textDecoration = TextDecoration.Underline
            )
        )
        append(createAccountText)
    }

    ClickableText(
        modifier = Modifier,
        text = annotedString,
        onClick = { },
        style = TextStyle(
            fontSize = TextStyle.Default.fontSize,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
@Preview(name = "Login")
private fun LoginScreenPreview() {
    LoginScreen(
        state = LoginState(),
        actions = LoginActions()
    )
}

