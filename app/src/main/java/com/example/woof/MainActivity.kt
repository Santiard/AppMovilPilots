/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.woof

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.woof.data.Pilot
import com.example.woof.ui.theme.WoofTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.woof.data.PilotViewModel

class MainActivity : ComponentActivity() {

    private val pilotViewModel: PilotViewModel by viewModels()
    private val addPilotLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // No necesitamos un resultado específico aquí, la adición se maneja en el ViewModel
        // El simple hecho de volver a la actividad hará que se observe de nuevo el StateFlow
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PilotApp(pilotViewModel,addPilotLauncher)
                }
            }
        }
    }
}






@Composable
fun PilotApp(viewModel: PilotViewModel, addPilotLauncher: ActivityResultLauncher<Intent>) {
    val context = LocalContext.current
    val pilots: List<Pilot> by viewModel.pilots.collectAsState()
    Log.d("PilotApp", "Lista de pilotos en MainActivity actualizada. Tamaño: ${pilots.size}")


    WoofTheme() {
        Scaffold(
            topBar = { PilotTopAppBar() }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(pilots) {
                        PilotItem(pilot = it, modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                    }
                }
                Button(
                    onClick = {
                        val intent = Intent(context, AddPilotActivity::class.java)
                        addPilotLauncher.launch(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Add new pilot")
                }
            }
        }
    }
}



@Composable
fun PilotItem(
    pilot : Pilot,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            PilotIcon(pilot.imageResourceId)
            PilotInformation(pilot.name, pilot.team)

        }
    }
}

@Composable
fun PilotTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size)),
                    painter = painterResource(R.drawable.f1),
                    contentDescription = "Listado de Pilotos"
                )
            }
        },

        modifier = modifier
    )
}



@Composable
fun PilotIcon(
    @DrawableRes pilotIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(pilotIcon),
        contentDescription = null
    )
}


@Composable
fun PilotInformation(
    name: Any,
    team: Any,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = when (name) {
                is Int -> stringResource(name)
                is String -> name
                else -> ""
            },
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.team,
                when (team) {
                    is Int -> stringResource(team)
                    is String -> team
                    else -> ""
                }
            ),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


