package com.example.wordlegamecodepathkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.isVisible
import com.github.jinatonic.confetti.ConfettoGenerator
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        fun scorekeeper(guess: String): Int {
            var score = 0
            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    score++
                }
            }
            return score
        }

        fun checkGuess(guess: String): SpannableStringBuilder {
            val builder = SpannableStringBuilder()
            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    val redSpannable = SpannableString(guess[i].toString())
                    redSpannable.setSpan(ForegroundColorSpan(Color.GREEN), 0, 1, 0)
                    builder.append(redSpannable)
                } else if (guess[i] in wordToGuess) {
                    val greenSpannable = SpannableString(guess[i].toString())
                    greenSpannable.setSpan(ForegroundColorSpan(Color.RED), 0, 1, 0)
                    builder.append(greenSpannable)
                } else {
                    val greenSpannable = SpannableString(guess[i].toString())
                    greenSpannable.setSpan(ForegroundColorSpan(Color.DKGRAY), 0, 1, 0)
                    builder.append(greenSpannable)
                }
            }
            return builder
        }

        var firstguess = true
        var x = 0
        val restart: Button = findViewById<Button>(R.id.btnRestart)
        if (firstguess) {
            findViewById<Button>(R.id.btnCheck).setOnClickListener {
                Log.i("Main ", "a $wordToGuess")
                val etInputWord: EditText = findViewById(R.id.etEnteredWord)
                val inputWord = etInputWord.text.toString()
                if (isAlpha(inputWord) and (inputWord.length == 4)) {
                    Log.i("MainActivity", isAlpha(inputWord).toString() + " check")
                    val input1: TextView = findViewById(R.id.tvFirstGuessed)
                    val status1: TextView = findViewById(R.id.tvFirstStatus)
                    val check1: TextView = findViewById(R.id.tvFirstCheck)
                    val statuscheck1: TextView = findViewById(R.id.tvFirstStatusCheck)
                    if (scorekeeper(inputWord) == 4) {
                        findViewById<Button>(R.id.btnCheck).isEnabled = false
                        etInputWord.isEnabled = false
                    }
                    input1.text = inputWord.uppercase()
                    input1.isVisible = true
                    status1.isVisible = true
                    check1.isVisible = true
                    statuscheck1.isVisible = true
                    etInputWord.setText("")
                    etInputWord.hideKeyboard()
                    check1.setText(checkGuess(inputWord.uppercase()), TextView.BufferType.SPANNABLE)
                } else {
                    Toast.makeText(this, "You can only enter 4 letters", Toast.LENGTH_SHORT).show()
                }
                findViewById<Button>(R.id.btnCheck).setOnClickListener {
                    var etInputWord1: EditText = findViewById(R.id.etEnteredWord)
                    var inputWord1 = etInputWord1.text.toString()
                    if (isAlpha(inputWord1) and (inputWord.length == 4)) {
                        var input2: TextView = findViewById(R.id.tvSecondGuessed)
                        var check2: TextView = findViewById(R.id.tvSecondCheck)
                        var statuscheck2: TextView = findViewById(R.id.tvSecondStatusCheck)
                        input2.text = inputWord1.uppercase()
                        input2.isVisible = true
                        var status2: TextView = findViewById(R.id.tvSecondStatus)
                        status2.isVisible = true
                        check2.setText(
                            checkGuess(inputWord1.uppercase()),
                            TextView.BufferType.SPANNABLE
                        )
                        check2.isVisible = true
                        statuscheck2.isVisible = true
                        etInputWord.setText("")
                        etInputWord.hideKeyboard()
                    } else {
                        Toast.makeText(this, "You can only enter 4 letters", Toast.LENGTH_SHORT)
                            .show()

                    }
                    findViewById<Button>(R.id.btnCheck).setOnClickListener {
                        var etInputWord: EditText = findViewById(R.id.etEnteredWord)
                        var inputWord = etInputWord.text.toString()
                        if (isAlpha(inputWord) and (inputWord.length == 4)) {
                            var input3: TextView = findViewById(R.id.tvThirdGuessed)
                            var check3: TextView = findViewById(R.id.tvThirdCheck)
                            input3.text = inputWord.uppercase()
                            input3.isVisible = true
                            var status3: TextView = findViewById(R.id.tvThirdStatus)
                            status3.isVisible = true
                            check3.setText(
                                checkGuess(inputWord.uppercase()),
                                TextView.BufferType.SPANNABLE
                            )
                            check3.isVisible = true
                            var statuscheck3: TextView = findViewById(R.id.tvThirdStatusCheck)
                            statuscheck3.isVisible = true
                            var actualword: TextView = findViewById(R.id.tvCorrectAns)
                            actualword.text = wordToGuess
                            actualword.isVisible = true
                            etInputWord.setText("")
                            etInputWord.hideKeyboard()
                        } else {
                            Toast.makeText(this, "You can only enter 4 letters", Toast.LENGTH_SHORT)
                                .show()

                        }
                        findViewById<Button>(R.id.btnCheck).isEnabled = false
                        findViewById<EditText>(R.id.etEnteredWord).isEnabled = false
                        restart.isVisible = true
                        restart.setOnClickListener {
                            val intent = intent
                            finish()
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun isAlpha(name: String): Boolean {
    val chars = name.toCharArray()
    for (c in chars) {
        if (!Character.isLetter(c)) {
            return false
        }
    }
    return true
}




