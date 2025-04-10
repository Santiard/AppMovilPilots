package com.example.woof.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.woof.R


class PilotViewModel : ViewModel() {
    private val _pilots = MutableStateFlow<List<Pilot>>(emptyList())
    val pilots: StateFlow<List<Pilot>> = _pilots.asStateFlow()

    init {
        _pilots.value = listOf(
            Pilot(R.drawable.max, R.string.pilot_name1, R.string.redbull),
            Pilot(R.drawable.yuki, R.string.pilot_name2, R.string.redbull),
            Pilot(R.drawable.lewis, R.string.pilot_name3, R.string.ferrari),
            Pilot(R.drawable.charles, R.string.pilot_name4, R.string.ferrari),
            Pilot(R.drawable.lando, R.string.pilot_name5, R.string.mclaren),
            Pilot(R.drawable.oscar, R.string.pilot_name6, R.string.mclaren),
            Pilot(R.drawable.george, R.string.pilot_name7, R.string.mercedes),
            Pilot(R.drawable.kimi, R.string.pilot_name8, R.string.mercedes),
            Pilot(R.drawable.fernando, R.string.pilot_name9, R.string.astonmartin),
            Pilot(R.drawable.lance, R.string.pilot_name10, R.string.astonmartin),
            Pilot(R.drawable.liam, R.string.pilot_name11, R.string.racingbulls),
            Pilot(R.drawable.isack, R.string.pilot_name12, R.string.racingbulls),
            Pilot(R.drawable.oliver, R.string.pilot_name13, R.string.haas),
            Pilot(R.drawable.esteban, R.string.pilot_name14, R.string.haas),
            Pilot(R.drawable.alex, R.string.pilot_name15, R.string.williams),
            Pilot(R.drawable.carlos, R.string.pilot_name16, R.string.williams),
            Pilot(R.drawable.pierre, R.string.pilot_name17, R.string.alpine),
            Pilot(R.drawable.jack, R.string.pilot_name18, R.string.alpine),
            Pilot(R.drawable.nico, R.string.pilot_name19, R.string.sauber),
            Pilot(R.drawable.gabriel, R.string.pilot_name20, R.string.sauber)
        )
    }

    fun addPilot(newPilotInfo: NewPilotInfo, context: Context) {
        Log.d("PilotViewModel", "addPilot() llamado con name: ${newPilotInfo.name}, team: ${newPilotInfo.team}")
        Log.d("PilotViewModel", "Tamaño de la lista antes de agregar: ${_pilots.value.size}")
        val newPilot = Pilot(
            imageResourceId = R.drawable.jorge,
            name = newPilotInfo.name,
            team = newPilotInfo.team
        )
        Log.d("PilotViewModel", "Nuevo piloto creado: name=${newPilot.name}, team=${newPilot.team}")
        _pilots.value = _pilots.value + newPilot
        Log.d("PilotViewModel", "Tamaño de la lista después de agregar: ${_pilots.value.size}")
    }
}