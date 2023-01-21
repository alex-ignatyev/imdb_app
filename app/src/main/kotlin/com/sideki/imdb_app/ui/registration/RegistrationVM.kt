package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.MutableLiveData

class RegistrationVM (

) {
    val firstName = MutableLiveData<String>()
    val login = MutableLiveData<String>()
    val password = MutableLiveData<String>()
}
