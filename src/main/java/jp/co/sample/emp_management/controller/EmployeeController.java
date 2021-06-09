package jp.co.sample.emp_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author daiki.takayama
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
	
	


	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////


	public String showList(String employeeName,Model model) {

		
		List<Employee> employeeList = null;

		//検索欄が空欄だった場合
		if(employeeName == null ) {
			employeeList = employeeService.showList();
			return "employee/list";
		}
		
		employeeList = employeeService.showListByLikeEmployeeName(employeeName);
		//検索結果が見つからなかった場合
		if( employeeName != null  && employeeList.size() == 0) {
			model.addAttribute("notFoundLikeName", "１件もありませんでした");
			employeeList = employeeService.showList();
			return "employee/list";
		}
		
		//名前によるあいまい検索が成功した場合、その結果を表示


		employeeList = employeeService.showListByLikeEmployeeName(employeeName);

		model.addAttribute("employeeList",employeeList);
		
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
	
}
