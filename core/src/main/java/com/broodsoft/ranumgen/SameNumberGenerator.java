package com.broodsoft.ranumgen;

class SameNumberGenerator implements RandomNumberGenerator {
	private final int num;

	SameNumberGenerator(int num) {
		this.num = num;
	}

	@Override
	public int generate() {
		return num;
	}
}
