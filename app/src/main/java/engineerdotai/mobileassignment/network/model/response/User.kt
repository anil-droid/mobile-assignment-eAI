package engineerdotai.mobileassignment.network.model.response

data class User(
    val image: String?,
    val items: MutableList<String?>?,
    val name: String?,
    var firstImage: String?
)