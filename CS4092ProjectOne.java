import javax.swing.* ;

public class CS4092ProjectOne
{
	public static void main(String [] args)
	{
		int choice ;
		String menuOption = "" ;
		
		while ((menuOption != null) && (!(menuOption.equals("0"))))
		{
			menuOption = getMenuOption () ;
			if (menuOption != null)
			{
				choice = Integer.parseInt(menuOption) ;
				if (choice != 0)
				{
					switch(choice)
					{
						case 1: vowelContent() ; break ;
						case 2: consonantContent() ; break ;
						case 3: characterContent() ; break ;
						case 4: qwertyRows() ; break ;
						case 5: alternatingVowelsConsonants() ; break ;
						case 6: wordLengths() ; break ;
						case 7: anagrams() ; break ;
						case 8: palindromes() ; break ;
					}
				}
			}
		}
	}
	
	public static String getMenuOption()
	{
		String menuOptions = 	"1. Analyze vowle content of a word/phrase." +
								"\n2. Analyze consonant content of a word/phrase." +
								"\n3. Analyze character content of a word/phrase." +
								"\n4. Determine rows of a QWERTY keyboard used in a word/phrase." +
								"\n5. Determine whether a word/phrase has alternating vowles and consonants." +
								"\n6. Display the shortest and longest words in a sentence." +
								"\n7. Display whether two words/phrases are anagrams of eachother." +
								"\n8. Determine whether a word/phrase is a palindrome." +
								"\n0. Exit." ;
								
		String menuMessage = "Choose number of the option you wish to execute." ;
		String 	errorMessage = "Invalid menu selection. \n\nValid options are 0-8 inclusive." ;
				errorMessage += "\nSelect OK to retry" ;
		String errorHeader = "Error in user input" ;
		boolean validInput = false; ;
		String selection = "", menuChoicePattern = "[0-8]{1}" ;
		
		while (!(validInput))
		{
			selection = JOptionPane.showInputDialog(null, menuOptions, menuMessage, 3) ;
			
			if (selection == null || selection.matches(menuChoicePattern))
				validInput = true ;
			else
				JOptionPane.showMessageDialog(null, errorMessage, errorHeader, 2) ;
		}
		return selection ;
	}
	
	public static String isValid(String prompt, String pattern)
	{
		String userInput, errorMessage = "Invalid input!";
		//Assume that input is invalid unless proven otherwise.
		boolean valid = false;
		do{
			userInput = JOptionPane.showInputDialog(null, prompt);
			//If the user clicks on cancel button it returns them to the main menu.
			if(userInput == null)
				return userInput;
			else
			{
				//Trim the input in case of leading and following spaces and compare it to the pattern.
				userInput = userInput.trim();
				if(userInput.matches(pattern))
					valid = true;
				//Display an error message if input is invalid.
				else
					JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}while(!valid);
		return userInput;
	}
	
	public static void vowelContent()
	{

	//Daniel Keighley 16185153
	String prompt = "Enter phrase to show what vowels are used. \n(Alphabetic characters only.)" ;
	String pattern = "(([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)|((\")([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)(\")" ;
	String n = isValid(prompt, pattern) ;
		
	String vowels = "aeiou";
	int i, j;
	int vowelCounter[] = new int[vowels.length()];   //how many vowels 
	int vowelMax[] = new int[vowels.length()];   //Used to record max index of vowels
	int vowelMin[] = new int[vowels.length()];	 //Used to record min index of vowels
	boolean oneOfEach = true;
	boolean anyVowels = false;
	boolean allVowels = true;
	String result = "";
	int forwardOrder = 0;
	int backwardOrder = 0;
	
		for(i = 0; i < (vowels.length()); i++){
			for(j = 0; j < (n.length()); j++){
			
				if(vowels.charAt(i) == n.charAt(j)){   //if the character is the vowel at current subscript
					vowelCounter[i]++;
					anyVowels = true;
					vowelMax[i] = j;					//records the max index into vowelMax
					if(vowels.charAt(i) < vowelMax[i]){		//if this is the smallest index, it's min index
						vowelMin[i] = j;
					}
				}
			}
		}
	
	if(anyVowels == false){
		result += "There are no vowels\n";
	}
	else{
		for(i = 0; i < vowelCounter.length; i++){
			if(vowelCounter[i] == 0)
				allVowels = false;
		}
		if(allVowels == true){
			result += "All vowels are present\n";
		}
		
		for(i = 0; i < vowelCounter.length; i++){
			if(vowelCounter[i] > 1){
				oneOfEach = false;
			}
		}
		if(oneOfEach == true)
		{
		for(i = 0; i < vowelMax.length - 1; i++) {		
		if(vowelMin[i] < vowelMax[i + 1])		//If the min index of the vowel is less than the max index of the next one			
			forwardOrder++;						//Then that's one vowel in alphabetical order
		if(vowelMax[i] > vowelMin[i + 1])
			backwardOrder++;
		}
		
				}	
				
				
			if(forwardOrder == 4){				//if all vowels are in order
			result += "All vowels are in alphabetical order\n";
			}
			if(backwardOrder == 4){
			result += "All vowels are in backward alphabetical order\n";
			}
		}	

	for(i = 0; i < vowels.length(); i++)
			result+= "Frequency of " + vowels.substring(i, i+1) + " = " + vowelCounter[i] + "\n";
		
		JOptionPane.showMessageDialog(null, result) ;
	}
	
	public static void consonantContent()
	{
		//Stephen Jameson 16175786
		String pattern = "([a-zA-Z]+)|((([a-zA-Z]+((,\\s)|(\\s)))+)(([a-zA-Z]+)|(([a-zA-Z]+)((\\.)|(!)|(?)))))", prompt = "Please enter a word, a phrase or a sentence to check the consonant frequency.\nOnly use alphabetic characters and punctuation.";
		String userInput = isValid(prompt, pattern);
		if(userInput != null)
		{
			String newString, message, consonants = "bcdfghjklmnpqrstvwxz";
			int count [] = new int [consonants.length()];
			userInput = JOptionPane.showInputDialog("Please enter a string.");
			//Convert the user input to lower case characters and replace all characters that are not consonants with empty string.
			newString = userInput.toLowerCase().replaceAll("[^bcdfghjklmnpqrstvwxz]","");
			do{
				//Get the first character of the string and increment the location in the array corresponding to the index of the character in the string with consonants.
				count[consonants.indexOf(newString.charAt(0))]++;
				//Keep replacing the first character with the empty string until there are no characters left.
				newString = newString.replaceFirst("" + newString.charAt(0),"");
			}while(newString.length() != 0);
			message = "User input \"" + userInput + "\" contains following consonants:\nConsonant\tFrequency";
			//Set up a loop to go through the array and add all characters and their frequency to the message if they appeared at least once int the input.
			for(int i = 0; i < count.length;i++)
				if(count[i] != 0)
					message += "\n" + consonants.charAt(i) + "\t" + count[i];
			JOptionPane.showMessageDialog(null, new JTextArea(message), "Consonant content", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public static void characterContent()
	{

		/*
		Project 1, question 3, accept a word/phrase/sentence from the end user. 
		Analyze the character content of this input, reporting the count of the alphabetic characters, the count of the numberic characters, and the count of any other symbols. 
		In addition your program should report the frequency of each character found in this input.

		Coded by Eric Lambert 15117626
		*/
	
		String prompt = "Please enter word, phrase, or sentence to analyze the content of. Any use of alphabetic, numeric, or symbols is allowed.";
		//Pattern to take any character, but still in a proper sentence structure. S takes any non-white character
		String pattern = "((\\S)+)|((((\\S)+(\\s))+)(\\S)+)"; 
		String userInput = isValid(prompt, pattern);
		//String userInput = JOptionPane.showInputDialog(null, prompt); //used to test

		
		if (userInput != null)
		{
			String spaces = "\\s", numbers = ("[0-9]"), alphabet = "([a-z])", tempChar;
			String result = "With your original word, phrase, or sentence of \n\"" + userInput + "\"\n\n";
			int alphabeticCount = 0, numberCount = 0, symbolCount = 0, duplicateCount, tempCount;
			//Makes a duplicate string without spaces to edit and modify as needed. Also lowers any upper case letters to lower case.
			String userPhrase = userInput.replaceAll("\\s", "").toLowerCase();
			
			for (int i = 0; i < userPhrase.length(); i++)
			{
				tempChar = userPhrase.substring(i, i+1);
				if (tempChar.matches(numbers))
					numberCount++;			
				
				else if (tempChar.matches(alphabet))
					alphabeticCount++;
				
				else
					symbolCount++;
			}	

			result += "The amount of Alphabetic characters = " + alphabeticCount + "\n";
			result += "The amount of Numeric characters = " + numberCount + "\n";
			result += "The amount of Symbol characters = " + symbolCount + "\n\n";
			result += "Your word, phrase, or sentence was made up of the following characters: " + "\n\n";
			
			/*
			This loop finds the frequency of each character.
			It works be getting the first character always, deletes it, then uses a nested loop to find any other occurrences of that character.
			If it finds another occurrence of the current character, it incrementes the counter, deletes that character, and starts again in the same spot.
			Not incrementing the outer loop was by choice to prevent skipping of characters. It ends when the userPhrase is empty.
			Decrementing the inner loop if it finds a duplicate prevents potentially skipping a character.
			After each iteration of the outer loop, it adds the current character and count to result.
			*/
			
			for (int i = 0; i < userPhrase.length(); )
			{
				tempCount = 1;
				tempChar = userPhrase.substring(i, i + 1);
				userPhrase = userPhrase.replaceFirst(tempChar, "");
				for (int j = 0; j < userPhrase.length(); j++)
				{
					if(userPhrase.contains(tempChar))
					{
						tempCount += 1;
						userPhrase = userPhrase.replaceFirst(tempChar, "");
						j--;
					}
				}
				result += "\' " + tempChar.toUpperCase() + " \' :\t" + tempCount + "\n";
			}

			JOptionPane.showMessageDialog(null, new JTextArea(result), "Break down of your input", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void qwertyRows()
	{
	//Thomas Bowles; ID 16166884.
	//Variables needed for the code including patterns for each qwerty row.
		String pattern1 = "qwertyuiop" ;
		String pattern2 = "asdfghjkl" ;
		String pattern3 = "zxcvbnm" ;
		boolean row1 = false, row2 = false, row3 = false ;
		String tempInput = "", aChar = "", result = "The rows of the QWERTY Keyboard used are:" ;
		int check ;
		
		String prompt = "Enter phrase to see what QWERTY rows are used. \n(Alphabetic characters only.)" ;
		String pattern = "(([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)|((\")([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)(\")" ;
		String input = isValid(prompt, pattern) ;
		
		if (input != null)
		{
			tempInput = input.toLowerCase().replaceAll("[^a-z0-9]", "") ;
				
			/* 3 for loops that run until they find a letter from their respective row.
			Once they find that letter they break out using a boolean and adds a string that 
			says the row has been used to the result variable.*/ 
				for(int i=0 ; i < tempInput.length() ; i++)
					{
						aChar = tempInput.substring(i, i+1) ;
						check = pattern1.indexOf(aChar) ;
						if(check != -1)
							row1 = true ;
					}
					if(row1 == true)
						result += "\nRow One" ;
					
				for(int i=0 ; i < tempInput.length() ; i++)
					{
						aChar = tempInput.substring(i, i+1) ;
						check = pattern2.indexOf(aChar) ;
						if(check != -1)
							row2 = true ;
					}
					if(row2 == true)
						result += "\nRow Two" ;
					
				for(int i=0 ; i < tempInput.length() ; i++)
					{
						aChar = tempInput.substring(i, i+1) ;
						check = pattern3.indexOf(aChar) ;
						if(check != -1)
							row3 = true ;
					}
					if(row3 == true)
						result += "\nRow Three" ;
					
				JOptionPane.showMessageDialog(null, result) ;
		}
	}
	
	public static void alternatingVowelsConsonants()
	{
		//Thomas Bowles; ID 16166884
	//Below are all the variables needed for the code.
		String pattern1 = "aeiou" ;
		Boolean alternating = true ; 
		String tempInput = "", result = "", aChar = "", bChar = "" ;
		int checkA, checkB ;
		
		String prompt = "Enter phrase to checl if vowels and consonants alternate. \n(Alphabetic characters only.)" ;
		String pattern = "(([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)|((\")([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)(\")" ;
		String input = isValid(prompt, pattern) ;
		
		if(input != null)
		{
		//removal of all non alphabetic data and making every letter lower case.
			tempInput = input.toLowerCase().replaceAll("[^a-z]", "") ;

			
		/*For loop to pull out the letter at subscript i and the laetter after to
		determine if the vowles and consonants are alternating.
		It took a while to get this part of the code running correctly and I am not sure
		if there is a more efficiant way to run this, but this was the way that worked for me.*/
			for(int i = 0 ; i < (input.length()-1) && alternating == true; i++)
			{
				aChar = tempInput.substring(i,i+1) ;
				bChar = tempInput.substring(i+1,i+2) ;
				checkA = pattern1.indexOf(aChar) ;
				checkB = pattern1.indexOf(bChar) ;	
				
				if((checkA == -1 && checkB != -1)||(checkA != -1 && checkB == -1))
					alternating = true ;
				else
					alternating = false ;
			}
			
			if(alternating==false)
				result+="Vowles and Consonants are not Alternating." ;
			else
				result+="Vowles and Consonants are Alternating." ;
			
			JOptionPane.showMessageDialog(null, result) ;
		}
	}
	
	public static void wordLengths()
	{
	
	//Daniel Keighley 16185153
	
		String prompt = "Enter phrase to see what the longest word is. \n(Alphabetic characters only.)" ;
		String pattern = "(([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)|((\")([a-zA-Z']+((((,)|(;))\\s)|(\\s)))*([a-zA-Z']+)((\\.)|(!)|(\\?))?)(\")" ;
		String n = isValid(prompt, pattern) ;
		
		String[] words = n.split(" ");  //array of singular words
		int i;
		int longestIndex = 0;
		int shortestIndex = 10000;
		String longestWords = "";
		String shortestWords = "";
		String result = "";
		String errorMessage = "Invalid input";
		
		
		if(n != null)
		{
			for(i = 0; i < (words.length - 1); i++)  
				if(words[i].length() > longestIndex)     
					longestIndex = (words[i].length());     //if it's longer than anything previous it becomes the longest
				else if(words[i].length() < shortestIndex)
					shortestIndex = (words[i].length());    //if it's shorter than anything previous it becomes the shortest
					
			for(i = 0; i < (words.length - 1); i++)
				if(words[i].length() == longestIndex && !(longestWords.contains(words[i])))
					longestWords += words[i] + " ";         //the words of longest length go into a string
				else if(words[i].length() == shortestIndex && !(shortestWords.contains(words[i])))
					shortestWords += words[i] + " ";	    //the words of shortest length go into a string
					
			result += "The longest length of a word is " + longestIndex + "\n";
			result += "Words that are this length are: " + longestWords + "\n";
			result += "The shortest length of a word is " + shortestIndex + "\n";
			result += "Words that are this length are: " + shortestWords + "\n";
		}
		else
			result = errorMessage;	
	}
	
	public static void anagrams()
	{
		//Stephen Jameson 16175786
		String pattern = "([a-zA-Z]+)|((([a-zA-Z]+((,\\s)|(\\s)))+)(([a-zA-Z]+)|(([a-zA-Z]+)((\\.)|(!)|(?)))))", prompt = "Please enter a word, a phrase or a sentence to check the consonant frequency.\nOnly use alphabetic characters and punctuation.";
		String message = "" ;
		String userInput1 = isValid(prompt, pattern);
		String userInput2 = isValid(prompt, pattern);
		if(userInput1 != null && userInput2 != null)
		{
			String newString1, newString2, alphabet = "abcdefghijklmnopqrstuvwxyz";
			boolean isAnagram = true;
			//Convert the user inputs to lower case and get rid of all unnecessary characters.
			newString1 = userInput1.toLowerCase().replaceAll("[^a-z]","");
			newString2 = userInput2.toLowerCase().replaceAll("[^a-z]","");
			if(userInput1.length()!=userInput2.length())
				isAnagram = false;
			else
			{
				//Set up arrays to keep the count of the consonants, each slot corresponds to one character.
				int count1 [] = new int [alphabet.length()];
				int count2 [] = new int [alphabet.length()];
				do{
					//Get the first character of the string and increment the arrray at the same index as the corresponding character.
					count1[alphabet.indexOf(newString1.charAt(0))]++;
					count2[alphabet.indexOf(newString2.charAt(0))]++;
					//Keep replacing the first charcter with empty string until there are no characters left.
					newString1 = newString1.replaceFirst("" + newString1.charAt(0),"");
					newString2 = newString2.replaceFirst("" + newString2.charAt(0),"");
				}while(newString1.length() != 0);
				message = "User input \"" + userInput2 + "\" is ";
				//Set up a loop to compare the two arrays, if they are identical, that means the user inputs are anagrams of each other.
				for(int i = 0; i < count1.length && isAnagram; i++)
					if(count1[i]!=count2[i])
						isAnagram = false;
			}
			//If the inputs are not anagrams simply add "not" to the message.
			if(!isAnagram)
			message += "not " ;
			message += "an anagram of \"" + userInput1 + "\"";
			JOptionPane.showMessageDialog(null, message, "Anagram check result", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public static void palindromes()
	{
	
	/*
	Project 1, question 8, accept a word/phrase/sentence from the end user. 
	Determine if the user input is a palindrome. A palindrome, in this case, a palindrome can either be alphabetic, phrase, or both.

	Coded by Eric Lambert
	*/
	
	String prompt = "Please enter word or phrase or sentence to check if it is a Palindrome.\nOnly use alphabetic characters and common punctuation (punctuation will not affect outcome)."; 
	String pattern = "((\\S)+)|((((\\S)+(\\s))+)(\\S)+)";
	String userInput = isValid(prompt, pattern);
	//String userInput = JOptionPane.showInputDialog(null, prompt); //used to test

	//Checks if user cancelled out of the IsValid method, if it does it exits the method.
	if(userInput.length() != 0)
	{
		String checkChar, stringChar, userPhrase, checkWord, result;
		userPhrase = userInput.toLowerCase().replaceAll("[^a-z]", "");
		int phraseLength = userPhrase.length();
		boolean isPalindrome = true, isPhrasePalindrome = true;
		String[] phrasePalindrome;
		
		
		/*
		This for loop checks if userInput is an alphabetic palindrome.
		By definition, it checks both end characters against the other, and moves in 1 respectively until it fails, or reaches at the middle.
		If the userInput.length() is odd, it does not check the middle character, as it is the same character.
		*/
		
		for (int i = 0; i < phraseLength /2 && isPalindrome; i++) 
		{
			checkChar = userPhrase.substring((phraseLength - i)-1,phraseLength - i); //could also use charAt
			stringChar = userPhrase.substring(i, i + 1);
			if (!(checkChar.equals(stringChar)))
				isPalindrome = false;
		}
		
		//Prepares userPhrase to be checked if it is a phrase palindrome. It splits the userInput into an array.
		userPhrase = userInput.toLowerCase().replaceAll("[^a-z\\s]", ""); //replaces everything except a-z and " "
		phrasePalindrome = userPhrase.split("\\s");
		
		/*
		This loop checkes to see if userInput is greater than 1 word. If it is only 1, it cannot be a phrase palindrome.
		It uses the same check but, checks the word in the string[] phrasePalindrome.
		*/
		
		if(phrasePalindrome.length <= 1)
			isPhrasePalindrome = false;
		else
		{
			for (int i = 0; i < phrasePalindrome.length /2 && isPalindrome; i++) //only enters this if statement if the previous check failed.
			{
				userPhrase = phrasePalindrome[i];
				checkWord = phrasePalindrome[phrasePalindrome.length - i - 1];
				if (!(checkWord.equals(userPhrase)))
					isPhrasePalindrome = false;
			}
		}
		
		if (isPalindrome)
			result = "\"" + userInput + "\"" + " is a alphabetic palindrome!";
		else
			result = "\"" + userInput + "\"" + " is not a alphabetic palindrome.";
		
		if (isPhrasePalindrome)
			result += "\n\"" + userInput + "\"" + " is a phrase palindrome!";
		else
		{
			result += "\n\"" + userInput + "\"" + " is not a phrase palindrome!";
			if (phrasePalindrome.length <= 1)
				result += "\nHas to be more than 1 word to qualify as a phrase palindrome.";
		}
		JOptionPane.showMessageDialog(null, result, "Palindrome Check", JOptionPane.INFORMATION_MESSAGE);
	}
}
}