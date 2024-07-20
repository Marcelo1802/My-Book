package com.example.book.ui.main.book

import android.Manifest
import android.R
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.book.api.model.request.CreateBookModel
import com.example.book.api.model.request.RegisterModel
import com.example.book.api.model.response.categories.CategoriesResponseItem
import com.example.book.databinding.FragmentNewBookBinding
import com.example.book.databinding.FragmentStepOnboardingBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream


class NewBookFragment : Fragment() {


    private var _binding: FragmentNewBookBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by viewModel()
   private val GALLERY_REQUEST_CODE = 123
    private val CAMERA_PERMISSION_CODE = 1001
    var selectedImageUri: Uri? = null
    private var imageUrl = "null"
    private var catergoryID = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategories()
        setupObserver()
        onClick()

    }

    private fun setupObserver() {
        viewModel.uploadSuccess.observe(viewLifecycleOwner){
            binding.imagePhoto.load(it.url)
            imageUrl = it.url

        }

        viewModel.successGetCategories.observe(viewLifecycleOwner){
            initSpinnerCategories(it)
        }

        viewModel.successNewBook.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Livro criado com sucesso", Toast.LENGTH_SHORT).show()
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
            btnEditPhoto.setOnClickListener {
                openGallery()
            }
            btnEnter.setOnClickListener {
                validate()
            }
        }

    }

    private fun initSpinnerCategories(list: List<CategoriesResponseItem>) {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            list.map { it.title }
        )
        binding.editCategories.setAdapter(adapter)

        binding.editCategories.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as String
            val selectedPet = list.find { it.title == selectedItem }
            selectedPet?.let {
                catergoryID = it.id!!
            }
        }

    }

    private fun validate() {
        val title = binding.editTitleBook.text.toString()
        val sumary = binding.editSummary.text.toString()
        val author = binding.editAutor.text.toString()


        var validateFileds = true

        if(title.isNullOrEmpty()){
            binding.editTitleBook.error = "Campo obrigatorio"
            validateFileds = false
        }

        if(sumary.isNullOrEmpty()){
            binding.editSummary.error = "Campo obrigatorio"
            validateFileds = false
        }

        if(author.isNullOrEmpty()){
            binding.editAutor.error = "Campo obrigatorio"
            validateFileds = false
        }

        if (imageUrl == "null") {
            validateFileds = false
            Toast.makeText(requireContext(), "Selecione uma imagem", Toast.LENGTH_SHORT).show()
        }

        if (catergoryID == 0) {
            validateFileds = false
            Toast.makeText(requireContext(), "Selecione uma categoria", Toast.LENGTH_SHORT).show()
        }


        if(validateFileds){
            viewModel.createBook(
                CreateBookModel(
                    title = title,
                    summary = sumary,
                    author = author,
                    imageUrl = imageUrl,
                    categoryId = catergoryID
                )
            )
        }
    }

    private fun openGallery() {
        val options = arrayOf<CharSequence>("Abrir Galeria", "Usar Câmera")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Escolha uma opção")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> {
                        val galleryIntent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
                    }
                    1 -> {
                        checkCameraAndStoragePermissions()
                    }
                }
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == GALLERY_REQUEST_CODE || requestCode == REQUEST_IMAGE_CAPTURE) && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            selectedImageUri?.let { uri ->
                val imageStream = requireActivity().contentResolver.openInputStream(uri)
                val imageBytes = imageStream?.readBytes()
                imageStream?.close()
                if (imageBytes != null) {
                    viewModel.uploadImage(imageBytes)
                }
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val imageBytes = bitmapToByteArray(imageBitmap)
            viewModel.uploadImage(imageBytes)
        }

    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun checkCameraAndStoragePermissions() {
        val cameraPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        )
        val storagePermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        val permissionsToRequest = mutableListOf<String>()

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.CAMERA)
        }

        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                CAMERA_AND_STORAGE_PERMISSION_CODE
            )
        } else {
            // Se as permissões forem concedidas, abra a câmera
            openCamera()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, abra a câmera
                openCamera()
            } else {
                // Permissão negada, exiba uma mensagem ou tome alguma outra ação adequada
                Toast.makeText(requireContext(), "Permissão de câmera negada", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val CAMERA_AND_STORAGE_PERMISSION_CODE = 0
    }


}