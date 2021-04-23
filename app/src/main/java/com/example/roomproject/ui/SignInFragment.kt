package com.example.roomproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.roomproject.MainActivity
import com.example.roomproject.MusicDao
import com.example.roomproject.MusicDatabase
import com.example.roomproject.R
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment(), View.OnTouchListener, View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var musicDao: MusicDao? = null

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
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        musicDao = this.context?.let {
            MusicDatabase.getDatabase(it).musicDao()
        }

        // go back event
        ivBackSignIn.setOnClickListener {
            activity?.onBackPressed()
        }

        // Toggle password
        edtPasswordSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(v: CharSequence?, p1: Int, p2: Int, p3: Int) {
                ivShowPassword.visibility = if (v.isNullOrEmpty()) View.GONE else View.VISIBLE
            }
        })

        // set password in Edit text
        ivShowPassword.setOnTouchListener(this)

        // go to SignUp event
        tvGoSignUp.setOnClickListener(this)

        // go to dash board screen
        tvSignIn.setOnClickListener(this)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignInFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onTouch(v: View?, motionEvent: MotionEvent?): Boolean {
        when (v) {
            ivShowPassword -> {
                val buffInputType = when (motionEvent?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        -1
                    }
                    MotionEvent.ACTION_UP -> {
                        1
                    }
                    else -> 0
                }
                edtPasswordSignIn.inputType = edtPasswordSignIn.inputType + buffInputType

            }
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v) {
            tvGoSignUp -> {
                (activity as? MainActivity)?.addFragment(SignupFragment(), true, "SignUpFragment")
            }
            tvSignIn -> {
                checkUserValid()
            }
        }
    }

    private fun checkUserValid() {
        if (edtEmailSignIn.text.isNotEmpty() &&
            edtPasswordSignIn.text.isNotEmpty()
        ) {
            val email = edtEmailSignIn.text.toString()
            val password = edtPasswordSignIn.text.toString()

            lifecycleScope.launch {
                val listUser = musicDao?.getUserWithInput(email, password)

                if (!listUser.isNullOrEmpty()) {
                    (activity as? MainActivity)?.destroyAllFragment()
                } else {
                    Toast.makeText(context, "Email or Password not Found!", Toast.LENGTH_LONG)
                        .show()
                }
            }

        } else {
            Toast.makeText(context, "Email or Password is Empty!", Toast.LENGTH_LONG).show()
        }
    }
}