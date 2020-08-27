package CoffeeShop;

import com.sun.jndi.ldap.Ber;

import javax.swing.*;
import java.util.Scanner;

public class CoffeShopTest {
    public static void main(String[] args) {
        System.out.println("欢迎点单～");
        System.out.println("请选择咖啡：");
        System.out.println("1. 浓缩    2. 拿铁   3.星冰乐  （默认拿铁）");
        Scanner scanner = new Scanner(System.in);
        int coffeeIndex = scanner.nextInt();

        Beverage coffee;
        switch (coffeeIndex){
            case 1:
                coffee = new Espresso();
                break;
            case 2:
                coffee = new Latte();
                break;
            case 3:
                coffee = new Frappuccino();
                break;
            default:
                coffee = new Latte(); //默认拿铁
        }

        System.out.println("请选择杯型：");
        System.out.println("1. 中杯    2. 大杯   3.超大杯  （默认大杯）");

        int size = scanner.nextInt();
        switch (size){
            case 1:
                coffee.setSize(Size.Tall);
                break;
            case 2:
                coffee.setSize(Size.Grande);
                break;
            case 3:
                coffee.setSize(Size.Venti);
                break;
        }


        System.out.println("请选择加料：");
        System.out.println("1. 牛奶    2. 焦糖  （默认不加）");
        int CondimentIndex = scanner.nextInt();

        Beverage coffeeWithCondiment;
        switch (CondimentIndex){
            case 1:
                coffeeWithCondiment = new Milk(coffee);
                break;
            case 2:
                coffeeWithCondiment = new Caramel(coffee);
                break;
            default:
                coffeeWithCondiment = coffee;
        }



        System.out.println(coffeeWithCondiment.getDescription() + " 价格是："+ coffeeWithCondiment.cost());
    }
}
