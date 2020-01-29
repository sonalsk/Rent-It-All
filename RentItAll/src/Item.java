
public class Item {
	protected int id;
	protected String name;
	protected String category;
	protected int price;
	protected int duration;
	
	public Item() {
		
	}
	
	public Item(int id) {
		this.id = id;
	}
	
	public Item(int id, String name, String category, int price, int duration) {
	       this.name = name;
	       this.category = category;
	       this.price = price;
	       this.duration = duration;
	       this.id = id;
   }
	
	
    public Item(String name, String category, int price, int duration) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.duration = duration;
    }
    
    public int getID() {
    	return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getPrice() {
    	return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getDuration() {
    	return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
}






























