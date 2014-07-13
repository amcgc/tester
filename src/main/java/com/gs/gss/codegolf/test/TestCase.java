package com.gs.gss.codegolf.test;

public class TestCase implements Comparable<TestCase> {

	private final int n;
	private int nextPrime;

	public TestCase(int n, int nextPrime) {
		this.n = n;
		this.nextPrime = nextPrime;
	}

	public int getExpectedOutput() {
		return nextPrime;
	}

	public int getInput() {
		return n;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		result = prime * result + nextPrime;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestCase other = (TestCase) obj;
		if (n != other.n)
			return false;
		if (nextPrime != other.nextPrime)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "TestCase [n=" + n + ", nextPrime=" + nextPrime + "]";
	}

	@Override
	public int compareTo(TestCase that) {
		int nCompared = Integer.compare(this.n, that.n);
		
		if (nCompared == 0)
			return Integer.compare(this.nextPrime, that.nextPrime);
		else
			return nCompared;
	}
		
	
}
