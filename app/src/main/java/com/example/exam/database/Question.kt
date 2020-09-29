package com.example.exam.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Questions")
data class Question (
    @PrimaryKey(autoGenerate = true)
    val questionId: Int = 0,

//    @ColumnInfo(name = "Number")
//    val questionId: Int = 0,

    @ColumnInfo(name = "Question")
    val questionName: String = "",

    @ColumnInfo(name = "Answer")
    val answerId: Int = 0,

    @ColumnInfo(name = "Variant_1")
    val variantOne: String = "",

    @ColumnInfo(name = "Variant_2")
    val variantTwo: String = "",

    @ColumnInfo(name = "Variant_3")
    val variantThree: String = "",

    @ColumnInfo(name = "Variant_4")
    val variantFour: String = "",

    @ColumnInfo(name = "Variant_5")
    val variantFive: String = ""
)