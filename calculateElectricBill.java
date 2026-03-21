import java.util.Scanner;
public class calculateElectricBill{

public static void main(String[]args){
  Scanner input= new Scanner(System.in);

  System.out.print("Enter the number of units consumed:");
  double inputs= input.nextDouble();

  //Calling the method and storing the result
  double totalBill= calculateElectricBill(units);

  System.out.println("The total bill amount is:Ksh "+totalBill);

  input.close();

  //Method to calculate bill based on tiered pricing

  public static double calculateElectricBill(double units){
    double bill=0;

  if (units<=100){
    bill= units*10;}
  else if(units<=200){
    bill=(100*10)+(units-100)*15);
  }
  else{
    bill =(100*10)+(100*15)+(units-200)*20)
      }
  return bill;
   }
}
