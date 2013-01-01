package com.broodsoft.ranumgen;

import java.util.Random;

class RangeBasedGenerator implements RandomNumberGenerator {
	private final int max;
	private final int offset;
	private final Random generator;

	RangeBasedGenerator(int max, int offset, Random generator) {
		this.max = max;
		this.offset = offset;
		this.generator = generator;
	}

	@Override
	public int generate() {
		return generator.nextInt(max) + offset;
	}
}
