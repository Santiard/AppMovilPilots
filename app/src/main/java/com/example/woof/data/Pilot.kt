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
package com.example.woof.data

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.woof.R
import androidx.compose.runtime.mutableStateListOf



data class  Pilot (

    @DrawableRes val imageResourceId: Int,
    val name: Any,
    val team: Any


)



fun getIdResource (resourceName: String, context: Context): Int {
    return try {

        context.resources.getIdentifier(resourceName, "string", context.packageName)
    } catch (e: Exception) {
        // Si ocurre un error, retornar 0 (indicando que no se encontró el recurso)
        e.printStackTrace()
        0
    }
}

fun createPilot(name: String, team: String, context: Context): Pilot {

    if (name.isBlank() || team.isBlank()) {
        Toast.makeText(context, "Name and team must not be empty", Toast.LENGTH_SHORT).show()
        return Pilot(R.drawable.jorge, R.string.default_name, R.string.default_team)
    }

    val nameId = getIdResource(name, context)
    val teamId = getIdResource(team, context)

    if (nameId == 0 || teamId == 0) {
        Toast.makeText(context, "Invalid resource IDs for name or team", Toast.LENGTH_SHORT).show()
        return Pilot(R.drawable.jorge, R.string.default_name, R.string.default_team)
    }

    return Pilot(R.drawable.jorge, nameId, teamId)
}





