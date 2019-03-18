package e.yoppie.newengineerblogs.model.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class CompanyEntity {
    @PrimaryKey
    var companyEntityId: Int = 0

    @ColumnInfo(name = "company_id")
    var companyId: String? = null
}