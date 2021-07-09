import java.time.LocalDate;
import java.util.List;

public class JournalPaper extends Publication {

	private String journalName;
	private int startingPage;
	private int endingPage;
	
	public JournalPaper() {
	
	}

	public JournalPaper(String title, List<String> authors, String yearOfProduction, String publisher,String journalName, int startingPage, int endingPage) {
		super(title,authors,yearOfProduction,publisher);
		this.journalName = journalName;
		this.startingPage = startingPage;
		this.endingPage = endingPage;
	}
	
	public int getPageNumbers() {
		return endingPage-startingPage;
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
		return "Journal : Title: "+ super.getTitle() + ", Authors: ("+ authors + "), Year: "+super.getYearOfProduction() + ", Publisher: "+super.getPublisher()+", "+ journalName + ", Pages: "+getPageNumbers();
	
	}
	
	
	
	
}
