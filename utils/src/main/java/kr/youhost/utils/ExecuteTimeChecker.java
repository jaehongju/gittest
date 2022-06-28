package kr.youhost.utils;


public class ExecuteTimeChecker {
	private long begAt=0L;
	private long duration=0L;
	
	@SuppressWarnings("PMD.AvoidStringBufferField")
	private StringBuilder sb = new StringBuilder();
	
	public long start() {
		begAt = System.currentTimeMillis(); //nanoTime(); //
		return begAt;
	}
	public long end() {
		duration = System.currentTimeMillis() - begAt; //; nanoTime()
		return duration;
	}
	public String endAt() {
		return getFormattedDuration(end());
	}
	public String getDuration() {
		return getFormattedDuration(duration);
	}
	public void endLog(String tag) {
		sb.append(tag).append(" : ").append(endAt());		
	}
	public String getLog() {
		return sb.toString();
	}
	public String getLog(String tag) {
		StringBuilder sb = new StringBuilder();
		sb.append(tag).append(" : ").append(endAt());
		return sb.toString();
	}
	
	public String getFormattedDuration(long duration) {
		long min = duration/(1000*60);
		long sec = (duration%(1000*60))/1000;
		long msec = duration%1000;
		StringBuilder s = new StringBuilder();
		s.append(min).append(':').append(sec).append('.').append(msec);
//		Calendar c = Calendar.getInstance();
//		c.setTimeInMillis(duration);
//		
//		s.append(c.get(Calendar.HOUR_OF_DAY)).append(":").append( c.get(Calendar.MINUTE) ).append(":").append(c.get(Calendar.SECOND)).append(".").append(c.get(Calendar.MILLISECOND));
		return s.toString();
	}
}
