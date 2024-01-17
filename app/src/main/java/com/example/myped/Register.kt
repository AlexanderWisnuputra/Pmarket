package com.example.myped

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myped.databinding.FragmentRegisterBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.SharedPreferences

class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var isEmailValid = false
    private var isPasswordValid = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding = registerBinding
        tcolor()
        emailUpdate()
        passwordUpdate()
        return registerBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnDaftar.setOnClickListener {
                if (isEmailValid && isPasswordValid) {
                    findNavController().navigate(R.id.action_register_to_login)
                } else {
                    Toast.makeText(context,"Invalid email or password. Please check the requirements.",Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun emailUpdate() {
        val outlinedTextField = binding.outlinedTextField
        outlinedTextField.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                val text = editable.toString()
                if (text.isNullOrBlank() || !isValidEmail(text)) {
                    outlinedTextField.error = "Email tidak valid" // Set the error message
                    isEmailValid = false

                } else {
                    outlinedTextField.error = null // Clear the error if the text is valid
                    isEmailValid = true

                }
            }
        })

    }

    private fun passwordUpdate() {
        val password = binding.password
        password.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                val text = editable.toString()
                // Validate the password using a regular expression
                if (text.isEmpty() || !isValidPassword(text)) {
                    password.error =
                        "Password tidak valid"
                    isPasswordValid = false

                } else {
                    password.error = null // Clear the error if the password is valid
                    isPasswordValid = true

                }
            }
        })
    }


    private fun isValidEmail(email: String): Boolean {
        // Add your email validation logic here
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}\$"
        return password.matches(passwordRegex.toRegex())
    }


    fun tcolor() {
        val textView5 = binding.textView5

        val fullText =
            "Dengan masuk disini, kamu menyetujui Syarat & Ketentuan serta Kebijakan Privasi TokoPhincon."

        val spannableString = SpannableString(fullText)

        val startGreen = fullText.indexOf("Syarat & Ketentuan")
        val endGreen = startGreen + "Syarat & Ketentuan".length

        val startBlue = fullText.indexOf("Kebijakan Privasi")
        val endBlue = startBlue + "Kebijakan Privasi".length

        val customGreenColor = ContextCompat.getColor(requireContext(), R.color.primary)
        spannableString.setSpan(
            ForegroundColorSpan(customGreenColor),
            startGreen,
            endGreen,
            0
        )
        spannableString.setSpan(
            ForegroundColorSpan(customGreenColor),
            startBlue,
            endBlue,
            0
        )

        // Set the SpannableString to the TextView
        textView5.text = spannableString

    }
}




