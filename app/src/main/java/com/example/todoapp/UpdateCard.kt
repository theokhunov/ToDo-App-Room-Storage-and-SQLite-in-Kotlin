package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.todoapp.Adapter.DataObject
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val posit = intent.getIntExtra("id", -1)

        if (posit != -1) {
            val title = DataObject.getData(posit).title
            val priority = DataObject.getData(posit).priority

            create_title.setText(title)
            create_priority.setText(priority)


            delete_button.setOnClickListener {
                DataObject.deleteData(posit)

                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            posit + 1,
                            create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }

                myIntent()
            }


            update_button.setOnClickListener {

                DataObject.updateData(posit,
                    create_title.text.toString(),
                create_priority.text.toString()
                    )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            posit + 1, create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }


                myIntent()
            }


        }

    }

    fun myIntent() {
val intent = Intent(this,MainActivity::class.java)
startActivity(intent)
    }


}