import java.util.HashMap;

public class HashMapDemo1 {
	
	public static void main(String[] args) {
		HashMap h = new HashMap();
		
		h.put("smith", "I am Lowercase smith");
		h.put("SMITH", "I am uppercase SMITH");
		
		
		System.out.println(h.get("SMITH"));
		

	}

}
