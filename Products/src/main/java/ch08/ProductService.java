package ch08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
	Map<String, ProductDAO> ProductDAOs = new HashMap<String, ProductDAO>();
	
	
	public ProductService() {
		ProductDAO p = new ProductDAO("101", "애플사과폰 12",  "애플전자", 1200000, "2020.12.12");
		ProductDAOs.put("101", p);
		p = new ProductDAO("102", "삼전우주폰 21",  "삼전전자", 1300000, "2021.2.2");
		ProductDAOs.put("102", p);
		p = new ProductDAO("103", "엘스듀열폰 ",  "엘스전자", 1500000, "2021.3.2");
		ProductDAOs.put("103", p);
	}
	
	public List<ProductDAO> findAll(){
		return new ArrayList<ProductDAO>(ProductDAOs.values());
	}
	
	public ProductDAO find(String id) {
		return ProductDAOs.get(id);
	}
	
}
