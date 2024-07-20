package com.example.book.ui.main.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.book.api.model.response.book_by_id.BookByIdResponse
import com.example.book.databinding.FragmentCarDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookDetailsFragment : Fragment() {


    private var _binding: FragmentCarDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by viewModel()
    private val args: BookDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMyBook(args.idBook)
        setupObserver()
        onClick()

    }

    private fun setupObserver() {
        viewModel.successGetBook.observe(viewLifecycleOwner) { success ->
            setupUI(success)
        }
    }

    private fun setupUI(success: BookByIdResponse) {
        with(binding) {
            tvTitleName.text = success.title
            tvTitleAge.text = buildString {
                append("Resumo: ")
                append(success.summary)
            }
            tvColorCar.text = buildString {
                append("Categoria: ")
                append(success.category?.title)
            }
            tvPriceCar.text = success.author
        }

        success.imageUrl?.let { image(it) }

    }


    private fun image(image: String) {
        val viewPager = binding.viewPager
        val imageList = mutableListOf<String>()

        imageList.add(image)

        val adapter = ImageViewPagerAdapter(imageList) { position ->
        }

        viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()

    }

    private fun onClick() {

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

//            btnContact.setOnClickListener {
//                showDialog()
//            }
        }

    }


    private fun showDialog() {
//        val carDetail = args.carDetail
//        val customDialog = SolicitationDialogFragment(carDetail, {})
//        customDialog.show(requireActivity().supportFragmentManager, "custom_dialog")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}