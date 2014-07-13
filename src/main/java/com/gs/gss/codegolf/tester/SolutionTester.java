package com.gs.gss.codegolf.tester;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.gs.gss.codegolf.solution.Solution;
import com.gs.gss.codegolf.test.Result;
import com.gs.gss.codegolf.test.Test;
import com.gs.gss.codegolf.test.TestCase;

public class SolutionTester implements Runnable {

	private static final int TIMEOUT = 100;

	private final Solution solution;
	private final Map<Test, Result> testToResult = new HashMap<Test, Result>();
	private final ThreadPoolExecutor testPool = new ThreadPoolExecutor(
			10, 
			100, 
			100, 
			TimeUnit.MILLISECONDS, 
			new LinkedBlockingQueue<Runnable>(20000), 
			new ThreadPoolExecutor.CallerRunsPolicy());


	public SolutionTester(Solution solution) {
		this.solution = solution;
	}

	@Override
	public void run() {	

		System.out.println("Testing solution: ");
		
		Map<Test, Future<Result>> testToFuture = new HashMap<Test, Future<Result>>();

		for (TestCase testcase : TestCases.getInstance()) {
			Test test = new Test(testcase, solution);
			testToFuture.put(test, testPool.submit(test));
			System.out.println("Submitted Test:" + test);
		}

		try {

			for(Entry<Test, Future<Result>> testcase : testToFuture.entrySet()) {
				testToResult.put(
						testcase.getKey(), 
						testcase.getValue().get(TIMEOUT, TimeUnit.MINUTES));
			}

		} catch (InterruptedException | ExecutionException	| TimeoutException e) {
			// TODO handle
		}

		new ResultWriter(solution, testToResult).write();
	}

	public static final void main(String args[]) {

		for (Solution solution : SolutionFactory.getSolutions()) {
			System.out.println("Submitting solution to SolutionTester: " + solution.getClass().getCanonicalName());
			Executors.newSingleThreadExecutor().execute(new SolutionTester(solution));
		}
	}
}
