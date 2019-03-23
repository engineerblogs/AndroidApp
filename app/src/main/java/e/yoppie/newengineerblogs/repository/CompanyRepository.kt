package e.yoppie.newengineerblogs.repository

import android.content.Context
import e.yoppie.newengineerblogs.model.room.AppDatabase
import e.yoppie.newengineerblogs.model.room.entity.CompanyEntity

class CompanyRepository: BaseRepository() {

    fun getCompanyList() = this.retrofit
            .create(CompanyApiInterface::class.java)
            .getCompanyList()

    fun saveCompanyList(companyIdList: List<String>, context: Context) {
        // todo: RetrofitでDynamoDBにデータを保存するAPIを叩く

        val companyEntityList: MutableList<CompanyEntity> = mutableListOf()
        companyIdList.forEach {
            companyEntityList.add(CompanyEntity.create(it))
        }

        val db = AppDatabase.getInstance(context)!!
        db.companyDao().insertItems(companyEntityList)
    }
}
