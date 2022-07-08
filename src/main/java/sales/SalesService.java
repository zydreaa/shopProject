package sales;

import java.util.ArrayList;

public class SalesService {
    private ArrayList<Sale> sales = new ArrayList<>();

    public SalesService() {
    }

    public void addSale(Sale sale){
        this.sales.add(sale);
    }

    public ArrayList<Sale> getAll(){
        return this.sales;
    }
}
