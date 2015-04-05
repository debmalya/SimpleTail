

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class SimpleTailTest {

	@Test
	public void testTail() {
		try {
			String[] args = new String[]{"./resource/FileWith10lines"};
			List<String> resultList = SimpleTail.tail(args);
			Assert.assertEquals("Expected result size 10, but found " + resultList.size(), 10, resultList.size());
			for (int i = 0; i < resultList.size(); i++) {
				Assert.assertEquals("Expected " + (i + 1) + " but found " + resultList.get(i), Integer.valueOf(i+1), Integer.valueOf(resultList.get(i)));
			}
		} catch(Throwable th){
			Assert.assertFalse("There should not be any error but found" + th.getMessage(), true);
		}finally {
			
		}
	}
	
	@Test
	public void testTail5() {
		try {
			String[] args = new String[]{"-n","5","./resource/FileWith10lines"};
			List<String> resultList = SimpleTail.tail(args);
			Assert.assertEquals("Expected result size 5, but found " + resultList.size(), 5, resultList.size());
			for (int i = 0; i < resultList.size(); i++) {
				Assert.assertEquals("Expected " + (i + 6) + " but found " + resultList.get(i), Integer.valueOf(i+6), Integer.valueOf(resultList.get(i)));
			}
		} catch(Throwable th){
			Assert.assertFalse("There should not be any error but found" + th.getMessage(), true);
		}finally {
			
		}
	}
	
	@Test
	public void testFail5() {
		try {
			String[] args = new String[]{"-n","-5","./resource/FileWith10lines"};
			SimpleTail.tail(args);
			Assert.assertFalse("There should  be error for -5", true);
		} catch(Throwable th){
			Assert.assertFalse("There should be error" + th.getMessage(), false);
		}finally {
			
		}
	}

	@Test
	public void testFailNoArgument() {
		try {
			String[] args = new String[]{};
			SimpleTail.tail(args);
			Assert.assertFalse("There should  be error for -5", true);
		} catch(Throwable th){
			Assert.assertFalse("There should be error" + th.getMessage(), false);
		}finally {
			
		}
	}
}
