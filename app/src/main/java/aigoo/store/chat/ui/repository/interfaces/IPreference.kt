package aigoo.store.chat.ui.repository.interfaces

import aigoo.store.chat.ui.data.remote.local.PreferenceImpl

interface IPreference {
    fun getAccessToken(): String
    fun setAccessToken(token: String)
    fun getRefreshToken(): String
    fun setRefreshToken(token: String)
    fun remove(key: String)
    fun clear()

    fun <T> getCountersOption(key: T): Any?
    fun <T> setCountersOption(key: String = PreferenceImpl.COUNTERS_OPTION, value: T)
}