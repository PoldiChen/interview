package interview20200617alibaba;

import java.util.*;

/*
购物车有很多商品，需要按特定规则排布，要求是
1. 按店铺分组
2. 按规定顺序排序，排序规则如下：
  1.对店铺内商品排序，先按商品类别排序，图书类按出版时间排序，服装按价格排序，再按加购时间排序；
  2.对店铺间排序，按店铺内商品的加购顺序
    比如A店铺下有a,b,c三个商品，加购时间为2020-01-02,2020-01-02,2020-01-04，B店铺下有d,e两个商品，加购时间分别为2020-01-03,2020-02-01，那么B应该排在A店铺前
注：使用java或其他语言实现，非SQL
注：购物车下有N个店铺，店铺内有N个商品，商品都有类目属性，比如A商品是服装类目的，B商品是手机类目的，C商品是箱包类目的，D商品是美状类目等等
注：一个店铺下可以有多个类目，也就是说一个卖家可以卖多个类目的商品，比如同时卖电子类目的商品和运动类目的商品
注：加购时间=加入购物车时的时间
注：输入List<Product>对象  输出ShoppingCar对象，Product代表商品，ShoppingCar代表购物车
注：【加分项】业务上，排序规则随时可能发生变化
注：时间60-70分钟
注：可以使用JDK排序，不建议使用Stream
*/

/**
 * @author poldi.chen
 * @className Main
 * @description TODO
 * @date 2020/6/20 21:09
 **/
public class Main {

    public ShoppingCar sortProduct(List<Product> products) {

        Map<String, List<Product>> sortedProducts = new HashMap<>();

        // 1.将商品按店铺分组
        for (Product product : products) {
            String storeName = product.getStore();
            List<Product> storeProducts;
            if (sortedProducts.containsKey(storeName)) {
                storeProducts = sortedProducts.get(storeName);
            } else {
                storeProducts = new ArrayList<>();
            }
            storeProducts.add(product);
            sortedProducts.put(storeName, storeProducts);
        }

        // 2.对店铺内的商品进行排序
        Set<Map.Entry<String, List<Product>>> entries = sortedProducts.entrySet();
        for (Map.Entry<String, List<Product>> entry : entries) {
            List<Product> storeProducts = sortedProducts.get(entry);
            Collections.sort(storeProducts, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.getCategory().equals(o2.getCategory())) { // 类别相同
                        if (o1.getCategory().equals("图书")) {
                            if (o1.getPublishDate().equals(o2.getPublishDate())) { // 图书的出版时间相同，按加购时间排序
                                return o1.getAddTime().before(o2.getAddTime()) ? 1 : -1;
                            } else {
                                return o1.getPublishDate().before(o2.getPublishDate())? 1 : -1;
                            }
                        } else if (o1.getCategory().equals("服装")) {
                            if (o1.getPrice() == o2.getPrice()) { // 服装的价格相同，按加购时间排序
                                return o1.getAddTime().before(o2.getAddTime()) ? 1 : 0;
                            } else {
                                return o1.getPrice() > o2.getPrice()? 1 : -1;
                            }
                        } else {
                            return 0;
                        }
                    } else {
                        return o1.getCategory().compareTo(o2.getCategory()); // 类别不同，按类别排序
                    }
                }
            });
            sortedProducts.put(entry.getKey(), storeProducts);
        }

        // 3.对店铺进行排序
        // 3.1 取出每个店铺中的最早商品加购时间
        List<Store> stores = new ArrayList<>();
        Set<Map.Entry<String, List<Product>>> entries2 = sortedProducts.entrySet();
        for (Map.Entry<String, List<Product>> entry : entries2) {
            Store store = new Store(entry.getKey());
            List<Product> storeProducts = sortedProducts.get(entry);
            Date firstAddTime = null;
            for (Product product : storeProducts) {
                if (firstAddTime == null) {
                    firstAddTime = product.getAddTime();
                } else if (product.getAddTime().before(firstAddTime)) {
                    firstAddTime = product.getAddTime();
                }
            }
            store.setFirstAddTime(firstAddTime);
            stores.add(store);
        }
        // 3.2 根据最早商品加购时间对店铺排序
        Collections.sort(stores, new Comparator<Store>() {
            @Override
            public int compare(Store o1, Store o2) {
                return o1.getFirstAddTime().before(o2.getFirstAddTime()) ? 1: -1;
            }
        });

        // 4. 将排序后的商品放入购物车中
        ShoppingCar shoppingCar = new ShoppingCar();
        List<List<Product>> carProducts = new ArrayList<>();
        for (Store store : stores) {
            carProducts.add(sortedProducts.get(store.getName()));
        }
        shoppingCar.setProducts(carProducts);
        return shoppingCar;
    }
}

class Store {
    private String name;
    private Date firstAddTime;

    public Store(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFirstAddTime() {
        return firstAddTime;
    }

    public void setFirstAddTime(Date firstAddTime) {
        this.firstAddTime = firstAddTime;
    }
}

class Product {

    private String productId; // 商品ID
    private String store; // 商品所属店铺
    private String category; // 商品类别
    private Date publishDate; // 出版时间
    private double price; // 价格
    private Date addTime; // 加购时间

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}

class ShoppingCar {

    private List<List<Product>> products;

    public List<List<Product>> getProducts() {
        return products;
    }

    public void setProducts(List<List<Product>> products) {
        this.products = products;
    }
}
