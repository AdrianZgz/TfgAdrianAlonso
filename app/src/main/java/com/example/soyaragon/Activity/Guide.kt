package com.example.soyaragon.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

// Actividad que muestra la pantalla "Guía Rápida"
class Guide : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewGuideScreen(onItemClick = { item ->
                val intent = Intent(this, DetailMovieActivity::class.java)
                intent.putExtra("object", item)
                startActivity(intent)
            })
        }
    }
}

// Composable principal que estructura la pantalla "Guía Rápida"
@Composable
fun ViewGuideScreen(onItemClick: (ShopItemModel) -> Unit = {}) {
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
            GuideScreenContent(onItemClick)
        }
    }
}

@Composable
fun GuideScreenContent(onItemClick: (ShopItemModel) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            //Título Principal
            text = "Guía Rápida",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        // Caja contenedora del texto informativo, con fondo claro y esquinas redondeadas
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
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("1. Crea tu cuenta o inicia sesión", style = MaterialTheme.typography.headlineSmall)

                Text("Solo necesitas tu email y unos segundos. Si ya te has registrado antes, simplemente inicia sesión.")

                Text("2. Explora negocios locales", style = MaterialTheme.typography.headlineSmall)

                Text("Desde la pantalla principal puedes descubrir tiendas, bares, restaurantes, alojamientos y mucho más en Aragón. Usa los filtros para encontrar lo que te interesa cerca de ti.")

                Text("3. Consigue cupones y ofertas", style = MaterialTheme.typography.headlineSmall)

                Text("Muchos negocios tienen descuentos y promociones especiales. Pulsa en el botón (Conseguir cupón), ¡y listo! Algunos cupones tienen límite de uso o fecha, así que no te los pierdas.")

                Text("4. Escanea tu código QR", style = MaterialTheme.typography.headlineSmall)

                Text("Cuando uses un cupón en un negocio, muéstrale tu código QR para que lo escaneen. Cada cupón es único y de un solo uso.")

                Text("5. Participa en campañas", style = MaterialTheme.typography.headlineSmall)

                Text("Durante el año lanzamos campañas como sorteos, premios por comprar en negocios locales o eventos especiales. Entra en la sección “Campañas” y sigue las instrucciones para participar. ¡Sube tu ticket y suma participaciones!")

                Text("6. Consulta tu perfil y tu historial", style = MaterialTheme.typography.headlineSmall)

                Text("Desde tu perfil puedes ver:\n" +
                        "\n" +
                        "Tus cupones activos y usados.\n" +
                        "\n" +
                        "Tus participaciones en campañas.\n" +
                        "\n" +
                        "Tus datos personales y configuración.\n" +
                        "\n")

                Text("7. Activa las notificaciones", style = MaterialTheme.typography.headlineSmall)

                Text("Te avisaremos cuando haya nuevas ofertas, campañas o negocios interesantes cerca de ti.")

                Text("8. Consejo final", style = MaterialTheme.typography.headlineSmall)

                Text("¡Abre la app a menudo! Siempre hay algo nuevo que descubrir en Aragón.")
            }
        }
    }
}