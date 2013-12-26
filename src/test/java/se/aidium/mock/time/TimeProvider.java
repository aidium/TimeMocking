package se.aidium.mock.time;

public class TimeProvider {
	private final static TimeProvider instance = new TimeProvider();

	public static TimeProvider instance() {
		return instance;
	}

	private long currentTimeMillis;
	private boolean mockTime;

	public boolean isTimedMocked() {
		return mockTime;
	}

	public void useMockTime(long currentTime) {
		currentTimeMillis = currentTime;
		mockTime = true;
	}

	public void useSystemTime() {
		mockTime = false;
	}

	public long currentTimeMillis() {
		return currentTimeMillis;
	}

	public void tick(int tick) {
		currentTimeMillis += tick;
	}
}
