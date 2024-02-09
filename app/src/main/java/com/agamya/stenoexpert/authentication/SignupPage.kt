package com.agamya.stenoexpert.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.agamya.stenoexpert.R
import android.content.Intent
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class SignupPage : AppCompatActivity() {
    private lateinit var userName:EditText
    private lateinit var userEmail:EditText
    private lateinit var password:EditText
    private lateinit var signUpBtn:Button
    private lateinit var getOTP:Button
    private lateinit var signInBtn:TextView
    private lateinit var countdownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 60000  // 1 minute in milliseconds
    private var timerRunning: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
        userName = findViewById(R.id.name)
        userEmail = findViewById(R.id.email_id)
        password = findViewById(R.id.password)
        signUpBtn = findViewById(R.id.sign_up)
        signInBtn = findViewById(R.id.sign_in)
        getOTP = findViewById(R.id.getOTP)

        signUpBtn.setOnClickListener {
            if (userName.text.isEmpty()){
                userName.error = "This field required"
            }

            if (userEmail.text.isEmpty()){
                userEmail.error = "This field required"
            }

            if (password.text.isEmpty()){
                password.error = "This field required"
            }

            if (userName.text.isNotEmpty()&&userEmail.text.isNotEmpty()&&password.text.isNotEmpty()){
                //Logic for User Registration
                registerUser(userName.text.toString(),userEmail.text.toString(),password.text.toString())
            }
        }

        signInBtn.setOnClickListener {
            val intent = Intent(this@SignupPage,LoginPage::class.java)
            startActivity(intent)
            finish()
        }

        getOTP.setOnClickListener {
            if (userName.text.isEmpty()){
                userName.error = "This field required"
            }

            if (userEmail.text.isEmpty()){
                userEmail.error = "This field required"
            }

            if (password.text.isEmpty()){
                password.error = "This field required"
            }

            if (userName.text.isNotEmpty()&&userEmail.text.isNotEmpty()&&password.text.isNotEmpty()){
                //Logic for User Registration
                verifyOTP(userEmail.text.toString())
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        val url = "http://154.41.254.57:7000/registerUser"
        val queue = Volley.newRequestQueue(this)
        // Create the request object with POST method and the data to be sent
        val postData = JSONObject()
        postData.put("name", name)
        postData.put("email", email)
        postData.put("password", password)// Add your data fields here

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, postData,
            { response ->
                // Handle the JSON response

                try {
                    val status = response.getString("status")
                    val message = response.getString("message")
                    if (status == "Success") {
                        // Successful response, handle OTP
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignupPage,LoginPage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Failed response, handle accordingly
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Check Your Network", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            },
            { error ->
                // Handle errors
                error.printStackTrace()
            }
        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    private fun verifyOTP(email:String){
        val url = "http://154.41.254.57:7000/sendOtp"
        val queue = Volley.newRequestQueue(this)
        // Create the request object with POST method and the data to be sent
        val postData = JSONObject()
        postData.put("email", email) // Add your data fields here

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, postData,
            { response ->
                // Handle the JSON response

                try {
                    val status = response.getString("status")
                    val otp = response.getString("OTP")
                    if (status == "Success") {
                        // Successful response, handle OTP
                        showOtpDialog(otp)
                    } else {
                        // Failed response, handle accordingly
                        Toast.makeText(this, "Check Your Email or Network", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Check Your Network", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            },
            { error ->
                // Handle errors
                error.printStackTrace()
            }
        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }


    private fun showOtpDialog(otp:String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_otp, null)
        val editTextOTP = dialogView.findViewById<EditText>(R.id.editTextOTP)
        val btnVerify = dialogView.findViewById<Button>(R.id.btnVerify)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnResend = dialogView.findViewById<TextView>(R.id.btnResend)
        val tvTimer = dialogView.findViewById<TextView>(R.id.tvTimer)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false) // This line prevents closing when clicking outside the dialog
            .setTitle("OTP Verification")

        val dialog = dialogBuilder.create()
        dialog.show()

        // Set up CountDownTimer
        timeLeftInMillis = 60000;
        setupTimer(tvTimer, btnResend)
        startTimer(tvTimer, btnResend)

        // Set up button click listeners
        btnVerify.setOnClickListener {
            val enteredOtp = editTextOTP.text.toString().trim()

            // Here, you can add your logic to verify the entered OTP
            if (enteredOtp == otp) { // Replace with your actual OTP validation logic
                Toast.makeText(this, "OTP Verified!", Toast.LENGTH_SHORT).show()
                getOTP.visibility = View.GONE
                signUpBtn.visibility = View.VISIBLE
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        btnResend.setOnClickListener {

            Toast.makeText(this, "Resending OTP...", Toast.LENGTH_SHORT).show()

            startTimer(tvTimer, btnResend)
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun setupTimer(tvTimer: TextView, btnResend: TextView) {
        countdownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountdown(tvTimer, btnResend)
            }

            override fun onFinish() {
                timerRunning = false
                // Show Resend button when the timer finishes
                tvTimer.visibility = TextView.GONE
                btnResend.visibility = Button.VISIBLE
            }
        }
        updateCountdown(tvTimer, btnResend)
    }

    private fun updateCountdown(tvTimer: TextView, btnResend: TextView) {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60

        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
        tvTimer.text = timeLeftFormatted
    }

    private fun startTimer(tvTimer: TextView, btnResend: TextView) {
        countdownTimer.start()
        timerRunning = true

        // Hide Resend button and show the timer
        btnResend.visibility = Button.GONE
        tvTimer.visibility = TextView.VISIBLE
    }



}