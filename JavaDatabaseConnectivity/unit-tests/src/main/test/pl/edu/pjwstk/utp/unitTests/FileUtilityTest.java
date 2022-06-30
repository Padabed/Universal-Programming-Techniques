package pl.edu.pjwstk.utp.unitTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class FileUtilityTest {
	
	@Test
	public void test() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void isReadableFilePath_SUCCESS() {
		// TEST DEFINITION
		final String existingFilePath = "data/input.txt";
		Assert.assertTrue(FileUtility.isReadableFilePath(existingFilePath));
		System.out.println("test success");
	}
	
	@Test
	public void isReadableFilePath_FAILURE() {
		// TEST DEFINITION
		String notExistingFilePath = "not-existing-file.txt";
		Assert.assertFalse(FileUtility.isReadableFilePath(notExistingFilePath));
		System.out.println("test failure");
		//Assert.fail();
	}
	
	@Before
	public void initializeBeforeEachTest() {
		// INVOKED BEFORE EACH TEST
		System.out.println("\nbefore");
	}
	
	@After
	public void deinitializeAfterEachTest() {
		// INVOKED AFTER EACH TEST
		System.out.println("after\n");
	}
	
	@BeforeClass
	public static void initializeTestClass() {
		// INVOKED ONCE WHEN A TEST CLASS IS LOADED
		System.out.println("before class");
	}
	
	@AfterClass
	public static void deinitializeTestClass() {
		// INVOKED ONCE WHEN A TEST CLASS IS UNLOADED
		System.out.println("after class");
	}
}