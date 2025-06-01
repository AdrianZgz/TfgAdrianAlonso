package com.example.soyaragon

import android.app.Activity
import android.widget.Toast
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soyaragon.Activity.CampaignsActivity
import com.example.soyaragon.Activity.LoginActivity
import com.example.soyaragon.Activity.MainActivity
import com.example.soyaragon.Activity.ProfileActivity
import com.example.soyaragon.Activity.ShopsActivity

@Preview
/*@Composable
fun BottomNavigationBar(){
    val bottomMenuItemsList= prepareBottomMenu()
    val contextForToast= LocalContext.current.applicationContext
    var selectedItem by remember {
        mutableStateOf("Home")
    }

    BottomAppBar(
        cutoutShape = CircleShape,
        contentColor = colorResource(id=R.color.white),
        backgroundColor = colorResource(id=R.color.black3),
        elevation = 3.dp
    ) {
        bottomMenuItemsList.forEachIndexed{ index, bottomMenuItem ->

            if(index==2){
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    enabled = false
                )
            }
            BottomNavigationItem(
                selected = (selectedItem==bottomMenuItem.label),
                onClick = {
                    selectedItem=bottomMenuItem.label
                    Toast.makeText(contextForToast,bottomMenuItem.label,Toast.LENGTH_SHORT).show()
                }, icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = bottomMenuItem.label,
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )
                }, label = {
                    Text(
                        text = bottomMenuItem.label,
                        modifier = Modifier.padding(top=14.dp)
                    )
                }, alwaysShowLabel = true,
                enabled = true
            )
        }
    }
}

data class BottomMenuItem(
    val label:String, val icon:Painter
)

@Composable
fun prepareBottomMenu():List<BottomMenuItem>{
    return listOf(
        BottomMenuItem(
            label= "Home",
            icon = painterResource(id=R.drawable.btn_1)
        ),
        BottomMenuItem(
            label= "Profile",
            icon = painterResource(id=R.drawable.btn_2)
        ),
        BottomMenuItem(
            label= "Support",
            icon = painterResource(id=R.drawable.btn_3)
        ),
        BottomMenuItem(
            label= "Settings",
            icon = painterResource(id=R.drawable.btn_4)
        )
    )
}*/

//////////////
//MODIFICADO//
//////////////

@Composable
fun BottomNavigationBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    val contextForToast = LocalContext.current.applicationContext
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        modifier = Modifier
            .height(100.dp) // Define la altura
            .navigationBarsPadding(), // Evita solaparse con los botones del sistema
        cutoutShape = CircleShape,
        contentColor = colorResource(id = R.color.white),
        backgroundColor = colorResource(id = R.color.black3),
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top // Alinea los elementos en la parte superior
        ) {
            bottomMenuItemsList.forEachIndexed { index, bottomMenuItem ->
                if (index == 2) {
                    BottomNavigationItem(
                        selected = false,
                        onClick = {},
                        icon = {},
                        enabled = false
                    )
                }
                BottomNavigationItem(
                    selected = (selectedItem == bottomMenuItem.label),
                    onClick = {
                        //Elimina la actividad
                        (context as? Activity)?.finish()
                        selectedItem = bottomMenuItem.label
                        Toast.makeText(contextForToast, bottomMenuItem.label, Toast.LENGTH_SHORT).show()
                        context.startActivity(Intent(context, bottomMenuItem.destination))
                    },
                    icon = {
                        Icon(
                            painter = bottomMenuItem.icon,
                            contentDescription = bottomMenuItem.label,
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    },
                    label = {
                        BoxWithConstraints {
                        val textSize = if (maxWidth < 50.dp) 5.sp else 10.sp
                        Text(
                            text = bottomMenuItem.label,
                            modifier = Modifier.padding(top = 4.dp), // Reduce el espacio para que quede más arriba
                            maxLines = 1, // Mantiene una sola línea
                            softWrap = false, // Evita que se divida en varias líneas
                            fontSize = textSize
                            //overflow = TextOverflow.Ellipsis
                        )
                    }
                            },
                    alwaysShowLabel = true,
                    enabled = true
                )

            }
        }
    }
}

data class BottomMenuItem(
    val label: String, val icon: Painter, val destination: Class<*> // Agregamos la actividad de destino
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem(
            label = "Inicio",
            icon = painterResource(id = R.drawable.casa),
            destination = MainActivity::class.java
        ),
        BottomMenuItem(
            label = "Campañas",
            icon = painterResource(id = R.drawable.campagnas),
            destination = CampaignsActivity::class.java
        ),
        BottomMenuItem(
            label = "Comercios",
            icon = painterResource(id = R.drawable.btn_3),
            destination = ShopsActivity::class.java
        ),
        BottomMenuItem(
            label = "Perfil",
            icon = painterResource(id = R.drawable.btn_4),
            destination = ProfileActivity::class.java
        )
    )
}
