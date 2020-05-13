package com.example.pluralsitenotekeeperapplication

import android.content.Intent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, MainActivity::class.java)
//            we're creating a new instance of the Intent class, passing in two parameters:
//            1 context - 'this;
//            2 type information for our main activity class
//            android api is expecting a java description of a class as it;'s written in java
            startActivity(activityIntent)
        }

        listNotes.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)
//        First param, context (this for our activity)
//        2 build in layout
//        3 the data - the notes collection from our DataManager

        listNotes.setOnItemClickListener{parent, view, position, id ->
            val activityIntent = Intent(this, MainActivity::class.java)
            activityIntent.putExtra(NOTE_POSITION, position)
            startActivity(activityIntent)
        }
    }
//    This method accepts a reference to the implementation face" AdapterView.OnItemClickListener which is a function interface (an interface that has one method we override - very specific task)
//    We don't need to implement this interface, we can use a lambda expression instead
//    To use a lambda, we use curly brackets after the method rather than ()
//    We then specify our 2 params; 1 parents (list view itself), 2 view (view within the list view selected, position of item selected, ID)
//     Method implementation is ->
//    We launch activity with an intent (sign once variable activityIntent), create an instance of intent Intent()
//    this takes two params: the context for the ntoe list activity, and class inforatmion for the activity you want to start (Main), which is passed in a java compatible way (.java)
//    In this case, creating a intent isn't enough
//    We want to show the note that was selected so we need to provide infotatmoin about the ntoe - aka it's position
//    Start with  activity intent reference, associate an extra (putExtra()), with the name of the activity intent, and then taking the position that has been passed in in our llambda expression
// Extras rely on name value pairs. With the name, we're using a literal string - it woud be better to have a constant
//    We could go to main activity class and add an assign once property that gives a name to the string
//    we can define a constant outside of any class with Kotlin so we'll try this - find this in the Consts.kt file

    override fun onResume() {
        super.onResume()
        (listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}
