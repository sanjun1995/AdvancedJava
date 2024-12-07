package com.example.demo.generics.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caozhixin
 * @date 2024/12/8 00:12
 */
public class Main {
    public static void main(String[] args) {
//        Fruit fruit = new Apple();

//        List<Fruit> fruits = new ArrayList<Apple>();

//        List<Apple> appleList = new ArrayList<>();
//        appleList.add(new Apple());
//        appleList.add(new Apple());
//
//        List<? extends Fruit> fruits = appleList;
//        Fruit fruit = fruits.get(0);

//        List<Apple> appleList = new ArrayList<>();
//        appleList.add(new Apple());
//        List<? extends Fruit> fruits = appleList;
//        fruits.add(new Fruit());

//        List<? extends Fruit> plate = Arrays.asList(new Apple(), new Banana());
//
//        Fruit apple = plate.get(0);
//        Fruit banana = plate.get(1);

//        List<? super Apple> plate = new ArrayList<>();
//
//        plate.add(new Apple());
//        plate.add(new RedApple());
//
//        Object apple = plate.get(0);

//        MyStack<Fruit> myStack = new MyStack<>();
//
//        List<Apple> appleList = new ArrayList<>();
//        appleList.add(new Apple());
//        appleList.add(new Apple());
//
//        myStack.pushAll2(appleList);
//        for (Fruit fruit : myStack) {
//            System.out.println(fruit);
//        }

        MyStack<Fruit> myStack = new MyStack<>();

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple());
        appleList.add(new Apple());

        myStack.pushAll2(appleList);

        List<Food> testPopAll = new ArrayList<>();
        myStack.popAll2(testPopAll);
        testPopAll.forEach(System.out::println);
    }
}
