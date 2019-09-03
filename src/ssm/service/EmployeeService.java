package ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.dao.EmployeeDao;
import ssm.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	public int addEmployee(Employee employee){
		int cnt=employeeDao.getCntByUsername(employee.getUsername());
		if(cnt>0){
			return -2;
		}
		int i= employeeDao.insertEmployee(employee);
		return i;
	}
	
	public List<Employee> getEmployees(){
		return employeeDao.getEmployees();
	}
	
	public int delEmp(String username){
		return employeeDao.delEmp(username);
	}
	
	public Employee getEmployeeByUsername(String username){
		return employeeDao.getEmployeeByUsername(username);
	}
	
	public int updateEmp(Employee employee){
		return employeeDao.updateEmp(employee);
	}
}
