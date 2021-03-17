Apzumi - task requirement by Daniel Besztak

How to start? - It's easy need only start Main.java file.
To correctly application work needed local database in my case i use MySQL database.
In file application.properties are all needed parameters to connect with DB like, address URL, login and password.
To making request for my application i used software Postman.
At first start probably will be need to download by Maven dependency all used libraries.

Application check post's author in PUT and DELETE command.


Command:
GET Method:
 */posts - display all posts existing in data base,
 */posts/{postTitle} - display all posts from DB with {postTitle}
 */posts/withoutId - display all posts without userId
 
PUT Method:
 */posts/title/{id} + RequestBody (String new Title) + Header (int userid) - edit Title in post{id} to (new Title) 
 */posts/body/{id} + RequestBody (String new Body) + Header (int userid) - edit Body in post{id} to (new Body) 
 */rest - allows users to update DB without waiting to automatic update (at 20:00)
 
DELETE Method:
 */posts/{id} - delete post with {id}
 
 ----------------------------------------------- WERSJA POLSKA----------------------------------------------------------
 Apzumi - zadanie rekrutacyjne - wykonał Daniel Besztak
 
 Jak uruchomić aplikacje? - Wystarczy uruchomić klasę główną - Main.java.
 Do prawidłowego działania aplikacji potrzebna będzie lokolna baza danych - w moim przypadku jest to MySQL.
 W pliku application.properties są wszystkie niezbędne parametry potrzebne do nawiązania połaczenia z bazą danych - adres URL, login i hasło.
 To wykonywania zapytań do aplikacji użyto programu Postman.
 Przy pierwszym uruchomieniu programu może być niezbędne pobranie zależności Maven.
 
 Aplikacja sprawdza autora postów w komendach PUT i DELETE.
 
Żadania Restowe:
Żądanie GET:
 */posts - wyświetla wszystkie posty
 */posts/{postTitle} - wyświetla wszystkie posty o podanym tytule {postTitle}
 */posts/withoutId - wyświetla wszystkie posty bez pola userId
 
Żądanie PUT:
 */posts/title/{id} + RequestBody (String new Title) + Header (int userid) - edytuje pole Title w poście o podanym{id} na (new Title) 
 */posts/body/{id} + RequestBody (String new Body) + Header (int userid) - edytuje pole Body w poście o podanym {id} na (new Body) 
 */rest - pozwala użytkownikowi na zaktualizowanie bazy danych bez konieczności czekania na automatyczną aktualizacje o godzienie 20:00
 
Żądanie DELETE:
 */posts/{id} - usuwa post o podanym Id {id}
 