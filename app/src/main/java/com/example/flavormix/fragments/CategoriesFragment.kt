package com.example.flavormix.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flavormix.R
import com.example.flavormix.activities.CategoryMealsActivity
import com.example.flavormix.activities.MainActivity
import com.example.flavormix.adapters.CategoriesAdapter
import com.example.flavormix.databinding.FragmentCategoriesBinding
import com.example.flavormix.viewModel.HomeViewModel


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        binding.shimmerLayoutCategories.startShimmer()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeCategoriesLiveData()
        onCategoryItemClick()
    }

    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

            adapter = categoriesAdapter
        }
    }

    // Observe the Categories and set up recycler view
    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setCategoryList(categories)

            // Hide shimmer effect
            binding.shimmerLayoutCategories.stopShimmer()
            binding.shimmerLayoutCategories.visibility = View.GONE
            // Make the view visible again
            binding.categoriesMain.visibility = View.VISIBLE
        }
    }

    // Function to navigate to CategoryMealsActivity from popular item list onClick
    private fun onCategoryItemClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }


}