package com.bldevelopers.ankur.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bldevelopers.ankur.models.Cat_Detail_Model


@Database(entities = [Cat_Detail_Model::class], version = 1)
abstract class VisitedDatabase:RoomDatabase() {
    abstract fun visitedDao():visitedDAO
}