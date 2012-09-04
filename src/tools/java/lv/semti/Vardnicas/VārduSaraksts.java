/*******************************************************************************
 * Copyright 2012 Institute of Mathematics and Computer Science, University of Latvia; Author: Pēteris Paikens
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package lv.semti.Vardnicas;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import lv.semti.morphology.analyzer.*;
import lv.semti.morphology.attributes.AttributeNames;
import lv.semti.morphology.lexicon.*;

public class VārduSaraksts {

	public static void main(String[] args) throws Exception {
		Analyzer analizators = new Analyzer("dist/Lexicon.xml",false);
		
		PrintWriter izeja = new PrintWriter(new PrintStream(System.out, true, "UTF8"));
		//BufferedWriter izeja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF-8"));
		
		for (Paradigm p : analizators.paradigms)
			for (Lexeme l : p.lexemes) {
				//if (p.getID() != 20 /*&& p.getID() != 17*/) continue;
				if (p.getID() != 15 && p.getID() != 17) continue;
				if (!l.getStem(0).endsWith("gulē")) continue;
				//if (!l.getStem(0).endsWith("cā")) continue;
				
				izeja.println(l.getValue(AttributeNames.i_Lemma));
				ArrayList<Wordform> formas = analizators.generateInflections(l);
				for (Wordform forma : formas) {
					forma.removeNonlexicalAttributes();
					izeja.printf("%s\t%s\n",forma.getToken(),forma.toJSON());
				}
//				break;
			}
	
		izeja.flush();
		izeja.close();
	}

}
