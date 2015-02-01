package ProductExtractor;

import java.util.List;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;

public class TescoSpider extends WebSpider
{
	public TescoSpider() {
		this.rootPageURL = "http://www.tesco.com/groceries";
	}
	
	public List<Department> listDepartments() {
		List<Department> allDepartments = new ArrayList<Department>();
		
		Elements departmentsNodes = this.getHTML(this.rootPageURL).select("#secondaryNav ul li");
		for (Element d : departmentsNodes) {
			String name = d.select("a").text();
			String link = d.select("a").attr("href");
			Department dep = new Department(name, link);
			allDepartments.add(dep);
		}
		return allDepartments;
	}

	public List<Category> listCategories(String departmentURL) {
		List<Category> allCategories = new ArrayList<Category>();

		Elements categoriesNodes = this.getHTML(departmentURL).select("#superDeptItems div.column ul li");

		for (Element c : categoriesNodes) {
			String name = c.select("a").text();
			String link = c.select("a").attr("href");
			Category cat = new Category(name, link);
			allCategories.add(cat);
		}
		return allCategories;
	}
	
	public List<Product> listProducts(String productsGridURL) {
		List<Product> allProducts = new ArrayList<Product>();
		List<String> productURLs = new ArrayList<String>();
		
		Elements productsNodes = this.getHTML(productsGridURL).select("div.productLists ul li");
		for (Element p : productsNodes) {
			productURLs.add("http://www.tesco.com" + p.select("a").attr("href"));
		}
		
		for (String productURL : productURLs) {
			Document productPage = this.getHTML(productURL);
			String productName = productPage.select("h1").text();
			String price;
			String pricePerUnit;
			
			try {
				price = productPage.select("span.linePrice").first().text();
			}
			catch (NullPointerException npe) {
				price = productPage.select("span.linePrice").text();
			}
			
			try {
				pricePerUnit = productPage.select("span.linePriceAbbr").first().text();
			}
			catch (NullPointerException npe) {
				pricePerUnit = productPage.select("span.linePriceAbbr").text();
			}
			
			Product prod = new Product(productName, productURL, price, pricePerUnit);
			allProducts.add(prod);
		}
		
		return allProducts;
	}
}
