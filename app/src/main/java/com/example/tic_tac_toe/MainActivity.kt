package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.ViewStubCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var board: Array<Array<Button>>

    var PLAYER=true
    var TURN_COUNT=0

    var boardStatus=Array(3){ IntArray(3) }
    //var dsplytv=findViewById<TextView>(R.id.displayTv)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn1=findViewById<Button>(R.id.button1)
        var btn2=findViewById<Button>(R.id.button2)
        var btn3=findViewById<Button>(R.id.button3)
        var btn4=findViewById<Button>(R.id.button4)
        var btn5=findViewById<Button>(R.id.button5)
        var btn6=findViewById<Button>(R.id.button6)
        var btn7=findViewById<Button>(R.id.button7)
        var btn8=findViewById<Button>(R.id.button8)
        var btn9=findViewById<Button>(R.id.button9)
        var reset=findViewById<Button>(R.id.resetBtn)

        board= arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )
        for(i in board){
            for(button in i) {
                button.setOnClickListener(this)
            }
        }
        initialiseBoardStatus()
        reset.setOnClickListener {
            TURN_COUNT=0
            PLAYER=true
            initialiseBoardStatus()
        }
    }

    private fun initialiseBoardStatus() {
        for (i in 0..2){
            for(j in 0..2) {
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button1 -> {
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2 -> {
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3 -> {
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4 -> {
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5 -> {
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6 -> {
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7 -> {
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8 -> {
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9 -> {
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        PLAYER=!PLAYER
        TURN_COUNT++

        if(PLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player Y Turn")
        }

        if(TURN_COUNT==9)
            updateDisplay("Game Draw ! ")

        checkWinner()

    }

    private fun checkWinner() {
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]){
                if(boardStatus[i][0]==1) {
                    updateDisplay("Player X Wins")
                    break
                }
                else
                    if(boardStatus[i][0]==0) {
                        updateDisplay("Player Y Wins")
                        break
                    }
            }
        }

        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]){
                if(boardStatus[0][i]==1) {
                    updateDisplay("Player X Wins")
                    break
                }
                else
                    if(boardStatus[0][i]==0) {
                        updateDisplay("Player Y Wins")
                        break
                    }
            }
        }

        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][2]){
            if(boardStatus[0][0]==1) {
                updateDisplay("Player X Wins")
            }
            else
                if(boardStatus[0][0]==0) {
                    updateDisplay("Player Y Wins")
                }
        }
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][0]){
            if(boardStatus[0][2]==1) {
                updateDisplay("Player X Wins")
            }
            else
                if(boardStatus[0][2]==0) {
                    updateDisplay("Player Y Wins")
                }
        }

    }

    private fun updateDisplay(s: String) {
        displayTv.text = s
        if(s.contains("Wins"))
            disableButtons()
    }

    private fun disableButtons() {
        for(i in board){
            for(button in i) {
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "0"
        val value = if(player) 1 else 0
        board[row][col].apply{
            isEnabled=false
            setText(text)

        }
        boardStatus[row][col]=value


    }
}