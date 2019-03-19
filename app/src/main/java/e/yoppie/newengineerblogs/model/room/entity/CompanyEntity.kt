package e.yoppie.newengineerblogs.model.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class CompanyEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "company_id")
    var companyId: String? = null

    companion object {
        fun create(companyId: String): CompanyEntity {
            val companyEntity = CompanyEntity()
            companyEntity.companyId = companyId
            return companyEntity
        }
    }
}