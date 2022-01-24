Project introduction:
This project was carried out as part of Programming 2 course, under professer Jian-Yun Nie at Université de Montréal.
The project's goal is to build a simplified information retrieval system (search engine).The user selects system a set of texts (documents).The program uses the files to build the first linked list, whose nodes contain the document
name, and a sub linked list containing the words and their frequencies. Next, the program creates an inverted list,
whose nodes contain a word and its occurnces in the set of texts. The program uses these data structs to find the words corresponding to a user request.

Most notable skills in this project : The organization of classes and methods, the use of the linked list,
sorting and the graphical interface.

The course grade given to this work is 100%.
____________________________________________________________________________________________________________________
How to use:
1_ Open .txt files from open->file
2_ Create inverted data structure from open->invert
3_ Display data structures from graph->display
4_ type input in 'Word to search' zone, then click search to display occurnces and frequency

Note: If you input several words, the program will display the documents where all words exist, with the accumulated
frequency of the words
____________________________________________________________________________________________________________________

Class organization:
_Main class
_MVC model classes
_AnsComparator class: comparator to sort lists by frequency
_SubNode: sub linked list node, contains attributes text and frequency
_MainNode: main linked list node, contains attributes text and sublist (SubNode linked list) 

Project done by: Yacine Mkhinini