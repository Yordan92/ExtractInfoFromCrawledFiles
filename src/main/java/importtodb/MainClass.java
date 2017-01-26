package importtodb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.entities.FlatOffer;
import entity.entities.Floors;
import service.ExtractInformation;
import stemmer.bg.Stemmer;
import stemmer.bg.StemmerFactory;

public class MainClass {
	private static SessionFactory factory;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		ExtractInformation ei = new ExtractInformation();
		persistFlatOffer(ei.extractFlatOffers());
//		Stemmer s = StemmerFactory.getBGStemmer();
		
	}
	
	
	
	private static void persistToFile(List<FlatOffer> flatOffer) throws IOException {
		File htmlsDir = new File("D:\\java\\workspace\\Crawling\\htmls");
		File testResult = new File("result1.txt");
		testResult.createNewFile();
		FileOutputStream debugFileOutputStram = new FileOutputStream(testResult);
		OutputStreamWriter charOutput = new OutputStreamWriter(
				debugFileOutputStram, Charset.forName("UTF-8").newEncoder() 
		);
		String result = flatOffer
			.stream()
			.map(f -> {
				return f.toFile();
			})
		     .collect(Collectors.joining("\n____________________________________________________________________________________\n\n\n"));
				
		charOutput.write(result);
		charOutput.close();
	}
	private static void persistFlatOffer(List<FlatOffer> flatOffer) {
		factory = new Configuration().configure().buildSessionFactory();
		  Session session = factory.openSession();
		  Transaction tx = session.beginTransaction();
		  flatOffer.forEach(f -> session.persist(f));
		  tx.commit();
		  session.close();
		  factory.close();
	}
	
}
