package main;

public class LogSearch {
	
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	public class LogSearch {

	    public static void searchByStationName(String stationName) throws IOException {
	        String logPattern = "logs/" + stationName + "_\\d{4}-\\d{2}-\\d{2}.log";
	        searchLogs(logPattern);
	    }

	    public static void searchByDate(String date) throws IOException {
	        String logPattern = "logs/.*_" + date + ".log";
	        searchLogs(logPattern);
	    }

	    private static void searchLogs(String logPattern) throws IOException {
	        Pattern pattern = Pattern.compile(logPattern);
	        Files.list(Paths.get("logs/")).forEach(path -> {
	            Matcher matcher = pattern.matcher(path.getFileName().toString());
	            if (matcher.matches()) {
	                try {
	                    Files.lines(path).forEach(System.out::println);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	}

}
