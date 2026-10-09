package com.example.potafolio_personal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.annotation.DrawableRes
import com.example.potafolio_personal.ui.theme.Potafolio_PersonalTheme

private const val FULL_NAME = "Isaac"
private const val ROLE = "Desarrollador Movil"
private const val LOCATION = "Mexico"
private const val PHONE_DISPLAY = "+52 466 202 1475"
private const val PHONE_URI = "+524662021475"
private const val EMAIL = "isaacortizarias@outlook.com"
private const val INSTAGRAM_URL = "https://www.instagram.com/ortiz__117"
private const val LINKEDIN_URL = "www.linkedin.com/in/isaac-ortiz-arias-8b8005315"
private const val GITHUB_URL = "https://github.com/ortiz117"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Potafolio_PersonalTheme {
                PortfolioApp()
            }
        }
    }
}

@Composable
private fun PortfolioApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        PortfolioScreen(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
private fun PortfolioScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFFF4F0E8)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF102A2A),
                            Color(0xFF1F4A45),
                            Color(0xFFF4F0E8)
                        )
                    )
                )
                .padding(horizontal = 22.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeroSection()
            Spacer(modifier = Modifier.height(18.dp))

            ActionRow(
                onCall = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$PHONE_URI"))
                    context.startActivity(intent)
                },
                onEmail = {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$EMAIL"))
                    context.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.height(18.dp))
            AboutSection()
            Spacer(modifier = Modifier.height(14.dp))
            SkillsSection()
            Spacer(modifier = Modifier.height(14.dp))
            ProjectsSection()
            Spacer(modifier = Modifier.height(14.dp))
            SocialSection(
                onOpenInstagram = { context.openUrl(INSTAGRAM_URL) },
                onOpenLinkedIn = { context.openUrl(LINKEDIN_URL) },
                onOpenGitHub = { context.openUrl(GITHUB_URL) }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun HeroSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_photo),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(220.dp)
                .clip(CircleShape)
                .border(5.dp, Color(0xFFE8C16B), CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = FULL_NAME,
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = ROLE,
            color = Color(0xFFE8C16B),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$LOCATION | Kotlin | Android | UI/UX",
            color = Color(0xFFE7F0EC),
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ActionRow(
    onCall: () -> Unit,
    onEmail: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = onCall,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8C16B)),
            contentPadding = PaddingValues(vertical = 13.dp)
        ) {
            Text(
                text = "Llamar",
                color = Color(0xFF102A2A),
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = onEmail,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            contentPadding = PaddingValues(vertical = 13.dp)
        ) {
            Text(
                text = "Email",
                color = Color(0xFF102A2A),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun AboutSection() {
    SectionCard(title = "Sobre mi") {
        Text(
            text = "Soy un desarrollador movil enfocado en crear aplicaciones Android claras, rapidas y agradables de usar. Me gusta convertir ideas en productos funcionales, cuidar los detalles visuales y escribir codigo que pueda crecer.",
            color = Color(0xFF233332),
            fontSize = 16.sp,
            lineHeight = 23.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        ContactLine(label = "Telefono", value = PHONE_DISPLAY)
        ContactLine(label = "Correo", value = EMAIL)
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun SkillsSection() {
    val skills = listOf(
        "Kotlin",
        "Jetpack Compose",
        "Android Studio",
        "Firebase",
        "REST APIs",
        "Git/GitHub",
        "Material Design",
        "UX Movil"
    )

    SectionCard(title = "Habilidades") {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE9F2EF))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = skill,
                        color = Color(0xFF173A38),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectsSection() {
    SectionCard(title = "Proyectos") {
        ProjectItem(
            title = "App de portafolio personal",
            description = "Presentacion profesional con contacto directo, redes sociales y una interfaz moderna en Compose."
        )
        ProjectItem(
            title = "Aplicacion de tareas",
            description = "Gestion de actividades con estados, prioridad y una experiencia pensada para el uso diario."
        )
        ProjectItem(
            title = "Catalogo movil",
            description = "Listado de productos con busqueda, detalles y navegacion optimizada para pantallas pequenas."
        )
    }
}

@Composable
private fun SocialSection(
    onOpenInstagram: () -> Unit,
    onOpenLinkedIn: () -> Unit,
    onOpenGitHub: () -> Unit
) {
    SectionCard(title = "Redes y plataformas") {
        SocialButton(
            label = "Instagram",
            icon = R.drawable.ic_instagram,
            onClick = onOpenInstagram
        )
        SocialButton(
            label = "LinkedIn",
            icon = R.drawable.ic_linkedin,
            onClick = onOpenLinkedIn
        )
        SocialButton(
            label = "GitHub",
            icon = R.drawable.ic_github,
            onClick = onOpenGitHub
        )
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(
                text = title,
                color = Color(0xFF102A2A),
                fontSize = 21.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun ContactLine(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color(0xFF62706D),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = value,
            color = Color(0xFF102A2A),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun ProjectItem(title: String, description: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = title,
            color = Color(0xFF173A38),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = description,
            color = Color(0xFF4A5A57),
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun SocialButton(
    label: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 9.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF173A38)),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(21.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = label,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun android.content.Context.openUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

@Preview(showBackground = true)
@Composable
private fun PortfolioPreview() {
    Potafolio_PersonalTheme {
        PortfolioApp()
    }
}
