package e.yoppie.newengineerblogs.model.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import e.yoppie.newengineerblogs.model.room.dao.CompanyDao
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity

@Database(entities = [CompanyEntity::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "AppDatabase.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}