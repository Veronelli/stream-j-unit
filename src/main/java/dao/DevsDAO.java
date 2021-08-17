package dao;

import entity.Dev;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DevsDAO {

    private final PrintStream console = new PrintStream(System.out);
    
    //Imprime todos los devs
    public void forEach(List<Dev> devs) {
        console.println("//Method .forEach():");
        devs.forEach(System.out::println);
    }

    //Le da un año a todos
    public List<Dev> map(List<Dev> devs) {
        console.println("//Method .map():");
        return devs.stream().map(dev -> {
            Dev obj = new Dev();
            obj.setAge(dev.getYear() + 1);
            obj.setName(dev.getName());
            return obj;
        }).collect(Collectors.toList());

    }

    //Busca a todas las personas con la edad X
    public List<Dev> filter(List<Dev> devs, int age) {
        console.println("//Method .filter()");
        return devs.stream().filter(dev -> dev.getYear() == age).collect(Collectors.toList());

    }

    //Limita a la lista a la mitad de Devs
    public List<Dev> limit(List<Dev> devs) {
        console.println("//Method .limit()");
        Long halfDevs = devs.size() / 2L;
        return devs.stream().limit(halfDevs).collect(Collectors.toList());

    }

    //Ordena a lista de menor a mayor
    public List<Dev> stored(List<Dev> devs) {
        console.println("//Method .sorted()");
        return devs.stream().sorted(Comparator.comparingInt(Dev::getYear)).collect(Collectors.toList());

    }

    //Devs mayores de 30 años
    public long parallelStream(List<Dev> devs) {
        console.println("//Method .parallelStrem()");
        return devs.parallelStream().filter(dev -> dev.getYear() > 30).count();

    }

    //Obtiene una estadistica de la edad min, max, average, count
    public IntSummaryStatistics summaryStatistics(List<Dev> devs) {
        console.println("//Method .statistics");
        return devs.stream().mapToInt(dev -> dev.getYear()).summaryStatistics();

    }

    //Busca si el X aparece en toda la lista
    public boolean allMatch(List<Dev> devs, String name) {
        console.println("//Method .allMatch()");
        return devs.stream().allMatch(dev -> name.equals(dev.getName()));

    }

    //Busca que ninguno coincide, si coincide devolverá false
    public boolean noneMatch(List<Dev> devs, String name) {
        console.println("//Method .noneMatch()");
        return devs.stream().noneMatch(dev -> name.equals(dev.getName()));

    }

    //Busca que alguno coincida, sí es así devolverá True
    public boolean anyMatch(List<Dev> devs,String name) {
        console.println("//Method .anyMatch()");
        return devs.stream().anyMatch(dev -> name.equals(dev.getName()));

    }

    //Se encarga de quitar los elementos que se repiten en una lista
    public List<Integer> distinct(List<Integer> nums) {
        console.println("//Method .distinct()");
        return nums.stream().distinct().collect(Collectors.toList());

    }

    //Busca aleatoriamente un elemento
    public Dev findAny(List<Dev> devs) {
        console.println("//Method .findAny()");
        Optional<Dev> dev = devs.stream().parallel().findAny();
        return DevsDAO.optionalToDev(dev);
        
    }

    //Busca al primer elemento en una lista
    public Dev findFirst(List<Dev> devs) {
        console.println("//Method .findFirst()");
        Optional<Dev> dev = devs.stream().findFirst();
        return DevsDAO.optionalToDev(dev);
    }

    //Se encarga de guardar el dev con mayor logitud en su nombre
    public Dev reduce(List<Dev> devs) {
        console.println("//Method .reduce()");
        Optional<Dev> dev = devs.stream().reduce((dev1, dev2)
                -> dev1.getName().length() > dev2.getName().length()
                ? dev1 : dev2);

        return DevsDAO.optionalToDev(dev);

    }

    //Devuelve el Dev mas joven
    public Dev min(List<Dev> devs) {
        console.println("//Method .min()");

        //Integer var = list.stream().min(Integer::compare).get();
        //Optional<Dev> maxDev = this.devs.stream().max((dev1,dev2)->dev1.getYear().compareTo(dev2.getYear()));
        Optional<Dev> dev = devs.stream().min(Comparator.comparingInt(Dev::getYear));
        return DevsDAO.optionalToDev(dev);
    }

    //Devuelve el Dev mas viejo
    public Dev max(List<Dev> devs) {
        console.println("//Method .max()");
        Optional<Dev> dev = devs.stream().max(Comparator.comparingInt(Dev::getYear));
        return DevsDAO.optionalToDev(dev);

    }

    public static Dev optionalToDev(Optional<Dev> dev) {
        if (dev.isPresent()) {
            return dev.get();

        }
        return null;
    }
}
