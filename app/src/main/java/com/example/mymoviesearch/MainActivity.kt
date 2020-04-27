package com.example.mymoviesearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var storedValue: Int = 0

    private lateinit var titles: Array<TextView>
    private lateinit var buttons: Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titles = arrayOf(
            findViewById<TextView>(R.id.movie_title1),
            findViewById<TextView>(R.id.movie_title2),
            findViewById<TextView>(R.id.movie_title3))

         buttons = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3))

        val oclBtn: OnClickListener = OnClickListener() {

            storedValue = when (it) {
                buttons[0] -> 1;
                buttons[1] -> 2;
                buttons[2] -> 3;
                else -> 0;
            }
            selectionMovie(storedValue, true)

        }

        buttons.forEach { it.setOnClickListener(oclBtn) }

        savedInstanceState?.apply {
            storedValue = this.getInt(MESS_KEY, 0)
            selectionMovie(storedValue, false)
            Log.d(TAG, "onCreate:[$storedValue]")
        }


    }

     override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        storedValue = savedInstanceState.getInt(MESS_KEY,0)
        selectionMovie(storedValue, false)
        Log.d(TAG, "onRestoreInstanceState:[$storedValue]")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(MESS_KEY, storedValue)
        Log.d(TAG, "onSaveInstanceState:[$storedValue]")
    }

    private fun selectionMovie(i: Int, isShowDetails: Boolean) {

        // обнулить цвет для всех наименований из списка
        titles.forEach { it.setTextColor(getColor(R.color.colorText)) }

        if (i in 1..titles.size) {

            // выделить цветом название выбранного фильма
            val selectedTitle: TextView = titles[i - 1]
            selectedTitle.setTextColor(getColor(R.color.colorAccent))

            // показать детали фильма в отдельном окне
            if (isShowDetails) {
                val intent = Intent(this, MovieActivity::class.java)
                intent.putExtra("movie_title", selectedTitle.text)
                startActivity(intent)
            }
        }
    }

    fun clickImplicit(view: View) {
        val textMessage = "Сообщение..."
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val title = resources.getString(R.string.chooser_title)
        // Создаем Intent для отображения диалога выбора.
        val chooser = Intent.createChooser(sendIntent, title)
        // Проверяем, что intent может быть успешно обработан
        sendIntent.resolveActivity(packageManager)?.let {
            startActivity(chooser)
        }
    }
    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val MESS_KEY = "saved_mess_key"
    }

 }




