package com.example.pluralsitenotekeeperapplication

object DataManager {

    //    properties:
    val courses = HashMap<String, CourseInfo>()
    //    HashMap is a generic type that allows us to specify the type we want to use in the type- new instance of a hashmap which has string values and courseinfo is the type that will be stored
//    the () creates an instance of this
    val notes = ArrayList<NoteInfo>()
//    Array list is generic type and allows us to store values with an index, the array list will hold note info, and we create an instance with ()

    init {
        initializeCourses()
        initializeNotes()
    }

//    any time an instance of our datamanager is created, we run the above initialisation code which means we automatically have the courses below to populate our courses hashmap
// initialiser blocks cannot accept parameters as they are created on initialisation rather than being called
//    They can however access parameters in the primary constructor, and properties within the class
//     type initialisation occurs to a combination type consurtor with initialisation vaklues along with code that runs inside our initialiser block

    //    function:
    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseID, course)
//    we want to add the course to our courses collection. The set method is the value we'll use to look up the course (the id), second param is the course itself.

        course =
            CourseInfo(courseID = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseID, course)

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseID = "Java_lang")
        courses.set(course.courseID, course)

//    Here we're using named parameters so it doesnt matter what order they come in

        course = CourseInfo("java_core", "Java Fundamentals: THe Core Platform")
        courses.set(course.courseID, course)
    }


    private fun initializeNotes() {

        var course = courses["android_intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        course = courses["android_async"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)


    }
}
// By default members are public (member visibility), but we don't want other people to access it so we make it private
// want to create instance of our courses

// to create an initializer block