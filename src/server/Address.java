package server;

public class Address {



	private static String dbAddress = "115.139.206.16";
	private static int dbPort = 1521;
	private static String serverAddress = "115.139.206.16";
	private static int serverPort = 5003;
	public static String getDbAddress() {
		return dbAddress;
	}
	public static int getDbPort() {
		return dbPort;
	}
	public static String getServerAddress() {
		return serverAddress;
	}
	public static int getServerPort() {
		return serverPort;
	}
	
}
