/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.time.LocalDate;
/**
 *
 * @author carlconrad
 */
abstract class User{
   int userId;
   String userName;
   String email;
   User(int userId,String userName,String email){
    this.userId = userId;
    this.userName =userName;
    this.email= email;
   } 
     void LogIn(){
           System.out.println("\nWelcome: "+userId +" "+ userName +" "+email +"\n" );
     };
    void LogOut(){};
}
interface CustomerMethods{
    void viewOrderHistory(Order orderHistory);
    void placeOrder(Admin prods);  
}

interface AdminMethods{
 void madeProduct();
 void addProduct();
 void printProduct();
 void removeProduct();
 void manageInventory(Admin product);
}

 class Customer  extends User implements CustomerMethods{
  
    int customerId;
    String address;
    Order order;
    String user,email;
    int id;
   
    public Customer(int userId,String userName,String email,int customerId,String address) {
        super( userId,userName, email);    
        user = userName; this.email = email;id=userId; 
        this.customerId = customerId;
        this.address= address;       
         order = new Order();
     }   
        // override the abstract     
    // public  void logI(){
    //     System.out.println("\nWelcome: "+id +" "+ user +" "+email +"\n" );
    //   }
    //  public void logO(){}


     public void placeOrder(Admin prods){ 
        prods.printProduct();        
    }
   public void viewOrderHistory(Order orderHistory){
      LocalDate currentDate = LocalDate.now();
      System.out.println("\n\tOrder History\n");        
      for (Map.Entry<Integer, List<Product>> entry : orderHistory.hash.entrySet()) {
                int key = entry.getKey();
                List<Product> productList = entry.getValue();
                System.out.println("Customer Id: "+  userId  +"\nOrder id: " + key + " \nDate: " + currentDate );
                int total = 0;
                for (Product product : productList) {
                    System.out.println("Id: " + product.productId + " Name: " + product.name + " price: " + product.price);
                    total+=product.price;
                }
                System.out.println("Total: "+total + "\n");
      }    
    }
}
 class Admin extends User implements AdminMethods{
     int adminId;
     String department;
     String user,email;
     int id;
     List<Product> products = new ArrayList<>();
     Scanner sc = new Scanner(System.in);
              
     Admin(int userId,String userName,String email,int adminId,String department) {
            super(userId,userName,email);
             user = userName; this.email = email;id=userId;
            this.adminId = adminId;
            this.department = department;          
        } 
        // override the abstract       
        // void LogIn(){
        //     System.out.println("\n Admin 2Welcome: "+id +" "+ user +" "+email +"\n" );
        // }
        // void LogOut(){}

  public void madeProduct(){
            Product prod1 = new Product(1,"iPhone 15",30,50);
            Product prod2 = new Product(2,"Air Force 1",20,50);
            Product prod3 = new Product(3,"Apple Watch",10,50); 
            products.add(prod1);
            products.add(prod2);
            products.add(prod3);        
      } 
    Product makeProduct() {
        System.out.print("Enter Product Id: ");
        int prodId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String prodName = sc.nextLine();
        System.out.print("Enter Price: ");
        int prodPrice = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Stock Quantity: ");
        int stockQty = sc.nextInt();
        return new Product(prodId, prodName, prodPrice, stockQty);      
    }  
    public void addProduct(){
      products.add(makeProduct());
    }        
    public void printProduct(){       
           for(Product prod : products){
            System.out.println("[id: "+prod.productId +", Name: "+prod.name+", Price: "+ prod.price + "., Quantity: "+ prod.stockQty);
          }          
      }  
    public void removeProduct(){
        System.out.println("Enter id to remove:");
        int x = sc.nextInt();       
        for(Product prod : products){
           if(prod.productId == x){
               products.remove(x);          
               return;
           }
        }
    }
    public void manageInventory(Admin product){
      Product manageInven =  new Product();
      System.out.println("\n 1,Update Price \n2, Update Stock : ");
      int ans = sc.nextInt();
      product.printProduct();
      if(ans == 1)
          manageInven.updatePrice(product);
      else if(ans == 2)
          manageInven.updateStock(product);
    }
 }
class Product{
      int productId;
      String name;
      int price;
      int stockQty;
      Scanner sc = new Scanner(System.in);
      Product(){}
      Product(int productId,String name,int price,int stockQty){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQty = stockQty;    
      }
      void updatePrice(Admin product){
          System.out.println("Enter id: ");
          int x = sc.nextInt();
          for(Product prods : product.products){
            if(prods.productId == x){
               System.out.println("Enter new price: ");
               int newPrice = sc.nextInt();
               prods.price = newPrice;
               break;
            }
          }
      }
      void updateStock(Admin product){
          System.out.println("Enter id: ");
          int x = sc.nextInt();
          for(Product prods : product.products){
            if (prods.productId == x) {
                System.out.println("Enter amount: ");
                int newQty = sc.nextInt();
                prods.stockQty = newQty;   
                break; 
            }
                
          }
      }     
}

class Order{
     
      int orderId;
      int customerId;  
      int orderDate;
      int prodID;
      String name;
      int price;  
 
      List<Product> orderr = new ArrayList<>();     
      HashMap<Integer,List<Product>> hash = new HashMap<>();   
      Scanner sc = new Scanner(System.in);
        
        Order(){}
        Order(int orderId,int customerId,int prodId, String name,int price){
            this.orderId = orderId;
            this.customerId =customerId;     
            this.prodID = prodId;
            this.name = name;
            this.price = price;
        }    
       void addProductToOrder(Admin produ){
            List<Product> pro = produ.products;
            System.out.print("Enter id to add order: ");
            int id = sc.nextInt();
            for(Product prod: pro){
                if(prod.productId == id){                
                    orderr.add(prod);
                    prod.stockQty--;
                    break;
                }
            }
        }          
        int idOrder = 229101;
        void confirmOrder(){      
                 hash.put(idOrder,new ArrayList<>(orderr));
                 idOrder++;      
                 orderr.clear();
                 System.out.println("\n<<<Your order has been successfully placed>>>\n");
             }          
        int calculateTotalAmount(){
             int total =0;
              for (Product ordr : orderr) {
               total+=ordr.price;
            }
              return total;
         }       
       void printOrder() {
                if (orderr.isEmpty()) {
                    System.out.println("No orders placed.");
                    return;
                }
                System.out.println("\nOrders: ");            
                for (Product ordr : orderr) {
                    System.out.println("{" + "id: " + ordr.productId + "\t name: " + ordr.name + "\t price: " + ordr.price + "}");            
                }
                System.out.println("Total: " + calculateTotalAmount() + "\n");
               }
}
public class RetailStore {
       public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin(1, "AdminUser", "", 12345, "Computer Science");
        admin.madeProduct();
        Order order = new Order();
           
        boolean exec = true;     
        
        do{
               System.out.println("Login as: \n  1)Customer \n  2)Admin \n  3,Exit: ");
               int as = sc.nextInt();
               
            switch (as) {
                case 1:
                    {
                        System.out.print("\nEnter Username: ");
                        String userName = sc.nextLine();
                        userName =sc.nextLine();
                        System.out.print("Enter User Email: ");
                        String userEmail = sc.nextLine();
                        System.out.print("Enter UserId: ");
                        int userId = sc.nextInt();
                        Customer p = new Customer(userId,userName,userEmail,0,"CS");
                        p.LogIn();
                        boolean con = true;
                        do{
                            System.out.println("[[[Main Menu]]] \n 1,Placeorder \n 2,ViewOrderHistory \n 3,Exit ");
                            int choice = sc.nextInt();
                            sc.nextLine();
                            
                            switch(choice){
                                case 1:
                                    System.out.println("\n|| Select Products: ||");
                                    boolean run = true;
                                    do{
                                        p.placeOrder(admin);
                                        order.addProductToOrder(admin);
                                        System.out.print("Add more? (y/n)");
                                        String ans = sc.nextLine();
                                        if(ans.equals("n")){
                                            order.printOrder();
                                            System.out.println("Do you want to confirm your order? (y/n):");
                                            String confirm =  sc.nextLine();
                                            if(confirm.equals("y")){
                                                order.confirmOrder();
                                            }
                                            run = false;
                                        }
                                    }while(run);
                                    break;
                                case 2:
                                    p.viewOrderHistory(order);
                                    break;
                                case 3 :
                                    p.LogOut();
                                    con = false;
                            }
                        }while(con);
                        break;
                    }
                case 2:
                    {
                        System.out.print("\nEnter Username: ");
                        String userName = sc.nextLine();
                        userName= sc.nextLine();
                        System.out.print("Enter User Email: ");
                        String userEmail = sc.nextLine();
                        System.out.print("Enter UserId: ");
                        int userId = sc.nextInt();
                        Admin p2 = new Admin(userId,userName,userEmail,0,"CS");
                        p2.LogIn();
                        boolean runAdmin = true;
                        do{
                            System.out.println("[[[Main Menu]] \n 1,Add Product \n 2,Remove Product \n 3,Manage Inventory\n 4,Exit");
                            int adminChoice= sc.nextInt();
                            
                            switch(adminChoice){
                                
                                case 1:
                                    admin.addProduct();
                                    System.out.println("\n <<<New Item Added>>\n");
                                    break;
                                case 2 :
                                    admin.printProduct();
                                    admin.removeProduct();
                                     System.out.println("\n <<<Item revomed Succesfully>>\n");
                                    break;
                                case 3:
                                    admin.manageInventory(admin);
                                    break;
                                case  4:
                                    admin.LogOut();
                                    runAdmin = false;
                                    break;
                            }
                        }while(runAdmin);
                        break;
                    }
                default:   
                    System.out.println("\n\tThank you for using!\n");
                    exec = false;
                    break;
            }
          }while(exec);
       }
}
