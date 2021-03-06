package com.example.exam.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionsDatabaseDao {

    @Query("SELECT * FROM Questions WHERE Number = :key")
    fun get(key: Int): Question?
}