package se.aidium.mock.time;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class TimingTest {

	@Mock
	DataGeneratorI dataGeneratorMock;

	@InjectMocks
	Timing timing;

	@Test(expected = RuntimeException.class)
	public void shouldTimoutAfterFourthCallAndThrowBadImplementation() {
		// Given
		given(dataGeneratorMock.generateDatasetWithIndex(anyInt())).will(new Answer<List<Integer>>() {

			@Override
			public List<Integer> answer(InvocationOnMock invocation) throws Throwable {
				// Simulate work
				Thread.sleep(10);
				return Arrays.asList(1);
			}
		});

		try {
			// When
			Date timeout = new Date(System.currentTimeMillis() + 35);
			timing.processLotsOfDataWithTimout(timeout);
		} finally {
			// Then
			verify(dataGeneratorMock, times(4)).generateDatasetWithIndex(anyInt());
		}
	}

	@Test(expected = RuntimeException.class)
	public void shouldTimoutAfterFourthCallAndThrow() {
		// Given
		TimeProvider.instance().useMockTime(System.currentTimeMillis());
		given(dataGeneratorMock.generateDatasetWithIndex(anyInt())).will(new Answer<List<Integer>>() {

			@Override
			public List<Integer> answer(InvocationOnMock invocation) throws Throwable {
				TimeProvider.instance().tick(1);
				return Arrays.asList(1);
			}
		});

		try {
			// When
			Date timeout = new Date(System.currentTimeMillis() + 3);
			timing.processLotsOfDataWithTimout(timeout);
		} finally {
			// Then
			verify(dataGeneratorMock, times(4)).generateDatasetWithIndex(anyInt());
			TimeProvider.instance().useSystemTime();
		}
	}
}
