package com.example.airfiindia.roomdb


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "airline_db"
        ).build()
    }

    @Provides
    fun provideFavoriteDao(db: AppDatabase): FavoriteAirlineDao {
        return db.favoriteAirlineDao()
    }
}
