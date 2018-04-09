package com.neo.web;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
/**
 * Created by fx on 2017/12/25.
 */
public class demoLamda {


    public static void main(String[] args){

        List<Car> carList = Arrays.asList(new Car("ave","jeep",2011),
                new Car("aRRRRe","jeep",1990),
                new Car("CAAA","Dodge",2010),
                new Car("asdddd","Comanche",2016),
                new Car("ave","jeep",2018),
                new Car("Css","Ford",1992));

        //转换大小写
        List<String> stringList = Arrays.asList(new String("Deep"),new String("Aooe"),new String("eSdas"));
        List li = stringList.stream().map(String::toUpperCase)
                .collect(toList());
        li.stream().forEach((l1)-> System.out.print(l1+" "));


//        我得不到你  我的心  你多不清晰  你的逃离  可你在我心里 无法抹去
//                如果在遇到你  你的心  我一定会证明  你的意义

        //取出字符串大于三并且把每个字符串转换为小写
        List lll = stringList.stream().filter(e->e.length()>3)
                .peek(e-> System.out.print(e+" "))
                .map(String::toLowerCase)
                .peek(e-> System.out.println(e+" "))
                .collect(toList());
        lll.stream().forEach(l-> System.out.println(l+" "));



        System.out.println();

        //大于2000并且按照年份排序
        carList.stream().filter((car)->car.getYear()>2000)
                .sorted(Comparator.comparing(Car::getYear))
                .map(Car::getModel)
                .map(String::toUpperCase)
                .collect(toList())
                .forEach((gg)-> System.out.print(gg+" "));
        //reduce使用
        //字符拼接
        System.out.println();
        String concat = Stream.of("A","B","C","D").reduce("",String::concat);
        System.out.println(concat);

        //最小值
        Double doubleNumber = Stream.of(-1.5,-2.0,1.0,-3.0).reduce(Double.MAX_VALUE,Double::min);
        System.out.println("min："+doubleNumber);

        //和
        int sumValue = Stream.of(1,3,5,6).reduce(1,Integer::sum);
        System.out.println(sumValue);

        sumValue = Stream.of(1,3,5,6).reduce((Integer::sum)).get();
        System.out.println(sumValue);



        concat = Stream.of("a","e","w","r")
                .filter(s ->s.equals("a"))
                .reduce("",String::concat);
        System.out.println(concat);


        //循环排序并且加入数量限制
        List<Person> personList = new ArrayList<>();
        Person person = null;
        for(int i =1;i<=5;i++){
            person = new Person(i,"name"+i);
            personList.add(person);
        }

        personList.stream().limit(2).sorted((p1,p2)->p1.getName().compareTo(p2.getName()))
        .collect(toList()).forEach((p3)-> System.out.println(p3));


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/Destop/sql.txt"));
            int maxSize = bufferedReader.lines().mapToInt(String::length).max().getAsInt();
            bufferedReader.close();
            System.out.println(maxSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    }
     class Person {
        public int no;
        private String name;
        public Person (int no, String name) {
            this.no = no;
            this.name = name;
        }
        public String getName() {
            System.out.println(name);
            return name;
        }
}
