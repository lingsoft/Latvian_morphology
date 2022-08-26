package analysis;

import lv.semti.morphology.analyzer.Analyzer;
import lv.semti.morphology.analyzer.Splitting;
import lv.semti.morphology.analyzer.Word;
import lv.semti.morphology.analyzer.Wordform;
import lv.semti.morphology.attributes.AttributeNames;
import lv.semti.morphology.attributes.AttributeValues;
import lv.semti.morphology.lexicon.Ending;
import lv.semti.morphology.lexicon.Lexeme;
import lv.semti.morphology.lexicon.Paradigm;
import java.io.BufferedReader;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
    
public class GetWordAnalysis {
  private static boolean stopOnEmpty = true; // quit on empty line
  public static void main(String[] args) {
    try {
      Analyzer analyzer = new Analyzer();
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "UTF8"));
      String s = "";
      while ((s = in.readLine()) != null && (s.length() != 0 || !stopOnEmpty)) {
        String format_s = s.replaceAll("[^\\p{Graph}\n\r\t ]", "");
        String[] s_split = format_s.split("\\s+");
        if (format_s.startsWith("analysis")) {
          Word result = analyzer.analyze(s_split[1]); 
          for (Wordform wf : result.wordforms) {
            wf.describe();
          }
        } else {
          List<Wordform> wordforms = analyzer.generateInflections(s_split[1]); 
          for (Wordform wf : wordforms) {
            wf.describe();
          }
        }
        System.out.println("");
      }
		  in.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
