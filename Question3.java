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