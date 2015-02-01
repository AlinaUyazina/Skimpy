package ProductExtractor;

import org.jsoup.nodes.Document;

public class AsdaScraper extends WebScraper {

	public static void main(String[] args) {
		AsdaSpider asda = new AsdaSpider();
		
		Document asdaRootPage = asda.getHTML(asda.rootPageURL);
		System.out.println(asda.getDocumentTitle(asdaRootPage) + System.lineSeparator());
	}

}
