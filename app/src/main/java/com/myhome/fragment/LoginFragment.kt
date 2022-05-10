package com.myhome.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.myhome.R
import com.myhome.api.impl.AccountApiService
import com.myhome.databinding.FragmentLoginBinding
import com.myhome.other.Strings
import com.myhome.blueprint.Account
import com.myhome.other.ApiConstants
import com.myhome.other.SpStrings
import com.myhome.service.data.DataHandlingService
import java.lang.Exception

/**
 * @author Z-100
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var accountService = AccountApiService()
    private var dataService = DataHandlingService()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val account = dataService.loadData(context!!
            .getSharedPreferences(SpStrings.SP_NAME_ACC, Context.MODE_PRIVATE))

        if (account != null) { // Redirect if login exists
            findNavController().navigate(R.id.login_to_members)
        } else {
            generateBindings()
        }
    }

    private fun generateBindings() {
        binding.submitButton.isEnabled = false
        binding.submitButton.setOnClickListener {
            if (noFieldEmpty()) validateLogin()
            else Snackbar.make(view!!, Strings.FILL_IN_ALL_FIELDS, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        binding.registerInsteadButton.setOnClickListener {
            findNavController().navigate(R.id.login_to_members)
        }

        binding.inputEmail.doOnTextChanged {
                _,_,_,_ -> binding.submitButton.isEnabled = noFieldEmpty()
        }

        binding.inputPassword.doOnTextChanged {
                _,_,_,_ -> binding.submitButton.isEnabled = noFieldEmpty()
        }
    }

    private fun validateLogin() {
        val email: String = binding.inputEmail.text.toString()
        val password: String = binding.inputPassword.text.toString()

        try {
            if (email == "hans@peter.ch" && password == "sananas")
                findNavController().navigate(R.id.login_to_members)
            else throw Exception("Testing Error!") // DEBUG

            accountService.getLoginToken(context, email, password) {
                    result ->
                val sp = context!!.getSharedPreferences(SpStrings.SP_NAME_ACC, Context.MODE_PRIVATE)
                val account = Account(email, password, result.getString(ApiConstants.TOKEN_FIELD))

                dataService.saveData(sp, account)

                findNavController().navigate(R.id.login_to_members)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Snackbar.make(view!!, Strings.INVALID_USERNAME_OR_PASSWORD, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun noFieldEmpty(): Boolean {
        return binding.inputEmail.text.toString().trim().isNotEmpty()
                && binding.inputPassword.text.toString().trim().isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
