# Jackbot

> "JACK, scrap those duplicates!"


![Jackbot](https://github.com/TheanosLearning/Jackbot/raw/master/images/Jackbot.png)


Deploy Jackbot onto your Delta team and he will accompany you into battle! Jackbot remains cloaked and
appears only when you need him to scrap your duplicate characters, emblems & weapon skins.

### Requirements
* `Java 8`
* `Google Chrome` _tested on v57.0 (64 bit)_

#### Jackbot uses Selenium 3 and the Chrome WebDriver to automate the process of scrapping duplicate cards so you don't have to. Jackbot
works through your web broswer interface on <https://gearsofwar.com>.

## How to Run
1) Download the Jackbot jar with dependencies [JackbotJar](https://github.com/TheanosLearning/Jackbot/raw/master/target/Jackbot-Scrap-Duplicates-jar-with-dependencies.jar "Download Jackbot")
2) Execute the jar with `java -jar Jackbot-Scrap-Duplicates-jar-with-dependencies.jar`
3) Sit back and watch Jackbot go to work or go do something better with your time.

#### Compiling Source Code
Use the Maven assembly plugin to compile the jar with `mvn clean compile assembly:single`
 