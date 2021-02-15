package com.srihari.practice;



interface HelloWorldInterface2{
	
	public void displayHelloWorld();
}



public class HelloWorldWithLambda {

	public static void main(String[] args) {
		
		HelloWorldInterface2 interfaceRef = () -> System.out.println("Hello World With Lambda");
		
		interfaceRef.displayHelloWorld();
	}
}
