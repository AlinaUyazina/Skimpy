package ProductExtractor;

import java.util.List;
import java.io.*;

import org.jsoup.nodes.Document;

class ProductExtractor
{
	public List<Product> completeProductsList;
	
	public static void main(String[] args)
	{
		AsdaSpider asda = new AsdaSpider();
		SainsburysSpider snsbry = new SainsburysSpider();
		TescoSpider tesco = new TescoSpider();
		
		// Connect to ASDA
		Document asdaRootPage = asda.getHTML(asda.rootPageURL);
		System.out.println(asda.getDocumentTitle(asdaRootPage) + System.lineSeparator());
		
		// Connect to Tesco
		Document tescoRootPage = tesco.getHTML(tesco.rootPageURL);
		System.out.println(tesco.getDocumentTitle(tescoRootPage) + System.lineSeparator());
		
		File oFile = new File("products.txt");
		
		List<Department> tescoDepartments = tesco.listDepartments().subList(0, 2);
		for (Department t : tescoDepartments)
		{
			List<Category> categories = tesco.listCategories(t.url);
			for (Category c : categories)
			{
				List<Product> productsFromGrid = tesco.listProducts(c.url);
				
				for (Product p : productsFromGrid)
				{
					try
					{
						if (!oFile.exists())
						{
							oFile.createNewFile();
						}
						if (oFile.canWrite())
						{
							BufferedWriter oWriter = new BufferedWriter(new FileWriter("products.txt", true));
							oWriter.write(p.toString() + System.lineSeparator());
							oWriter.close();
						}
					}
					catch (IOException ioe)
					{
						ioe.printStackTrace();
					}
				}
			}
		}
		System.out.println(System.lineSeparator());
		
		
		
		// Connect to Sainsbury's
		Document snsbryRootPage = snsbry.getHTML(snsbry.rootPageURL);
		System.out.println(snsbry.getDocumentTitle(snsbryRootPage) + System.lineSeparator());
	}
}