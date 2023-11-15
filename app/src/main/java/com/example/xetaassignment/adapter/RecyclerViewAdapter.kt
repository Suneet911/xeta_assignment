package com.example.xetaassignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xetaassignment.R
import com.example.xetaassignment.model.Category1
import com.example.xetaassignment.model.Plan1

class RecyclerViewAdapter(private val planList: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_PLAN = 1
    private val TYPE_CATEGORY = 2

    override fun getItemViewType(position: Int): Int {
        Log.d("HELLO", "getItemViewType called for position $position")
        val type = when (planList[position]) {
            is Plan1 -> TYPE_PLAN
            is Category1 -> TYPE_CATEGORY
            else -> throw IllegalArgumentException("Invalid item type $position")
        }
        Log.d("HELLO", "getItemViewType: $type")
        return type
    }

    inner class PlanNameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Plan1) {
            val recommendPlan: TextView = itemView.findViewById(R.id.planName)
            val difficulty: TextView = itemView.findViewById(R.id.difficulty)

            recommendPlan.text = item.plan_name
            difficulty.text = item.difficulty
        }
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Category1) {
            val categoryName: TextView = itemView.findViewById(R.id.categoryName)
            val noOfExercise: TextView = itemView.findViewById(R.id.noOfExercise)

            categoryName.text = item.category_name
            noOfExercise.text = item.no_of_exercises
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_PLAN -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_recommend, parent, false)
                PlanNameViewHolder(view)
            }

            TYPE_CATEGORY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_categories, parent, false)
                CategoryViewHolder(view)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is PlanNameViewHolder ->
                holder.bind(planList[position] as Plan1)

            is CategoryViewHolder ->
                holder.bind(planList[position] as Category1)
        }
    }

    override fun getItemCount(): Int {
        return planList.size
    }
}

