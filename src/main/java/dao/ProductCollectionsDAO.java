package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Mark George
 */
public class ProductCollectionsDAO implements ProductDAO {

    private static final Multimap<String, Product> categories = HashMultimap.create();
    private static final Map<String, Product> products = new HashMap<>();
    private static Collection<Product> items = new HashSet<>();

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ProductCollectionsDAO() {
        if (products.isEmpty()) {
            saveProduct(new Product("WD1233", "Ultra Pre workout", "heart attack juice", "Energy", new BigDecimal("7.32"), new BigDecimal(35)));
            saveProduct(new Product("WD1234", "Amino Energy", "Quick recovery from amino acids", "Recovery", new BigDecimal("21.43"), new BigDecimal(3)));
            saveProduct(new Product("DH8831", "High Chocolate Protein", "Just pure whey protein", "Protein", new BigDecimal("12.32"), new BigDecimal(5)));
            saveProduct(new Product("DH8832", "Vanilla Protein", "Just pure vanilla protein ideal for baking", "Protein", new BigDecimal("43.23"), new BigDecimal(6)));
        }
    }

    @Override
    public void saveProduct(Product product) {
        products.put(product.getProductId(), product);
        categories.put(product.getCategory(), product);
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product.getProductId());
        categories.remove(product.getCategory(), product);
    }

    @Override
    public Collection<Product> getProducts() {
        return products.values();
    }

    @Override
    public Collection<String> getCategories() {
        return categories.keySet();
    }

    @Override
    public Product searchById(String id) {
        return products.get(id);
    }

    @Override
    public Collection<Product> filterByCategory(String category) {
        return categories.get(category);
    }

}
