package e.yoppie.newengineerblogs.model.data

data class Category(
        val id: Long,
        val name: String,
        val articleList: MutableList<Article>
)