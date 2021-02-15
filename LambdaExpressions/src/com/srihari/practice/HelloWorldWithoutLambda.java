package com.srihari.practice;


interface HelloWorldInterface{
	
	public void displayHelloWorld();
}

class HelloWorldInterfaceImpl implements HelloWorldInterface{

	@Override
	public void displayHelloWorld() {
		System.out.println("Hello World without Lambda Implementation");
		
	}
	
	
}

public class HelloWorldWithoutLambda {

	public static void main(String[] args) {
		
		HelloWorldInterface interfaceref = new HelloWorldInterfaceImpl();
		interfaceref.displayHelloWorld();

	}

}
