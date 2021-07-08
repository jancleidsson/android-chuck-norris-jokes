# android-chuck-norris-jokes

 Android project created to apply concepts of Clean Architecture and SOLID with MVVM architecture and using some jetpack components as: Room, RXJava, Dagger Hilt, Navigation Component and etc. 

 - The tests (unit and instrumented) were writen using libraries as Mokito, Mockk and Espresso.

The main feature performs a query to list Chuck Norris joke categories (from REST API - https://api.chucknorris.io/) and displays a ramdom joke from the selected category, moreover the app creates a local database to persist the favorite jokes chosen by the user.
