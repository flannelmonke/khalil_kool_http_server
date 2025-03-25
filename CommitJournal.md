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

## My second sync

---

It's coming along faster than I expected. In this commit I defined a ServerSocket by passing in the configuration we received from our http.json file.

I then defined a Socket object which, by using the accept() function, can tell my code to listen to a socket until it hears a reponse. Since this code uses one socket listener we can only handle one request from a browser. This can be fixed making our server multi-threaded.

Next we defined out Input and Output Stream Objects. These will be used to receive requests and provide responses. Next for testing purposes I defined some simple HTML. Inside of a String, declared a constant String which is our CRLF.

#### CRLF

---

This is not something I've seen a lot of using the before mentioned frameworks. I knew that every response had a header and body, where the body was the content and the header was the meta data. I just never knew how it was delimited. Which is done by the ASCII characters 13 and 10 served consecutively.

I also learned that the HTTP standard is required when writing your head and body and this standard is not very difficult to follow.

### Third sync

So far on my third commit. The code runs and actually works. The only downfall is that everything is hard coded in my main function. However this is very easily fixable since we are going to be using the java.io package. Again though. The code runs, once you run it pauses after printing the start up message and the actually serves the HTML we gave it in that String. Also the funniest thing I've seen is writing raw HTML in another language and declaring it as a String.

Here is a screenshot of that.
![image](https://github.com/flannelmonke/khalil_kool_http_server/assets/123114205/6131cdd3-a7a1-4ff9-9017-62e62e45b19d)

## Fourth sync

This server is rapidly gaining functions I never thought I would understand let alone implement myself. In this commit, I've added two key pieces of functionality. Logging and Multi-Threaded request handling.

### Logging

This feature has always burdened me. No one likes to implement logging, yet we all use it... most of the time. Logging in Java is like magician pulling out a wrag from his pocket. The thing just never ends. That being said, I'm starting to see why they never end. There's this class creating an instance of a factory which is calling a service which is then reading a file then forwards a port, etc... I'm just lucky that I'm able to not have logging messages barfed at me, unlike with my spring boot experience. All in all, this logging tool has taught me not only how to read logs better, but show me the benefit between printing and logging.

### Multi-Threaded Request Handling

This feature is by far the most important. If I tried going on a website and saw "You are `x` in queue." I would lose it. So this helps us handle user/client requests concurrently and in parallel. Ensuring that we are not handling requests one at a time. This feature taught me quite a bit on how threads work. I never worked with them before, but I am very glad to have finally been able to work on it now. I think I will definitely be using them again.

## Fifth sync

### Scanner-less parsing (Lexer-less parsing)

So far, I've only set up a parser and made sure that it can receive my input streams. This part was not hard at all. Setting up JUnit tests is nothing new to me, and in this current version of the simple HTTP server. The parser doesn't actually have any logic. We'll see how easy the logic part actually is.

#### Parser configuration

This should be about my sixth sync or so now. I haven't yet added the parser logic. This is because there were things I needed to define in my system first. Such as my parsing exceptions, my implemented HTTP status codes and my implemented HTTP methods. These are actually very important to define first before you start parsing different HTTP request methods. Because of the http 1.1 standard rfc 2616. To make an actual general use server there rules and specifications that must be met by a party. Such as required implemented methods. If you look at the timeline or even now, depending on the current time you are seeing this. You should be able to see a version of the HttpStatusCodes enum, only has around 5 or status codes and messages. Same for the HttpMethod enum. There is only two methods defined. This is because according http 1.1 standard rfc 2616, these are the methods and messages that are required for any and every server to have implemented.

In my opinion, this helps a lot being a software developer. Sometimes, I feel as if I don't know where to start and the documentation provided by IETF showed me a great place to start.

## Parser Logic implementation
