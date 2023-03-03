package org.jfree.data;

import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import junit.framework.TestCase;

public class DataUtilitiesTest extends TestCase  {
	
	DataUtilities dataUtilities = new DataUtilities() {};
	private DefaultKeyedValues2D values2D;
	private DefaultKeyedValues keyedValues;

	// Initialises variables for test
	@BeforeClass
	public void setUp() throws Exception {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		
		DefaultKeyedValues defaultKeyedValues = new DefaultKeyedValues();
		keyedValues = defaultKeyedValues;
	}

	// Resets all variables before next test is ran
	@After
	public void tearDown() throws Exception {
		values2D = null;
		keyedValues = null;
	}

//	// ****** Method 1 - Tests for Calculate Column Total *****************

	// Case 1: Column total with all positive values
	@Test
	public void testCalculateColumnTotalPositiveValues() {
		values2D.addValue(1, 0, 0);
		values2D.addValue(5, 1, 0);
		values2D.addValue(10, 2, 0);
		values2D.addValue(15.6, 3, 0);

		double expected = 31.6;
		double actualColTotal = DataUtilities.calculateColumnTotal(values2D, 0);

		assertEquals(expected, actualColTotal);
	}

	// Case 2: Column total with all negative values
	@Test
	public void testCalculateColumnTotalNegativeValues() {
		values2D.addValue(-1, 0, 0);
		values2D.addValue(-5, 1, 0);
		values2D.addValue(-10.6, 2, 0);
		values2D.addValue(-15, 3, 0);

		double expected = -31.6;
		double actualColTotal = DataUtilities.calculateColumnTotal(values2D, 0);

		assertEquals(expected, actualColTotal);
	}
	
	// Case 3: Column total with positive and negative values
	@Test
	public void testCalculateColumnTotalNegativeAndPositiveValues() {
		values2D.addValue(-1, 0, 0);
		values2D.addValue(-5, 1, 0);
		values2D.addValue(-10, 2, 0);
		values2D.addValue(16, 3, 0);
		values2D.addValue(20, 4, 0);
		values2D.addValue(2.7, 5, 0);

		double expected = 22.7;
		double actualColTotal = DataUtilities.calculateColumnTotal(values2D, 0);

		assertEquals(expected, actualColTotal);
	}
	
	// Case 4: Test calculate column only calculates requested column 
	@Test
	public void testCalculateColumnTotalOnlyCalculatesRequestedColumn() {
		values2D.addValue(1, 0, 0);
		values2D.addValue(5, 1, 0);
		values2D.addValue(10, 2, 0);
		values2D.addValue(15.6, 3, 0);
		values2D.addValue(1, 0, 1);
		values2D.addValue(5, 1, 1);
		values2D.addValue(10, 2, 1);
		values2D.addValue(15, 3, 1);

		double expected = 31;
		double actualColTotal = DataUtilities.calculateColumnTotal(values2D, 1);

		assertEquals(expected, actualColTotal);
	}
	
	// Case 5: Column total with one value totalling 0 
	@Test
	public void testCalculateColumnTotalOneValueTotallingZero() {
		values2D.addValue(0, 0, 0);

		double expected = 0;
		double actualColTotal = DataUtilities.calculateColumnTotal(values2D, 0);

		assertEquals(expected, actualColTotal);
	}
	
	// Case 6: Test column total with invalid data returns 0 
	@Test
	public void testColumnDataInvalidInputReturnsZero(){
		values2D.addValue(null, 0, 0);
	    double actualColTotal = DataUtilities.calculateColumnTotal(values2D, 0);
	    
	    assertEquals(0.0, actualColTotal);
	}
	
	// Case 7: Column total for row that doesn't exist throws exception  
	@Test
	public void testCalculateColumnTotalForColumnNotExisitngThrowsException() {
		values2D.addValue(0, 0, 0);

		try {
		DataUtilities.calculateColumnTotal(values2D, 5);
	    fail("No exception thrown. The expected outcome was: a thrown "
	    		+ "exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IndexOutOfBoundsException.class));
		}
	}
		
	// Case 8: Test column total with invalid data object
	@Test
	public void testColumnTotalWithInvalidDataObjectThrowsException(){
	try {
	    DataUtilities.calculateColumnTotal(null, 0);
	    fail("No exception thrown. The expected outcome was: a thrown "
	    		+ "exception of type: InvalidParameterException");
	} catch (Exception e){
	    assertTrue("Incorrect exception type thrown",  
	    e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	
	// ****** Method 2 - Tests for Calculate Row Total *****************
	
	// Case 1: Row total with all positive values
	@Test
	public void testCalculateRowTotalPositiveValues() {
		values2D.addValue(1, 0, 0);
		values2D.addValue(5, 0, 1);
		values2D.addValue(10, 0, 2);
		values2D.addValue(15.3, 0, 3);

		double expected = 31.3;
		double actualRowTotal = DataUtilities.calculateRowTotal(values2D, 0);

		assertEquals("Calculate row total: Didn't return expected output: ", expected, actualRowTotal);
	}

	// Case 2: Row total with all negative values
	@Test
	public void testCalculateRowTotalNegativeValues() {
		values2D.addValue(-1, 0, 0);
		values2D.addValue(-5, 0, 1);
		values2D.addValue(-10, 0, 2);
		values2D.addValue(-15.6, 0, 3);

		double expected = -31.6;
		double actualRowTotal = DataUtilities.calculateRowTotal(values2D, 0);

		assertEquals("Calculate row total: Didn't return expected output: ", expected, actualRowTotal);
	}
		
	// Case 3: Row total with positive and negative values
	@Test
	public void testCalculateRowTotalNegativeAndPositiveValues() {
		values2D.addValue(-1, 0, 0);
		values2D.addValue(-5, 0, 1);
		values2D.addValue(-10, 0, 2);
		values2D.addValue(16, 0, 3);
		values2D.addValue(20, 0, 4);
		values2D.addValue(2.7, 0, 5);

		double expected = 22.7;
		double actualRowTotal = DataUtilities.calculateRowTotal(values2D, 0);

		assertEquals("Calculate row total: Didn't return expected output: ", expected, actualRowTotal);
	}
	
	// Case 4: Row total is calculated for only the requested row 
	@Test
	public void testCalculateRowTotalOnlyCalculateRequestedColumn() {
		values2D.addValue(1, 0, 0);
		values2D.addValue(5, 0, 1);
		values2D.addValue(10, 0, 2);
		values2D.addValue(15.3, 0, 3);
		values2D.addValue(1, 1, 0);
		values2D.addValue(5, 1, 1);
		values2D.addValue(10, 1, 2);
		values2D.addValue(15.3, 1, 3);

		double expected = 31.3;
		double actualRowTotal = DataUtilities.calculateRowTotal(values2D, 1);

		assertEquals("Calculate row total: Didn't return expected output: ", expected, actualRowTotal);
	}
		
	
	// Case 5: Row total with one value totalling 0 
	@Test
	public void testCalculateRowTotalContainingOneValue() {
		values2D.addValue(10, 0, 0);

		double expected = 10;
		double actualRowTotal = DataUtilities.calculateColumnTotal(values2D, 0);

		assertEquals("Calculate row total: Didn't return expected output: ", expected, actualRowTotal);
	}
	
	// Case 6: Row total for row that doesn't exist throws exception  
	@Test
	public void testCalculateRowTotalForRowNotExisitngThrowsException() {
		values2D.addValue(0, 0, 0);

		try {
			DataUtilities.calculateRowTotal(values2D, 5);
			fail("No exception thrown. The expected outcome was: a thrown "
	    		+ "exception of type: IndexOutOfBoundsException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IndexOutOfBoundsException.class));
		}
	}
		
	// Case 7: Test row total with null data object throws exception 
	@Test
	public void testNullDataObjectRowTotalThrowsException(){
	try {
	    DataUtilities.calculateRowTotal(null, 0);
	    fail("No exception thrown. The expected outcome was: a thrown "
	    		+ "exception of type: InvalidParameterException");
	} catch (Exception e){
	    assertTrue("Incorrect exception type thrown",  
	    e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	// Case 8: Test row total with invalid data returns 0 
	@Test
	public void testRowDataInvalidInputReturnsZero() {
		values2D.addValue(null, 0, 0);
	    double actualRowTotal = DataUtilities.calculateRowTotal(values2D, 0);
	    
	    assertEquals("Calculate row total: Invalid input should return zero ", 0.0, actualRowTotal);
	}
	
	// ****** Method 3 - Tests for Create Number Array *****************

	// Case 1: Test creating an empty number array 
	@Test
	public void testCreateNumberArrayWithEmptyArray() {
		double[] values = new double[] {};
		Number[] numberArr = DataUtilities.createNumberArray(values);
		
		assertNotNull(numberArr);
		assertEquals(0, numberArr.length);
	}
	
	// Case 2: Testing creating number array with a single value
	@Test
	public void testCreateNumberArrayWithSingleValue() {
		double[] values = new double[] {0.0};
		Number[] numberArr = DataUtilities.createNumberArray(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for(int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i], numberArr[i]);
		}
	}
	
	// Case 3: Testing creating number array with positive values 
	@Test
	public void testCreateNumberArrayWithMultiplePositiveValues() {
		double[] values = new double[] {0.0, 1.0, 2.0, 3.0};
		Number[] numberArr = DataUtilities.createNumberArray(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for(int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i], numberArr[i]);
		}
	}
	
	// Case 4: Testing creating number array with negative values 
	@Test
	public void testCreateNumberArrayWithMultipleNegativeValues() {
		double[] values = new double[] {-1.0, -2.0, -3.0, -4.0};
		Number[] numberArr = DataUtilities.createNumberArray(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for(int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i], numberArr[i]);
		}
	}
	
	// Case 5: Testing creating number array with negative and positive values 
	@Test
	public void testCreateNumberArrayWithMultipleNegativeAndPositiveValues() {
		double[] values = new double[] {1.0, -2.0, 3.0, -4.0};
		Number[] numberArr = DataUtilities.createNumberArray(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for(int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i], numberArr[i]);
		}
	}
	
	// Case 6: Testing creating a number array with null object throws Invalid Parameter Exception
	@Test
	public void testCreateNumberArrayWithNullObjectThrowsException() {
		try {
			DataUtilities.createNumberArray(null);
			fail("No exception thrown. The expected outcome was: a thrown "
		    		+ "exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(InvalidParameterException.class));
		}
	}
	

	// ****** Method 4 - Tests for Create Number Array 2D *****************

	// Case 1: Test creating an empty number array 
	@Test
	public void testCreate2DNumberArrayWithEmptyArray() {
		double[][] values = new double[][] {{}, {}};
		Number[][] numberArr = DataUtilities.createNumberArray2D(values);
		
		assertNotNull(numberArr);
		assertEquals(2, numberArr.length);
	}
	
	// Case 2: Test creating 2D number array with multiple positive values 
	@Test
	public void testCreate2DNumberArrayWithMultiplePositiveValues() {
		double[][] values = new double[][] {{0.0, 1.0, 2.0}, {3.0, 4.0, 5.0}};
		Number[][] numberArr = DataUtilities.createNumberArray2D(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for (int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i].length, numberArr[i].length);
			for (int j = 0; j < numberArr[i].length; j++) {
				assertEquals(values[i][j], numberArr[i][j]);
			}
		}
	}
	
	// Case 3: Test creating 2D number array with multiple negative values 
	@Test
	public void testCreate2DNumberArrayWithNegativeValues() {
		double[][] values = new double[][] {{-1.0, -2.0}, {-3.0, -4.0, -5.0}};
		Number[][] numberArr = DataUtilities.createNumberArray2D(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for (int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i].length, numberArr[i].length);
			for (int j = 0; j < numberArr[i].length; j++) {
				assertEquals(values[i][j], numberArr[i][j]);
			}
		}
	}
	
	// Case 4: Test creating 2D number array with positive and negative values 
	@Test
	public void testCreate2DNumberArrayWithPositiveAndNegativeValues() {
		double[][] values = new double[][] {{1.0, -2.0}, {-3.0, 4.0, -5.0}};
		Number[][] numberArr = DataUtilities.createNumberArray2D(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for (int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i].length, numberArr[i].length);
			for (int j = 0; j < numberArr[i].length; j++) {
				assertEquals(values[i][j], numberArr[i][j]);
			}
		}
	}
	
	// Case 5: Test creating 2D number array with single value
	@Test
	public void testCreate2DNumberArrayWithSingleValue() {
		double[][] values = new double[][] {{0.0}};
		Number[][] numberArr = DataUtilities.createNumberArray2D(values);
		
		assertNotNull(numberArr);
		assertEquals(values.length, numberArr.length);
		
		for (int i = 0; i < numberArr.length; i++) {
			assertEquals(values[i].length, numberArr[i].length);
			for (int j = 0; j < numberArr[i].length; j++) {
				assertEquals(values[i][j], numberArr[i][j]);
			}
		}
	}
	
	// Case 6: Testing creating a number array with null object throws Invalid Parameter Exception
	@Test
	public void testCreate2DNumberArrayWithNullObjectThrowsException() {
		try {
			DataUtilities.createNumberArray2D(null);
			fail("No exception thrown. The expected outcome was: a thrown "
		    		+ "exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	// Method 5 - Tests for Get Cumulative Percentages
	
	//Case 1: test cumulative percentages for positive number based on javadocs example
	@Test
	public void testGetCumulativePercentagesPositiveNumbers() {
		keyedValues.addValue("0", 5);
		keyedValues.addValue("1", 9);
		keyedValues.addValue("2", 2);
		
		DefaultKeyedValues expectedPercentages = new DefaultKeyedValues();
		
		expectedPercentages.addValue("0", 0.3125);
		expectedPercentages.addValue("1", 0.875);
		expectedPercentages.addValue("2", 1.0);
		
		KeyedValues cumulativePercentages = DataUtilities.getCumulativePercentages(keyedValues);

		for (int i = 0; i < cumulativePercentages.getItemCount(); i++) {
			assertEquals(expectedPercentages.getKey(i), cumulativePercentages.getKey(i));
			assertEquals(expectedPercentages.getValue(i), cumulativePercentages.getValue(i));
		}
	}
	
	//Case 2: test cumulative percentages for negative numbers based on javadocs example
		@Test
		public void testGetCumulativePercentagesNegativeNumbers() {
			keyedValues.addValue("0", -5);
			keyedValues.addValue("1", -9);
			keyedValues.addValue("2", -2);
			
			DefaultKeyedValues expectedPercentages = new DefaultKeyedValues();
			
			expectedPercentages.addValue("0", 0.3125);
			expectedPercentages.addValue("1", 0.875);
			expectedPercentages.addValue("2", 1.0);
			
			KeyedValues cumulativePercentages = DataUtilities.getCumulativePercentages(keyedValues);
			
			for (int i = 0; i < cumulativePercentages.getItemCount(); i++) {
				assertEquals(expectedPercentages.getKey(i), cumulativePercentages.getKey(i));
				assertEquals(expectedPercentages.getValue(i), cumulativePercentages.getValue(i));
			}
		}
		
	//Case 3: test cumulative percentages based on a larger sample 
		@Test
		public void testGetCumulativePercentagesLargerSample() {
			keyedValues.addValue("0", 5);
			keyedValues.addValue("1", 9);
			keyedValues.addValue("2", 2);
			keyedValues.addValue("3", 5);
			keyedValues.addValue("4", 9);
			keyedValues.addValue("5", 2);
			
			DefaultKeyedValues expectedPercentages = new DefaultKeyedValues();
			
			expectedPercentages.addValue("0", 0.15625);
			expectedPercentages.addValue("1", 0.4375);
			expectedPercentages.addValue("2", 0.5);
			expectedPercentages.addValue("3", 0.65625);
			expectedPercentages.addValue("4", 0.9375);
			expectedPercentages.addValue("5", 1.0);
			
			KeyedValues cumulativePercentages = DataUtilities.getCumulativePercentages(keyedValues);

			for (int i = 0; i < cumulativePercentages.getItemCount(); i++) {
				assertEquals(expectedPercentages.getKey(i), cumulativePercentages.getKey(i));
				assertEquals(expectedPercentages.getValue(i), cumulativePercentages.getValue(i));
			}
		}

	//Case 4: test cumulative percentages for no keyed values 
		@Test
		public void testGetCumulativePercentagesWithNoKeyedValues() {
			KeyedValues cumulativePercentages = DataUtilities.getCumulativePercentages(keyedValues);
			assertEquals(0, cumulativePercentages.getItemCount());
		}
		
	//Case 5: test cumulative percentages for individual value 
		@Test
		public void testCumulativePercentageForSingleKeyValue() {
			keyedValues.addValue("0", 5);
			KeyedValues cumulativePercentages = DataUtilities.getCumulativePercentages(keyedValues);
		
			// 0 - 1.0 (5/5)
			assertEquals(1.0, cumulativePercentages.getValue(0));
	}
		
	//Case 6: test cumulative percentages throws invalid param exception when invalid data object passed
		@Test
		public void testCumulativePercentageThrowsExceptionOnInvalidDataObject() {
			try {
				DataUtilities.getCumulativePercentages(null);
				fail("No exception thrown. The expected outcome was: a thrown "
			    		+ "exception of type: InvalidParameterException");
			} catch (Exception e) {
				assertTrue("Incorrect exception type thrown",  
					    e.getClass().equals(InvalidParameterException.class));
			}
		}
		
	// ** Added Test For Assignment 3 	
	//Case 7: test cumulative percentages for individual value 
		@Test
		public void testCumulativePercentageWithNullValue() {
			keyedValues.addValue("1", null);
			KeyedValues cumulativePercentages = DataUtilities.getCumulativePercentages(keyedValues);
		
			assertEquals(Double.NaN, cumulativePercentages.getValue(0));
		}
}