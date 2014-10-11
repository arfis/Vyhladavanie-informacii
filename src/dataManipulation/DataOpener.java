package dataManipulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataOpener {

	String[] suppLang = {"de","fr"};
	@SuppressWarnings("null")
	public DataOpener(String path,String searched)
	{
		File file = new File(path);
		BufferedReader reader = null;
		String line;
		Matcher matcher;

		Pattern pattern = 
	            Pattern.compile(normalizePattern(searched));
		
	           
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while((line = reader.readLine()) != null)
			{
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					
					//System.out.println("whole line: " + line + " matcher: \n" + 
					//matcher.group() + " at start: " +matcher.start() + " and end: " + matcher.end());
					System.out.println(matcher.group(2));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String normalizePattern(String searched)
	{
		searched = searched+".*";
		int count = 0;
		
		searched = normalizeText(searched);
		for(String lang:suppLang)
		{
			count++;
			if(count < suppLang.length)
			searched = searched+"((http://"+lang+".*resource/(.*)>) (<http://.*wiki.*>))|Industrial_action.*(";
			else
			searched = searched+"(http://"+lang+".*resource/(.*)>) (<http://.*wiki.*>))";
		}
		
		System.out.println("pattern: " + searched);
		return searched;
	}

	public String normalizeText(String text)
	{
		if(text.contains(" "))
		{
			text = text.replace(" ","_");
		}
		return text;
	}
}
