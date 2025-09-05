package com.example.medreminder.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medreminder.R
import com.example.medreminder.ui.component.auth.AuthLogoCard
import com.example.medreminder.ui.component.auth.EmailTextField
import com.example.medreminder.ui.component.auth.AuthInfoText
import com.example.medreminder.ui.component.auth.PasswordTextField
import com.example.medreminder.ui.component.auth.UsernameTextField
import com.example.medreminder.ui.theme.InfoTextColor
import com.example.medreminder.ui.theme.InfoTextTitleColor
import com.example.medreminder.ui.viewmodel.AuthViewModel


@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel()
) {
    val showRegister by authViewModel.showRegister.collectAsState()
    val emailInput by authViewModel.emailInput.collectAsState()
    val passwordInput by authViewModel.passwordInput.collectAsState()
    val passwordRepeatInput by authViewModel.passwordRepeatInput.collectAsState()
    val usernameInput by authViewModel.usernameInput.collectAsState()
    val showEmailHint by authViewModel.showEmailHint.collectAsState()
    val showPasswordHint by authViewModel.showPasswordHint.collectAsState()
    val showPasswordRepeatHint by authViewModel.showPasswordRepeatHint.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        AuthLogoCard()


        Column(
            modifier = Modifier
                .weight(1f)
                .padding(32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.tab_label_auth_welcome_text),
                color = Color.Black,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.tab_label_auth_info),
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            EmailTextField(
                emailText = emailInput,
                onEmailTextChange = { authViewModel.onEmailInputChange(it) },
                showSupportingText = showEmailHint,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            PasswordTextField(
                value = passwordInput,
                onValueChange = { authViewModel.onPasswordInputChange(it) },
                modifier = Modifier.fillMaxWidth()
            )

            AnimatedVisibility(showRegister) {
                Column {
                    Spacer(Modifier.height(16.dp))
                    PasswordTextField(
                        value = passwordRepeatInput,
                        onValueChange = { authViewModel.onPasswordRepeatInputChange(it) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    UsernameTextField(
                        value = usernameInput,
                        onValueChange = { authViewModel.onUsernameInputChange(it) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { authViewModel.loginOrRegister() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF0000FE))
            ) {
                Text(
                    if (showRegister) stringResource(R.string.tab_label_auth_button_register) else stringResource(
                        R.string.tab_label_auth_button_login
                    ), fontSize = 18.sp
                )
            }

            TextButton(
                onClick = { authViewModel.toggleShowRegister() }
            ) {
                Text(
                    if (showRegister) stringResource(R.string.tab_label_auth_login) else stringResource(
                        R.string.tab_label_auth_register
                    ),
                    color = Color.Blue,
                    fontSize = 16.sp
                )
            }
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.info_title),
                    fontSize = 18.sp,
                    color = InfoTextTitleColor
                )
                AuthInfoText(
                    stringResource(R.string.info_item_search_medications),
                    16,
                    InfoTextColor
                )
                AuthInfoText(stringResource(R.string.info_item_reminders), 16, Color(0xFF424242))
                AuthInfoText(
                    stringResource(R.string.info_item_manage_favorites),
                    16,
                    InfoTextColor
                )
                AuthInfoText(
                    stringResource(R.string.info_item_medication_list),
                    16,
                    InfoTextColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthScreenPreview() {
    // Use Theme here
    AuthScreen()
}