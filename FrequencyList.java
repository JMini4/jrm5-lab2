/*                                                                              * Name: Julia Mini                                        
 *
 * Lab: Thursday 1- 4
 */

import structure5.*;

// class FrequencyList creates a vector of associations which has a key that is
// a letter and a value which is the count for the frequency of that letter
public class FrequencyList {
    protected Vector<Association<String, Integer>> frequencyList;

    // variable that keeps track of the number of times any character is found
    public int sumCounts = 0;
    public FrequencyList() {
	frequencyList = new Vector<Association<String, Integer>>();
    }
    
    // method that creates an association for each new character and increments
    // the frequency value for each previous seen character
    public void addLetter(String letter){
	sumCounts++;
        for(int i = 0; i < frequencyList.size(); i++){
	    Association <String, Integer> association = 
		frequencyList.elementAt(i);
	    if (association.getKey().equals(letter)){
		association.setValue(association.getValue()+1);
		return;
	    }
	}     
	frequencyList.add(new Association<String, Integer>(letter, 1));
    }

    // returns the frequency given a character, else returns 0
    public int freqLetter(String letter){
	for(int i = 0; i < frequencyList.size(); i++){
	    Association <String, Integer> association = 
		frequencyList.elementAt(i);
	    if (association.getKey().equals(letter)){
		return association.getValue();
	    }
	}
	return 0;
    }
    
    // pre: index > 0 and index < frequencyList.size()
    // post: method that given an index returns the element at that index
    public Association<String, Integer> getLetter(int index) {
	assert (index > 0):
	index + " is out of bounds (negative)";
	assert (index < frequencyList.size()):
	index + " is out of bounds (larger than " +frequencyList.size() + ")";

	return frequencyList.elementAt(index);
    }

    // method that returns the size of the frequency list
    public int getNumLetters() {
	return frequencyList.size();
    }

    public String toString() {	
	String result = "";
	for(int i = 0; i < frequencyList.size(); i++){
	    Association <String, Integer> association = 
		frequencyList.elementAt(i);
	    result += association.getKey() + "- " +
		association.getValue() + ", ";
	}
	return result;
    }
}
