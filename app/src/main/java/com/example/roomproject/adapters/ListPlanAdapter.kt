package com.example.roomproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.R
import com.example.roomproject.models.ListPlanModel
import kotlinx.android.synthetic.main.plan_item.view.*

class ListPlanAdapter(
    val listPlan: List<ListPlanModel>
) : RecyclerView.Adapter<ListPlanAdapter.ListPlanViewHolder>() {
    inner class ListPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plan_item, parent, false)
        return ListPlanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPlan.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ListPlanViewHolder, position: Int) {

        holder.itemView.apply {
            tvTitlePlanDashBoard.text = listPlan[position].title
            tvLevelPlanDashBoard.text = listPlan[position].level
            cbPlanDashBoard.isChecked = listPlan[position].isCheck

            val primaryBackground =
                ContextCompat.getDrawable(context, R.drawable.plan_item_primary_rds)

            val primaryColor = ContextCompat.getColor(context, R.color.primary_line)

            val blackColor = ContextCompat.getColor(context, R.color.black)

            when (listPlan[position].isCheck) {
                true -> {
                    layoutPlanDashBoard.background = primaryBackground
                    tvTitlePlanDashBoard.setTextColor(blackColor)
                    tvLevelPlanDashBoard.setTextColor(primaryColor)
                }
            }

        }
    }
}