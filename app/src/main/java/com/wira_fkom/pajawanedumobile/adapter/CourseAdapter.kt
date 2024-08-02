package com.wira_fkom.pajawanedumobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wira_fkom.pajawanedumobile.data.Course
import com.wira_fkom.pajawanedumobile.databinding.ItemCourseBinding

class CourseAdapter(
    private val courseList: List<Course>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(course: Course)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.bind(course, listener)
    }

    override fun getItemCount(): Int = courseList.size

    class CourseViewHolder(private val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course, listener: OnItemClickListener) {
            binding.courseTitle.text = course.title
            binding.courseInstructor.text = course.instructor
            binding.coursePrice.text = course.price
            // Load image using Picasso or Glide
            // Picasso.get().load(course.imageUrl).into(binding.courseImage)

            binding.root.setOnClickListener {
                listener.onItemClick(course)
            }
        }
    }
}
