package com.example.element.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.element.Element
import com.example.element.R

class Adapter(_elements: ArrayList<Element>) : RecyclerView.Adapter<Adapter.ElementViewHolder>() {

    private var elements: ArrayList<Element> = _elements

    override fun getItemCount(): Int {
        return this.elements.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.content_main, parent, false)
        return ElementViewHolder(view)
    }

    override fun onBindViewHolder(elementViewHolder: ElementViewHolder, i: Int) {
        elementViewHolder.nameA.setText(elements[i].name)
        elementViewHolder.valueA.text = elements[i].value.toString()

        elementViewHolder.minusA.setOnClickListener {
            val value = elementViewHolder.textViewA.text.toString()
            var result = value.toInt()
            result--
            elements[i].value = result
            elementViewHolder.textViewA.text = result.toString()
        }
        elementViewHolder.plusA.setOnClickListener {
            val value = elementViewHolder.textViewA.text.toString()
            var result = value.toInt()
            result++
            elements[i].value = result
            elementViewHolder.textViewA.text = result.toString()
        }
        elementViewHolder.deleteA.setOnClickListener {
            elements.removeAt(i)
            update()
        }

        elementViewHolder.editTextA.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                var name = elementViewHolder.editTextA.text.toString()
                elements[i].name = name
            }
        })
    }

    fun update() {
        this.notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    inner class ElementViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var nameA: EditText = itemView.findViewById(R.id.editText)
        internal var valueA: TextView = itemView.findViewById(R.id.textView)
        internal val cardView: CardView = itemView.findViewById(R.id.cardView)
        internal val minusA: Button = itemView.findViewById(R.id.minus)
        internal val plusA: Button = itemView.findViewById(R.id.plus)
        internal val textViewA: TextView = itemView.findViewById(R.id.textView)
        internal val deleteA: Button = itemView.findViewById(R.id.delete)
        internal val editTextA: EditText = itemView.findViewById(R.id.editText)
    }
}