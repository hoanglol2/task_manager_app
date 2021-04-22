package com.example.roomproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomproject.R
import com.example.roomproject.adapters.ListPlanAdapter
import com.example.roomproject.adapters.ListTaskAdapter
import com.example.roomproject.models.ListPlanModel
import com.example.roomproject.models.ListTaskModel
import kotlinx.android.synthetic.main.fragment_dash_board.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashBoardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashBoardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // render item list task
        var listTask = mutableListOf<ListTaskModel>()

        var listPlan = mutableListOf<ListPlanModel>()

        for (item in 0 until 10) {
            listTask.add(
                item,
                ListTaskModel(
                    "Writing a book $item",
                    "28.12.2021, 9:41 PM",
                    "hard level",
                    item * 10
                )
            )

            listPlan.add(
                item,
                ListPlanModel(item === 2, "Workout $item", "Medium")
            )
        }

        // initialize adapter
        val adapter = ListTaskAdapter(listTask)
        val adapterPlan = ListPlanAdapter(listPlan)

        // set layout manager
        rvListTask.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPlanDashBoard.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        // set adapter
        rvListTask.adapter = adapter
        rvPlanDashBoard.adapter = adapterPlan
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashBoardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashBoardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}