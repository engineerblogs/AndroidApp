package e.yoppie.newengineerblogs.model.data

data class Category(
        val name: String,
        val id: String,
        val articles: MutableList<Article>
)