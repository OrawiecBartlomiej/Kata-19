import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class WordChainTest {
	
	private WordChain testedClass;

	@Before
	public void setUp() throws Exception 
	{
		testedClass = new WordChain();
	}
	
	@Test
	public void testWordsSimilar() 
	{
		boolean result = true;
		assertEquals(result,testedClass.wordsSimilar("cup", "cut"));
		
		result = true;
		assertEquals(result,testedClass.wordsSimilar("told", "gold"));
		
		result = true;
		assertEquals(result,testedClass.wordsSimilar("glistered", "blistered"));
		
		
		result = false;
		assertEquals(result,testedClass.wordsSimilar("cup", "cup"));
		
		result = false;
		assertEquals(result,testedClass.wordsSimilar("", ""));
		
		result = false;
		assertEquals(result,testedClass.wordsSimilar("cup", "dog"));
		
		result = false;
		assertEquals(result,testedClass.wordsSimilar("cup", "cat"));
	}
	
	@Test
	public void testCheckWords() 
	{
		
		testedClass.loadWords(3);
		
		
		
		boolean result = false;
		assertEquals(result,testedClass.checkWords("ddd","aaa"));
		
		result = false;
		assertEquals(result,testedClass.checkWords("dog","aaa"));
		
		result = false;
		assertEquals(result,testedClass.checkWords("tttt","tree"));
		
		result = false;
		assertEquals(result,testedClass.checkWords("","hut"));
		
		result = false;
		assertEquals(result,testedClass.checkWords("",""));
		
		result = false;
		assertEquals(result,testedClass.checkWords("tree","aaa"));
		
		result = false;
		assertEquals(result,testedClass.checkWords("dog","gold"));
		
		result = false;
		assertEquals(result,testedClass.checkWords("orange","hut"));
		
		result = false;
		assertEquals(result,testedClass.checkWords("gold","hear"));
		
		result = true;
		assertEquals(result,testedClass.checkWords("cog","dog"));
	}

	
	@Test
	public void testLoadWords() 
	{
		testedClass.loadWords(3);	
		assertEquals(true, testedClass.checkWords("cat","dog"));
		assertEquals(false, testedClass.checkWords("",""));
		assertEquals(false, testedClass.checkWords("lead","gold"));
		 
	}
	
	@Test
	public void testWordsChain() 
	{
		String[] result = {"cat","cot","cog","dog"};
		ArrayList<String> methodResult = testedClass.wordsChain("cat","dog");
		assertEquals(result[0], methodResult.get(0));
		assertEquals(result.length, methodResult.size());
		
		result = new String[] {"goad","gold"};
		methodResult = testedClass.wordsChain("goad","gold");
		assertEquals(result[1], methodResult.get(1));
		assertEquals(2, methodResult.size());
		 
		result = new String[] {"lead","load","gold"};
		methodResult = testedClass.wordsChain("lead","gold");
		assertNotEquals(result.length, methodResult.size());
		
		//timing
		long startTime = System.currentTimeMillis(); 
		methodResult = testedClass.wordsChain("cat","dog");
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("cat-dog miliseconds: "+estimatedTime);
		
		
		
		startTime = System.currentTimeMillis();
		methodResult = testedClass.wordsChain("ruby","code");
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("ruby-code miliseconds: "+estimatedTime);
		
	}

}
