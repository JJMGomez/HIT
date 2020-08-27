package CoffeeShop;

import com.sun.jndi.ldap.Ber;

import javax.swing.*;
import java.util.Scanner;

public class CoffeShopTest {

    public static final int COFFEETYPE = 3;
    public static final int SIZETYPE = 3;
    public static final int CARAMELTYPE = 2;

    public static Scanner scanner = new Scanner(System.in);


    public static Beverage ChooseCoffee(int coffeeIndex){
        switch (coffeeIndex) {
            case 1:
                return new Espresso();
            case 2:
                return new Latte();
            case 3:
                return new Frappuccino();
            default:
                return null;
        }
    }

    public static void ChooseCoffeeSize(Beverage beverage,int coffeeIndex){
        switch (coffeeIndex) {
            case 1:
                beverage.setSize(Size.Tall);
                break;
            case 2:
                beverage.setSize(Size.Grande);
                break;
            case 3:
                beverage.setSize(Size.Venti);
                break;
        }
    }

    //验证输入：判断是否为数字，判断输入的数字范围是否正确
    public static int InputCheck(int num){
        int output;
        if (scanner.hasNextInt()) {
            output = Integer.parseInt(scanner.next());
            if (output >= 0 && output<=num){
                return output;
            }
        }
        System.out.println("请输入正确数字！");
        return -1;
    }

    public static Beverage ChooseDecorate(Beverage beverage, int decorateIndex, int num){
        while (num>0){
            switch (decorateIndex) {
                case 1:
                    beverage = new Milk(beverage);
                    num --;
                    break;
                case 2:
                    beverage = new Caramel(beverage);
                    num --;
                    break;
                default:
                    num --;
                    break ;
            }
        }
        return beverage;
    }

    public static void main(String[] args) {

        double totalCost = 0.0;
        int totalNum = 0;
        boolean flag = true;
        while (flag) {

            System.out.println("—————————————————————————");
            System.out.println("欢迎点单～");
            System.out.println("请选择咖啡：");
            System.out.println("1. 浓缩（30）    2. 拿铁（31）   3.星冰乐（32）  （输入其他内容退出点单，并计算总价） ");

            //输入咖啡编号并验证
            int coffeeIndex = InputCheck(COFFEETYPE);
            if (coffeeIndex == -1) {
                flag = false;
                break;
            }
            //根据咖啡编号选择咖啡
            Beverage coffee = ChooseCoffee(coffeeIndex);
            if (coffee == null) {
                flag = false;
                break;
            }

            System.out.println("请选择杯型：");
            System.out.println("1. 中杯    2. 大杯（+2）   3.超大杯（+4）  （默认大杯）");

            //输入杯型编号并验证
            int size = InputCheck(SIZETYPE);
            if (size == -1) {
                flag = false;
                break;
            }
            //选择杯型
            ChooseCoffeeSize(coffee, size);

            boolean flag2 = true;
            while (flag2) {
                System.out.println("请选择加料：");
                System.out.println("1. 牛奶（+3）    2. 焦糖（+2）   3.不想加了   （可以加多次，默认不加）");
                int condimentIndex = InputCheck(CARAMELTYPE);

                if (condimentIndex != -1) {

                    System.out.println("请输入加料份数：");
                    int condimentNum;

                    if (scanner.hasNextInt()) {
                        condimentNum = Integer.parseInt(scanner.next());
                    } else {
                        System.out.println("请输入正确数字！");
                        break;
                    }

                    coffee = ChooseDecorate(coffee, condimentIndex, condimentNum);


                } else {
                    flag2 = false;
                    break;
                }

            }
            System.out.println("您点了 " + coffee.getSize() + coffee.getDescription() + " 价格是：" + coffee.cost());
            totalCost += coffee.cost();
            totalNum++;



        }
        System.out.println("您一共点了 " + totalNum + " 杯，总价： " + totalCost);

    }

}
