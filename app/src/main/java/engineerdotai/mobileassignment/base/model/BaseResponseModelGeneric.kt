package engineerdotai.mobileassignment.base.model

data class BaseResponseModelGeneric<D>(
    val `data`: D?,
    val message: String?,
    val status: Boolean = false
)