package e.yoppie.newengineerblogs.model.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity

@Dao
interface CompanyDao {
    @Insert
    fun insertItem(entity: CompanyEntity)

    @Insert
    fun insertItems(items: MutableList<CompanyEntity>)

    @Query("SELECT * FROM CompanyEntity")
    fun findAll(): List<CompanyEntity>

    @Query("DELETE FROM CompanyEntity")
    fun deleteAll()
}