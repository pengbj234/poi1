package ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ssm.entity.Department;
import ssm.entity.Employee;
import ssm.entity.ResultMsg;
import ssm.service.DepartmentService;
import ssm.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService deptService;
	
	@RequestMapping("/add")
	public ModelAndView add(){
		List<Department> depts=deptService.getDepts();
		ModelAndView mv=new ModelAndView("employee/add");
		mv.addObject("depts", depts);
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(){
		List<Employee> employees=employeeService.getEmployees();
		ModelAndView mv=new ModelAndView("employee/list");
		mv.addObject("employees", employees);
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(String username){
		List<Department> depts=deptService.getDepts();
		ModelAndView mv=new ModelAndView("employee/update");
		Employee employee=employeeService.getEmployeeByUsername(username);
		mv.addObject("depts", depts);
		mv.addObject("employee", employee);
		return mv;
	}
	
	
	@RequestMapping("/add_submit")
	@ResponseBody
	public ResultMsg add_submit(String username,Integer sex,Integer age,Integer deptid ){
		//������Ա
		Employee employee=new Employee();
		employee.setAge(age);
		employee.setDeptid(deptid);
		employee.setSex(sex);
		employee.setUsername(username);
		int i=employeeService.addEmployee(employee);
		if(i>0){
			return new ResultMsg(1,"�����ɹ�");
		}else if(i==-2){
			return new ResultMsg(2,"���û����Ѿ�����");
		}
		return new ResultMsg(0,"����ʧ��");
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public ResultMsg del(String username){
		//ɾ����Ա
		int i=employeeService.delEmp(username);
		if(i>0){
			return new ResultMsg(1,"ɾ����Ա�ɹ�");
		}else{
			return new ResultMsg(0,"ɾ����Աʧ��");
		}
	}
	
	@RequestMapping("/update_submit")
	@ResponseBody
	public ResultMsg update_submit(String username,Integer sex,Integer age,Integer deptid ){
		//�޸Ĺ�Ա
		Employee employee=new Employee();
		employee.setAge(age);
		employee.setDeptid(deptid);
		employee.setSex(sex);
		employee.setUsername(username);
		int i=employeeService.updateEmp(employee);
		if(i>0){
			return new ResultMsg(1,"�޸Ĺ�Ա�ɹ�");
		}else{
			return new ResultMsg(0,"�޸Ĺ�Աʧ��");
		}
	}
}
