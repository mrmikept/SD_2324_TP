import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class WarehouseEx1 {
    private Map<String, Product> map =  new HashMap<String, Product>();
    private ReentrantLock l = new ReentrantLock();

    private class Product
    {
        int quantity = 0;
        Condition isEmpty = l.newCondition();
    }

    private Product get(String item)
    {
        Product p = map.get(item);
        if (p != null) return p;
        p = new Product();
        map.put(item, p);
        return p;
    }

    public void supply(String item, int quantity)
    {
        l.lock();
        try
        {
            Product p = get(item);
            System.out.println("Supplying " + quantity + " of item " + item);
            p.quantity += quantity;
            p.isEmpty.notifyAll();
        } finally {
            l.unlock();
        }
    }

    // Errado se faltar algum produto...
    public void consume(Set<String> items)
    {
        l.lock();
        try
        {
            for (String s : items)
            {
                Product p = get(s);
                while (p.quantity == 0)
                {
                    System.out.println("Awaiting for " + s);
                    p.isEmpty.await();
                }
                System.out.println("Consuming " + s);
                p.quantity--;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            l.unlock();
        }

    }

}
