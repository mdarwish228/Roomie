package com.darwish.roomie.common

import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class ToastUtils {
    companion object {
        fun createToast(activity: FragmentActivity, message: String){
            val toast =
                Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 20)
            toast.show()
        }
    }
}