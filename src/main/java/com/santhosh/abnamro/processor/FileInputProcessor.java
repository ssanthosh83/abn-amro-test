package com.santhosh.abnamro.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


/**
 * This class reads the Input file and returns the list of transactions
 *
 */
@Component
public class FileInputProcessor implements InputProcessor{

private static final Logger log = LoggerFactory.getLogger(FileInputProcessor.class);
	
	/**
	 * Reads the file and returns the list of transactions
	 * @param file
	 * @return List<String>
	 */
	@Override
	public List<String> readInput(MultipartFile file) {
		
		List<String> transactions = new ArrayList<>();
		String line = null;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"))){
			while((line = reader.readLine())!=null) {
				transactions.add(line);
			}
		} catch (IOException e) {
			log.error("File not found!");
			e.printStackTrace();
		} 
		return transactions;
		
	}
}
