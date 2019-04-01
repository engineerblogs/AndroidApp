package e.yoppie.newengineerblogs.model.data

import com.squareup.moshi.Json

data class Category(
        @Json(name = "companyName")
        val name: String,
        @Json(name = "companyId")
        val id: String,
        val articles: MutableList<Article>
)