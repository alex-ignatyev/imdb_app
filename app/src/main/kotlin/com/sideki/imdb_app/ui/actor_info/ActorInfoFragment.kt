package com.sideki.imdb_app.ui.actor_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sideki.imdb_app.ui.theme.Imdb_appTheme
import com.sideki.imdb_app.util.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorInfoFragment : Fragment() {

    private val vm by viewModels<ActorInfoVM>()
    private val args by navArgs<ActorInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ) = setContent {
        vm.getActorInfo(args.actorId)
        Imdb_appTheme {
            val actorInfo by vm.actorInfo.collectAsState()
        }
    }
}
