package dev.fabled.home_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabled.domain.repository.home.GetUserName
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserName: GetUserName
) : ViewModel() {

    var userName by mutableStateOf(value = "")
        private set

    init {
        userName = getUserName()
    }

}