package com.example.soyaragon.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soyaragon.BottomNavigationBar
import com.example.soyaragon.Domain.ShopItemModel
import com.example.soyaragon.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewProfileScreen(onItemClick = { item ->
                val intent = Intent(this, DetailMovieActivity::class.java)
                intent.putExtra("object", item)
                startActivity(intent)
            })
        }
    }
}

@Composable
fun ViewProfileScreen(onItemClick: (ShopItemModel) -> Unit = {}) {
    androidx.compose.material.Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.redBackground),
                                colorResource(R.color.redBackground)
                            )
                        ),
                        shape = CircleShape
                    )
                    .padding(3.dp)
            ) {
                val context = LocalContext.current

                androidx.compose.material.FloatingActionButton(
                    onClick = {
                        (context as? Activity)?.finish()
                        Toast.makeText(context, "Cupones", Toast.LENGTH_SHORT).show()
                        context.startActivity(Intent(context, CouponsActivity::class.java))
                    },
                    backgroundColor = colorResource(id = R.color.black3),
                    modifier = Modifier.size(58.dp),
                    contentColor = Color.White,
                    content = {
                        androidx.compose.material.Icon(
                            painter = painterResource(R.drawable.cupones128),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.blackBackground)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = colorResource(R.color.blackBackground))
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            ViewProfileScreenContent(onItemClick)
        }
    }
}

@Composable
fun ViewProfileScreenContent(onItemClick: (ShopItemModel) -> Unit) {
    val user = FirebaseAuth.getInstance().currentUser
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }

    // Cargamos datos desde Firebase
    LaunchedEffect(user?.uid) {
        user?.uid?.let { uid ->
            val ref = FirebaseDatabase.getInstance().getReference("usuarios").child(uid)
            ref.get().addOnSuccessListener {
                nombre = it.child("nombre").value?.toString() ?: ""
                apellidos = it.child("apellidos").value?.toString() ?: ""
                dni = it.child("dni").value?.toString() ?: ""
                email = it.child("email").value?.toString() ?: user.email ?: ""
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)
    ) {
        Text(
            // Título
            text = "Ver Perfil",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray1Background),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            // Columna con separación entre textos para mostrar la informacion del usuario
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Mis Datos", style = MaterialTheme.typography.headlineSmall)

                // Mostramos campos con datos cargados
                Text("Nombre: $nombre")
                Text("Apellidos: $apellidos")
                Text("DNI: $dni")
                Text("Correo: $email")
            }
        }
    }
}
