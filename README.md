# HoldemHandEvaluater in Java (JavaSE-14)

Overview
--------
Import the project in your IDE and run the ../src/main/Main.java file, no extra packages were used, only the java built in ones. The program responds each line of input with one line of output - hands in ascending order or error message. Program runs till it recieves 'stop' in input. The amount of players - 2 till 10 included. The program uses Hand and Card objects for evaluation.

Valid input is a string with first 10 charcter representing the board cards which is followed by 'space' and next four characters representing each hand afterwards. Valid card ranks are - "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2". Valid card suits are - "h", "d", "c", "s".
Example: 4cKs4h8s7s Ad4s Ac4d As9s KhKd Kc7h



How it works:

1) Recieves a String in input;
2) If input correct passes the String to next operations, otherwise prints error message;
3) Sets up the board list (5 cards) and hand list (4 cards each);
4) Passes the board and each hand to the evaluater method. Sets the best combination for each hand;
5) Rearranges hand list by comparing strengths;
6) Prints out the hand list in ascending order with ' ' inbetween, if scores are equal then the spacing is '='.


Improvements needed:

0) As for the task given, hands with equal strength are not printed in an alphabetic order;
1) The task was completed with a straigth forward method, where the logic might still be improved;
