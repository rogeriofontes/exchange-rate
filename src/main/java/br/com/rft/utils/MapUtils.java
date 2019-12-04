package br.com.rft.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtils<T> {
	
	public static <K, V> List<K> convertToList(Map<K, V> map) {
		// create an empty list to store keys
		List<K> key = new ArrayList<>();

		map.entrySet().stream().forEach(entry -> {
			key.add(entry.getKey());
		});

		return key;
	}

	// Program to convert Map to a List in Java 8
	public static void main(String[] args) {
		Map<String, Integer> asciiMap = new HashMap<>();

		asciiMap.put("A", 65);
		asciiMap.put("B", 66);
		asciiMap.put("C", 67);

		// list to store map keys
		List<String> keys = convertToList(asciiMap);

		System.out.println(keys);
	}
}
