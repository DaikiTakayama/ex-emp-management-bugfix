package jp.co.sample.emp_management.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author daiki.takayama
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return　従業員情報一覧
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
	
	/**
	 * 名前のあいまい検索に該当する従業員情報を取得します.
	 * 
	 * @return　従業員情報一覧
	 */
	public List<Employee> showListByLikeEmployeeName(String name) {
		List<Employee> employeeList = employeeRepository.findByLikeEmployeeName(name);

		return employeeList;
	}
	
	/**
	 * 従業員情報をページングするメソッド
	 * 
	 * @param page 表示するページ
	 * @param size ページの数
	 * @param employeeList	表示する従業員情報
	 * @return
	 */
	public Page<Employee> showPaging(int page,int size,List<Employee> employeeList){
		page--;
	    int cnt = page * size;
	    List<Employee> pagingEmployeeList;

        int index = Math.min(cnt + size, employeeList.size());
        pagingEmployeeList = employeeList.subList(cnt, index);

		
		Page<Employee> employeePage =new PageImpl<>(pagingEmployeeList,PageRequest.of(page,size),employeeList.size());
		return employeePage;
	}
	
	
	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws org.springframework.dao.DataAccessException 検索されない場合は例外が発生します
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}
	
	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee 更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
}
