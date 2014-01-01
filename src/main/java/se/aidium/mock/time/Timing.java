package se.aidium.mock.time;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timing {

	private DataGeneratorI dataGeneretor;

	public void processLotsOfDataWithTimout(Date timeout) {
		Map<Integer, List<Integer>> generatedData = new HashMap<>();
		for (int i = 0; i < 10; ++i) {
			generatedData.put(i, dataGeneretor.generateDatasetWithIndex(i));
			checkForTimeout(timeout);
		}

	}

	private void checkForTimeout(Date timeout) {
		if (new Date(System.currentTimeMillis()).after(timeout)) {
			// if (new Date().after(timeout)) {
			throw new RuntimeException("Timeout");
		}
	}

}
