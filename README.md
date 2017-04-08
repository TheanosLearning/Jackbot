# Jackbot v1.0 (beta)

> "Jack, scrap those duplicates!"

![Jackbot](https://github.com/TheanosLearning/Jackbot/raw/master/images/Jackbot.png)

> "Oh shit! Jack is back, baby!"

Deploy [Jackbot](http://gearsofwar.wikia.com/wiki/JACK) onto your Delta team and he will accompany you into battle! Jackbot remains cloaked and
appears only when you need him to scrap your duplicate characters, emblems & weapon skins.

Watch a short [demo](https://www.youtube.com/watch?v=U2nvu6s4HQQ) of Jackbot in action.

### Requirements
* `Java 8`
* `Google Chrome` _tested on v57.0 (64 bit)_

#### Jackbot works through your web browser interface on <https://gearsofwar.com> by using Selenium 3 and the Chrome WebDriver to automate the process of scrapping duplicate cards so you don't have to.

## How to Run
1. Download the Jackbot jar with dependencies: [Jackbot-Scrap-Duplicates](https://github.com/TheanosLearning/Jackbot/raw/master/target/Jackbot-Scrap-Duplicates-jar-with-dependencies.jar "Download Jackbot")
2. Execute the jar with `java -jar Jackbot-Scrap-Duplicates-jar-with-dependencies.jar <username> <password>`
    * Your _username_ & _password_ are the same credentials you use to sign in on _gearsofwar.com_
3. Sit back and supervise Jackbot's work or go do something better with your time.

#### Compiling Source Code
Use the Maven assembly plugin to compile the jar with `mvn clean compile assembly:single`

![Jackbot-Scan](https://github.com/TheanosLearning/Jackbot/raw/master/images/Jackbot-Scan.png)

> "Jack, scan these cards. Looks like there's some kind of duplicates."
 