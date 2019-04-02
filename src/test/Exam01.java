package test;

import java.util.Map;

import com.google.gson.Gson;

public class Exam01 {
	
	public static void main(String[] args) {
		String jsonStr = "{\"key\":1}";
		Gson g = new Gson();
		Map<String,Double> m = g.fromJson(jsonStr, Map.class);
		System.out.println(m);		
	}
}



