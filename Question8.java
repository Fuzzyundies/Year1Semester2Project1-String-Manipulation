//TJ make sure to import this and the function from question 3

import javax.swing.JOptionPane;
	

public static void Question8()
{
	
	/*
	Project 1, question 8, accept a word/phrase/sentence from the end user. 
	Determine if the user input is a palindrome. A palindrome, in this case, a palindrome can either be alphabetic, phrase, or both.

	Coded by Eric Lambert
	*/
	
	String prompt = "Please enter word or phrase or sentence to check if it is a Palindrome.\nOnly use alphabetic characters and common punctuation (punctuation will not affect outcome)."; 
	String pattern = ("[a-zA-Z,.-;'!?");
	String userInput = isValid(prompt, pattern);
	//String userInput = JOptionPane.showInputDialog(null, prompt); //used to test

	//Checks if user cancelled out of the isValid method, if it does it exits the method.
	if(userInput.length() != 0)
	{
		String checkChar, stringChar, userPhrase, checkWord, result;
		int phraseLength = userPhrase.length();
		boolean isPalindrome = true, isPhrasePalindrome = true;
		String[] phrasePalindrome;
		String userPhrase = userInput.toLowerCase().replaceAll("[^a-z]", "");
		
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