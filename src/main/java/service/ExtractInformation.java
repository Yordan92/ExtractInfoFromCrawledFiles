package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entity.entities.FlatOffer;
import entity.entities.Floors;
import entity.enums.BuildYear;
import entity.enums.ConstructionType;
import entity.enums.Extras;
import entity.enums.FlatType;
import entity.enums.Furnished;
import entity.enums.Heating;
import entity.enums.interfaces.Properties;
import stemmer.bg.Stemmer;
import stemmer.bg.StemmerFactory;

public class ExtractInformation {
	
	private List<Properties> isPropertyExists(Element d, Properties[] properties) {
		return Arrays.stream(properties).map(v -> {
			if (d.getElementsContainingText(v.getBgTranslate()).size() > 0) {
				return v;
			} else {
				return null;
			}
		})
		.filter(v -> v != null)
		.collect(Collectors.toList());
	}
	
	public FlatOffer extractStructureData(Document d) throws IOException {
		FlatOffer f = new FlatOffer();
		Pattern p = Pattern.compile(",\\s(\\d+)m2\\s\\(([а-я]*\\s*[а-я]*)\\)");
		
		Arrays.stream(FlatType.values()).forEach(v -> {
			String result = d.select(".ver30black").first().getElementsContainingText(v.getBgTranslate()).text();
			
			Matcher m = p.matcher(result);
			if (m.find()) {
				f.setFlatType(v.getBgTranslate());
				f.setSize(m.group(1));
				f.setForRent(m.group(2).equals("под наем"));				
			}
			
		});
		
		List<Properties> flatProperty = isPropertyExists(d, Furnished.values());
		if (!flatProperty.isEmpty()) {
			f.setFurnished(flatProperty.get(0).getBgTranslate());
		}
		
		flatProperty = isPropertyExists(d, Heating.values());
		if (!flatProperty.isEmpty()) {
			f.setHeating(flatProperty.get(0).getBgTranslate());
		}
		
		flatProperty = isPropertyExists(d, ConstructionType.values());
		if (!flatProperty.isEmpty()) {
			f.setConsturctionType(flatProperty.get(0).getBgTranslate());
		}
		
		flatProperty = isPropertyExists(d, BuildYear.values());
		if (!flatProperty.isEmpty()) {
			f.setBuildYear(flatProperty.get(0).getBgTranslate());
		}
		
		f.setFloor(Floors.setByRegex(d.getElementsContainingOwnText("Етаж").text()));
		
		String neighbourhoodAndPrice = d.select(".ver13black > tbody tbody").first().text();
		Pattern pat = Pattern.compile("((жк|кв).\\s)?([а-яА-Я ]*),\\s([а-яА-Я]*)\\s\\D*((\\d|,)*)\\s(([а-яА-Я]|\\w)*)");
		Matcher m = pat.matcher(neighbourhoodAndPrice);
		if (m.find()) {
			f.setNeighbourhood(m.group(3));
			f.setCity(m.group(4));
			f.setMoney(m.group(5));
			f.setCurrency(m.group(7));
		}
		Elements searchedElements = d.getElementsContainingOwnText("Особености");
		if (searchedElements.size() > 0) {
			Elements extrasConteiner = searchedElements.first().parent().parent().siblingElements();
			for(Element el : extrasConteiner) {
				if(el.text().length() > 0) {
					f.setExtrasConteiner (
							isPropertyExists(el, Extras.values())
								.stream()
								.map(Properties::getBgTranslate)
								.collect(Collectors.joining(";"))
					);
					break;
				}
			}
		}
		return f;
		
	}
	public List<FlatOffer> extractFlatOffers() throws Exception {
		File htmlsDir = new File("D:\\java\\workspace\\Crawling\\htmls");
//		File htmlsDir = new File("D:\\java\\workspace\\Crawling\\htmls\\as852084.html");
		Stemmer bgStemmer = StemmerFactory.getBGStemmer();
//		extractFlatOfferFromFile(bgStemmer, htmlsDir);
//		return null;
		return Arrays.stream(htmlsDir.listFiles())
			.parallel()
			.map(f -> {
				return extractFlatOfferFromFile(bgStemmer, f);
			})
			.filter(f -> f != null)
			.collect(Collectors.toList());
		
	}

	private FlatOffer extractFlatOfferFromFile(Stemmer bgStemmer, File f) {
		Document d;
		try {
			d = Jsoup.parse(f, "UTF-8");
			FlatOffer flatOffer = extractStructureData(d);
			flatOffer.setFileName(f.getName());
			String additionalInfo = extractAdditionInfoFromOffer(d);
			if (additionalInfo.isEmpty()) {
				System.out.println("There is a problem when extracting additional information from file :   " + f.getName());
			}
			flatOffer.setAdditionInformation(additionalInfo);
			flatOffer.setStemmedAdditionInformation(bgStemmer.stemParagraph(additionalInfo));
			return flatOffer;
		}
		catch(Exception e) {
			System.out.println(f.getName());
			return null;
		}
	}
	
//	public void extractInfo() throws Exception {
//		File htmlsDir = new File("D:\\java\\workspace\\Crawling\\htmls");
//		File testResult = new File("result.txt");
//		testResult.createNewFile();
//		FileOutputStream debugFileOutputStram = new FileOutputStream(testResult);
//		OutputStreamWriter charOutput = new OutputStreamWriter(
//				debugFileOutputStram, Charset.forName("UTF-8").newEncoder() 
//			 );
////		String result = Arrays.stream(htmlsDir.listFiles())
////			.map(f -> {
////				return f.getName() + " \n " + extractAdditionInfoFromOffer(f) + "\n";
////			})
//		.collect(Collectors.joining("\n\n\n"));
//				
//		charOutput.write(result);
//		charOutput.close();
//	}

	private String extractAdditionInfoFromOffer(Document d) {
		Element mainInformation = d.getElementsMatchingOwnText("Допълнителна информация").first();
		String additionInformation = "";
		if (mainInformation == null) {
			return "";
		}
		for (Element parent : mainInformation.parents()) {
			if (parent.text().length() > mainInformation.text().length()) {
				Elements childElements = parent.getElementsByIndexGreaterThan(1);
		
				additionInformation = childElements
										  .stream()
										  .map(el -> el.text())
										  .collect(Collectors.joining("\n"));
				break;
			}
		}
		return additionInformation;
	}
}
