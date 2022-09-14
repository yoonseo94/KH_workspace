package com.kh.spring.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;
import com.kh.spring.demo.model.dto.Dev;

/**
 * 
 * SqlSession 생성/반환 -> Dao DI받아서 처리
 * dao요청
 * 트랜잭션처리 -> AOP로 구현
 *
 */
@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao demoDao;

	@Override
	public int insertDev(Dev dev) {
		return demoDao.insertDev(dev);
	}
	@Override
	public List<Dev> selectDevList() {
		return demoDao.selectDevList();
	}
	@Override
	public int deleteDev(int no) {
		return demoDao.deleteDev(no);
	}
	@Override
	public Dev selectOneDev(int no) {
		return demoDao.selectOneDev(no);
	}
	@Override
	public int updateDev(Dev dev) {
		return demoDao.updateDev(dev);
	}
	@Override
	public Dev selectOneDevByEmail(String email) {
		return demoDao.selectOneDevByEmail(email);
	}
}
