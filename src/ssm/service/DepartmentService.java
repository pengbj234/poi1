package ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.dao.DepartmentDao;
import ssm.entity.Department;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentDao deptDao;
	
	public List<Department> getDepts(){
		return deptDao.getDepts();
	}
}
