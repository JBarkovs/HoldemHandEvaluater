# HoldemHandEvaluater in Java (JavaSE-14)

Overview
--------
Import the project in your IDE and run the ../src/main/Main.java file. The program responds each line of input with one line of output - hands in ascending order or error message. Program runs till it recieves 'stop' in input. The input is checked as for the task specifics, the amount of players - 2 till 10 included.

How it works:

1) Recieves a String in input;
2) If input correct passes the String to next operations, otherwise print error message;
3) Sets up lists for hand evaluation;
4) Finds and sets the best combination for each hand;
5) Rearranges hand list by comparing strengths;
6) Prints out the hand list in ascending order with ' ' inbetween, if scores are equal then the spacing is '='.


Improvements needed:

0) As for the task given, hands with equal strength are not printed in an alphabetic order;
1) The task was completed with a straigth forward method, where the logic might still be improved;
2) The code would need some cleaning up;
