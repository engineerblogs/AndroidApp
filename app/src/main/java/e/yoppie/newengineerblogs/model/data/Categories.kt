package e.yoppie.newengineerblogs.model.data

import com.squareup.moshi.Json

data class Categories(
        @Json(name = "companies")
        val categories: MutableList<Category>
)