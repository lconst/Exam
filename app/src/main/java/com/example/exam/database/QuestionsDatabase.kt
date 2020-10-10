package com.example.exam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class QuestionsDatabase : RoomDatabase() {

    abstract val questionsDatabaseDao: QuestionsDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: QuestionsDatabase? = null

        fun getInstance(context: Context): QuestionsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuestionsDatabase::class.java, "Exam1.db"
                    )
                        .createFromAsset("Exam1.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}