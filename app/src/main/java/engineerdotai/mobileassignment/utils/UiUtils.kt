package engineerdotai.mobileassignment.utils

import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.support.design.widget.Snackbar
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import engineerdotai.mobileassignment.R
import engineerdotai.mobileassignment.base.BaseActivity


class UiUtils {

    companion object {

        private var snackbar: Snackbar? = null
        private var toast: Toast? = null
        private var progressDialog: ProgressDialog? = null


        fun showSnackBar(context: Context?, msg: String?, positive: Boolean) {
            if (context is BaseActivity) {
                val view: View? = context.findViewById(android.R.id.content)
                showSnackBar(view, msg, positive)
            }
        }

        fun showSnackBar(view: View?, msg: String?, positive: Boolean) {
            if (!isEmpty(msg) && view != null) {
                hideSnackBar()

                snackbar = Snackbar.make(view, msg!!, Snackbar.LENGTH_SHORT)
                val snackView = snackbar?.view
                if (positive) {
                    snackView?.setBackgroundColor(AndroidUtils.getColor(R.color.colorPrimaryDark))
                } else {
                    snackView?.setBackgroundColor(AndroidUtils.getColor(R.color.colorAccent))
                }

                val tv: TextView? = snackView?.findViewById(android.support.design.R.id.snackbar_text)

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                    tv?.setTextAlignment(View.TEXT_ALIGNMENT_CENTER)
                } else
                    tv?.setGravity(Gravity.CENTER_HORIZONTAL)

                snackbar?.show()
            }
        }

        fun hideSnackBar() {
            try {
                snackbar?.dismiss()
            } catch (e: Exception) {
                LogUtils.e(e)
            }
        }

        fun showToast(context: Context?, message: String?) {
            if (context != null && !isEmpty(message)) {
                hideToast()

                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
                toast?.show()
            }
        }

        fun hideToast() {
            toast?.cancel()
        }

        fun showProgressDialog(context: Context?, title: String?, message: String?, cancelable: Boolean) {
            try {
                dismissProgressDialog()

                if (context == null) {
                    return
                }

                progressDialog = ProgressDialog.show(
                    context,
                    title,
                    message,
                    true,
                    cancelable
                )
            } catch (e: Exception) {
                LogUtils.e(e)
            }

        }

        fun dismissProgressDialog() {
            try {
                if (progressDialog?.isShowing == true) {
                    progressDialog?.dismiss()
                }
            } catch (e: Exception) {
                LogUtils.e(e)
            } finally {
                progressDialog = null
            }
        }


    }
}