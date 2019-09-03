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
		//新增雇员
		Employee employee=new Employee();
		employee.setAge(age);
		employee.setDeptid(deptid);
		employee.setSex(sex);
		employee.setUsername(username);
		int i=employeeService.addEmployee(employee);
		if(i>0){
			return new ResultMsg(1,"新增成功");
		}else if(i==-2){
			return new ResultMsg(2,"该用户名已经存在");
		}
		return new ResultMsg(0,"新增失败");
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public ResultMsg del(String username){
		//删除雇员
		int i=employeeService.delEmp(username);
		if(i>0){
			return new ResultMsg(1,"删除雇员成功");
		}else{
			return new ResultMsg(0,"删除雇员失败");
		}
	}
	
	@RequestMapping("/update_submit")
	@ResponseBody
	public ResultMsg update_submit(String username,Integer sex,Integer age,Integer deptid ){
		//修改雇员
		Employee employee=new Employee();
		employee.setAge(age);
		employee.setDeptid(deptid);
		employee.setSex(sex);
		employee.setUsername(username);
		int i=employeeService.updateEmp(employee);
		if(i>0){
			return new ResultMsg(1,"修改雇员成功");
		}else{
			return new ResultMsg(0,"修改雇员失败");
		}
	}
}
