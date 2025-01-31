/*******************************************************************************
 * Copyright 2008, 2009, 2014 Institute of Mathematics and Computer Science, University of Latvia
 * Author: Pēteris Paikens
 * 
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
package lv.semti.morphology.attributes;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;

abstract class Attribute {
	String attributeLV = "";
	String attributeEN = "";
	String description = "";
	
	protected abstract String xmlTagName(); 

	Attribute (Node node) {
		if (!node.getNodeName().equalsIgnoreCase(xmlTagName())) throw new Error("Node was '" + node.getNodeName() + "' but " + xmlTagName() + " was expected.");

		Node n = node.getAttributes().getNamedItem("LV");
		if (n != null)
			this.attributeLV = n.getTextContent();

		n = node.getAttributes().getNamedItem("EN");
		if (n != null)
			this.attributeEN = n.getTextContent();
		
		n = node.getAttributes().getNamedItem("Description");
		if (n != null)
			this.description = n.getTextContent();
	}
	
	public void toXML (Writer straume) throws IOException {
		straume.write("<"+xmlTagName());
		if (!attributeLV.equals(""))
			straume.write(" LV=\"" + this.attributeLV + "\"");
		if (!attributeEN.equals(""))
			straume.write(" EN=\"" + this.attributeEN + "\"");
		if (!this.description.equals(""))
			straume.write(" Description=\"" + this.description + "\"");
		straume.write(">\n");
		// FIXME - netiek noseivoti tagu pozīcijas atribūti
		toXMLData(straume);
		straume.write("</" + xmlTagName() + ">\n");
	}

	protected abstract void toXMLData(Writer straume) throws IOException;
	public abstract boolean isAllowed(String value);

	public abstract List<String> getAllowedValues(String language);
}
