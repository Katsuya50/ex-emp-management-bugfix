package jp.co.sample.emp_management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.SearchEmployeeForm;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 使用する検索用フォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public SearchEmployeeForm setUpSearchForm() {
		return new SearchEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		setEmployeeName(model);
		return "employee/list";
	}

	
	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form
	 *            従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
	
	@RequestMapping("/search")
	public String search(SearchEmployeeForm searchEmployeeForm, RedirectAttributes redirectAttributes, Model model) {
		System.out.println(searchEmployeeForm.getSearchName());
		if(searchEmployeeForm.getSearchName().equals("")) {
			System.out.println(1);
			return "redirect:/employee/showList";
		}else {
			List<Employee> employeeList = employeeService.searchByName(searchEmployeeForm.getSearchName());
			System.out.println(employeeList.size());
			if(employeeList.size() == 0) {
				redirectAttributes.addFlashAttribute("message", "「検索結果がありません」");
				return "redirect:/employee/showList";
			}else {
				model.addAttribute("employeeList", employeeList);
				setEmployeeName(model);
				return "employee/list";
			}
		}
	}
	
	/**
	 * オートコンプリート用に従業員情報を格納するメソッド.
	 * 
	 * @param model リクエストスコープ
	 */
	public void setEmployeeName(Model model) {
		List<Employee> employeeList = employeeService.showList();
		List<String> employees = new ArrayList<>();
		for(Employee emp: employeeList) {
			employees.add(emp.getName());
		}
		model.addAttribute("employees", employees);
	}
	
}
