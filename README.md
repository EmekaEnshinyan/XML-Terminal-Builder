
# Xml & Terminal Parameters Builder

[![](https://img.shields.io/badge/license-Picocli-blue.svg?)](https://github.com/remkop/picocli/blob/master/LICENSE)
[![](https://img.shields.io/badge/license-WorldLingo-red.svg?)](https://www.worldlingo.com/en/downloads/ServiceAPI.pdf)

*Questions?  Comments?  Feedback? Email me at emeka.enshinyan@gmail.com 

-----

Fundamental function: write data retrieved from an xml file and API calls to a file, place the data in a new xml file during build phase, and run the application from the terminal.

This application takes in data from two sources: a source .xml file saved on the local disk and target data from an API call. The API being used is from WorldLingo's API Documentation guide. There are four pieces of data that is stored in a .txt file: 1. an array of sentences from the source .xml file that will be in some language L, 2. an array of the source sentences but modified as a property value to build an API URL for each sentence, 3. the language code for the source language to be used in the API call, 4. the language code for the target language to be used in the API call, and the retrieved data will be of a translation T(L).

The program is run by using the terminal with custom-made parameters that I built and inputting the following: the directory of the source .xml, the language url parameters to be inserted for the API call, and the output path for the newly built .xml file (the target file) with the data inserted in the required places in the target file.

---This application uses the DOM XML parser, Maven Compiler, and Picocli for creating custom commands---

Dom Parsing Documentation: https://docs.oracle.com/javase/tutorial/jaxp/dom/readingXML.html

Picocli: https://picocli.info/

# Documentation
1. [Requirements](#Requirements)
2. [Setup](#Setup)
3. [Running-The-Application](#Running-The-Application)
4. [Plans-&-Improvements](#Plans-&-Improvements)

## Requirements
**NOTE** This has not been tested on a Mac or Linux OS.
The computer specification requirements are negligable, meaning you can run this program on almost any local or remote machine with minimal drive space and CPU speed.

###### The following tools are required to to have the application run:
- Java 15 (can also be run on Java 8)
- Apache-Maven version 15
- PicoCli version 4.6.3
- Internet connection for the API calls

## Setup

1. Simply download the repository
2. Make sure pom.xlm contains the right dependencies
3. Open the file **TxtFile.java** and set the return statement in method **returnDatabase()** as the desired filepath of the .txt file


## Running-The-Application

**Make sure to compile the code before running**

In the Terminal, type:

```
$cd {?}CommandSetup\target\classes  //where {?} is the directory for where the application was saved
```


Run the program using the following command
```
$java buildxml -f -s -t -o
```
Meaning of each command line option:

-f => source .xml filepath

-s => source language code

-t => target language code

-o => the filepath for where the .xml will be written

Example:
```
$java buildxml -"C:\Users\emeka\Documents\source.xml -en -fr -"C:\Users\emeka\Documents\randfilename.xml"
```

## Plans-&-Improvements

1. Shift to a self-made cloud database for target sentences 
