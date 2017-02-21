/*
 * Name: Julia Mini 
 *
 *Lab: Thursday 1- 4
 */

import structure5.*;

// creates a class table that is a vector of associations whose key is a prefix
// and value is a frequency list
public class Table {
    protected Vector<Association<String, FrequencyList>> table;

    public Table() {
	    table = new Vector<Association<String, FrequencyList>>();
	}

    // method that takes a prefix and the letter that follows the length of the
    // prefix and creates an association for the prefix and frequency list
    // that is created by addLetter() in the FrequencyList class
    public void addPrefix(String prefix, String letter){
	for(int i = 0; i < table.size(); i++){
	    Association <String, FrequencyList> association = 
		table.elementAt(i);
	    if (association.getKey().equals(prefix)){
		association.getValue().addLetter(letter);
		return;
	    }
	}

	FrequencyList frequencyList = new FrequencyList();
	frequencyList.addLetter(letter);
	table.add(new Association<String, FrequencyList>
		  (prefix, frequencyList)); 
    }

    public String toString() {
	String result = "";
	for (int i = 0; i < table.size(); i++) {
	    Association <String, FrequencyList> association = 
		table.elementAt(i);
	    result += association.getKey() + ": " + association.getValue()
		+ "\n";
	}
	return result;
    }

    // method that returns the size of the table
    public int getNumPrefixes(){
	return table.size();
    }

    // pre: index > 0 and index < table.size()
    // post: method that given an index returns element at that index in table
    public String getPrefix(int index) {
	assert (index > 0):
	index + " is out of bounds (negative)";
	assert (index < table.size()):
	index + " is out of bounds (larger than " + table.size() + ")";
	
	return table.elementAt(index).getKey();
    }

    // method that given a prefix returns the frequency list it is associated
    // with, if the prefix does not exist the method intializes a new FL
    public FrequencyList getFreqList(String prefix){
	for(int i = 0; i < table.size(); i++){
            Association<String, FrequencyList> association =
		table.elementAt(i);
            if (association.getKey().equals(prefix)){
                return association.getValue();
	    }
	}
	return new FrequencyList();
    }
}