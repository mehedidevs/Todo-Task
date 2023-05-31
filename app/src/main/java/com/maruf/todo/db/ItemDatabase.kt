package com.maruf.todo.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maruf.todo.data.local.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): TodoDao

    companion object {
        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                    "todo_database"
                ).build()
                return INSTANCE as ItemDatabase
            }else{
                return INSTANCE as ItemDatabase
            }
        }
    }
}
/*@Provides
@Singleton
fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "RssReader"
    ).build()
}*/