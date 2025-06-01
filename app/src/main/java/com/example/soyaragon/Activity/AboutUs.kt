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

// Actividad que muestra la pantalla "Sobre Nosotros"
class AboutUs : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewAboutScreen(onItemClick = { item ->
                val intent = Intent(this, DetailMovieActivity::class.java)
                intent.putExtra("object", item)
                startActivity(intent)
            })
        }
    }
}

// Composable principal que estructura la pantalla "Sobre Nosotros"
@Composable
fun ViewAboutScreen(onItemClick: (ShopItemModel) -> Unit = {}) {
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
            ViewAboutScreenContent(onItemClick)
        }
    }
}

@Composable
fun ViewAboutScreenContent(onItemClick: (ShopItemModel) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.redBackground))
            .padding(top = 60.dp, bottom = 0.dp)
    ) {
        Text(
            //Título Principal
            text = "Sobre Nosotros",
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
                //Subtítulo
                Text("Proyecto", style = MaterialTheme.typography.headlineSmall)

                // Texto descriptivo del proyecto SoyAragón
                Text("En SoyAragón creemos en el poder de lo local. Nacimos con una idea muy clara: apoyar al comercio de proximidad, dar visibilidad a los negocios de nuestra tierra y conectar a las personas con todo lo que hace única a Aragón.\n" +
                        "\n" +
                        "Queremos que descubras nuevas tiendas, bares, restaurantes, alojamientos y experiencias cerca de ti. Que aproveches promociones exclusivas, participes en campañas especiales y disfrutes más siendo parte de una comunidad que valora lo auténtico.\n" +
                        "\n" +
                        "Cada día trabajamos para que esta app te acerque más a lo que tienes al lado, para que comprar aquí tenga más valor, y para que juntos impulsemos el crecimiento de nuestros pueblos y ciudades.\n" +
                        "\n" +
                        "Porque ser de aquí es mucho más que una dirección: es una forma de vivir.\n" +
                        "\n" +
                        "Bienvenid@ a SoyAragón.\n" +
                        "\n")
            }
        }
    }
}