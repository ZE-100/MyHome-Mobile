package com.myhome.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.myhome.R
import com.myhome.blueprint.Account
import com.myhome.databinding.FragmentLoginBinding
import com.myhome.other.*
import com.myhome.service.api.components.impl.FetchAccountService
import com.myhome.service.api.constants.ApiConst
import com.myhome.service.data.DataHandlingService
import com.myhome.util.Logger

/**
 * @author Z-100
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var accountService = FetchAccountService()
    private var dataService = DataHandlingService()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        dataService.loadData()

        // Redirect if credentials present
        if (Session.userLoginPresent())
            findNavController().navigate(R.id.login_to_members)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createXmlBindings()
    }

    private fun createXmlBindings() {
        binding.submitButton.isEnabled = false
        binding.submitButton.setOnClickListener {
            submit()
        }

        binding.registerInsteadButton.setOnClickListener {
            findNavController().navigate(R.id.login_to_register)
        }

        binding.inputEmail.doOnTextChanged {
                _,_,_,_ -> binding.submitButton.isEnabled = noFieldEmpty()
        }

        binding.inputPassword.doOnTextChanged {
                _,_,_,_ -> binding.submitButton.isEnabled = noFieldEmpty()

                    if (binding.inputPassword.length() > 0)
                        binding.inputPassword.setHintTextColor(Color.GRAY)
        }
    }

    private fun submit() {
        if (noFieldEmpty()) {
            sendLoginRequest()
            validateLogin()
        } else {
            binding.inputEmail.setHintTextColor(Color.RED)
            Snackbar.make(requireView(), Strings.FILL_IN_ALL_FIELDS, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun sendLoginRequest() {
        val email: String = binding.inputEmail.text.toString()
        val password: String = binding.inputPassword.text.toString()

        Logger.log(ApiConst.LOG_DEBUG, this.javaClass, "Email: $email", "Password: $password")

        accountService.login(email, password) {
            result ->
            Session.replaceHeader(Headers.TOKEN, result)
            Session.addAccount(Account(email, password, result))

            dataService.saveData()
        }
    }

    private fun validateLogin() {
        if (Session.userLoginPresent())
            findNavController().navigate(R.id.login_to_members)
        else {
            binding.inputPassword.setHintTextColor(Color.RED)
            binding.inputPassword.text.clear()
            binding.inputPassword.error = Strings.INVALID_USERNAME_OR_PASSWORD
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
