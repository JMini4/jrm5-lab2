/*
 * Name: Julia Mini
 *
 * Lab: Thursday 1 - 4
 *
 * Self Check Problem 3.2: What is the difference between the add(v) and add
 * add(i,v) methods of vector? The difference between these two methods is
 * that the add(v) method appends the object v to the end of the vector and
 * the add(i,v) method inserts the object v at the index i. Therefore in the
 * method which inserts, every object locates at i and higher is shifted up. 
 * 
 * Self Check Problem 3.3: What is the difference between the add(i,v) method
 * and the set(i,v) method? The difference between these two methods is that
 * the add(i,v) method inserts a new object v at the index i. In this case the
 * vector increases in size. The set(i,v) will look at the object currently at
 * index i in the vector and change the object to the one, v, that the method
 * had been passed. In this case the vector's size does not change.
 *
 * Self Check Problem 3.4: What is the difference between the remove(v) method
 * (v is an Object value) and the remove(i) (i is an int value)? The remove(v)
 * method searches the vector for the first instance of the object v and 
 * removes it. If there is no object within the vector that matches the 
 * what was passed to the method then the vector remains unchanged. Whereas
 * the remove(i) method removes the object found at index i. The size of the 
 * vector in both cases is decreased.
 *
 * Problem 3.6: What is the advantage of having a special purpose vector like
 * BitVector? The main advantage is space. In a vector, each element may be of 
 * size 32 bits, but a boolean such as 0 for false and 1 for true is only 1
 * bit. Therefore the vector is 32 times as large as it needs to be so we can
 * efficiently store it. 
 *
 */

import structure5.*;
import java.util.Random;
import java.util.Scanner;

// Class WordGen creates a string of any desired length based off of the Table
// and input from a scanner
public class WordGen {
    Table table = new Table();

    public static void main(String[] args){
	WordGen wordGen = new WordGen();
	if (args.length != 2) {
	    
	    // no arguments, so print usage line and don't do anything else
	    System.out.println("Usage: java Wordgen <numLetters, k>");
	    System.exit(0);
	}
	int numLetters = Integer.parseInt(args[0]);
	int k = Integer.parseInt(args[1]);

	Scanner in = new Scanner(System.in);
	String text = "";
	while (in.hasNextLine()) {
	    text += in.nextLine() + " ";
	}
	
	wordGen.buildTable(text, k);
	System.out.println(wordGen.generateLetters(numLetters, k));

    }
    
    // iterates through text and creates a table of prefixes with a substring
    // of length k
    public void buildTable(String text, int k){
	for(int i = 0; i+k < text.length(); i++){
	    String prefix = text.substring(i, i+k);
	    String letter = text.substring(i+k, i+k+1);
	    table.addPrefix(prefix, letter);
	}
    }
    
    // randomly generates a prefix from the table
    public String getPrefix(){
	Random rand = new Random();
	int randPrefix = rand.nextInt(table.getNumPrefixes());
	return table.getPrefix(randPrefix);
    }

    // starting with the randomly generate prefix, generate a frequency list
    public String generateLetters(int numLetters, int k){
	String result = getPrefix();
	int i = 0;
	while (i < numLetters){
	    String prefix = result.substring(i, i+k);
	    FrequencyList freqList = table.getFreqList(prefix);
	   
	    // addition accounts for when prefix is not found
	    String addition = generateLetter(freqList);
	    result += addition;
	    i += addition.length();
	}
	return result;
    }

    // picks a random letter, weighted by frequency, from the frequency list
    // of the prefix
    public String generateLetter(FrequencyList freqList) {
	if(freqList.sumCounts == 0){
	    return getPrefix();
	}
        Random randLetters = new Random();
	int randLetter = randLetters.nextInt(freqList.sumCounts);
	
	// keeps track of the counts in the frequency list to weight the 
	// letters appropriately
	int runningCount = 0;
	for(int i = 0; i < freqList.getNumLetters(); i++){
	    Association <String,Integer> association = freqList.getLetter(i);
	    runningCount += association.getValue();
	    if (randLetter < runningCount){
		return association.getKey();
	    }
	}

	// in case where there are no known successor characters for the prefix
	return "a";	    
    }

}