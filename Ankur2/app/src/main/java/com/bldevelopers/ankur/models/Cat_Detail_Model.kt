package com.bldevelopers.ankur.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "visited")
data class Cat_Detail_Model(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @SerializedName("No ") val No: Int = 0,
    val ACTIVITY: String,
    val Link: String,
    val Language: String
)
