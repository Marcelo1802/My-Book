package com.example.book.ui.account.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.book.api.model.request.LoginModel
import com.example.book.databinding.FragmentLoginBinding
import com.example.book.ui.main.MainActivity
import com.example.book.ui.main.home.HomeViewModel
import com.example.book.utils.isValidEmail
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        setupObserver()

    }

    private fun setupObserver() {

        viewModel.successLogin.observe(viewLifecycleOwner) {
            viewModel.createSession(it)
            Toast.makeText(requireContext(), "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun validation() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        var valideFields = true

        if (!email.isValidEmail()) {
            binding.editEmail.error = "Email inválido"
            valideFields = false
        }

        if (password.isEmpty()) {
            binding.editPassword.error = "Campo vazio"
            valideFields = false
        }

        if (valideFields) {
           viewModel.login(
               LoginModel(
                   credential = email,
                   password = password
               )
           )
        }


    }


    private fun onClick() {

        binding.apply {
            btnRegister.setOnClickListener {
              findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            btnEnter.setOnClickListener {
                validation()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}