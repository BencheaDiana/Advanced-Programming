package javalab1;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		String languages[]= {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
		int n = (int) (Math.random() * 1_000_000);
		n = n*3;
		int bin = Integer.parseInt("10101",2);
		int hexa = Integer.parseInt("FF",16);
		n = n + bin;
		n = n + hexa;
		n = n*6;
		int result=ocifra(n);
		System.out.println(result);
		System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
	}
	public static int ocifra(int x) {
		int result = 0;
		while(x>9) {
			result = result+ x%10;
			x=x/10;
		}
		result=result+x;
		if(result>9)
			return ocifra(result);
		else
			return result;
		 
	}
}
