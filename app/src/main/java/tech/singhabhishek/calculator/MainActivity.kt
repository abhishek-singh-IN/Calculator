package tech.singhabhishek.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastnumeric:Boolean=false
    var lastdot:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view:View)
    {
        val textinput=findViewById<TextView>(R.id.tvinput)
        textinput.append((view as Button).text)
        lastnumeric=true
    }

    fun onClear(view: View)
    {
        val clearbutton=findViewById<TextView>(R.id.tvinput)
        clearbutton.text=""
        lastnumeric=false
        lastdot=false
    }

    fun onDecimalPoint(view:View){
        val decimalbutton=findViewById<TextView>(R.id.tvinput)
        if(lastnumeric && !lastdot)
        {
            decimalbutton.append(".")
            lastnumeric=false
            lastdot=true
        }
    }

    fun onEquals(view: View){
        if(lastnumeric){
            val tvequalbutton=findViewById<TextView>(R.id.tvinput)
            var tvvalue=tvequalbutton.text.toString()
            var prefix=""

            try {
                if(tvvalue.startsWith("-")){
                    prefix="-"
                    tvvalue=tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                    val splitvalue=tvvalue.split("-")

                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    tvequalbutton.text=removezeroafterdot((one.toDouble()-two.toDouble()).toString())

                }else if(tvvalue.contains("+")){
                    val splitvalue=tvvalue.split("+")

                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    tvequalbutton.text=removezeroafterdot((one.toDouble()+two.toDouble()).toString())

                }else if(tvvalue.contains("*")){
                    val splitvalue=tvvalue.split("*")

                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    tvequalbutton.text=removezeroafterdot((one.toDouble()*two.toDouble()).toString())

                }else if(tvvalue.contains("/")){
                    val splitvalue=tvvalue.split("/")

                    var one=splitvalue[0]
                    var two=splitvalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    tvequalbutton.text=removezeroafterdot((one.toDouble()/two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        val operatorbutton=findViewById<TextView>(R.id.tvinput)
        if(lastnumeric && !ismainoperatorAdded(operatorbutton.text.toString())){
            operatorbutton.append((view as Button).text)
            lastnumeric=false
            lastdot=false
        }
    }

    private fun removezeroafterdot(result : String):String{
        var value=result
        if(result.contains("."))
        {
            while(result.endsWith("0") && result.contains("."))
            {
                value=result.substring(0,result.length-1)
            }
            if(value.endsWith("."))
            {
                value=result.substring(0,result.length-1)
            }

        }
        return value
    }

    private fun ismainoperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")) {
            false

            }else{
                value.contains("+") || value.contains("-")
                        || value.contains("/") || value.contains("*")
            }
    }
}