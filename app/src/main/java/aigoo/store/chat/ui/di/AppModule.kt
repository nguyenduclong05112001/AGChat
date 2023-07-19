package aigoo.store.chat.ui.di

import aigoo.store.chat.ui.data.remote.local.PreferenceImpl
import aigoo.store.chat.ui.repository.interfaces.IPreference
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



//    @Singleton
//    @Provides
//    fun providePreference(preference: PreferenceImpl): IPreference = preference

}