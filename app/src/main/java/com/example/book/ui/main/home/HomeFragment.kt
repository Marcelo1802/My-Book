package com.example.book.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book.api.model.response.book.DataBook
import com.example.book.api.model.response.categories.CategoriesResponseItem
import com.google.gson.Gson
import com.example.book.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeBooksAdapter: HomeBooksAdapter
    private lateinit var categoriesBookAdapter: CategoriesBookAdapter
    private val viewModel: HomeViewModel by viewModel()
    private var selectCategory = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        onClick()

    }

    private fun setupObserver() {
        viewModel.successGetBook.observe(viewLifecycleOwner) {
            it.data?.let { listBooks -> initListBooks(listBooks) }
        }

        viewModel.successGetCategories.observe(viewLifecycleOwner) {
            it?.let { listCategories -> initListCategories(listCategories) }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupUI() {
        viewModel.getCategories()
        viewModel.getMyBooks()

    }

    private fun onClick() {

        binding.apply {

        }

    }

    private fun initListBooks(list: List<DataBook?>) {
        binding.rvRecommended.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            homeBooksAdapter = HomeBooksAdapter(emptyList(), { book ->
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToCarDetailsFragment(idBook = book?.id!!))
            }, {})
            adapter = homeBooksAdapter
        }

        homeBooksAdapter.updateData(list)

    }

    private fun initListCategories(list: List<CategoriesResponseItem>) {
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            categoriesBookAdapter = CategoriesBookAdapter(emptyList()) { category ->
                if (categoriesBookAdapter.selectedPosition == RecyclerView.NO_POSITION) {
                    viewModel.getMyBooks()
                } else {
                    viewModel.getMyBooks(categoryId = category.id)
                }
            }
            adapter = categoriesBookAdapter
        }

        categoriesBookAdapter.updateData(list)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}