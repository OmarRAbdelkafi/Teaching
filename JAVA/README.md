# Java
We use the OOP to model the Goose game in this example:
https://en.wikipedia.org/wiki/Game_of_the_Goose

To execute the project:

-->Go to the Goose_Game/src field:
$cd Goose_Game/src

-->Generate the documentation (the documentation is not well seted in this project with key world like @param @return ect):
$javadoc -d ../docs -subpackages goosegame

-->Generate the classes for the game:
$javac goosegame/Main.java -d ../classes

-->Go to the main field and execute the tests
$cd ..
$javac -classpath test4poo.jar test/goosegame/PlayerTest.java
$java -jar test4poo.jar goosegame.PlayerTest

-->Go to classes to run the Game
$cd classes
$java goosegame.Main

-->Go to main filed and create the application
$cd ..
$jar cvfe appli.jar goosegame.Main -C classes goosegame
$java -jar appli.jar
