package com.example.xetaassignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xetaassignment.adapter.RecyclerViewAdapter
import com.example.xetaassignment.apiService.ApiService
import com.example.xetaassignment.apiService.ApiUtility
import com.example.xetaassignment.databinding.FragmentHomeBinding
import com.example.xetaassignment.model.Section3
import com.example.xetaassignment.model.Section4
import com.example.xetaassignment.repository.Repository
import com.example.xetaassignment.viewModel.ExerciseViewModelFactory
import com.example.xetaassignment.viewModel.MainViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerViewRecommend
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val categoryRecyclerView = binding.categoriesRecyclerView
        gridLayoutManager = GridLayoutManager(requireContext(), 2)
        categoryRecyclerView.layoutManager = gridLayoutManager

        val apiInterface = ApiUtility.getInstance().create(ApiService::class.java)

        val exerciseRepository = Repository(apiInterface)

        viewModel = ViewModelProvider(
            this,
            ExerciseViewModelFactory(exerciseRepository)
        ).get(MainViewModel::class.java)

        binding.mainView.visibility = View.INVISIBLE

        viewModel.exercise.observe(viewLifecycleOwner) { exercise ->

            binding.mainView.visibility = View.VISIBLE
            binding.loading.visibility = View.INVISIBLE

            binding.planName1.text = (exercise.data.section_1.plan_name)

            val percentageString = exercise.data.section_1.progress
            binding.progressPercent.text = percentageString
            val progressValue = percentageString.replace("%", "").toFloatOrNull() ?: 0f
            binding.progressBar.progress = progressValue.toInt()

            binding.accuracyPercent.text = exercise.data.section_2.accuracy
            binding.workoutDurationPercent.text = exercise.data.section_2.workout_duration
            binding.repsPercent.text = exercise.data.section_2.reps.toString()
            binding.caloriesPercent.text = exercise.data.section_2.calories_burned.toString()


            val section3Data: Section3 = exercise.data.section_3
            val planList: List<Any> = listOf(
                section3Data.plan_1,
                section3Data.plan_2
            )

            val section4Data: Section4 = exercise.data.section_4
            val categoryList: List<Any> = listOf(
                section4Data.category_1,
                section4Data.category_2
            )

            if (exercise != null) {

                val adapter1 = RecyclerViewAdapter(planList)
                recyclerView.adapter = adapter1


                val adapter2 = RecyclerViewAdapter(categoryList)
                categoryRecyclerView.adapter = adapter2
            }
        }
        return binding.root
    }
}