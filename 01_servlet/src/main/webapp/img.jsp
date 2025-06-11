<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
    String src = "C:\\Users\\tj\\Desktop\\KakaoTalk_20250521_170228359.jpg";
	File file = new File(src);	//string -> file 변환
	InputStream fis = new FileInputStream(file);	
	byte[] bytes = fis.readAllBytes();	//file -> byte로 변환
	
	OutputStream os = response.getOutputStream();
	os.write(bytes);
	
	fis.close();
%>,,