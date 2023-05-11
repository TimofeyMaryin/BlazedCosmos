package com.chvanova.blazedcosmos

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class FriendActivityViewModel : ViewModel()  {

    var fieldOfDots = MutableList<Int>(42) {index -> 0}
    var fieldOfDotsForCheck = MutableList<Int>(156) {index -> 0}
    //39 - is zero element, left-up corner
    var columnPress : Int = 8
    var whoseTurn : Int = 1
    var winner = 0



    fun determinePlaceOfDot(){
        whoseTurn = if (whoseTurn == 1) 2 else 1

        for (i in columnPress*6+5 downTo columnPress*6)
            if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; break}
    }

    fun compMove(){
        var nearMoveDone = false
        whoseTurn = if (whoseTurn == 1) 2 else 1
        columnPress = Random.nextInt(columnPress-1, columnPress+2)
        if (columnPress<0) columnPress = 0
        if (columnPress>6) columnPress = 6
        for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}

        if (!nearMoveDone) {columnPress=4; for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}}
        if (!nearMoveDone) {columnPress=4; for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}}
        if (!nearMoveDone) {columnPress=4; for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}}
        if (!nearMoveDone) {columnPress=4; for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}}
        if (!nearMoveDone) {columnPress=4; for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}}
        if (!nearMoveDone) {columnPress=4; for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}}
        if (!nearMoveDone) {columnPress=4; for (i in columnPress*6+5 downTo columnPress*6) if (fieldOfDots[i] == 0) {fieldOfDots[i] = whoseTurn; nearMoveDone = true; break}}

    }


    fun evaluateWinner(){

        for (ii in 39 ..44)
            fieldOfDotsForCheck[ii] = fieldOfDots[ii-39]
        for (ii in 51 ..56)
            fieldOfDotsForCheck[ii] = fieldOfDots[ii-51+6]
        for (ii in 63 ..68)
            fieldOfDotsForCheck[ii] = fieldOfDots[ii-63+12]
        for (ii in 75 ..80)
            fieldOfDotsForCheck[ii] = fieldOfDots[ii-75+18]
        for (ii in 87 ..92)
            fieldOfDotsForCheck[ii] = fieldOfDots[ii-87+24]
        for (ii in 99 ..104)
            fieldOfDotsForCheck[ii] = fieldOfDots[ii-99+30]
        for (ii in 111 ..116)
            fieldOfDotsForCheck[ii] = fieldOfDots[ii-111+36]

        for (i in 3..9) {
            for (ii in (i * 12 + 3)..(i * 12 + 8)) {
                for (iii in 0..3) {
                    if ((fieldOfDotsForCheck[ii - 3 + iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 2 + iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 1 + iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii + iii] == whoseTurn)
                    ) winner = whoseTurn

                    if ((fieldOfDotsForCheck[ii - 36 + iii * 12] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 24 + iii * 12] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 12 + iii * 12] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii + iii * 12] == whoseTurn)
                    ) winner = whoseTurn
                    if ((fieldOfDotsForCheck[ii - 39 + iii * 12 + iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 26 + iii * 12 + iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 13 + iii * 12 + iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii + iii * 12 + iii] == whoseTurn)
                    ) winner = whoseTurn

                    if ((fieldOfDotsForCheck[ii - 33 + iii * 12 - iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 22 + iii * 12 - iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii - 11 + iii * 12 - iii] == whoseTurn) &&
                        (fieldOfDotsForCheck[ii + iii * 12 - iii] == whoseTurn)
                    ) winner = whoseTurn
                }


            }
        }

    }





}