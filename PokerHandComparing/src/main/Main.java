package main;

import java.util.Scanner;

import main.evaluator.RunEvaluation;

public class Main {

	public static void main(String[] args) {
		Scanner scInput = new Scanner(System.in);		
		RunEvaluation.evaluate(scInput);		
		scInput.close();
	}
}
