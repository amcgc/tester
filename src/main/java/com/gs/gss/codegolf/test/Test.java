package com.gs.gss.codegolf.test;

import java.util.concurrent.Callable;

import com.gs.gss.codegolf.solution.Solution;

public class Test implements Callable<Result>, Comparable<Test> {

	private final TestCase testcase;
	private final Solution solution;

	public Test(TestCase testcase, Solution solution) {
		this.testcase = testcase;
		this.solution = solution;
	}


	@Override
	public Result call() throws Exception {
		
		System.out.println("Running Test: " + this.toString());
		
		long start = System.currentTimeMillis();

		int testOutput = this.solution.nextPrime(testcase.getInput());

		long end = System.currentTimeMillis();

		Result result = new Result(testOutput, testcase.getExpectedOutput(), end - start);
		System.out.println("Returning result: " + result);
		return result;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((solution == null) ? 0 : solution.hashCode());
		result = prime * result
				+ ((testcase == null) ? 0 : testcase.hashCode());
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
		Test other = (Test) obj;
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		if (testcase == null) {
			if (other.testcase != null)
				return false;
		} else if (!testcase.equals(other.testcase))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Test [testcase=" + testcase + ", solution=" + solution + "]";
	}


	@Override
	public int compareTo(Test that) {
		return this.testcase.compareTo(that.testcase);
	}
	
	
	

}
