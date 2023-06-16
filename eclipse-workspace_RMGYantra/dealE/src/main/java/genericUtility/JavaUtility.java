package genericUtility;

import java.util.Random;

public class JavaUtility {
	
	public int generateRandomNo(int limit) {
		Random ran =new Random();
		return ran.nextInt(limit);
	}

}
