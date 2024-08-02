//package com.wira_fkom.pajawanedumobile.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.wira_fkom.pajawanedumobile.data.Course
//import com.wira_fkom.pajawanedumobile.databinding.ItemCourseBinding
//
//class ModulAdapter(private val courses: List<Course>) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
//
//    class CourseViewHolder(val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
//        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CourseViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CourseAdapter.CourseViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
//        val course = courses[position]
//        holder.binding.courseImage.setImageResource(course.imageResId)
//        holder.binding.courseTitle.text = course.title
//        holder.binding.courseDuration.text = course.duration
//        holder.binding.courseDescription.text = course.description
//    }
//
//    override fun getItemCount(): Int = courses.size
//}