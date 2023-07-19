package aigoo.store.chat.ui.view.splash

import aigoo.store.chat.ui.data.remote.local.PreferenceImpl
import aigoo.store.chat.ui.repository.interfaces.IPreference
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferences: PreferenceImpl
): ViewModel() {

    fun isAccessToken(): Boolean {
        return sharedPreferences.getAccessToken() != ""
    }
}