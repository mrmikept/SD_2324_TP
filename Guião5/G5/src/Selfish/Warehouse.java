package Selfish;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Warehouse {
    private Map<String, Product> map =  new HashMap<String, Product>();
    private ReentrantLock warehouseLock = new ReentrantLock();

    private class Product {
        Condition condition = warehouseLock.newCondition();
        int quantity = 0;
    }

    private Product get(String item) {
        this.warehouseLock.lock();
        Product p;
        try {
            p = map.get(item);
            if (p != null) return p;
        }
        finally {
            this.warehouseLock.unlock();
        }
            p = new Product();
            map.put(item, p);
            return p;

    }

    public void supply(String item, int quantity)
    {
        this.warehouseLock.lock();
        try {
            Product p = get(item);
            p.quantity += quantity;
            p.condition.signalAll();
        } finally {
            this.warehouseLock.unlock();
        }
    }

    // Errado se faltar algum produto...
    public void consume(Set<String> items) throws InterruptedException {
        this.warehouseLock.lock();
        try
        {
            for (String s : items)
            {
                Product product = get(s);
                while (product.quantity == 0)
                {
                    product.condition.await();
                }
                product.quantity--;
            }
        } finally {
            this.warehouseLock.unlock();
        }

    }

}
