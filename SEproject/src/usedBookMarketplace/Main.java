package usedBookMarketplace;

public class Main {
	public static void main(String[] args) {
		//드라이버로딩 
		try {
			Class.forName("com.mysql.jdbc.Driver"); 			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Market market = new Market();
		market.marketOpen();
	}	
}
