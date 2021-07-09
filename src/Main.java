import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		URL url = Main.class.getResource("publicationData.txt");
		File file = new File(url.getPath());
		Map<String, List<Publication>> publicationsMap = main.initDatabase(file);

		url = Main.class.getResource("queries.txt");
		file = new File(url.getPath());

		List<String[]> words = main.processQueries(file);

		for (int i = 0; i < words.size(); i++) {
			List<Publication> publications = publicationsMap.get(words.get(i)[0]);
			if (publications != null) {
				if (words.get(i)[1].equalsIgnoreCase("book")) {
					int count=0;
					System.out.println("Searching \"" + words.get(i)[0] + "\" for \"" + words.get(i)[1] + "\" ....");
					for (int j = 0; j < publications.size(); j++) {
						if(publications.get(j).getClass().getSimpleName().equals("Book")) {
							System.out.println(publications.get(j));
							count++;
						}
					}
					if(count!=0) {
						System.out.println("Author: " + words.get(i)[0] + " has " + count + " "
								+ words.get(i)[1] + " publications");
					}
					else {
						System.out.println("Author: " + words.get(i)[0] + " has no publications in "+words.get(i)[1]);
					}
				}
				else {
					int count=0;
					System.out.println("Searching \"" + words.get(i)[0] + "\" for \"" + words.get(i)[1] + "\" ....");
					for (int j = 0; j < publications.size(); j++) {
						if(publications.get(j).getClass().getSimpleName().equals("JournalPaper")) {
							System.out.println(publications.get(j));
							count++;
						}
					}
					System.out.println("Author: " + words.get(i)[0] + " has " + count + " "
							+ words.get(i)[1] + " publications");
				}
			} else {
				System.out.println("Searching \"" + words.get(i)[0] + "\" for \"" + words.get(i)[1] + "\" ....	");
				System.out.println("Author: " + words.get(i)[0] + " is not in the database.");
			}
			System.out.println("");
		}

	}

	public Map<String, List<Publication>> initDatabase(File file) {
		Map<String, List<Publication>> publicationsMap = new HashMap<String, List<Publication>>();

		Book book;
		JournalPaper journalPaper;

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String sentence = scanner.nextLine();
				if (!sentence.equals("")) {
					String[] words = sentence.split(",");
					if (words[0].equals("book")) {
						List<String> authors = new ArrayList<String>();
						for (int j = 7; j < words.length; j++) {
							authors.add(words[j]);
						}
						book = new Book(words[1], authors, words[2], words[3], words[4], Double.parseDouble(words[5]),
								Integer.parseInt(words[6]));
						for (var j = 0; j < authors.size(); j++) {
							if (publicationsMap.containsKey(authors.get(j))) {
								List<Publication> publications = publicationsMap.get(authors.get(j));
								publications.add(book);
								publicationsMap.replace(sentence, publications);
							} else {
								List<Publication> publications = new ArrayList<Publication>();
								publications.add(book);
								publicationsMap.put(authors.get(j), publications);
							}
						}
					} else {
						List<String> authors = new ArrayList<String>();
						for (int j = 7; j < words.length; j++) {
							authors.add(words[j]);
						}
						journalPaper = new JournalPaper(words[1], authors, words[2], words[3], words[4],
								Integer.parseInt(words[5]), Integer.parseInt(words[6]));
						for (var j = 0; j < authors.size(); j++) {
							if (publicationsMap.containsKey(authors.get(j))) {
								List<Publication> publications = publicationsMap.get(authors.get(j));
								publications.add(journalPaper);
								publicationsMap.replace(sentence, publications);
							} else {
								List<Publication> publications = new ArrayList<Publication>();
								publications.add(journalPaper);
								publicationsMap.put(authors.get(j), publications);
							}
						}
					}
				}
			}

			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return publicationsMap;
	}

	public List<String[]> processQueries(File file) {
		List<String[]> words = new ArrayList<String[]>();
		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String[] tempWordStrings;
				String sentence = scanner.nextLine();
				tempWordStrings = sentence.split(",");
				words.add(tempWordStrings);
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return words;
	}
}
