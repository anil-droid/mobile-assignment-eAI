package engineerdotai.mobileassignment.utils

import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.widget.EditText
import engineerdotai.mobileassignment.base.BaseApplication


fun EditText?.getTextTrimmed() = this?.text?.toString()?.trim() ?: ""

class AndroidUtils {

    companion object {


        fun getColor(@ColorRes id: Int) = ContextCompat.getColor(BaseApplication.getInstance(), id)

        fun getDimension(@DimenRes id: Int) = BaseApplication.getInstance().resources.getDimensionPixelSize(id)


        @JvmStatic
        fun getString(@StringRes id: Int, vararg objects: Any?) = if (objects.isEmpty()) {
            BaseApplication.getInstance().resources.getString(id)
        } else {
            BaseApplication.getInstance().resources.getString(id, *objects)
        }


    }


}