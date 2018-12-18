Iaido helper application / IaiKai documentation	Jere Raassina

1.	Description
2.	Project targets
3.	Project achievements
4.	Project problems
5.	Further development
6.	Closing words


1.	Project Description and ideas

1.1.	The art
Iaido is a Japanese martial art focused of the drawing of the sword and reacting to the possible threat’s actions. The art dates all the way to the 16th century and has many styles or Ryus that many of them are still practiced to this day. In 1960s the Japanese kendo federation decided to form a standard for many of the old styles aimed for modern practice with very strict rules to follow. The new style offers 12 katas or techniques for different situations composed of several different old styles of Iaido.

1.2.	The idea
The modern ZNKR style of Iaido offers many adequate points to follow. These points are important for the practitioner’s development and are also used in the competitions and gradings. The points and details are offered in paper form both in Japanese and in few different translated languages, but not in any handy digital or application form. The practitioners study the grading points very actively and the need for a helpful source and tools have become apparent. 


1.3.	The purpose

The Iaido helper is an android application that offers multiple helpful features and tools for a ZNKR style Iaido practitioners. The application’s main attribute is to offer an easy access for the techniques and their grading points while also offering a video reference for correct actions and options to take grading point specific notes and record and store personal videos for comparison and further inspection. The application also offers other helpful features such as tools for competition and graduation by providing helpful timers and competition notes.
The application is coded in Java and created by using Android Studio.

2.	Project targets

The target of the project was to follow the original idea as well as possible and fulfil the project requirements while still bringing new solutions and implementations to the project by learning new tools and technologies. 

The application was expected to offer:
-	Multiple different views by using fragments and concrete views.
-	To implement different composite views such as ListViews and GridViews and offer custom adapters for their purposes.
-	Offer A SQLite database solution such as a Room model.
-	Provide networking functionality such as data fetching.
-	Use constraint layout for view UI construction.
-	Use a third-party API or framework that was previously unknow.
-	Use a MVC or MVVM design pattern.

3.	Project achievements

The project achieved its targets well and managed to offer most of the original ideas if not completely but at least to some degree.
The application offers multiple activities by using different views and fragments, one of the most important being the YouTube fragment that offers not only reusable fragment but also networking and API functionality by fetching the corresponding technique videos by using the YouTube API.
The app also uses several composite view models such as a list view for displaying the techniques, an expandable list view for the grading points and their notes and a grid view for the video gallery.
Multiple ways of storing application data were not only provided by using Room database for storing the graduation point note data using a singleton instance but by also using external device storage for the video files and Android shared preferences for the competition and training notes. The static technique and grading point data is stored in the application runtime memory and is populated and accessed by using a global model singleton class.
Constraint layout was used for all the view layouts and the Model View Controller pattern was used by providing models for the different data items and some of their functionality, by using multiple view controllers and by providing the layouts and the corresponding view adapters.

4.	Project problems

As I was also learning at the same time as developing the application it was no surprise that multiple problems and adversities were faced.


Some of the problems included:
-	The functionalities not working as desired while not getting any error response from the application or the development environment.
-	The handling of the data or content between different adapters, fragments and activities.
-	Problems with the proper Room database implementation and functionality, database schema migration and query multithreading.
-	Different usage permissions such as the file read and write functionality.
-	Combability issues with older Android SDK versions.
-	Thread optimization for trying to get the functionality to work in the right order and the right time.

5.	Further development ideas and possibilities
In the end the application was developed in fairly short time, with limited knowledge and during an ongoing learning process. Some of the functionalities such as the database inserting, and population functionality could have been developed differently or by taken a different approach. Multiple new features such as the synchronization the provided and the user videos, calculation and snapshots of different sword or body angles could be added. Of course, the art offering such a vast number of strict rules and points, more basic information and tools could be added in the future.

6.	Closing words
In the end the project offered nice hands on experience and a good conclusion for the introduction to mobile development. Most of the topics introduced during the course were used and many new ways and methods were learned during the project.

