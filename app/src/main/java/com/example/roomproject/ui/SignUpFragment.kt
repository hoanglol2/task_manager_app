package com.example.roomproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.roomproject.MainActivity
import com.example.roomproject.MusicDao
import com.example.roomproject.MusicDatabase
import com.example.roomproject.R
import com.example.roomproject.entities.User
import kotlinx.android.synthetic.main.form_input_layout.view.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment(), View.OnClickListener {
    private var musicDao: MusicDao? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //  che do tao bang lazy, k can goi trong create view
    /*val musicDao by lazy {
        this.context?.let { MusicDatabase.getDatabase(it).musicDao() }
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        musicDao = this.context?.let {
            MusicDatabase.getDatabase(it).musicDao()
        }

        layoutFulName.edtInputFormInput.onFocusChangeListener = focusChangeListener
        layoutPhone.edtInputFormInput.onFocusChangeListener = focusChangeListener
        layoutEmail.edtInputFormInput.onFocusChangeListener = focusChangeListener
        layoutPassword.edtInputFormInput.onFocusChangeListener = focusChangeListener

        // set data template
        changeSignUpTemplate(
            layoutFulName.tvLabelFormInput,
            layoutFulName.edtInputFormInput,
            "fullName"
        )
        changeSignUpTemplate(layoutPhone.tvLabelFormInput, layoutPhone.edtInputFormInput, "phone")
        changeSignUpTemplate(layoutEmail.tvLabelFormInput, layoutEmail.edtInputFormInput, "email")
        changeSignUpTemplate(
            layoutPassword.tvLabelFormInput,
            layoutPassword.edtInputFormInput,
            "password"
        )


        // go back event
        ivGoBack.setOnClickListener(this)

        // go to SignIn event
        tvGoSignIn.setOnClickListener(this)

        // signUp event
        btnSignUp.setOnClickListener(this)
    }

    private val focusChangeListener = View.OnFocusChangeListener { view, _ ->
        (view as? EditText)?.let {
            if (it.text.isNullOrEmpty()) it.error = "input invalid"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when (v) {
            btnSignUp -> {
                insertNewUser()
            }
            ivGoBack -> {
                activity?.onBackPressed()
            }
            tvGoSignIn -> {
                (activity as? MainActivity)?.addFragment(SignInFragment(), true, "SignInFragment")
            }
        }
    }


    private fun insertNewUser() {
        if (
            layoutFulName.edtInputFormInput.text.isNotEmpty() &&
            layoutPhone.edtInputFormInput.text.isNotEmpty() &&
            layoutEmail.edtInputFormInput.text.isNotEmpty() &&
            layoutPassword.edtInputFormInput.text.isNotEmpty()
        ) {

            val fullName = layoutFulName.edtInputFormInput.text.toString()
            val phone = layoutPhone.edtInputFormInput.text.toString().toLong()
            val email = layoutEmail.edtInputFormInput.text.toString()
            val password = layoutPassword.edtInputFormInput.text.toString()
            val register = listOf(
                User(0, fullName, phone, email, password)
            )

            lifecycleScope.launch {
                register.forEach { musicDao?.insertUser(it) }
            }

            Toast.makeText(context, "You Register account success!", Toast.LENGTH_LONG).show()

            // navigate
            (activity as? MainActivity)?.addFragment(SignInFragment(), true, "SignInFragment")
        }
    }

    private fun changeSignUpTemplate(label: TextView, input: EditText, action: String) {
        when (action) {
            "fullName" -> {
                label.text = action
                input.hint = action
            }
            "phone" -> {
                label.text = action
                input.hint = action
            }
            "email" -> {
                label.text = action
                input.hint = action
            }
            "password" -> {
                label.text = action
                input.hint = action
            }
        }
    }
}