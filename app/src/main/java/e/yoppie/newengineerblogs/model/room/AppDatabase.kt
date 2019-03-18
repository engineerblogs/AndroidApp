package e.yoppie.newengineerblogs.model.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import e.yoppie.newengineerblogs.model.room.dao.CompanyDao

@Database(entities = [CompanyDao::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun companyDao(): CompanyDao
}