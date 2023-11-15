package com.example.imdb_app.ui.actor_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb_app.domain.use_case.GetActorInfoUseCase
import com.example.imdb_app.domain.model.ActorInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ActorInfoVM @Inject constructor(
    private val getActorInfoUseCase: GetActorInfoUseCase
) : ViewModel() {

    val actorInfo = MutableStateFlow(ActorInfoModel())

    fun getActorInfo(actorId: String) {
        viewModelScope.launch {
            actorInfo.value = getActorInfoUseCase.getActorInfo(actorId)
        }
    }
}
