package com.example.kamil.profilescreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.WindowManager
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    private var isExpanded = false
    private val layout1 = ConstraintSet()
    private val layout2 = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraintLayout : ConstraintLayout = findViewById(R.id.constraintLayout)
        val coverImageView : ImageView = findViewById(R.id.coverImageView)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        layout1.clone(constraintLayout)
        layout2.clone(this@MainActivity, R.layout.activity_main_expanded)

        coverImageView.setOnClickListener {
            when(isExpanded){
                true -> {
                    TransitionManager.beginDelayedTransition(constraintLayout)
                    layout1.applyTo(constraintLayout)
                }
                false -> {
                    TransitionManager.beginDelayedTransition(constraintLayout)
                    layout2.applyTo(constraintLayout)
                }
            }
            isExpanded = !isExpanded
        }
    }
}
