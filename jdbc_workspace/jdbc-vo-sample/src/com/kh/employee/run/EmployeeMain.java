package com.kh.employee.run;

import java.util.List;

import com.kh.employee.model.dao.EmployeeDao;
import com.kh.employee.model.vo.Employee;

public class EmployeeMain {

	public static void main(String[] args) {
		EmployeeDao employeeDao = new EmployeeDao();
		List<Employee> list = employeeDao.findByJobName("대리");
		
		System.out.println("-------------------------------------------------------");
		System.out.println("사번	사원명	직급명	부서명		지역명	급여");
		System.out.println("-------------------------------------------------------");
		for(Employee employee : list) {
			System.out.printf("%s\t%s\t%s\t%s\t\t%s\t%s%n",
							 employee.getEmpId(),
							 employee.getEmpName(),
							 employee.getJob().getJobName(),
							 employee.getDept().getDeptTitle(),
							 employee.getDept().getLocation().getLocalName(),
							 employee.getSalary()
					);
		}
		System.out.println("-------------------------------------------------------");
	}

}
