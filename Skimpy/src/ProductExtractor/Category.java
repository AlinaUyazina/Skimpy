package ProductExtractor;

public class Category
{
	public String name;
	public String url;

	public Department(String name, String url)
	{
		this.name = name;
		this.url = url;
	}

	public String toString()
	{
		return this.name + " " + this.url;
	}
}