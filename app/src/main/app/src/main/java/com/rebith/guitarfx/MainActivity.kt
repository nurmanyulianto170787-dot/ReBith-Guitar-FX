package com.rebith.guitarfx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF0B1020)
private val Panel = Color(0xFF151D30)
private val ElectricBlue = Color(0xFF3987FF)
private val Orange = Color(0xFFFF9638)
private val SoftText = Color(0xFFADB9D0)

class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContent {
MaterialTheme(
colorScheme = darkColorScheme(
primary = ElectricBlue,
secondary = Orange,
background = Navy,
surface = Panel
)
) {
ReBithApp()
}
}
}
}

@Composable
private fun ReBithApp() {
var selectedTab by remember { mutableStateOf(0) }
var selectedPreset by remember { mutableStateOf("Clean & Warm") }

val presets = listOf(
    "Clean & Warm",
    "Blues Drive",
    "Classic Rock",
    "Modern High Gain",
    "Ambient Space"
)

Surface(
    modifier = Modifier.fillMaxSize(),
    color = Navy
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                "REBITH",
                color = ElectricBlue,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )
            Text(
                "Guitar FX",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Your Guitar. Your Sound. Your Studio.",
                color = SoftText,
                fontSize = 13.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf("Pedalboard", "Amp Studio").forEachIndexed { index, title ->
                Button(
                    onClick = { selectedTab = index },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == index)
                            ElectricBlue else Panel
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(title, fontSize = 12.sp)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Panel, RoundedCornerShape(18.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                if (selectedTab == 0) "SIGNAL CHAIN" else "AMP CONTROL",
                color = SoftText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )

            Text(
                if (selectedTab == 0) "Shape your tone" else "Build your sound",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Audio engine not connected yet",
                color = Orange,
                fontSize = 12.sp
            )
        }

        Text(
            "YOUR PRESET",
            color = SoftText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        presets.forEach { preset ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Panel, RoundedCornerShape(12.dp))
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        preset,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        if (preset == selectedPreset) "Selected preset"
                        else "Guitar tone preset",
                        color = SoftText,
                        fontSize = 12.sp
                    )
                }
                RadioButton(
                    selected = preset == selectedPreset,
                    onClick = { selectedPreset = preset },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = ElectricBlue
                    )
                )
            }
        }

        Text(
            "EFFECTS",
            color = SoftText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        listOf(
            "Noise Gate",
            "Drive",
            "Amp & Cab",
            "Equalizer",
            "Delay",
            "Reverb"
        ).forEach { effect ->
            var enabled by remember { mutableStateOf(true) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Panel, RoundedCornerShape(12.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    effect,
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Medium
                )
                Switch(
                    checked = enabled,
                    onCheckedChange = { enabled = it }
                )
            }
        }

        Text(
            "V1 • OFFLINE FIRST • ANDROID",
            color = SoftText,
            fontSize = 11.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp)
        )
    }
}

}
