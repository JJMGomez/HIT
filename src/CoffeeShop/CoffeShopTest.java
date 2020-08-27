package CoffeeShop;

import com.sun.jndi.ldap.Ber;

import javax.swing.*;
import java.util.Scanner;

public class CoffeShopTest {
    public static void main(String[] args) {

        double totalCost = 0.0;
        int totalNum = 0;
        boolean flag = true;
        ok:
        while (flag) {
            System.out.println("—————————————————————————");
            System.out.println("欢迎点单～");
            System.out.println("请选择咖啡：");
            System.out.println("1. 浓缩    2. 拿铁   3.星冰乐  （输入其他内容退出点单，并计算总价） ");
            Scanner scanner = new Scanner(System.in);

            int coffeeIndex = 1;
            if (scanner.hasNextInt()) {
                coffeeIndex = Integer.parseInt(scanner.next());
            } else {
                System.out.println("请输入数字！");
                flag = false;
                break;
            }


            Beverage coffee;
            switch (coffeeIndex) {
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
                    coffee = new Frappuccino();
                    flag = false; //默认
                    break ok;

            }

            System.out.println("请选择杯型：");
            System.out.println("1. 中杯    2. 大杯   3.超大杯  （默认大杯）");

            int size;
            if (scanner.hasNextInt()) {
                size = Integer.parseInt(scanner.next());
            } else {
                System.out.println("请输入数字！");
                flag = false;
                break;
            }

            switch (size) {
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

            Beverage coffeeWithCondiment = coffee;



            System.out.println("请选择加料：");
            System.out.println("1. 牛奶    2. 焦糖   3.不想加了   （可以加多次，默认不加）");
            int condimentIndex = scanner.nextInt();
            System.out.println("请输入加料份数：");
            int condimentNum = scanner.nextInt();
            while (condimentNum>0){
                switch (condimentIndex) {
                    case 1:
                        coffeeWithCondiment = new Milk(coffeeWithCondiment);
                        condimentNum --;
                        break;
                    case 2:
                        coffeeWithCondiment = new Caramel(coffeeWithCondiment);
                        condimentNum --;
                        break;
                    default:
                        break ;
                }
            }

            System.out.println(coffeeWithCondiment.getSize() + coffeeWithCondiment.getDescription() + " 价格是：" + coffeeWithCondiment.cost());
            totalCost += coffeeWithCondiment.cost();
            totalNum ++;
        }

        System.out.println("您点了 " + totalNum + " 杯，总价： " + totalCost );
    }
}
