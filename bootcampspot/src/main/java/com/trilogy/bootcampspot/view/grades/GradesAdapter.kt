package com.trilogy.bootcampspot.view.grades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.data.net.response.GradesResponse
import kotlinx.android.synthetic.main.item_grade.view.*

class GradesAdapter(
    private val grades: List<GradesResponse>,
    private val assignmentClickListener: AssignmentClickListener
) :
    RecyclerView.Adapter<GradesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vh = ViewHolder(inflater.inflate(R.layout.item_grade, parent, false))
        vh.itemView.setOnClickListener {
            assignmentClickListener.onAssignmentClicked(vh.itemView.tag as Int)
        }
        return vh
    }

    override fun getItemCount(): Int {
        return grades.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grade = grades[position]
        if (grade.submissionGrade == null) {
            holder.itemView.gradeTV.text = "-"
        } else {
            holder.itemView.gradeTV.text = grade.submissionGrade.grade
        }
        holder.itemView.typeTV.text = grade.context.name
        holder.itemView.statusTV.text = "Submitted"
        holder.itemView.submissionNameTv.text = grade.assignment.title
        holder.itemView.submissionDescriptionTv.text = grade.assignmentContent!!.content
        holder.itemView.tag = grade.assignment.id
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    interface AssignmentClickListener {
        fun onAssignmentClicked(assignmentId: Int)
    }
}