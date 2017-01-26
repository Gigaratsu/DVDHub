package ben.dvdhub;

public class DVD {
	
	private String id;
	private String title;
	private String cast;
	private String desc;
	private String genre;
	private String price;
	private String year;

	public DVD(){
		
	}
	
	public DVD(DVD aDVD){
		this.id = aDVD.getID();
		this.title = aDVD.getTitle();
		this.cast = aDVD.getCast();
		this.desc = aDVD.getDesc();
		this.genre = aDVD.getGenre();
		this.price = aDVD.getPrice();
		this.year = aDVD.getYear();
	}
	
	public String getID(){
		return id;
	}
	
	public void setID(String id){
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
