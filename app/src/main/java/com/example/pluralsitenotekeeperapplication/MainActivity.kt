package com.example.pluralsitenotekeeperapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
//        UI initialisation occurs here
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        this is where we indicate which layout resource we want to use
        setSupportActionBar(toolbar)

//        val dm = DataManager()
//        we can't keep this line in when we change our datamanager from a class to an object
//        here we are assigning a reference to a new instance of data manager
//        val adapterCourses = ArrayAdapter<CourseInfo>(this, android.R.layout.simple_spinner_item, dm.courses.values.toList())
//        here, we are creating an adapter,
//        Array adapter - you have to specifiy the type you're going to create, and this for us is courseinfo, we need pass:
//        1) the context (the creatiton state of certain kinds of android classes, often we can just pass the activities 'this' reference
//        2) We can create our own custom laytout, but we can use a built in layout android.R.layout.whatever_you_want
//        3) The data we want to display, for us this is the data manager courses. We don't want to do a look up we just want them all so we use 'values'.
//        to convert this into an array method we need to convert it to a list (`.list()` function
//        We also can't use the 'dm' val as we've just removed it having changed data manager into an object so we change it to:
        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )
//        we use type name and this will always reference the same instance of the datamanager now objectcN


        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        We need to provide a second layout source - the dropdown list as above
        spinnerCourses.adapter = adapterCourses
//        The adapted is now associated with our spinner

//        We need to look at our courseinfo class

        notePosition =
            savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?: intent.getIntExtra(
                NOTE_POSITION,
                POSITION_NOT_SET
            )

//        we know the intent that started in the activity is available in the intent property
//        from the intent, we want to get an extra, we want the name, and then the value that we'll return if there is no value (as we know there won't  always be a value set)
//        our property ntoe position will either have the value of extra note position of the 'position not set' string
//         we therefore want ot create an if statement that  says to display the note if we have a position, and not to if we don;t:

        if (notePosition != POSITION_NOT_SET)
            displayNote()
        else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    //Here, We're adding an empty note to our data store - our data manager notes collection so that the last member of our notes collection is our empty note.
//
//We then set the notePosition property to have the last index of the data manager notes property.

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION, notePosition)
    }

//    make sure you select the right override method
//    Make sure you still call the base class implementation (super line) before we say what we want
//    We take the Bundle that's being passed into the method (outState)
    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)
    }
//    how we display the note
//    we just want the values in the courses hash map from rhe spinner
//    you ca get the inde of values with indedxof which we pass in the course position of our note
//    aka we return the string values of our note title and text and then select the appropriate course poition from our spinner

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
//        menuInflater job is to inflate menu objects
//        first param - found in the R class (R.menu) and our menu resource is called menu_main
//        2 - menu you want to inflate it into - which for us is the menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
            //        checking the menu item and looking to see what value it might contain
//        here we're using a when statement which says, when the action_settings is mathced, we return true but otherwise we return super.onOptionsItemsSelected(item)
//        aka the base class implementation
//        we want to add our check for our action_next item menu in whcih we call a function and return true
        }

    }

    private fun moveNext() {
        ++notePosition
//        here we're increasing the value of note position
        displayNote()
        invalidateOptionsMenu()
    }

//    display the note and then make sure it's not the last note and therefore if we need to change the icon

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if(menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = textNoteTitle.text.toString()
        note.text = textNoteText.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
    }
    /*
    We create a variable called note, and use our note position properly to get the currently positioned note from our data property

    First we want o set our note title to `textNoteTitle` which has a property `text` - this is an editable, but we want it to be a string so we also add `toString()`

    We now want to get the note text - we do the same but with textNoteText

    The course is in spinner courses, and has a property selected item which has a reference to the selected course but it doesn't given a reference as a type
    CourseInfo so we need to cast it to CourseInfo using `as` operator.
     */
}
