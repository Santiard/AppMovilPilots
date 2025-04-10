package com.example.woof

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.NewPilotInfo
import com.example.woof.data.Pilot
import com.example.woof.data.createPilot
import com.example.woof.data.PilotViewModel
import com.example.woof.ui.theme.WoofTheme

class AddPilotActivity : ComponentActivity() {

    private val pilotViewModel: PilotViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddPilotScreen(pilotViewModel)
                }
            }
        }
    }
}




@Composable
fun AddPilotScreen(viewModel: PilotViewModel, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var equipo by remember { mutableStateOf("") }
    var botonPresionado by remember { mutableStateOf(false)}

    Scaffold(
        topBar = {
            PilotTopAppBar(

            )

        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(stringResource(R.string.namenew)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = equipo,
                onValueChange = { equipo = it },
                label = { Text(stringResource(R.string.teamnew)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (nombre.isNotBlank() && equipo.isNotBlank()) {
                        val newPilotInfo = NewPilotInfo(name = nombre, team = equipo)
                        viewModel.addPilot(newPilotInfo, contexto)
                        Toast.makeText(contexto, "Piloto agregado", Toast.LENGTH_SHORT).show()

                        (contexto as? ComponentActivity)?.finish()
                    } else {
                        Toast.makeText(contexto, "El nombre y el equipo no pueden estar vacíos", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (botonPresionado) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.addPilot))
            }
        }
    }
}








