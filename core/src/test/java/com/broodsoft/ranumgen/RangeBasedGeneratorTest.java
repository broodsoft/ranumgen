package com.broodsoft.ranumgen;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Range;

public class RangeBasedGeneratorTest {
	private static final String EMPTY_RANGES = "emptyRanges";
	private static final String RANGES = "ranges";

	@Test(dataProvider=EMPTY_RANGES, expectedExceptions={IllegalArgumentException.class})
	public void testEmptyRangeError(Range<Integer> range) throws IllegalArgumentException {
		RandomNumberGenerators.create(range, new Random());
	}

	@DataProvider(name=EMPTY_RANGES)
	public Object[][] emptyRanges() {
		return new Object[][] {
			{Range.closedOpen(3, 3)},
			{Range.openClosed(4, 4)},
		};
	}

	@Test(dataProvider=RANGES, invocationCount=10)
	public void testGenerate(Range<Integer> range) {
		final RandomNumberGenerator generator = RandomNumberGenerators.create(range, new Random());
		final int generatedValue = generator.generate();
		assertTrue(range.contains(generatedValue));
	}

	@DataProvider(name=RANGES)
	public Object[][] ranges() {
		return new Object[][] {
			{Range.closed(2,2)},
			{Range.closed(2,6)},
			{Range.closedOpen(3, 4)},
			{Range.closedOpen(11, 17)},
			{Range.openClosed(17, 18)},
			{Range.openClosed(23, 29)},
			{Range.open(13, 15)},
			{Range.open(31, 43)},
		};
	}
}
