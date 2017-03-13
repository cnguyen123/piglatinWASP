# piglatinWASP - Piglatin Translator
This repository contains files of a mini project Piglatin Translator as an assignment of Software Engineering course, WASP program.
The outcome is a small tool able to translate English word, sentence to Pig latin. See https://en.wikipedia.org/wiki/Pig_Latin. 

# Prerequisites
# System Requirement 
The piglatinWASP is tested on: Windows 7, Mac OS.  
## Dependencies
The project require 
* [JAVA SE JDK 8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache Ant] (http://ant.apache.org/)

## Compile and Run
Do
* `ant` to compile
* `ant test` to additionally run all tests
* `ant jar` to generate a `Piglatin.jar` file
* `ant clean` to clean away generated files.

## Run

Run the generated jar file by:
* `java -jar Piglatin.jar`

## Test
After running program with `java -jar Piglatin.jar`, a panel is showed with instructions to select the interested function as below:<br />
Please choose one of these functions:<br />
Pig Latin --> English(1)|English --> Pig Latin(2)|Exit(3)<br />
You choose:<br />
If one likes to translate English word/ sentence to Pig Latin, select 1.<br />
If one likes to decode Pig Latin to English, select 2.<br />
If feel bored, select 3 to exit program.<br />
------------ Translate English to Pig Latin-------------------------------<br />
Type Word to be translated, for example: hello<br />
Output should be: <br />
Translated in Pig Latin:<br />
ellohay<br />
**@Jiexiong**: Voice of translated Pit Lattin will be played after translation, :-) A free API is used with limit query times, so **DO TRY BUT NOT TOO MANY TIMES.**

------------ Translate English to Pig Latin from certain file -------------------------------<br />
Type file to be translated after selecting 1, for example: story.txt <br />
Output should be: A LOT OF TRANSLATED WORDS.

------------ Translate Pig latin to English-------------------------------<br />
Type Word to be translated: for example: ellohay<br />
Translate from Pig Latin to English:<br />
hello<br />

## Contributors
* JieXiong Tang (jiexiong@kth.se) and
* Chanh Nguyen (chanh@cs.umu.se).


