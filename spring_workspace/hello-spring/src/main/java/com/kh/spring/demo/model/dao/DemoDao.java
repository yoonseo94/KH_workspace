package com.kh.spring.demo.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.demo.model.dto.Dev;

@Mapper
public interface DemoDao {

	int insertDev(Dev dev);

	List<Dev> selectDevList();

	int deleteDev(int no);

	Dev selectOneDev(int no);

	int updateDev(Dev dev);

	@Select("select * from dev where email = #{email}")
	Dev selectOneDevByEmail(String email);

}
