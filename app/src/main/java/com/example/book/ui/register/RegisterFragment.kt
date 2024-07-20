package com.example.book.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.book.api.model.request.RegisterModel
import com.example.book.databinding.FragmentRegisterBinding
import com.example.book.databinding.FragmentStepOnboardingBinding
import com.example.book.ui.account.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {


    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        onClick()

    }

    private fun setupObserver() {
        viewModel.successRegister.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Conta criada com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun setupUI() {

    }

    private fun onClick() {

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnEnter.setOnClickListener {
                validate()
            }
        }

    }

    private fun validate() {
        val name = binding.editName.text.toString()
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val confirmPassword = binding.editConfirmPassword.text.toString()

        var validateFileds = true

        if(name.isNullOrEmpty()){
            binding.editName.error = "Campo obrigatorio"
            validateFileds = false
        }

        if(email.isNullOrEmpty()){
            binding.editEmail.error = "Campo obrigatorio"
            validateFileds = false
        }

        if(password.isNullOrEmpty()){
            binding.editPassword.error = "Campo obrigatorio"
            validateFileds = false
        }

        if(confirmPassword.isNullOrEmpty()){
            binding.editConfirmPassword.error = "Campo obrigatorio"
            validateFileds = false
        }

        if(password != confirmPassword){
            Toast.makeText(requireContext(), "As senhas não conferem", Toast.LENGTH_SHORT).show()
            validateFileds = false
        }

        if(validateFileds){
            viewModel.createAccount(
                RegisterModel(
                    name = name,
                    email = email,
                    password = password,
                    confirmPassword = confirmPassword
                )
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}