package com.gs.gss.codegolf.test;

public class Result {

	public int getActualOutput() {
		return actualOutput;
	}

	public void setActualOutput(int actualOutput) {
		this.actualOutput = actualOutput;
	}

	public int getExpectedOuput() {
		return expectedOuput;
	}

	public void setExpectedOuput(int expectedOuput) {
		this.expectedOuput = expectedOuput;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public long getDurationInMillis() {
		return durationInMillis;
	}

	public void setDuration(long duration) {
		this.durationInMillis = duration;
	}

	private int actualOutput;
	private int expectedOuput;
	private Outcome outcome;
	private long durationInMillis;
	
	Result(int testOutput, int expectedOuput, long millisTaken) {
		this.actualOutput = testOutput;
		this.expectedOuput = expectedOuput;
		this.outcome = expectedOuput == testOutput? Outcome.PASS : Outcome.FAIL;
		this.durationInMillis = millisTaken;
	}

	@Override
	public String toString() {
		return "Result [actualOutput=" + actualOutput + ", expectedOuput="
				+ expectedOuput + ", outcome=" + outcome + ", duration="
				+ durationInMillis + "]";
	}
	
	
}
