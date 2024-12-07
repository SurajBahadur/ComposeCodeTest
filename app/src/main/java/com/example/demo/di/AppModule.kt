package com.example.demo.di

import android.content.Context
import androidx.room.Room
import com.example.demo.data.api.NetworkService
import com.example.demo.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an implementation of FakeStoreService using Retrofit.
     *
     * @param retrofit The Retrofit instance used to create the service.
     * @return An implementation of FakeStoreService.
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }


    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = AppDatabase::class.java,
            name = "my_app.db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}