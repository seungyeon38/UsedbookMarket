package usedBookMarketplace;

//model
public class Book {
	//book의 정보 : 제목, ISBN, 저자, 출판사, 출판년도, 판매자id, 가격, 상태(Excellent, Good, Fair)
		int key = 0; //book에는 unique한 값이 없어서 추가, db에서 ai
		private String title;
		private String isbn;
		private String author;
		private String publisher;
		private String publication_year;
		private String bookseller_id;
		private String price;
		private String state;
		private String purchase_intention = "N";
		
		public Book() {}
		
		public Book(String title, String isbn, String author, String publisher, 
				String publication_year, String bookseller_id, String price, String state) 
		{
			this.title = title;
			this.isbn = isbn;
			this.author = author;
			this.publisher = publisher;
			this.publication_year = publication_year;
			this.bookseller_id = bookseller_id;
			this.price = price;
			this.state = state;
		}
		
		//getter
		public int getKey() {return key;}
		public String getTitle() {return title;}
		public String getISBN() {return isbn;}
		public String getAuthor() {return author;}
		public String getPublisher() {return publisher;}
		public String getPublicationYear() {return publication_year;}
		public String getBookSellerID() {return bookseller_id;}
		public String getPrice() {return price;}
		public String getState() {return state;}
		public String getPurchaseIntention() {return purchase_intention;}
	
		//setter 
		public void setKey(int key) {this.key = key;} 
		public void setTitle(String title) {this.title = title;}
		public void setISBN(String isbn) {this.isbn = isbn;}
		public void setAuthor(String author) {this.author = author;}
		public void setPublisher(String publisher) {this.publisher = publisher;}
		public void setPublicationYear(String publication_year) {this.publication_year = publication_year;}
		public void setBookSellerID(String bookseller_id) {this.bookseller_id = bookseller_id;}
		public void setPrice(String price) {this.price = price;}
		public void setState(String state) {this.state = state;}
		public void setPurchaseIntention(String purchase_intention) {this.purchase_intention = purchase_intention;}

}
