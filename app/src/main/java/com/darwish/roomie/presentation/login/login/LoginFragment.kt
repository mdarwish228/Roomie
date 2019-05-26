package com.darwish.roomie.presentation.login.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.darwish.roomie.R
import com.darwish.roomie.common.ButtonUtils
import com.darwish.roomie.common.ToastUtils
import com.darwish.roomie.presentation.group.GroupActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signinButton.setOnClickListener {
            if (emailEditText.text!!.isNotEmpty() && passwordEditText.text!!.isNotEmpty()) {
                ButtonUtils.disableButtons(Arrays.asList(signinButton, signupButton))

                auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(activity, GroupActivity::class.java)
                            startActivity(intent)
                            activity!!.finish()
                        } else {
                            ButtonUtils.enableButtons(Arrays.asList(signinButton, signupButton))
                            ToastUtils.createToast(activity!!, getString(R.string.toast_invalid_login))
                        }
                    }
            } else {
                ToastUtils.createToast(activity!!, getString(R.string.toast_empty_username_password))
            }
        }

        signupButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }
}