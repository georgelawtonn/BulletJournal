
Introducing the Plants Bullet Journal App!

Are you tired of juggling multiple tasks, events, and notes and don't have time to make a handwritten 
journal look pretty? Do you long for a seamless and efficient way to plan your week? Look no further!
With our app, recording your week becomes a breeze. Whether it is important events, daily tasks, inspiring quotes, 
or just a reminder note, our simple but elegant journal allows you to fill in just a few details to provide you the 
best bullet journal you will ever have! One of our biggest features is the ability to sort through your events and 
tasks using personalized categories that you create for the week. In addition, our filtering system ensures you can 
quickly locate any specific entries, saving you valuable time and effort.

On top of all those amazing features, we like to introduce the flexibility that our app provides: you can easily edit
existing events and tasks whenever you wish. Made a mistake? No problem! You can also delete any tasks or events that 
are no longer relevant, keeping your journal up to date and easy to navigate. Speaking of easy navigation, the menu bar
at the top of the app allows you to get to all the features in the app just in a few clicks. If you don't want to click?
Use our built-in keyboard shortcuts! Providing you with the most efficient and seamless journaling process you would 
ever think of!

Upon opening the app, you'll be greeted with an inviting warm orange welcome screen, setting the tone for a 
positive and productive week ahead. To add in an extra layer of security, our app allows yuo to set a password to 
lock your week, ensuring your personal information stays private. And you can save your week to a designated folder 
and continue at any time.

Don't miss out on the perfect opportunity to revolutionize your planning routine. Our Journal launch is scheduled for 
6/23/2023! Don't forget to download your own amazing Plants Bullet Journal App!

Screen Shots in Features Folder because the pngs do not show on our side of the github so we want to ensure that they 
are delivered. 

Solid Principles 

Single Responsibility Principle: 
We followed the single responsibility principle for this assignment as we created necessary class to eliminate 
any class that has multiple responsibilities. We made individual classes for each of the popup windows that we have,
controller and view classes for each scene, formatting class to translate JSON messages,
and separate classes for reader and writer. All the classes are only responsible for one responsibility and the methods 
are not longer than 40 lines. We added helpers when necessary to the methods so that the methods are not too long and
complicated. 

Open-Close Principle:
We demonstrated understanding for open close principle by implementing abstract classes, so it is easier for to 
add modification without changing the existing functionality. It can be seen through the multiple popup controllers 
that implement the abstract class APopup. Additionally, it can be seen through various dependency injections where we
supply most of the constructors with an instance instead of creating a new instance in the class. 

Liskov Substitution Principle:
It would not break the program if we sub in the abstract super classes with its subclasses because the subclass 
adhere to the contracts that superclasses created. They also do not change any of the return types of the inherited 
methods or throwing new exceptions that are not thrown in the superclass. 

Interface Segregation Principle:
We do not force any of the subclasses to inherit any methods that does not provide any use to them. Every subclass 
uses most of the methods that are implemented in its superclass and will only override the super class method when 
needed. Therefore, we believe that we stick to the interface segregation principle because we strictly organized 
our super and subclasses based on the methods they use. 

Dependency Inversion Principle:
As seen in our program (each of the popup controller subclasses and planner entry subclasses), none of the higher 
classes rely on its lower counterparts. Additionally, the abstractions do not depend on details which allow for them 
to be more flexible and interchangeable for implementations. 

Future Implementations
One feature that we can implement in the future is the horizontal and vertical Layout. This could be easily implemented
by creating two other scenes in scene builder with a horizontal/vertical layout with the days (listviews in our case)
lined up against each other in one row or stacked on top of each other. Then, we just have to give the user a choice 
when entering the app to decide which layout they want. Based on the layout, the start controller will delegate the 
program to different (new) controllers to render and handle the app based on the fxml and the interactions in the app. 









