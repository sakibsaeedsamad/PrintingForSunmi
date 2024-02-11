package com.sakib.printingforsunmi

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.sakib.printingforsunmi.R.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvText: TextView
    private lateinit var btnPrint: TextView

    val sunmiHelper = SunmiPrinterHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
         tvText = findViewById(id.tvText)
         btnPrint = findViewById(id.btnPrint)
        tvText.setText(" This is sample printing")

        sunmiHelper.initSunmiPrinterService(this)




        btnPrint.setOnClickListener {

            sunmiHelper.initPrinter()
            sunmiHelper.printBitmap(getLayoutBitmap(tvText), 2)
            /**
             * alignment: 0 to the left, 1 to the center, and 2 to the right.
             */
            sunmiHelper.cutpaper()
            sunmiHelper.feedPaper()
        }


    }
    fun getLayoutBitmap(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }
}