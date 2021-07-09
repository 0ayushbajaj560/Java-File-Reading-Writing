import java.time.LocalDate;
import java.util.List;

public abstract class Publication implements Document {

	private String title;
	private List<String> authors;
	private String yearOfProduction;
	private String publisher;

	public Publication() {

	}

	public Publication(String title, List<String> authors, String yearOfProduction, String publisher) {
		super();
		this.title = title;
		this.authors = authors;
		this.yearOfProduction = yearOfProduction;
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public String getYearOfProduction() {
		return yearOfProduction;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
