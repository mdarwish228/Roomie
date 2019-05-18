package com.darwish.roomie.common

import android.widget.Button

class ButtonUtils {
    companion object {
        fun disableButtons(buttons: List<Button>){
            for (button in buttons) {
                button.isEnabled = false
            }
        }

        fun enableButtons(buttons: List<Button>){
            for (button in buttons) {
                button.isEnabled = true
            }
        }
    }
}