package trainingManagementSystem.model;

public class Search {
	private String textSearch;
	private String date;

	public Search() {
		super();
	}

	public Search(String textSearch, String date) {
		super();
		this.textSearch = textSearch;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

}
