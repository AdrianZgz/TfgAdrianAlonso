package com.example.soyaragon.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soyaragon.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// Clase principal del registro
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen()
        }
    }
}

@Composable
fun SignUpScreen() {
    // Variables para los campos del formulario
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    // Contexto para mostrar Toasts
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    // Variables para mostrar diálogo de éxito
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    // Diálogo de confirmación cuando el registro se completa
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Registro completado") },
            text = { Text(dialogMessage) }
        )
    }
    // Contenedor general del formulario
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)
    ) {
        // Título
        Text(
            text = "Registro",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        // Caja contenedora del formulario
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray1Background),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campos del formulario
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            label = { Text("Repite contraseña") },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        // Checkbox de aceptación de términos
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
            Text("Acepto los términos y condiciones")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de registro
        Button(
            onClick = {
                // Validamos los campos
                if (email.isNotEmpty() && password.isNotEmpty() && password == repeatPassword && dni.isNotEmpty() && nombre.isNotEmpty() && apellido.isNotEmpty() && isChecked) {
                    // Comprobamos si el DNI existe en el nodo "dnis" en Firebase
                    database.child("dnis").child(dni)
                        .get().addOnSuccessListener { snapshot ->
                            if (snapshot.exists()) {
                                Toast.makeText(context, "El DNI ya está registrado", Toast.LENGTH_SHORT).show()
                            } else {
                                // Creamos usuario si no existe
                                auth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            val uid = auth.currentUser?.uid
                                            if (uid != null) {
                                                val userData = mapOf(
                                                    "nombre" to nombre,
                                                    "apellido" to apellido,
                                                    "dni" to dni,
                                                    "email" to email
                                                )
                                                // Guardamos datos bajo usuarios/uid
                                                database.child("usuarios").child(uid).setValue(userData)
                                                    .addOnSuccessListener {
                                                        // Guardamos índice del DNI bajo dnis/dni
                                                        database.child("dnis").child(dni).setValue(uid)
                                                            .addOnSuccessListener {
                                                                // Enviamos email de verificación
                                                                auth.currentUser?.sendEmailVerification()
                                                                    ?.addOnSuccessListener {
                                                                        dialogMessage =
                                                                            "Te hemos enviado un correo de verificación. Verifica tu email antes de iniciar sesión."
                                                                        showDialog = true
                                                                    }
                                                                    ?.addOnFailureListener {
                                                                        Toast.makeText(
                                                                            context,
                                                                            "Error al enviar correo de verificación",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                    }
                                                            }
                                                            .addOnFailureListener {
                                                                Toast.makeText(
                                                                    context,
                                                                    "Error al guardar el índice del DNI",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                    }
                                                    .addOnFailureListener {
                                                        Toast.makeText(
                                                            context,
                                                            "Error al guardar los datos del usuario",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                            }
                                        } else {
                                            // Error al crear el usuario
                                            Toast.makeText(
                                                context,
                                                "Error al registrar: ${task.exception?.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Error al consultar DNI", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    // Si hay algún campo vacío o términos no aceptados
                    Toast.makeText(context, "Completa todos los campos y acepta los términos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.redBackground))
        ) {
            Text(text = "Registrarse", color = Color.White)
        }
    }
}}}


