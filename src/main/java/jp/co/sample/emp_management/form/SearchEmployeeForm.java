package jp.co.sample.emp_management.form;

/**
 * 従業員曖昧検索用フォームクラス.
 * 
 * @author katsuya.fujishima
 *
 */
public class SearchEmployeeForm {
	
	/**	検索名 */
	private String searchName;

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	@Override
	public String toString() {
		return "SearchEmployeeForm [searchName=" + searchName + "]";
	}
}
