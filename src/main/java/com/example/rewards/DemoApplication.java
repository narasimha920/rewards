package com.example.rewards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {

		List<Long> janTransactions = Arrays.asList(Long.valueOf(20), Long.valueOf(80), Long.valueOf(120));
		List<Long> febTransactions = Arrays.asList(Long.valueOf(140), Long.valueOf(30), Long.valueOf(60));
		List<Long> marTransactions = Arrays.asList(Long.valueOf(200), Long.valueOf(10));

		List<List<Long>> listInvoices = new ArrayList<>();
		listInvoices.add(janTransactions);
		listInvoices.add(febTransactions);
		listInvoices.add(marTransactions);

		long rewards = threeMonthsRewards(listInvoices);
		System.out.println("totalRewardPointsForThreeMonths :: " + rewards);
		
	}

	private static long threeMonthsRewards(List<List<Long>> threeMonthsInvoice) {
		return threeMonthsInvoice.stream().mapToLong((invoice) -> {
			long eachMonth = perMonthRewards(invoice);
			System.out.println("Each Month total rewards invoice "+eachMonth);
			return eachMonth;
		}).sum();
	}

	private static long perMonthRewards(final List<Long> invoice) {
		return invoice.stream().mapToLong((transactionAmt) -> {
			return rewardsPerTransaction(transactionAmt);
		}).sum();
	}

	private static long rewardsPerTransaction(final long amount) {
		if(amount > 100) {
			return (amount - 100) * 2 + 50;
		} else if (amount >= 50 && amount <= 100) {
			return amount - 50;
		} else {
			return 0;
		}
	}

}
