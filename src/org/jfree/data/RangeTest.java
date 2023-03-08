package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

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
		assertEquals("The central value of -1 and 1 should be 0", 0, rangeObjectUnderTest.getCentralValue(),
				0.000000001d);
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

	// RANGE-1: constrain with an included negative value
	@Test
	public void testConstrainWithIncludedNegativeValue() {
		rangeObjectUnderTest = new Range(-2, 6);

		double expected = -1;
		double actual = rangeObjectUnderTest.constrain(-1);

		assertEquals("constrain: Did not return the expected output", expected, actual, 0.000000001d);
	}

	// RANGE-2: constrain with an excluded negative value
	@Test
	public void testConstrainWithExcludedNegativeValue() {
		rangeObjectUnderTest = new Range(2, 6);

		double expected = 2;
		double actual = rangeObjectUnderTest.constrain(-3.4);

		assertEquals("constrain: Did not return the expected output", expected, actual, 0.000000001d);
	}

	// RANGE-3: constrain with an included zero value
	@Test
	public void testConstrainWithIncludedZeroValue() {
		rangeObjectUnderTest = new Range(-2, 6);

		double expected = 0;
		double actual = rangeObjectUnderTest.constrain(0);

		assertEquals("constrain: Did not return the expected output", expected, actual, 0.000000001d);
	}

	// RANGE-4: constrain with an excluded zero value
	@Test
	public void testConstrainWithExcludedZeroValue() {
		rangeObjectUnderTest = new Range(2, 6);

		double expected = 2;
		double actual = rangeObjectUnderTest.constrain(0);

		assertEquals("constrain: Did not return the expected output", expected, actual, 0.000000001d);
	}

	// RANGE-5: constrain with an included positive value
	@Test
	public void testConstrainWithIncludedPositiveValue() {
		rangeObjectUnderTest = new Range(2, 6);

		double expected = 5;
		double actual = rangeObjectUnderTest.constrain(5);

		assertEquals("constrain: Did not return the expected output", expected, actual, 0.000000001d);
	}

	// RANGE-6: constrain with an excluded positive value
	@Test
	public void testConstrainWithExcludedPositiveValue() {
		rangeObjectUnderTest = new Range(2, 6);

		double expected = 6;
		double actual = rangeObjectUnderTest.constrain(8);

		assertEquals("constrain: Did not return the expected output", expected, actual, 0.000000001d);
	}

	// ****** Tests for 'contains' method *********************************

	// RANGE-7: contains with an included negative value
	@Test
	public void testContainsWithIncludedNegativeValue() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.contains(-3.4);

		assertEquals("contains: Did not return the expected output", expected, actual);
	}

	// RANGE-8: contains with an excluded negative value
	@Test
	public void testContainsWithExcludedNegativeValue() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.contains(-8);

		assertEquals("contains: Did not return the expected output", expected, actual);
	}

	// RANGE-9: contains with an included zero value
	@Test
	public void testContainsWithIncludedZeroValue() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.contains(0);

		assertEquals("contains: Did not return the expected output", expected, actual);
	}

	// RANGE-10: contains with an excluded zero value
	@Test
	public void testContainsWithExcludedZeroValue() {
		rangeObjectUnderTest = new Range(4, 8);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.contains(0);

		assertEquals("contains: Did not return the expected output", expected, actual);
	}

	// RANGE-11: contains with an included positive value
	@Test
	public void testContainsWithIncludedPositiveValue() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.contains(3.4);

		assertEquals("contains: Did not return the expected output", expected, actual);
	}

	// RANGE-12: contains with an excluded positive value
	@Test
	public void testContainsWithExcludedPositiveValue() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.contains(8.5);

		assertEquals("contains: Did not return the expected output", expected, actual);
	}

	// ****** Tests for 'expand' method ***********************************

	// RANGE-13: expand with a null Range object
	@Test
	public void testExpandWithNullObject() {
		rangeObjectUnderTest = null;

		try {
			Range.expand(rangeObjectUnderTest, 1.5, 0.25);

			fail("No exception thrown. The expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// RANGE-14: expand with an empty Range object and negative margin values
	@Test
	public void testExpandWithEmptyObjectAndNegativeMargins() {
		rangeObjectUnderTest = new Range(0, 0);
		rangeObjectUnderTest = Range.expand(rangeObjectUnderTest, -0.25, -0.1);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.getLowerBound() == 0 && rangeObjectUnderTest.getUpperBound() == 0;

		assertEquals("expand: Did not return the expected output", expected, actual);
	}

	// RANGE-15: expand with an empty Range object and positive margin values
	@Test
	public void testExpandWithEmptyObjectAndPositiveMargins() {
		rangeObjectUnderTest = new Range(0, 0);
		rangeObjectUnderTest = Range.expand(rangeObjectUnderTest, 1.5, 0.25);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.getLowerBound() == 0 && rangeObjectUnderTest.getUpperBound() == 0;

		assertEquals("expand: Did not return the expected output", expected, actual);
	}

	// RANGE-16: expand with a populated Range object and zero margin values
	@Test
	public void testExpandWithPopulatedObjectAndZeroMargins() {
		rangeObjectUnderTest = new Range(2, 6);
		rangeObjectUnderTest = Range.expand(rangeObjectUnderTest, 0.0, 0.0);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.getLowerBound() == 2 && rangeObjectUnderTest.getUpperBound() == 6;

		assertEquals("expand: Did not return the expected output", expected, actual);
	}

	// RANGE-17: expand with a populated Range object and a negative upper margin
	@Test
	public void testExpandWithPopulatedObjectAndNegativeUpperMargin() {
		rangeObjectUnderTest = new Range(2, 6);
		rangeObjectUnderTest = Range.expand(rangeObjectUnderTest, 0.5, -0.25);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.getLowerBound() == 0 && rangeObjectUnderTest.getUpperBound() == 5;

		assertEquals("expand: Did not return the expected output", expected, actual);
	}

	// RANGE-18: expand with a populated Range object and negative lower margin
	@Test
	public void testExpandWithPopulatedObjectAndNegativeLowerMargin() {
		rangeObjectUnderTest = new Range(2, 6);
		rangeObjectUnderTest = Range.expand(rangeObjectUnderTest, -0.25, 0.5);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.getLowerBound() == 3 && rangeObjectUnderTest.getUpperBound() == 8;

		assertEquals("expand: Did not return the expected output", expected, actual);
	}

	// RANGE-19: expand with a populated Range object and positive margin values
	@Test
	public void testExpandWithPopulatedObjectAndPositiveMarginValues() {
		rangeObjectUnderTest = new Range(2, 6);
		rangeObjectUnderTest = Range.expand(rangeObjectUnderTest, 0.5, 0.5);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.getLowerBound() == 0 && rangeObjectUnderTest.getUpperBound() == 8;

		assertEquals("expand: Did not return the expected output", expected, actual);
	}

	// ****** Tests for 'expandToInclude' method **************************

	// RANGE-20: expandToInclude with a null Range object and a negative value
	@Test
	public void testExpandToIncludeWithNullObjectAndNegativeValue() {
		rangeObjectUnderTest = null;
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, -5.0);

		boolean result = rangeObjectUnderTest.contains(-5.0);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-21: expandToInclude with a null Range object and a zero value
	@Test
	public void testExpandToIncludeWithNullObjectAndZeroValue() {
		rangeObjectUnderTest = null;
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, 0.0);

		boolean result = rangeObjectUnderTest.contains(0.0);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-22: expandToInclude with a null Range object and a positive value
	@Test
	public void testExpandToIncludeWithNullObjectAndPositiveValue() {
		rangeObjectUnderTest = null;
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, 8.0);

		boolean result = rangeObjectUnderTest.contains(8.0);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-23: expandToInclude with an empty Range object and a negative value
	@Test
	public void testExpandToIncludeWithEmptyObjectAndNegativeValue() {
		rangeObjectUnderTest = new Range(0, 0);
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, -5.0);

		boolean result = rangeObjectUnderTest.contains(-5.0) && rangeObjectUnderTest.contains(0);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-24: expandToInclude with an empty Range object and a zero value
	@Test
	public void testExpandToIncludeWithEmptyObjectAndZeroValue() {
		rangeObjectUnderTest = new Range(0, 0);
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, 0.0);

		boolean result = rangeObjectUnderTest.contains(0.0);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-25: expandToInclude with an empty Range object and a positive value
	@Test
	public void testExpandToIncludeWithEmptyObjectAndPositiveValue() {
		rangeObjectUnderTest = new Range(0, 0);
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, 8.0);

		boolean result = rangeObjectUnderTest.contains(8.0) && rangeObjectUnderTest.contains(0);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-26: expandToInclude with a populated Range object and a negative value
	@Test
	public void testExpandToIncludeWithPopulatedObjectAndNegativeValue() {
		rangeObjectUnderTest = new Range(-3, 3);
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, -5.0);

		boolean result = rangeObjectUnderTest.contains(-5.0) && rangeObjectUnderTest.contains(3);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-27: expandToInclude with a populated Range object and a zero value
	@Test
	public void testExpandToIncludeWithPopulatedObjectAndZeroValue() {
		rangeObjectUnderTest = new Range(-15, -5);
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, 0.0);

		boolean result = rangeObjectUnderTest.contains(0.0) && rangeObjectUnderTest.contains(-15);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// RANGE-28: expandToInclude with a populated Range object and a positive value
	@Test
	public void testExpandToIncludeWithPopulatedObjectAndPositiveValue() {
		rangeObjectUnderTest = new Range(-3, 3);
		rangeObjectUnderTest = Range.expandToInclude(rangeObjectUnderTest, 8.0);

		boolean result = rangeObjectUnderTest.contains(8.0) && rangeObjectUnderTest.contains(-3);

		assertTrue("expandToInclude: Did not return the expected output", result);
	}

	// ****** Tests for 'combine' method **********************************

	// RANGE-29: combine with two null Range objects
	@Test
	public void testCombineWithTwoNullObjects() {
		Range r1 = null;
		Range r2 = null;
		Range r3 = Range.combine(r1, r2);

		assertEquals("expandToInclude: Did not return the expected output", null, r3);
	}

	// RANGE-30: combine with one null Range object and one empty Range object
	@Test
	public void testCombineWithOneNullAndOneEmptyObject() {
		Range r1 = null;
		Range r2 = new Range(0, 0);
		Range r3 = Range.combine(r1, r2);

		boolean result = r3.getLength() == 0 && r3.contains(0);

		assertTrue("combine: Did not return the expected output", result);
	}

	// RANGE-31: combine with one null Range object
	@Test
	public void testCombineWithOneNullObject() {
		Range r1 = null;
		Range r2 = new Range(10, 15);
		Range r3 = Range.combine(r1, r2);

		boolean result = r3.getLength() == 5 && r3.contains(10) && r3.contains(15);

		assertTrue("combine: Did not return the expected output", result);
	}

	// RANGE-32: combine with two empty Range objects
	@Test
	public void testCombineWithTwoEmptyObjects() {
		Range r1 = new Range(0, 0);
		Range r2 = new Range(0, 0);
		Range r3 = Range.combine(r1, r2);

		boolean result = r3.getLength() == 0 && r3.contains(0);

		assertTrue("combine: Did not return the expected output", result);
	}

	// RANGE-33: combine with one empty Range object and one populated Range object
	@Test
	public void testCombineWithOneEmptyAndOnePopulatedObject() {
		Range r1 = new Range(2, 6);
		Range r2 = new Range(0, 0);
		Range r3 = Range.combine(r1, r2);

		boolean result = r3.contains(2) && r3.contains(6) && r3.contains(0);

		assertTrue("combine: Did not return the expected output", result);
	}

	// RANGE-34: combine with one null Range object and one negative value Range
	// object
	@Test
	public void testCombineWithOneNullAndOneNegativeValueObject() {
		Range r1 = null;
		Range r2 = new Range(-15, -10);
		Range r3 = Range.combine(r1, r2);

		boolean result = r3.contains(-15) && r3.contains(-10);

		assertTrue("combine: Did not return the expected output", result);
	}

	// RANGE-35: combine with one empty Range object and one negative value Range
	// object
	@Test
	public void testCombineWithOneEmptyAndOneNegativeValueObject() {
		Range r1 = new Range(0, 0);
		Range r2 = new Range(-15, -10);
		Range r3 = Range.combine(r1, r2);

		boolean result = r3.contains(-15) && r3.contains(-10) && r3.contains(0);

		assertTrue("combine: Did not return the expected output", result);
	}

	// RANGE-36: combine with one positive value Range object and one negative value
	// Range object
	@Test
	public void testCombineWithOnePositiveValueAndOneNegativeValueObject() {
		Range r1 = new Range(2, 6);
		Range r2 = new Range(-15, -10);
		Range r3 = Range.combine(r1, r2);

		boolean result = r3.contains(-15) && r3.contains(6);

		assertTrue("combine: Did not return the expected output", result);
	}

	// ********************************************************************
	// ****** Lab 3 - White Box Test Cases ********************************
	// ********************************************************************

	// ****** Tests for 'intersects' method *******************************

	// RANGE-37: intersects with an entirely overlapping Range object
	@Test
	public void testIntersectsWithEntirelyOverlappingRange() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.intersects(-5, 5);

		assertEquals("intersects: Did not return the expected output", expected, actual);
	}

	// RANGE-38: intersects with a non-overlapping negative Range object
	@Test
	public void testIntersectsWithNonOverlappingNegativeRange() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.intersects(-8, -6);

		assertEquals("intersects: Did not return the expected output", expected, actual);
	}

	// RANGE-39: intersects with a partially overlapping Range object
	@Test
	public void testIntersectsWithPartiallyOverlappingRange() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.intersects(0, 2);

		assertEquals("intersects: Did not return the expected output", expected, actual);
	}

	// RANGE-40: intersects with a non-overlapping positive Range object
	@Test
	public void testIntersectsWithNonOverlappingPositiveRange() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.intersects(8, 10);

		assertEquals("intersects: Did not return the expected output", expected, actual);
	}

	// RANGE-41: intersects with an invalid input Range object
	@Test
	public void testIntersectsWithInvalidInputRange() {
		rangeObjectUnderTest = new Range(-4, 4);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.intersects(0, -10);

		assertEquals("intersects: Did not return the expected output", expected, actual);
	}

	// ****** Tests for 'combine' method **********************************

	// RANGE-42: combine with null as the second parameter
	@Test
	public void testCombineWithSecondParameterNull() {
		Range r1 = new Range(0, 5);
		Range r2 = null;
		Range r3 = Range.combine(r1, r2);

		try {
			boolean result = r3.contains(0) && r3.contains(5) && r3.getLength() == 5;

			assertTrue("combine: Did not return the expected output", result);
		} catch (NullPointerException e) {
			fail("NullPointerException thrown. The expected outcome was a populated Range object from 0 to 5");
		}
	}

	// ****** Tests for 'toString' method *********************************

	// RANGE-43: toString output test
	@Test
	public void testToString() {
		rangeObjectUnderTest = new Range(-4.0, 4.0);

		String expected = "Range[-4.0,4.0]";
		String actual = rangeObjectUnderTest.toString();

		assertEquals("toString: Did not return the expected output", expected, actual);
	}

	// ****** Tests for 'equals' method ***********************************

	// RANGE-44: equals with a string value as the input
	@Test
	public void testEqualsWithStringObject() {
		rangeObjectUnderTest = new Range(-4, 4);
		String obj = "Range";

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.equals(obj);

		assertEquals("equals: Did not return the expected output", expected, actual);
	}

	// RANGE-45: equals where the Range object has an incorrect lower bound
	@Test
	public void testEqualsWithIncorrectLowerBound() {
		rangeObjectUnderTest = new Range(-4, 4);
		Range r2 = new Range(-15, 4);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.equals(r2);

		assertEquals("equals: Did not return the expected output", expected, actual);
	}

	// RANGE-46: equals where the Range object has an incorrect upper bound
	@Test
	public void testEqualsWithIncorrectUpperBound() {
		rangeObjectUnderTest = new Range(-4, 4);
		Range r2 = new Range(-4, 7);

		boolean expected = false;
		boolean actual = rangeObjectUnderTest.equals(r2);

		assertEquals("equals: Did not return the expected output", expected, actual);
	}

	// RANGE-47: equals where the two Range objects are equal
	@Test
	public void testEqualsWithCorrectValues() {
		rangeObjectUnderTest = new Range(-4, 4);
		Range r2 = new Range(-4, 4);

		boolean expected = true;
		boolean actual = rangeObjectUnderTest.equals(r2);

		assertEquals("equals: Did not return the expected output", expected, actual);
	}

	// ****** Tests for 'shift' method ************************************

	// RANGE-48: shift with a positive delta value
	@Test
	public void testShiftWithPositiveValue() {
		rangeObjectUnderTest = new Range(-4, 4);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 1.5);

		boolean result = rangeObjectUnderTest.getLength() == 8 && rangeObjectUnderTest.getLowerBound() == -2.5
				&& rangeObjectUnderTest.getUpperBound() == 5.5;

		assertTrue("shift: Did not return the expected output", result);
	}

	// RANGE-49: shift with a negative delta value
	@Test
	public void testShiftWithNegativeValue() {
		rangeObjectUnderTest = new Range(-4, 4);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -2);

		boolean result = rangeObjectUnderTest.getLength() == 8 && rangeObjectUnderTest.getLowerBound() == -6
				&& rangeObjectUnderTest.getUpperBound() == 2;

		assertTrue("shift: Did not return the expected output", result);
	}

	// ****** Tests for 'shift(boolean allowZeroCrossing)' method *********

	// RANGE-50: shift with zero crossing allowed
	@Test
	public void testShiftWithZeroCrossingAllowed() {
		rangeObjectUnderTest = new Range(-4, -2);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 10, true);

		boolean result = rangeObjectUnderTest.getLength() == 2 && rangeObjectUnderTest.getLowerBound() == 6
				&& rangeObjectUnderTest.getUpperBound() == 8;

		assertTrue("shift: Did not return the expected output", result);
	}

	// RANGE-51: shift with an empty Range object and zero crossing not allowed
	@Test
	public void testShiftWithEmptyRangeObjectAndZeroCrossingNotAllowed() {
		rangeObjectUnderTest = new Range(0, 0);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 10, false);

		boolean result = rangeObjectUnderTest.getLength() == 0 && rangeObjectUnderTest.getLowerBound() == 10
				&& rangeObjectUnderTest.getUpperBound() == 10;

		assertTrue("shift: Did not return the expected output", result);
	}

	// ****** Tests for Range constructor *********************************

	// RANGE-52: Range constructor with incorrect parameters (lower bound is greater
	// than upper bound)
	@Test
	public void testRangeConstructor() {
		try {
			rangeObjectUnderTest = new Range(10, 5);

			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

}
