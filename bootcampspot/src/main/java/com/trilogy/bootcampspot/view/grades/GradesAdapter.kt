package com.trilogy.bootcampspot.view.grades

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.data.net.response.Grade
import kotlinx.android.synthetic.main.item_grade.view.*

class GradesAdapter(
    private val grades: List<Grade>,
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
        val resources = holder.itemView.context.resources
        val color: Int
        if (grade.submissionGrade == null || grade.submissionGrade.grade == null    ) {
            holder.itemView.gradeTV.text = "-"
            color = ContextCompat.getColor(holder.itemView.context, R.color.error)
        } else {
            val gradeText = grade.submissionGrade.grade
            holder.itemView.gradeTV.text = gradeText
            if (gradeText.contains('A') || gradeText.contains('B')) {
                color = ContextCompat.getColor(holder.itemView.context, R.color.success_dark_green)
            } else {
                color = ContextCompat.getColor(holder.itemView.context, R.color.error)
            }
        }
        holder.itemView.gradeTV.setTextColor(color)
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