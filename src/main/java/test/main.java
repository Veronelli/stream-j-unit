/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.DevsDAO;
import entity.Dev;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author facu
 */
public class main {

    static public void main(String args[]) {
        PrintStream console = new PrintStream(System.out);

        List<Dev> devs = new ArrayList<>();
        List<Integer> nums = Arrays.asList(3, 6, 12, 65, 12, 76, 23, 65, 98, 23, 12, 3, 100, 125); 
        
        devs.add(new Dev("Lucho", 32));
        devs.add(new Dev("Santi", 43));
        devs.add(new Dev("Guss", 47));
        devs.add(new Dev("Garo", 23));
        devs.add(new Dev("Cris", 65));
        devs.add(new Dev("Mati", 12));
        devs.add(new Dev("Nahue", 41));
        devs.add(new Dev("Naza", 32));
        devs.add(new Dev("Iara", 42));
        devs.add(new Dev("Facu", 52));

        DevsDAO devDao = new DevsDAO();
        
        console.println(devs);
        
        devDao.forEach(devs);
        console.println("\n");

        devDao.map(devs).forEach(System.out::println);
        console.println("\n");

        devDao.filter(devs, 23).forEach(System.out::println);
        console.println("\n");

        devDao.limit(devs).forEach(System.out::println);
        console.println("\n");

        devDao.stored(devs).forEach(System.out::println);
        console.println("\n");

        console.println(devDao.parallelStream(devs));
        console.println("\n");

        console.println(devDao.summaryStatistics(devs));
        console.println("\n");

        console.println(devDao.allMatch(devs,"Facu"));
        console.println("\n");

        console.println(devDao.anyMatch(devs,"Santi"));
        console.println("\n");

        devDao.distinct(nums).forEach(System.out::println);
        console.println("\n");

        console.println(devDao.findAny(devs));
        console.println("\n");

        console.println(devDao.findFirst(devs));
        console.println("\n");
        
        console.println(devDao.noneMatch(devs,"Guss"));
        console.println("\n");
        
        console.println(devDao.reduce(devs));
        console.println("\n");
        
        console.println(devDao.min(devs));
        console.println("\n");
        
        console.println(devDao.max(devs));
        console.println("\n");
        
    }
}
