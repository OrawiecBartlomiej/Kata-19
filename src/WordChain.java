import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class WordChain {
	
	ArrayList<String> words;
	LinkedList<String> adjacentWords;
	HashMap<String, String> visited;
	
	public void loadWords(int length) 
	{	
		try
		{	
			words = new ArrayList<>();
			String word;
			BufferedReader reader = new BufferedReader(new FileReader("wordlist.txt"));
			while((word = reader.readLine())!=null)
				if(word.length()==length) words.add(word);
				
			reader.close();
		}
		catch(IOException ex)
		{
			System.out.println("IOException, loadWords");
		}
		
	}
	
	
	public boolean checkWords(String first, String last) 
	{
		if(first.length()!=last.length()) 
			{
				System.out.println("Words have different length");
				return false;
			}
		if(words.contains(first) && words.contains(last)) 
			{
				return true;
			}
		System.out.println("At least one word is not in the dictionary");
		return false;
	}
	
	
	public boolean wordsSimilar(String s1, String s2)
	{
		int counter = 0;
		for(int i = 0; i<s1.length();i++)
			if(s1.charAt(i)!=s2.charAt(i)) counter++;
		
		return counter==1 ? true:false;
	}
	
	
	public ArrayList<String> wordsChain(String first, String last) 
	{
		
		adjacentWords = new LinkedList<>();
		visited = new HashMap<>();
		ArrayList<String> chain = new ArrayList<>();
		String searched;
		
		if(first.equals(last)) 
		{
			chain.add(first);
			return chain;
		}
		
		loadWords(first.length());
		if(!checkWords(first,last)) return null;
		
		visited.put(first, null);
		adjacentWords.add(first);
		
		while(!adjacentWords.isEmpty())
		{
			searched = adjacentWords.removeFirst();
			Iterator<String> i = words.iterator();
			while(i.hasNext())
			{
				String w = (String) i.next();
				if(!visited.containsKey(w) && wordsSimilar(w,searched))
				{
					visited.put(w, searched);
					if(last.equals(w)) //when we reach last word
					{
						for(int j=0;j>-1;j++)
						{
							chain.add(0,w);
							w = visited.get(w);
							if(w==null) return chain;
						}
					}
					adjacentWords.add(w);
				}
	
			}
		
		}
		return null;
	}
	
	public static void main(String[] args) 
	{
		WordChain cf = new WordChain();
		ArrayList<String> s = cf.wordsChain("cat","dog");
		for(String i: s) System.out.print(i+", ");
	
	}

}
