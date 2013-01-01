package com.broodsoft.ranumgen;

import java.util.Random;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

public final class RandomNumberGenerators {
	public static RandomNumberGenerator create(Range<Integer> range, Random generator) throws IllegalArgumentException {
		if(range.isEmpty()) {
			throw new IllegalArgumentException("The range "+range+" is invalid. Empty ranges are not allowed.");
		}

		final int offset = determineLowestIncluded(range);
		final int upper = determineLargestIncluded(range);
		final int max = upper - offset;

		if(max == 0) {
			return new SameNumberGenerator(upper);
		} else if (max > 0){
			return new RangeBasedGenerator(max, offset, generator);
		} else {
			throw new IllegalArgumentException();
		}
	}

	private static int determineLargestIncluded(Range<Integer> range) {
		return adjustEndpointBasedOnBoundType(range.upperEndpoint(), range.upperBoundType(), -1);
	}

	private static int determineLowestIncluded(Range<Integer> range) {
		return adjustEndpointBasedOnBoundType(range.lowerEndpoint(), range.lowerBoundType(), 1);
	}

	private static int adjustEndpointBasedOnBoundType(int endpoint, BoundType boundType, int offset) {
		switch(boundType) {
			case OPEN:
				return endpoint + offset;
			case CLOSED:
				return endpoint;
			default:
				throw new IllegalArgumentException();
		}
	}
}
