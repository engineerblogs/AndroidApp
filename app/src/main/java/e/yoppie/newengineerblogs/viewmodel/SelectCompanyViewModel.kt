package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import e.yoppie.newengineerblogs.model.data.Company

class SelectCompanyViewModel : ViewModel() {
    var companyListData: MutableLiveData<MutableList<Company>>
    var companyList: MutableList<Company>

    init {
        val mutableLiveData = MutableLiveData<MutableList<Company>>()
        val companyList = mutableListOf(
                Company(1, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(2, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(3, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(4, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(5, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(6, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(7, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(8, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg"),
                Company(9, "ぐるなび", "http://fanblogs.jp/nogizakafan/file/E799BDE79FB3E9BABBE8A1A32CE883B82CE3818AE381A3E381AFE3829AE381842CE794BBE5838F2CE381BEE38184E38284E382932CE4B983E69CA8E59D82462CE5B9B4E5A5B32C5E4BD8D.jpg")
        )
        mutableLiveData.value = companyList
        this.companyList = companyList
        companyListData = mutableLiveData
    }

    fun loadMore() {
        val company = Company(10, "ぐるなび", "url")
        if (this.companyList.last().id != company.id) {
            this.companyList.add(company)
            this.companyListData.postValue(this.companyList)
        }
    }
}