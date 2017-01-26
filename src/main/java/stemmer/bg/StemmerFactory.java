package stemmer.bg;

import java.io.File;

public class StemmerFactory {
	
	public static Stemmer getBGStemmer() throws Exception {
		Stemmer s = new Stemmer();
		String path = "src/main/resources/stemmer/bg/dic/stem_rules_context_2_UTF-8.txt";
		s.loadStemmingRules(path);
		return s;
	}
}
