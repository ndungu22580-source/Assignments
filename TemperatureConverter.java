import java.util.Scanner;
public class TemperatureConverter{

public static double ConvertToCelcius(double fahrenheit){

  //We use 5.0 and 9.0 to ensure floating-point deviation
  return (fahrenheit-32)*5.0/9.0;
}
  public static void main(String[] args){
    double input=98.6;
    double result=ConvertToCelcius(input);

  System.out.println("fahrenheit"+ input+ "F");
    System.out.println("Celcius:%.1f",result);
    
