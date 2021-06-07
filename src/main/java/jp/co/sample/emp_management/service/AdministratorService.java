package jp.co.sample.emp_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author daiki.takayama
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * ログインをします.
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 管理者情報　存在しない場合はnullが返ります
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator administrator = administratorRepository.findByMailAddressAndPassward(mailAddress, password);
		return administrator;
	}
	
	
	/**
	 * メールアドレスから管理者情報を取得します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return　取得できた管理者情報
	 */
	public Administrator checkAdminByMailAddress(String mailAddress) {
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);

		return administrator;
	}
}
