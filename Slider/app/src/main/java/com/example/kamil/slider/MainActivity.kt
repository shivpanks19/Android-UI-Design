package com.example.kamil.slider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: SliderAdapter
    private var dots: MutableList<TextView> = mutableListOf()
    private var currentPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliderAdapter = SliderAdapter(this, listOf(
                SliderItem(R.drawable.eat_icon, "EAT", "EAT MORE"),
                SliderItem(R.drawable.sleep_icon, "SLEEP", "SLEEP MORE"),
                SliderItem(R.drawable.code_icon, "CODE", "CODE MORE")
        ))

        slideViewPager.adapter = sliderAdapter

        slideViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                setPage(position)
            }
        })

        // set current page
        setPage(currentPage)

        nextButton.setOnClickListener { _ -> slideViewPager.currentItem = currentPage + 1 }
        backButton.setOnClickListener { _ -> slideViewPager.currentItem = currentPage - 1 }
    }

    private fun addDotsIndicator(position: Int) {
        // TODO REFACTORING!
        dots.clear()
        dotsLayout.removeAllViews()
        for (i in 0 until sliderAdapter.count) {
            var textView = TextView(this)
            textView.text = "*"
            textView.textSize = 35F
            textView.setTextColor(resources.getColor(R.color.colorTransparentWhite, theme))
            dots.add(textView)
            dotsLayout.addView(textView)
        }
        dots[position].setTextColor(resources.getColor(R.color.colorWhite, theme))
    }

    private fun setPage(position: Int){
        addDotsIndicator(position)
        currentPage = position

        // enable and disable buttons
        when (currentPage) {
            0 -> onFirstPage()
            sliderAdapter.count - 1 -> onLastPage()
            else -> onInsidePages()
        }
    }

    private fun onFirstPage() {
        // disable backButton
        // enable nextButton
        backButton.isEnabled = false
        backButton.visibility = View.INVISIBLE
        nextButton.isEnabled = true
    }

    private fun onInsidePages() {
        // enable backButton
        // enable nextButton
        backButton.visibility = View.VISIBLE
        backButton.isEnabled = true
        nextButton.visibility = View.VISIBLE
        nextButton.isEnabled = true
    }

    private fun onLastPage() {
        // enable backButton
        // disable nextButton
        backButton.isEnabled = true
        nextButton.visibility = View.INVISIBLE
        nextButton.isEnabled = false
    }
}
