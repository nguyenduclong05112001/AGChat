package aigoo.store.chat.ui.data.remote.local

import aigoo.store.chat.ui.repository.interfaces.IPreference
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceImpl
@Inject constructor(@ApplicationContext val context: Context)
{
    private var sharedPreference: SharedPreferences
    private var editor: Editor
    companion object {
        const val APP_SP_NAME = "RECEPTIONIST"
        const val MASTER_KEY_ALIAS = MasterKey.DEFAULT_MASTER_KEY_ALIAS
        const val MASTER_KEY_SIZE = MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE

        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val COUNTERS_OPTION = "COUNTERS_OPTION"
    }

    init {
        val params = createKeyGenParameterSpec()
        val masterKey = createMasterKey(params)
        sharedPreference = createSharePreference(masterKey)
        editor = sharedPreference.edit()
    }

    private fun createSharePreference(
        masterKey: MasterKey
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            APP_SP_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun <T> getCountersOption(key: T): Any? {
        return when(key) {
            is Int -> {
                sharedPreference.getInt(key.toString(), -1)
            }
            is Long -> {
                sharedPreference.getLong(key.toString(), -1)
            }
            is Float -> {
                sharedPreference.getFloat(key.toString(), -1f)
            }
            is String -> {
                sharedPreference.getString(key.toString(), "")
            }
            else -> {
                sharedPreference.getBoolean(key.toString(), false)
            }
        }
    }

    fun <T> setCountersOption(key: String, value: T) {
        when(value) {
            is Int -> {
                editor.putInt(key, value).apply()
            }
            is Long -> {
                editor.putLong(key, value).apply()
            }
            is Float -> {
                editor.putFloat(key, value).apply()
            }
            is String -> {
                editor.putString(key, value).apply()
            }
            else -> {
                editor.putBoolean(key, value as Boolean).apply()
            }
        }
    }

    private fun createKeyGenParameterSpec(): KeyGenParameterSpec {
        return KeyGenParameterSpec.Builder(
            MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(MASTER_KEY_SIZE)
            .build()
    }

    private fun createMasterKey(spec: KeyGenParameterSpec): MasterKey {
        return MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
    }

     fun getAccessToken(): String {
        return sharedPreference.getString(ACCESS_TOKEN, "") ?: ""
    }

     fun setAccessToken(token: String) {
        editor.putString(ACCESS_TOKEN, token).apply()
    }

     fun getRefreshToken(): String {
        return sharedPreference.getString(REFRESH_TOKEN, "") ?: ""
    }

     fun setRefreshToken(token: String) {
        editor.putString(REFRESH_TOKEN, token).apply()
    }

     fun remove(key: String) {
        editor.remove(key).apply()
    }

     fun clear() {
        editor.clear().apply()
    }
}