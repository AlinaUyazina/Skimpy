package ProductExtractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

public class TescoScraper extends WebScraper {

	public static void main(String[] args) {
		TescoSpider tesco = new TescoSpider();
		
		Document tescoRootPage = tesco.getHTML(tesco.rootPageURL);
		System.out.println(tesco.getDocumentTitle(tescoRootPage) + System.lineSeparator());
		
		File oFile = new File("tesco.txt");
		
		List<Department> tescoDepartments = tesco.listDepartments().subList(0, 2);
		for (Department t : tescoDepartments) {
			List<Category> categories = tesco.listCategories(t.url);
			for (Category c : categories) {
				List<Product> productsFromGrid = tesco.listProducts(c.url);
				
				for (Product p : productsFromGrid) {
					try	{
						if (!oFile.exists()) {
							oFile.createNewFile();
						}
						if (oFile.canWrite()) {
							BufferedWriter oWriter = new BufferedWriter(new FileWriter("products.txt", true));
							oWriter.write(p.toString() + System.lineSeparator());
							oWriter.close();
						}
					}
					catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		}
	}
}
