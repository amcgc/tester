package com.gs.gss.codegolf.tester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.gs.gss.codegolf.solution.Solution;
import com.gs.gss.codegolf.test.Outcome;
import com.gs.gss.codegolf.test.Result;
import com.gs.gss.codegolf.test.Test;

public class ResultWriter {

	final Solution solution;
	final Map<Test, Result> testResults;
	
	ResultWriter(Solution solution, Map<Test, Result> testResults) {
		this.solution = solution;
		this.testResults = testResults;
	}
	
	void write() {
		
		Map<Test, Result> passed = new TreeMap<Test, Result>();
		Map<Test, Result> failed = new TreeMap<Test, Result>();
		long totalDuration = 0;
		
		for(Entry<Test, Result> test: testResults.entrySet()) {
			totalDuration = totalDuration + test.getValue().getDurationInMillis();
			
			if (test.getValue().getOutcome().equals(Outcome.PASS)) {
				passed.put(test.getKey(), test.getValue());
			} else {
				failed.put(test.getKey(), test.getValue());
			}
		}
		
		long averageDuration = totalDuration / testResults.size();
		
		StringBuilder sb = new StringBuilder();
		System.out.println("*****************************************");
		System.out.println("Solution: " + solution.getClass().getSimpleName());
		System.out.println("Total tests:" + testResults.size());
		System.out.println("Passed tests:" + passed.size());
		System.out.println("Passed examples " + getExamples(passed, 10));
		System.out.println("Failed tests:" + failed.size());
		System.out.println("Failed examples " + getExamples(failed, 100));
		System.out.println("Total Duration: " + totalDuration);
		System.out.println("Average Duration:" + averageDuration);
	}

	private List<String> getExamples(Map<Test, Result> tests, int max) {
		
		List<Entry<Test, Result>> entries = new ArrayList<Entry<Test, Result>>(tests.size());
		entries.addAll(tests.entrySet());
		
		int skip = (tests.size() < max)? 1 : tests.size() / max;
		
		List<String> examples = new LinkedList<String>();
		for (int i = 0; i<entries.size(); i = i+skip ) {
			examples.add(entries.get(i).getKey() +
					"], Outcome [" + entries.get(i).getValue().getOutcome() +
					"], Expected [" + entries.get(i).getValue().getExpectedOuput() +
					"], Actual [" + entries.get(i).getValue().getActualOutput() +
					"], Time Taken [" + entries.get(i).getValue().getDurationInMillis() +"ms"+
					"]");
		}
		return examples;
	}
}
