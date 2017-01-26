package stemmer.bg;
import java.io.*;
import java.nio.file.Files;
// jdk 1.4+!!!
import java.util.regex.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.util.*;

/**
 * Stemming algorithm by Preslav Nakov.
 * @author Alexander Alexandrov, e-mail: sencko@mail.bg
 * @since 2003-9-30
 */
public class Stemmer {
  
  public Hashtable stemmingRules = new Hashtable();
  
  public int STEM_BOUNDARY = 1;

  public static Pattern vocals = Pattern.compile("[^аъоуеияю]*[аъоуеияю]");
  public static Pattern p = Pattern.compile("([а-я]+)\\s==>\\s([а-я]+)\\s([0-9]+)");
  
  
  public void loadStemmingRules(String fileName) throws Exception {
    stemmingRules.clear();
    FileInputStream fis = new FileInputStream(fileName);
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    String s = null;
    while ((s = br.readLine()) != null) {
      Matcher m = p.matcher(s);
      if (m.matches()) {
        int j = m.groupCount();
        if (j == 3) {
          if (Integer.parseInt(m.group(3)) > STEM_BOUNDARY) {
            stemmingRules.put(m.group(1), m.group(2));
          }
        }
      }
    }
  }

  public String stem(String word) {
    Matcher m = vocals.matcher(word);
    if (!m.lookingAt()) {
      return word;
    }
    for (int i = m.end() + 1; i < word.length(); i++) {
      String suffix = word.substring(i);
      if ((suffix = (String) stemmingRules.get(suffix)) != null) {
        return word.substring(0, i) + suffix;
      }
    }
    return word;
  }
  
  public String stemParagraph(String text) throws Exception, IOException, UnsupportedEncodingException, FileNotFoundException {
		 Analyzer analyzer = new StandardAnalyzer();
		 StringBuffer stemmedFile = new StringBuffer(); 
		 TokenStream ts = analyzer.tokenStream("", new StringReader(text));	
		   try {
			   ts.reset();
				while(ts.incrementToken()) {
					String token = ts.getAttribute(CharTermAttribute.class).toString();
					String stemmedToken = stem(token);
					stemmedFile.append(stemmedToken).append(";");
				}
				ts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stemmedFile.toString();
	}
  
}