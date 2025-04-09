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
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.woof.R



data class  Pilot (

    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val team: Int,


)

fun getIdResource (resourceName: String, context: Context): Int {
    return context.resources.getIdentifier(resourceName, "string", context.packageName)
}

fun createPilot (name: String, team: String, context: Context ): Pilot {
    val nameId = getIdResource(name, context)
    val teamId = getIdResource(team, context)

    val newPilot = Pilot(R.drawable.jorge, nameId ,teamId )
    return newPilot
}

fun addPilot (pilot : Pilot, pilots : MutableList<Pilot>){

    pilots.add(pilot);

}

var pilots = mutableListOf(

    Pilot(R.drawable.max,R.string.pilot_name1,R.string.redbull),
    Pilot(R.drawable.yuki,R.string.pilot_name2,R.string.redbull),
    Pilot(R.drawable.lewis,R.string.pilot_name3,R.string.ferrari),
    Pilot(R.drawable.charles,R.string.pilot_name4,R.string.ferrari),
    Pilot(R.drawable.lando,R.string.pilot_name5,R.string.mclaren),
    Pilot(R.drawable.oscar,R.string.pilot_name6,R.string.mclaren),
    Pilot(R.drawable.george,R.string.pilot_name7,R.string.mercedes),
    Pilot(R.drawable.kimi,R.string.pilot_name8,R.string.mercedes),
    Pilot(R.drawable.fernando,R.string.pilot_name9,R.string.astonmartin),
    Pilot(R.drawable.lance,R.string.pilot_name10,R.string.astonmartin),
    Pilot(R.drawable.liam,R.string.pilot_name11,R.string.racingbulls),
    Pilot(R.drawable.isack,R.string.pilot_name12,R.string.racingbulls),
    Pilot(R.drawable.oliver,R.string.pilot_name13,R.string.haas),
    Pilot(R.drawable.esteban,R.string.pilot_name14,R.string.haas),
    Pilot(R.drawable.alex,R.string.pilot_name15,R.string.williams),
    Pilot(R.drawable.carlos,R.string.pilot_name16,R.string.williams),
    Pilot(R.drawable.pierre,R.string.pilot_name17,R.string.alpine),
    Pilot(R.drawable.jack,R.string.pilot_name18,R.string.alpine),
    Pilot(R.drawable.nico,R.string.pilot_name19,R.string.sauber),
    Pilot(R.drawable.gabriel,R.string.pilot_name20,R.string.sauber)
)


