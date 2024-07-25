package com.wira_fkom.pajawanedumobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wira_fkom.pajawanedumobile.data.Course
import com.wira_fkom.pajawanedumobile.R

class CourseAdapter(
    private val courseList: List<Course>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(course: Course)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.bind(course, listener)
    }

    override fun getItemCount(): Int = courseList.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.course_title)
        private val instructor: TextView = itemView.findViewById(R.id.course_instructor)
        private val price: TextView = itemView.findViewById(R.id.course_price)
        private val image: ImageView = itemView.findViewById(R.id.course_image)

        fun bind(course: Course, listener: OnItemClickListener) {
            title.text = course.title
            instructor.text = course.instructor
            price.text = course.price
            // Load image using Picasso or Glide
            // Picasso.get().load(course.imageUrl).into(image)

            itemView.setOnClickListener {
                listener.onItemClick(course)
            }
        }
    }
}
