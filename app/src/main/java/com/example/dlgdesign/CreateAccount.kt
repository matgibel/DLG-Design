package com.example.dlgdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_create__account.*


var email = ArrayList<String>()

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create__account)

        Log.d(PWMONITOR,et_Confirm_Password.text.toString())
        Log.d(PWMONITOR, et_Create_Password.text.toString())




        btn_next.setOnClickListener {

            Log.d(PWERROR, "Click started")
            if(pwCheck()) {
                Log.d(PWERROR, "Check was successful")
                if (pwValidation(et_Confirm_Password.text.toString())) {

                    if(!emaiAlreadyUsed()) {
                        Log.d(PWERROR, "Validaion was successful")
                        email.add(et_Email_Address.text.toString())

                        prompt_Password_Incorrect.visibility = View.INVISIBLE
                        iv_Password_Did_Not_Match.visibility = View.INVISIBLE

                        passwordExcepted()
                    }
                }
                if (!pwValidation(et_Create_Password.text.toString())) {
                    Log.d(PWERROR, "validation not successful")
                    prompt_Password_Incorrect.visibility = View.VISIBLE
                    iv_Password_Did_Not_Match.visibility = View.VISIBLE
                }
            }
            if(!pwCheck())
                Log.d(PWERROR,"Check was not successful")
            prompt_Password_Incorrect.visibility = View.VISIBLE
            iv_Password_Did_Not_Match.visibility = View.VISIBLE



        }



    }

    private fun emaiAlreadyUsed():Boolean{

        var r = false

        for (i in email){


            if(i == et_Email_Address.text.toString()){

                Log.d(PWERROR, "Email Already Used")
                prompt_Password_Incorrect.visibility = View.VISIBLE
                iv_Password_Did_Not_Match.visibility = View.VISIBLE
                r = true
                break
            }
        }

        return r
    }


    private fun passwordUnsuccessful() {
        intent = Intent(this,CreateAccount::class.java)

        Log.d(PWERROR, "Unsuccessful retry")
        startActivity(intent)
    }


    private fun passwordExcepted() {
        intent = Intent(this, PasswordAccepted::class.java)

        Log.d(PWERROR,"Moving to next Activity")
        startActivity(intent)
    }


    private fun pwCheck(): Boolean{
        return et_Create_Password.text.toString() == et_Confirm_Password.text.toString()

    }

    private fun pwValidation(a:String): Boolean{
        return minOfEightChar(a) && atLeastOneNum(a) && atLeastOneUpper(a) && atLeastOneLower(a)


    }

    //Check if password has min of 8 char
    // 1 of 4 req
    private fun minOfEightChar(a: String): Boolean{

        return a.length >= 8
    }
    //Check if password contains at least one number
    // 2 of 4 req
    private  fun atLeastOneNum(a: String): Boolean{

        var r = false

        for (i in a){

            if (i.isDigit())
                Log.d(PWERROR,i.toString())
                r = true
                break

        }

        return  r
    }
    //Check for at least 1 UpperCase
    // 3 of 4 req
    private fun atLeastOneUpper(a: String): Boolean{


        var r = false

        for (i in a){

            if (i.isUpperCase()){
                r = true
                break
            }

        }

        return r
    }
    //Check for at least 1 LowerCase
    // 4 of 4 req
    private fun atLeastOneLower(a: String): Boolean{

        var r = false

        for (i in a){

            if (i.isLowerCase()){
                r = true
                break
            }
        }

        return  r
    }


    companion object{
        const val PWERROR = "PWERROR"
        const val PWMONITOR = "PWMONITOR"
    }

}