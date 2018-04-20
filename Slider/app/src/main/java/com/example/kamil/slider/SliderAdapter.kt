package com.example.kamil.slider

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import org.w3c.dom.Text

class SliderAdapter(private val context: Context, private val items : List<SliderItem>) : PagerAdapter() {
    private lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int {
        return items.count()
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slide_layout, container, false)
        fillWithData(view, position)
        container?.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as ConstraintLayout)
    }

    private fun fillWithData(view: View, position: Int) {
        val imageView: ImageView = view.findViewById(R.id.slideImage)
        val header: TextView = view.findViewById(R.id.headerTextView)
        val description: TextView = view.findViewById(R.id.descriptionTextView)

        val dataItem = items[position]
        imageView.setImageResource(dataItem.imageID)
        header.text = dataItem.header
        description.text = dataItem.description
    }

}