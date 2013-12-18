package se.aidium.mock.time;

import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

public class TimingTest {

	@Mock
	DataGeneratorI dataGeneratorMock;

	@InjectMocks
	Timing timing;

	@Test(expected = RuntimeException.class)
	public void shouldTimoutAndThrow() {
		// Given
		given(dataGeneratorMock.generateDatasetWithIndex(anyInt())).willAnwer(
				new Answer() {
				});

		Date timeout = new Date(System.currentTimeMillis() + 30000);
		timing.processLotsOfDataWithTimout(timeout);
	}
}
