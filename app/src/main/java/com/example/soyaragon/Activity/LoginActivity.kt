package com.example.soyaragon.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soyaragon.R
//AUTH
import androidx.compose.runtime.*
import com.google.firebase.auth.FirebaseAuth

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

            LoginScreen (onLoginClick = {
                // Si el login es correcto, nos manda a MainActivity
                startActivity(Intent(this, MainActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview(){
    LoginScreen (onLoginClick = {})
}

// Pantalla de login
@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    val context = LocalContext.current

    // Estados para email, contraseña y visibilidad de la contraseña
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    // Estado para mostrar un diálogo de error
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Icono que cambia según si la contraseña está visible o no
    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.visible60)
    else
        painterResource(id = R.drawable.invisible60)

    // Instancias de Firebase Auth y Realtime Database
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    // Layout principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.redBackground))
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.zaragoza_pilar),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            alpha = 0.6f
        )

        // Columna con campos y botones
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(128.dp))
            Text(
                // Título
                text = "Log in",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(128.dp))

            // Campo de email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico *", color = colorResource(R.color.white)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(R.color.white),
                    unfocusedBorderColor = colorResource(R.color.white),
                    focusedBorderColor = colorResource(R.color.white),
                    focusedTextColor = colorResource(R.color.white)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de contraseña con opción de visibilidad
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña *", color = colorResource(R.color.white)) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(painter = icon, contentDescription = null)
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = colorResource(R.color.white),
                    unfocusedBorderColor = colorResource(R.color.white),
                    focusedBorderColor = colorResource(R.color.white),
                    focusedTextColor = colorResource(R.color.white)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Enlace para recuperación de contraseña (no implementado)
            Text(
                text = "Has olvidado tu contraseña?",
                style = TextStyle(color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Botón para iniciar sesión
            GradientButton(
                text = "Entrar",
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        // Si faltan datos, mostrar mensaje
                        errorMessage = "Debe rellenar usuario y contraseña"
                        showDialog = true
                    } else {
                        // Autenticación con Firebase
                        auth.signInWithEmailAndPassword(email.trim(), password.trim())
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = auth.currentUser
                                    // Verifica que el correo esté verificado
                                    if (user != null && user.isEmailVerified) {
                                        val userId = user.uid
                                        val userRef = database.child("usuarios").child(userId)
                                        // Comprueba si ya hay datos del usuario en la base de datos
                                        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                            override fun onDataChange(snapshot: DataSnapshot) {
                                                if (!snapshot.exists()) {
                                                    // Guarda datos mínimos si no existen
                                                    val userData = mapOf(
                                                        "email" to user.email,
                                                        "autorizado" to true
                                                    )
                                                    userRef.setValue(userData)
                                                        .addOnSuccessListener {
                                                            onLoginClick()
                                                        }
                                                        .addOnFailureListener {
                                                            errorMessage = "Error al guardar los datos"
                                                            showDialog = true
                                                        }
                                                } else {
                                                    // Si ya existe, continuamos
                                                    onLoginClick()
                                                }
                                            }

                                            override fun onCancelled(error: DatabaseError) {
                                                errorMessage = "Error de base de datos: ${error.message}"
                                                showDialog = true
                                            }
                                        })
                                    } else {
                                        // Si el correo no está verificado
                                        auth.signOut()
                                        errorMessage = "Verifica tu correo electrónico antes de acceder"
                                        showDialog = true
                                    }
                                } else {
                                    // Error de inicio de sesión
                                    errorMessage = task.exception?.localizedMessage ?: "Error desconocido al iniciar sesión"
                                    showDialog = true
                                }
                            }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para ir a la pantalla de registro
            GradientButton(
                text = "Registrarse",
                onClick = {
                    val intent = Intent(context, SignUpActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }

        // Diálogo de alerta para mostrar errores
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Alerta") },
                text = { Text(errorMessage) },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}


// Botón personalizado con borde y color degradado
@Composable
fun GradientButton(
    text: String,
    onClick:()->Unit,
    modifier: Modifier=Modifier
){
    Button(onClick = onClick,
        modifier=modifier,
        shape = RoundedCornerShape(60.dp),
        border = BorderStroke(
            width = 4.dp, brush = Brush.linearGradient(
                colors = listOf(colorResource(R.color.white), colorResource(R.color.white) )
            )
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,
            contentColor = Color.White)
    ){
        Text(
            text= text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)
    }
}


