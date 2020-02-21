package jp.co.sample.emp_management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * オートコンプリートのソースを返すレストコントローラー.
 * 
 * @author katsuya.fujishima
 *
 */
@RestController
@RequestMapping("/search-employees")
public class SearchEmployeesController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 従業員情報をajaxに返すメソッド.
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public List<String> searchEmployees(){
		List<String> list = new ArrayList<>();
		List<Employee> employeeList = employeeService.showList();
		for(Employee emp: employeeList) {
			list.add(emp.getName());
		}
		return list;
	}

}
