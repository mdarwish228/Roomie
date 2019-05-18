package com.darwish.roomie.presentation.common.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darwish.roomie.data.common.FireStoreResponse
import com.darwish.roomie.domain.user.UserInfoUseCases
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class UserInfoViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private var user: MutableLiveData<FireStoreResponse<DocumentSnapshot>> = MutableLiveData()

    fun getUser(userId: String): MutableLiveData<FireStoreResponse<DocumentSnapshot>> {
        if(user.value == null) {
            loadUser(userId)
        }

        return user
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun loadUser(userId: String) {
        ioScope.launch {
            user.postValue(UserInfoUseCases.getUserInformation(userId))
        }
    }
}