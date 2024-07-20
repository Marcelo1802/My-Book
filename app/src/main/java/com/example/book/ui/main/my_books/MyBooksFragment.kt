package com.example.book.ui.main.my_books

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book.api.model.response.book.DataBook
import com.example.book.databinding.FragmentMyBooksBinding
import com.example.book.ui.main.book.BookViewModel
import com.example.book.ui.main.home.HomeFragmentDirections
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyBooksFragment : Fragment() {


    private var _binding: FragmentMyBooksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by viewModel()
    private lateinit var myBooksAdapter: MyBooksAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        onClick()

    }

    private fun setupObserver() {
        viewModel.successGetBookList.observe(viewLifecycleOwner) {
            initListMyBooks(it.data)
        }

        viewModel.successGetBookDelete.observe(viewLifecycleOwner) {
            viewModel.getMyBooksList()
        }
    }

    private fun searchName() {
        binding?.editSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.getMyBooksList(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupUI() {
        viewModel.getMyBooksList()
        searchName()
    }

    private fun initListMyBooks(list: List<DataBook>) {
        binding.rvMyBooks.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            myBooksAdapter = MyBooksAdapter(emptyList(), { book ->
                findNavController().navigate(
                    MyBooksFragmentDirections.actionNavigationMyBooksToCarDetailsFragment(
                        idBook = book?.id!!
                    )
                )
            }, { book ->
                showDeleteDialog(book)
            })

            adapter = myBooksAdapter
        }

        myBooksAdapter.updateData(list)

    }

    private fun showDeleteDialog(book: DataBook) {
        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Deletar Livro")
        builder.setMessage("Deseja excluir permanentemente este livro?")
        builder.setPositiveButton("Sim") { _, _ ->
            viewModel.deleteBook(book.id)
                }
        builder.setNegativeButton("Não") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }


    private fun onClick() {

        binding.apply {
            btnAdd.setOnClickListener {
                findNavController().navigate(MyBooksFragmentDirections.actionNavigationMyBooksToNewBookFragment())
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}