package com.blog.devyoung.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CkEditorController {

	@Value("${ckeditor.default.file.path}")
	private String ckeditorPath;
	
	@PostMapping("/ckeditor/uploadfiles")
	public String uploadFiles(HttpServletRequest request, HttpServletResponse response, @RequestBody MultipartFile file) throws IllegalStateException, IOException {
		//System.out.println("file:" + file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		String dir = sdf.format(new Date());
		String rootPath = System.getProperty("user.dir");
		String dirPath = rootPath + File.separator + ckeditorPath + File.separator + dir;
		File d = new File(dirPath);
		if(!d.isDirectory()) {
			System.out.println("디렉토리 없음");
			d.mkdir();
		}
		// C:\workspace_react_spring\Blog\src\main\resources\static\ckeditor
		//System.out.println("filePath: " + filePath);
		String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
		File convertFile = new File(dirPath, fileName);
		file.transferTo(convertFile);
System.out.println("시발: " + ckeditorPath + "/" + dir + "/" + fileName);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("uploaded", 1);
		data.put("fileName", fileName);
		data.put("url", ckeditorPath + "/" + dir + "/" + fileName);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
