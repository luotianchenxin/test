package com.example.rov

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout

class FunctionButton : RelativeLayout {
    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr:Int):super(context,attrs, defStyleAttr){
        LayoutInflater.from(context).inflate(R.layout.function_button_view, this, true)
    }



}