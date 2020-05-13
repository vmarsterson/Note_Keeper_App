package com.example.pluralsitenotekeeperapplication

class CourseInfo constructor(val courseID: String, val title: String) {
    override fun toString(): String {
        return title
    }
}
// when the instances of the courseinfo class are displayed in the spinner we need to indicate what value from the instance we want displayed
// for this we need a string representation of our courseinfo class, We need to override the toString method by replacing it with the title which will now appear in the spinner
// We can make this a data class as it only has vals or vars in the primary constructor, and even though we've written our own toString() method, it will not generate a toString

// These properties cannot be changed
data class NoteInfo constructor(var course: CourseInfo? = null, var title: String? = null, var text: String? = null)
// These properties are mutable

