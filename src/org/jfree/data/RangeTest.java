package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

	private Range rangeObjectUnderTest;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
	}

	@After
	public void tearDown() throws Exception {
		rangeObjectUnderTest = null;
	}

	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0", 0, rangeObjectUnderTest.getCentralValue(), 0.000000001d);
	}

	@Test
	public void testGetLengthBothPositiveAndEqual() {
		Range r1 = new Range(2, 2);
		assertEquals("getLength: Did not return the expected output", 0.0, r1.getLength(), 0.000000001d);
	}

	@Test
	public void testGetLengthBothPositiveAndNotEqual() {
		Range r2 = new Range(4, 9);
		assertEquals("getLength: Did not return the expected output", 5.0, r2.getLength(), 0.000000001d);
	}

	@Test
	public void testGetLengthBothNegativeAndEqual() {
		Range r3 = new Range(-99, -99);
		assertEquals("getLength: Did not return the expected output", 0.0, r3.getLength(), 0.000000001d);
	}

	@Test
	public void testGetLengthBothNegativeAndNotEqual() {
		Range r4 = new Range(-11, -4);
		assertEquals("getLength: Did not return the expected output", 7.0, r4.getLength(), 0.000000001d);
	}

	@Test
	public void testGetLengthOnePositiveOneNegative() {
		Range r5 = new Range(-5, 3);
		assertEquals("getLength: Did not return the expected output", 8.0, r5.getLength(), 0.000000001d);
	}	

	// ****** Tests for 'constrain' method ********************************
	
	// Case 1: constrain with an included negative value
	@Test
	public void testConstrainWithIncludedNegativeValue() {
		Range r1 = new Range(-5, 10);
		assertEquals("constrain: Did not return the expected output", -2.5, r1.constrain(-2.5), 0.000000001d);
	}
	
	// Case 2: constrain with an excluded negative value
	@Test
	public void testConstrainWithExcludedNegativeValue() {
		Range r1 = new Range(-5, 10);
		assertEquals("constrain: Did not return the expected output", -5, r1.constrain(-20), 0.000000001d);
	}
	
	// Case 3: constrain with a zero value
	@Test
	public void testConstrainWithZeroValue() {
		Range r1 = new Range(-2, 5);
		assertEquals("constrain: Did not return the expected output", 0.0, r1.constrain(0), 0.000000001d);
	}
	
	// Case 4: constrain with an included positive value
	@Test
	public void testConstrainWithIncludedPositiveValue() {
		Range r1 = new Range(-2, 5);
		assertEquals("constrain: Did not return the expected output", 1, r1.constrain(1), 0.000000001d);
	}
	
	// Case 5: constrain with an excluded positive value
	@Test
	public void testConstrainWithExcludedPositiveValue() {
		Range r1 = new Range(-2, 5);
		assertEquals("constrain: Did not return the expected output", 5.0, r1.constrain(10), 0.000000001d);
	}
	
	// ****** Tests for 'contains' method *********************************
	
	// Case 1: contains with an included negative value
	@Test
	public void testContainsWithIncludedNegativeValue() {
		Range r1 = new Range(-20, 15);
		assertEquals("contains: Did not return the expected output", true, r1.contains(-10));
	}
	
	// Case 2: contains with an excluded negative value
	@Test
	public void testContainsWithExcludedNegativeValue() {
		Range r1 = new Range(-20, 15);
		assertEquals("contains: Did not return the expected output", false, r1.contains(-35));
	}
	
	// Case 3: contains with a zero value
	@Test
	public void testContainsWithZeroValue() {
		Range r1 = new Range(0, 0);
		assertEquals("contains: Did not return the expected output", true, r1.contains(0));
	}
	
	// Case 4: contains with an included positive value
	@Test
	public void testContainsWithIncludedPositiveValue() {
		Range r1 = new Range(-20, 15);
		assertEquals("contains: Did not return the expected output", true, r1.contains(5.7));
	}
	
	// Case 5: contains with an excluded positive value
	@Test
	public void testContainsWithExcludedPositiveValue() {
		Range r1 = new Range(-20, 15);
		assertEquals("contains: Did not return the expected output", false, r1.contains(41));
	}

	// ****** Tests for 'expand' method ***********************************
	
	// Case 1: expand with a null Range object
	@Test
	public void testExpandWithNullObject() {
		Range r1 = null;
		
		try {
			Range.expand(r1, 0.5, 0.5);
			
			fail("No exception thrown. The expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	// Case 2: expand with an empty Range object and negative margin values
	@Test
	public void testExpandWithEmptyObjectAndNegativeMargins() {
		Range r1 = new Range(0, 0);
		
		r1 = Range.expand(r1, -0.5, -1.5);

		assertEquals("expand: Did not return the expected output", true, (r1.getLowerBound() == 0 && r1.getUpperBound() == 0));
	}
	
	// Case 3: expand with an empty Range object and positive margin values
	@Test
	public void testExpandWithEmptyObjectAndPositiveMargins() {
		Range r1 = new Range(0, 0);
		
		r1 = Range.expand(r1, 0.5, 1.5);

		assertEquals("expand: Did not return the expected output", true, (r1.getLowerBound() == 0 && r1.getUpperBound() == 0));
	}
	
	// Case 4: expand with a populated Range object and zero margin values
	@Test
	public void testExpandWithPopulatedObjectAndZeroMargins() {
		Range r1 = new Range(2, 6);
		
		r1 = Range.expand(r1, 0.0, 0.0);

		assertEquals("expand: Did not return the expected output", true, (r1.getLowerBound() == 2 && r1.getUpperBound() == 6));
	}
	
	// Case 5: expand with a populated Range object and a negative upper margin
	@Test
	public void testExpandWithPopulatedObjectAndNegativeUpperMargin() {
		Range r1 = new Range(2, 6);
		
		r1 = Range.expand(r1, 0.5, -0.25);

		assertEquals("expand: Did not return the expected output", true, (r1.getLowerBound() == 0 && r1.getUpperBound() == 5));
	}
	
	// Case 6: expand with a populated Range object and negative lower margin
	@Test
	public void testExpandWithPopulatedObjectAndNegativeLowerMargin() {
		Range r1 = new Range(2, 6);
		
		r1 = Range.expand(r1, -0.25, 0.5);

		assertEquals("expand: Did not return the expected output", true, (r1.getLowerBound() == 3 && r1.getUpperBound() == 8));
	}
	
	// Case 7: expand with a populated Range object and positive margin values
	@Test
	public void testExpandWithPopulatedObjectAndPositiveMarginValues() {
		Range r1 = new Range(2, 6);
		
		r1 = Range.expand(r1, 0.25, 0.5);

		assertEquals("expand: Did not return the expected output", true, (r1.getLowerBound() == 1 && r1.getUpperBound() == 8));
	}
	
	// ****** Tests for 'expandToInclude' method **************************
	
	// Case 1: expandToInclude with a null Range object and a negative value
	@Test
	public void testExpandToIncludeWithNullObjectAndNegativeValue() {
		Range r1 = null;
		
		r1 = Range.expandToInclude(r1, -5.0);
			
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(-5.0));
	}
	
	// Case 2: expandToInclude with a null Range object and a zero value
	@Test
	public void testExpandToIncludeWithNullObjectAndZeroValue() {
		Range r1 = null;
		
		r1 = Range.expandToInclude(r1, 0.0);
			
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(0.0));
	}
	
	// Case 3: expandToInclude with a null Range object and a positive value
	@Test
	public void testExpandToIncludeWithNullObjectAndPositiveValue() {
		Range r1 = null;
		
		r1 = Range.expandToInclude(r1, 8.0);
			
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(8.0));
	}
	
	// Case 4: expandToInclude with an empty Range object and a negative value
	@Test
	public void testExpandToIncludeWithEmptyObjectAndNegativeValue() {
		Range r1 = new Range(0, 0);
		
		r1 = Range.expandToInclude(r1, -5.0);
			
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(-5.0));
	}
	
	// Case 5: expandToInclude with an empty Range object and a zero value
	@Test
	public void testExpandToIncludeWithEmptyObjectAndZeroValue() {
		Range r1 = new Range(0, 0);
		
		r1 = Range.expandToInclude(r1, 0.0);
		
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(0.0));
	}
	
	// Case 6: expandToInclude with an empty Range object and a positive value
	@Test
	public void testExpandToIncludeWithEmptyObjectAndPositiveValue() {
		Range r1 = new Range(0, 0);
		
		r1 = Range.expandToInclude(r1, 8.0);
		
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(8.0));
	}
	
	// Case 7: expandToInclude with a populated Range object and a negative value
	@Test
	public void testExpandToIncludeWithPopulatedObjectAndNegativeValue() {
		Range r1 = new Range(-3, 3);
		
		r1 = Range.expandToInclude(r1, -5.0);
		
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(-5.0));
	}
	
	// Case 8: expandToInclude with a populated Range object and a zero value
	@Test
	public void testExpandToIncludeWithPopulatedObjectAndZeroValue() {
		Range r1 = new Range(-15, -5);
		
		r1 = Range.expandToInclude(r1, 0.0);
		
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(0.0));
	}
	
	// Case 9: expandToInclude with a populated Range object and a positive value
	@Test
	public void testExpandToIncludeWithPopulatedObjectAndPositiveValue() {
		Range r1 = new Range(-3, 3);
		
		r1 = Range.expandToInclude(r1, 8.0);
		
		assertTrue("expandToInclude: Did not return the expected output", r1.contains(8.0));
	}

	// ****** Tests for 'combine' method **********************************
	
	// Case 1: combine with two null Range objects
	@Test
	public void testCombineWithTwoNullObjects() {
		Range r1 = null;
		Range r2 = null;
		
		Range r3 = Range.combine(r1, r2);
			
		assertEquals("expandToInclude: Did not return the expected output", null, r3);
	}
	
	// Case 2: combine with one null Range object and one empty Range object
	@Test
	public void testCombineWithOneNullAndOneEmptyObject() {
		Range r1 = new Range(0, 0);
		Range r2 = null;
		
		Range r3 = Range.combine(r1, r2);
			
		assertTrue("combine: Did not return the expected output", r3.getLength() == 0 && r3.contains(0));
	}
	
	// Case 3: combine with one null Range object
	@Test
	public void testCombineWithOneNullObject() {
		Range r1 = new Range(10, 15);
		Range r2 = null;
		
		Range r3 = Range.combine(r1, r2);
			
		assertTrue("combine: Did not return the expected output", r3.getLength() == 5 && r3.contains(10) && r3.contains(15));
	}
	
	// Case 4: combine with two empty Range objects
	@Test
	public void testCombineWithTwoEmptyObjects() {
		Range r1 = new Range(0, 0);
		Range r2 = new Range(0, 0);
		
		Range r3 = Range.combine(r1, r2);
			
		assertTrue("combine: Did not return the expected output", r3.getLength() == 0 && r3.contains(0));
	}
	
	// Case 5: combine with one empty Range object and one populated Range object
	@Test
	public void testCombineWithOneEmptyAndOnePopulatedObject() {
		Range r1 = new Range(2, 6);
		Range r2 = new Range(0, 0);
		
		Range r3 = Range.combine(r1, r2);
			
		assertTrue("combine: Did not return the expected output", r3.contains(2) && r3.contains(6));
	}
	
	// Case 6: combine with one null Range object and one negative value Range object
	@Test
	public void testCombineWithOneNullAndOneNegativeValueObject() {
		Range r1 = null;
		Range r2 = new Range(-15, -10);
		
		Range r3 = Range.combine(r1, r2);
			
		assertTrue("combine: Did not return the expected output", r3.contains(-15) && r3.contains(-10));
	}
	
	// Case 7: combine with one empty Range object and one negative value Range object
	@Test
	public void testCombineWithOneEmptyAndOneNegativeValueObject() {
		Range r1 = new Range(0, 0);
		Range r2 = new Range(-15, -10);
		
		Range r3 = Range.combine(r1, r2);
			
		assertTrue("combine: Did not return the expected output", r3.contains(-15) && r3.contains(-10));
	}
	
	// Case 8: combine with one positive value Range object and one negative value Range object
	@Test
	public void testCombineWithOnePositiveValueAndOneNegativeValueObject() {
		Range r1 = new Range(2, 6);
		Range r2 = new Range(-15, -10);
		
		Range r3 = Range.combine(r1, r2);
			
		assertTrue("combine: Did not return the expected output", r3.contains(-15) && r3.contains(6));
	}

}