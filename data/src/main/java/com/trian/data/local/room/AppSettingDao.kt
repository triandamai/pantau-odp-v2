package com.trian.data.local.room

import androidx.room.*
import com.trian.data.entities.AppSetting
import kotlinx.coroutines.flow.Flow

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface AppSettingDao {

    @Query("SELECT * FROM tb_app_setting WHERE id =1")
    fun getCurrentSetting():Flow<AppSetting?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSetting(appSetting:AppSetting): Long

    @Update
    fun updateSetting(appSetting:AppSetting)

    @Delete
    fun deleteSetting(pappSetting:AppSetting)

}