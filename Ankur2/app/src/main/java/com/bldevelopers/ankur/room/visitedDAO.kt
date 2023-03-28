package com.bldevelopers.ankur.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bldevelopers.ankur.models.Cat_Detail_Model

@Dao
interface visitedDAO {

    @Insert
    suspend fun insertVisitedVideo(catDetailModel: Cat_Detail_Model)

    @Query(" SELECT * FROM visited")
    fun getAllStudent(): LiveData<List<Cat_Detail_Model>>

}