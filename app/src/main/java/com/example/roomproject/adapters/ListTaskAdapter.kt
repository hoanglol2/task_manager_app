package com.example.roomproject.adapters

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.R
import com.example.roomproject.models.ListTaskModel
import kotlinx.android.synthetic.main.task_item.view.*

class ListTaskAdapter(
    val listTask: List<ListTaskModel>
) : RecyclerView.Adapter<ListTaskAdapter.ListTaskViewHolder>() {
    inner class ListTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ListTaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ListTaskViewHolder, position: Int) {
        holder.itemView.apply {
            tvTaskTitle.text = listTask[position].title
            tvDateTime.text = listTask[position].dateTime
            tvLevel.text = listTask[position].level

            var hardColor = ContextCompat.getColor(context, R.color.hard)
            var mediumColor = ContextCompat.getColor(context, R.color.medium)
            var easyColor = ContextCompat.getColor(context, R.color.easy)

            val param1 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
            )
            val param2 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                2f
            )
            val param3 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                3f
            )

            when (listTask[position].progress) {
                in 0..25 -> {
                    layoutCountProgress.setBackgroundColor(easyColor)
                    tvCountProgress.text = ""
                    tvPercentProgress.text = listTask[position].progress.toString() + "%"

                    // set color
                    tvPercentProgress.setTextColor(easyColor)

                    // set position text percent
                    layoutPercentProgress.gravity = Gravity.END

                    // set layout weight linear layout
                    layoutCountProgress.layoutParams = param3
                    layoutPercentProgress.layoutParams = param1

                }
                in 26..79 -> {
                    layoutCountProgress.setBackgroundColor(mediumColor)
                    tvCountProgress.text = ""
                    tvPercentProgress.text = listTask[position].progress.toString() + "%"

                    // set color
                    tvPercentProgress.setTextColor(mediumColor)

                    // set position text percent
                    layoutPercentProgress.gravity = Gravity.END

                    // set layout weight linear layout
                    layoutCountProgress.layoutParams = param3
                    layoutPercentProgress.layoutParams = param2
                }
                else -> {
                    layoutCountProgress.setBackgroundColor(hardColor)
                    tvCountProgress.text = listTask[position].progress.toString()
                    tvPercentProgress.text = "%"

                    // set color
                    tvPercentProgress.setTextColor(hardColor)

                    // set position text percent
                    layoutPercentProgress.gravity = Gravity.START

                    // set layout weight linear layout
                    layoutCountProgress.layoutParams = param1
                    layoutPercentProgress.layoutParams = param3
                }
            }

        }
    }
}