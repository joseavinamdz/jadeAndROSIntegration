
public class Main {

	public static void main(String[] args) {
		String a = "[1,2,3]";
		a = a.replaceAll("\\[", "");
		a = a.replaceAll("\\]", "");
		
		String[] re = a.split(",");
		for (String s: re){
			System.out.println(s);
		}
		
		

	}

}
