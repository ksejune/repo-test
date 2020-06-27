package com.semi.join.model.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.semi.member.model.dao.MemberDAO;

public class JoinDAO {
	private Properties prop;
	
	public JoinDAO() {
		prop = new Properties();
		
		String filePath = JoinDAO.class
				          .getResource("/config/join.properties")
				          .getPath();
		
		// System.out.println(filePath);
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
