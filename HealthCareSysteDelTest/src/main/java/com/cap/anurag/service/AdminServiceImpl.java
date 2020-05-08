package com.cap.anurag.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cap.anurag.dao.AdminDao;
import com.cap.anurag.dao.AdminRepository;

@Service
@Repository
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao dao;

	@Autowired
	AdminRepository repo;
	
	public void deleteTestById(String testId) {
		dao.delete(testId);
		
	}
}
