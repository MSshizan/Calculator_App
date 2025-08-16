package com.example.simplecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable


class CalculatprViewModel : ViewModel() {

    private val _calucaltion = MutableLiveData("")
    val calculation: LiveData<String> = _calucaltion


    private val _result = MutableLiveData("0")
    val result: LiveData<String> = _result

    private var lastResult: String? = null

    fun onclick(btn: String) {


        _calucaltion.value?.let {

            when (btn) {

                "AC" -> return allClear()


                "C" -> return delete(it)


                "=" -> return calculation(it)


            }


            if (isOperator(btn.first()) && it.isNotEmpty() && isOperator(it.last())) {
                _calucaltion.value = it.dropLast(1) + btn
            } else if (_calucaltion.value == result.value && isNumber(btn)) {
                _calucaltion.value = btn
                return

            } else {

                _calucaltion.value = it + btn
            }

        }


    }

    private fun allClear() {
        _calucaltion.value = ""
        _result.value = "0"

    }

    private fun delete(current: String) {
        if (current.isNotEmpty()) {
            _calucaltion.value = current.substring(0, current.length - 1)
        }
    }

    private fun calculation(current: String) {
        try {
            _result.value = calculating(current);
            _calucaltion.value = result.value
            lastResult = _result.value
            return
        } catch (_: Exception) {
            _result.value = _calucaltion.value
            return
        }

    }

    private fun calculating(calculation: String): String {
        val rhino = Context.enter()
        rhino.optimizationLevel = -1
        val scope: Scriptable = rhino.initStandardObjects()
        var result = rhino.evaluateString(scope, calculation, "JavaScript", 1, null).toString()
        if (result.endsWith(".0")) {
            result = result.replace(".0", "")

        }
        return result
    }


    private fun isOperator(char: Char): Boolean {
        return char in listOf('+', '-', '*', '/', '%')
    }

    private fun isNumber(btn: String): Boolean {
        return btn.all { it in '0'..'9' || it == '.' }
    }

}





