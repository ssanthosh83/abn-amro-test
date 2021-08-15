package com.santhosh.abnamro.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InputProcessorTest {
	
	@Autowired
    private InputProcessor inputProcessor;
    
    @Test
    public void testReadFile() throws FileNotFoundException, IOException {
    	MultipartFile multipartFile = new MockMultipartFile("Input.txt", new FileInputStream(new File("src/test/resources/Input.txt")));
    	List<String> transactionsList = inputProcessor.readInput(multipartFile);
    	assertNotNull(transactionsList);
    	assertEquals(2, transactionsList.size());    
    }

}
