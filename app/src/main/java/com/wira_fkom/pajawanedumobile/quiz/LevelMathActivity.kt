package com.wira_fkom.pajawanedumobile.quiz

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.adapter.Level
import com.wira_fkom.pajawanedumobile.adapter.LevelAdapter
import com.wira_fkom.pajawanedumobile.databinding.ActivityLevelMathBinding

class LevelMathActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelMathBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelMathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val levels = listOf(
            Level(1, "Level 1"),
            Level(2, "Level 2"),
            Level(3, "Level 3"),
            Level(4, "Level 4"),
            Level(5, "Level 5"),
            Level(6, "Level 6"),
            Level(7, "Level 7"),
            Level(8, "Level 8"),
            Level(9, "Level 9"),
            Level(10, "Level 10")
        )

        // Set up RecyclerView
        binding.levelRV.layoutManager = GridLayoutManager(this, 2)
        binding.levelRV.adapter = LevelAdapter(levels) { level ->
            // Handle item click, example:
            // Toast.makeText(this, "Clicked: ${level.name}", Toast.LENGTH_SHORT).show()
        }

        // Add spacing item decoration
        binding.levelRV.addItemDecoration(GridSpacingItemDecoration(2, 16))
    }

    // Define GridSpacingItemDecoration as an inner class or move it to a separate file
    class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        }
    }
}
