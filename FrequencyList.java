/*                                                                                                               
 * Name: Julia Mini                                                                                              
 * Lab: Thursday 1- 4                                                                                            
 */

import structure5.*;

public class FrequencyList {
    protected Vector<Association<String, Integer>> frequencyList;

    public FrequencyList() {
	frequencyList = new Vector<Association<String, Integer>>();
    }

    public void addLetter(String letter, Integer count){
	//	for(i = 1; i < frequencyList.size(); i++){
	//  if charAt(i + 1)		    
	    //using substrings here - look up doc
	frequencyList.add(new Association<String, Integer>(letter, count));
    }

    public int checkCount(String letter){
	for(int i = 1; i < frequencyList.size(); i++){
	    Association<String, Integer> a = (Association<String, Integer>)frequencyList.get(i);
	    if(a.getKey().equals(letter)){
		return a.getValue();
		    } else{
		// in this line i want to add the character and initialize its count to 1 -frequencyList.add();
	    }
	}
    }   
}
