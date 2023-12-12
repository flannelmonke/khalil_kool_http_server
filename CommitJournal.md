# HTTP Server in java
---
This project is a further study in the world of backend development. The frameworks I am using to learn backend development such as Spring Boot and NEXT.js feel very abstracted that in the sense my expertise are becoming reliant them. I want to be able to say, I am a full-stack developer someday. Instead of having to say, I am a Spring Boot dev, or I am React Dev. This is not going to be most revolutionary HTTP server, but it's still going to be cool as hell.

I am following a tutorial series from CodeFromScratch on YouTube, watch the first part [here](https://youtu.be/FNUdLeGfShU?si=iK85YJcIko2HyuXR).

## My First Commit
---
My first commit comes after finishing part 2 in the video series. I've already learned a lot that I have not learned from Sheridan. 

First things first. Every Spring Boot Application I have ever made as an assignment or as a follow along in class, have been using Maven as a dependency manager or package manager or whatever you wanna call it. We knew this because we would always select it in the project wizard, but we never really learned what it was or how to use it, wasn't that hard to wrap my head around...
That being said, as soon as I started this project I had problems importing new packages despite the fact I am able to import them upon initializing a new project.

The real solution to my very trivial problem was that I never installed Maven on my machine, either of them. My windows 11 laptop or my Ubuntu 22.04 Desktop... So I did some real troubleshooting and figured out how to install it on windows then the next day I used the apt command to install it on Ubuntu. One process was very easy in comparison by the way.

Another thing I have learned is how to perform JSON encoding and decoding in Java. This is something I never thought I could do. I didn't really expect the methods for that class to all be less than 3 lines to be honest. (Excluding the object mapper, which is a dependent part of that equation. Maybe I'll learn how that works some other time) to see the code I wrote for the JSON encoding and decoding click here to navigate to my Json.java file [Json.java](https://github.com/flannelmonke/khalil_kool_http_server/blob/main/src/main/java/com/khalil/httpserver/util/Json.java).

I've learned and applied the Singleton desing pattern. Applying this concept to my configurationManager.java file. Since we only need **one** configuration manager. See the code for the configuration manager class here [configurationManager.java](https://github.com/flannelmonke/khalil_kool_http_server/blob/main/src/main/java/com/khalil/httpserver/Config/configurationManager.java).

So far I've done "the boring stuff" but I am having real fun dooing this. 

## My second commit
---
It's coming along faster than I expected. In this commit I defined a ServerSocket by passing in the configuration we received from our http.json file. 

I then defined a Socket object which, by using the accept() function, can tell my code to listen to a socket until it hears a reponse. Since this code uses one socket listener we can only handle one request from a browser. This can be fixed making our server multi-threaded.

Next we defined out Input and Output Stream Objects. These will be used to receive requests and provide responses. Next for testing purposes I defined some simple HTML. Inside of a String, declared a constant String which is our CRLF.

#### CRLF
---
This is not something I've seen a lot of using the before mentioned frameworks. I knew that every response had a header and body, where the body was the content and the header was the meta data. I just never knew how it was delimited. Which is done by the ASCII characters 13 and 10 served consecutively. 

I also learned that the HTTP standard is required when writing your head and body and this standard is not very difficult to follow.

#### How it's going

So far on my third commit. The code runs and actually works. The only downfall is that everything is hard coded in my main function. However this is very easily fixable since we are going to be using the java.io package. Again though. The code runs, once you run it pauses after printing the start up message and the actually serves the HTML we gave it in that String. Also the funniest thing I've seen is writing raw HTML in another language and declaring it as a String.

Here is a screenshot of that.
![image](https://github.com/flannelmonke/khalil_kool_http_server/assets/123114205/6131cdd3-a7a1-4ff9-9017-62e62e45b19d)

