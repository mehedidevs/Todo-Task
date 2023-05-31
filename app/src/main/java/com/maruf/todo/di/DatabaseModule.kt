package com.maruf.todo.di

import android.content.Context
import com.maruf.todo.db.TodoDao
import com.maruf.todo.db.ItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ItemDatabase {
        return ItemDatabase.getInstance(context)

    }

    @Provides
    @Singleton
    fun provideItemDao(itemDatabase: ItemDatabase): TodoDao {
        return itemDatabase.itemDao()
    }


}