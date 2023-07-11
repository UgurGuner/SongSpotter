package com.eugurguner.songspotter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.eugurguner.songspotter.data.data_source.SongDatabase
import com.eugurguner.songspotter.data.repository.SongRepositoryImpl
import com.eugurguner.songspotter.domain.repository.SongRepository
import com.eugurguner.songspotter.domain.use_case.GetSongsUseCase
import com.eugurguner.songspotter.domain.use_case.SongUseCases
import com.eugurguner.songspotter.domain.util.VolleySingleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): SongDatabase {

        return Room.databaseBuilder(
            context = app,
            klass = SongDatabase::class.java,
            name = SongDatabase.DATABASE_NAME
        ).build()

    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: SongDatabase): SongRepository {
        return SongRepositoryImpl(songDao = db.songDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(songRepository: SongRepository): SongUseCases {
        return SongUseCases(
            getSongsUseCase = GetSongsUseCase(songRepository = songRepository),
        )
    }

    @Provides
    @Singleton
    fun provideVolleySingleton(): VolleySingleton {
        return VolleySingleton.getInstance()
    }

}