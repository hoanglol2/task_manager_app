package com.example.roomproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.roomproject.MainActivity
import com.example.roomproject.R
import kotlinx.android.synthetic.main.fragment_sign_in.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

        // go back event
        ivBackSignIn.setOnClickListener {
            activity?.onBackPressed()
        }

        // Toggle password
        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(v: CharSequence?, p1: Int, p2: Int, p3: Int) {
                ivShowPassword.visibility = if (v.isNullOrEmpty()) View.GONE else View.VISIBLE
            }
        })

        // set password in Edit text
        ivShowPassword.setOnTouchListener { view, motionEvent ->
            val buffInputType = when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    -1
                }
                MotionEvent.ACTION_UP -> {
                    1
                }
                else -> 0
            }
            edtPassword.inputType = edtPassword.inputType + buffInputType
            true
        }

        // go to SignUp event
        tvGoSignUp.setOnClickListener {
            (activity as? MainActivity)?.addFragment(SignupFragment(), true, "SignUpFragment")
        }

        // go to dash board screen
        tvSignIn.setOnClickListener {
            (activity as? MainActivity)?.destroyAllFragment()
        }

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
}