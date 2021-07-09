import java.time.LocalDate;
import java.util.List;

public class Book extends Publication{

	private String bookISBNNumber;
	private double bookPrice;
	private int totalPages;
	
	public Book() {
	
	}
	
	Book(String title, List<String> authors, String yearOfProduction, String publisher, String bookISBNNumber, double bookPrice, int totalPages) {
		super(title,authors,yearOfProduction,publisher);
		this.bookISBNNumber = bookISBNNumber;
		this.bookPrice = bookPrice;
		this.totalPages = totalPages;
	}
	
	public int getPageNumbers() {
		return totalPages;
	}

	@Override
	public String toString() {
		String authors = "";
		for(int i=0;i<super.getAuthors().size();i++) {
			authors = authors+super.getAuthors().get(i);
			if(i!=super.getAuthors().size()-1) {
				authors = authors+", ";
			}
		}
		return "Book: Title: "+super.getTitle()+", Authors: ("+authors+"), Year: "+super.getYearOfProduction()+", Publisher: "+super.getPublisher()+", Book ISBN Number: "+bookISBNNumber+", Price: "+bookPrice+", pages: "+totalPages;
	}
	
	
}
