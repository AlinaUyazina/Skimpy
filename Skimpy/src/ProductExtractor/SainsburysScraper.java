package ProductExtractor;

import org.jsoup.nodes.Document;

public class SainsburysScraper extends WebScraper {
	public static void main(String[] args) {
		SainsburysSpider snsbry = new SainsburysSpider();
		
		Document snsbryRootPage = snsbry.getHTML(snsbry.rootPageURL);
		System.out.println(snsbry.getDocumentTitle(snsbryRootPage) + System.lineSeparator());
	}
}
