package ProductExtractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Product {
	public String name;
	public String url;
	public String price;
	public String pricePerUnit;

	public Product(String name, String url, String ppi, String ppu) {
		this.name = name;
		
		Pattern r = Pattern.compile("^/groceries");
		Matcher m = r.matcher(url);
		if (m.find()) {
			this.url = "http://www.tesco.com" + url;
		}
		else {
			this.url = url;
		}
		
		this.price = ppi;
		
		this.pricePerUnit = ppu;
	}
	
	public String toString() {
		return this.name + ", " + this.price + ", " + this.pricePerUnit;
	}
}