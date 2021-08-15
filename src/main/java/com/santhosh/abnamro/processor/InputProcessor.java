package com.santhosh.abnamro.processor;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface InputProcessor {
	List<String> readInput(MultipartFile path);
}
