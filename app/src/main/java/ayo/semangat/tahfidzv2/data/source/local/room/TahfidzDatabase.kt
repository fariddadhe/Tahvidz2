package ayo.semangat.tahfidzv2.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ayo.semangat.tahfidzv2.data.source.remote.response.*

@Database(entities = [ChapterEntity::class, VersesEntity::class], version = 2, exportSchema = false)
@TypeConverters(VerseConverter::class, RevelationConverter::class, NameConverter::class)
abstract class TahfidzDatabase : RoomDatabase(){
    abstract fun tahfidzDao(): TahfidzDao

    companion object{

        @Volatile
        private var INSTANCE: TahfidzDatabase? = null

        fun getInstance(context: Context): TahfidzDatabase{
            if (INSTANCE == null){
                synchronized(TahfidzDatabase::class.java){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                TahfidzDatabase::class.java, "Tahfidz.db")
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE as TahfidzDatabase
        }
    }
}