package com.gs.gss.codegolf.tester;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gs.gss.codegolf.test.TestCase;

public class TestCases implements Iterable<TestCase> {

	private static final String PRIMES_DIR = "C:/Users/Alex/Desktop/Personal/codeComp/tester/src/main/resource/primes/";
	private static final LinkedList<File> files = new LinkedList<File>(Arrays.asList(
			new File(PRIMES_DIR + "primes-test.txt"),
			new File(PRIMES_DIR + "primes-2.txt"),
			new File(PRIMES_DIR + "primes-4256249.txt"),
			new File(PRIMES_DIR + "primes-44680327.txt"),
			new File(PRIMES_DIR + "primes-817504253.txt"),
			new File(PRIMES_DIR + "primes-2145390539.txt")));

	private static final TestCases singleton = new TestCases();

	private Map<File, List<Integer>> filesToPrimes = 
			new HashMap<File, List<Integer>>();

	private TestCases()  {
		try {
			System.out.println("Loading primes:");
			for(File file : files) {
				
				List<Integer> primes = new ArrayList<Integer>();
				filesToPrimes.put(file, primes);
				
				System.out.println("Loading primes from file :" + file.getAbsolutePath()
						+ ", cumulative count: " + primes.size());
				
				BufferedReader br = new BufferedReader(new FileReader(file));

				String line;
				while ((line = br.readLine()) != null) {
					primes.add(Integer.parseInt(line));
				}
				br.close();
				System.out.println("Loaded total primes: " + primes.size());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static TestCases getInstance() {
		return singleton;
	}
	
	class TestCaseIterator implements Iterator<TestCase> {

		Iterator<Entry<File, List<Integer>>> entryIter = filesToPrimes.entrySet().iterator();
		Entry<File, List<Integer>> currentEntry = entryIter.next();
		int nIndex = -1;

		@Override
		public boolean hasNext() {
			if (currentEntry.getValue().size() > (nIndex +2))
				return true;
			
			if (!entryIter.hasNext())
				return false; 
			
			currentEntry = entryIter.next();
			nIndex = -1;
			
			if (currentEntry.getValue().size() > (nIndex +2))
				return true;
			
			return false;			
		}

		@Override
		public TestCase next() {
			nIndex++;
			return new TestCase(currentEntry.getValue().get(nIndex),
					currentEntry.getValue().get(nIndex+1));
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public Iterator<TestCase> iterator() {
		return new TestCaseIterator();
	}
}