package dao;

import entity.Dev;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import static org.junit.Assert.*;
import org.junit.Test;

public class DevsDAOTest {

    @Test
    public void mapTrue() {
        var devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        assertFalse(devs.isEmpty());

    }

    @Test
    public void distinctTrue() {
        var devsDao = new DevsDAO();
        List<Integer> nums = Arrays.asList(3, 6, 12, 65, 12, 76, 23, 65, 98, 23, 12, 3, 100, 125);
        List<Integer> result = devsDao.distinct(nums);

        List<Integer> verify = Arrays.asList(3, 6, 12, 65, 76, 23, 98, 100, 125);
        assertTrue(result.equals(verify));
        System.out.println("DISTINCT TEST!!");

    }

    @Test
    public void storedTrue() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        List<Dev> devsStored = readFileAndGetList("storedTrue.json");

        List<Dev> result = devsDao.stored(devs);
        assertTrue(result.equals(devsStored));

    }

    @Test
    public void filterTrue() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        List<Dev> devsFiltred = readFileAndGetList("filtre1.json");

        List<Dev> result = devsDao.filter(devs, 65);
        assertTrue(result.equals(devsFiltred));

    }

    @Test
    public void limitTrue() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        List<Dev> devsLimited = readFileAndGetList("limit1.json");

        List<Dev> result = devsDao.limit(devs);
        assertTrue(result.equals(devsLimited));

    }

    @Test
    public void summaryStatisticsTrue() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        long count = devs.size();
        int min = devs.stream().min(Comparator.comparingInt(Dev::getYear)).get().getYear();
        int max = devs.stream().max(Comparator.comparingInt(Dev::getYear)).get().getYear();
        long sum = devs.stream().map(Dev::getYear).reduce((acum, dev) -> acum + dev).get();

        IntSummaryStatistics stts = new IntSummaryStatistics(count, min, max, sum);
        IntSummaryStatistics sttsTest = devsDao.summaryStatistics(devs);

        System.out.println(stts);
        System.out.println(sttsTest);
        assertTrue(stts.toString().equals(sttsTest.toString()));

    }

    @Test
    public void allMatch() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        boolean devsBool = devsDao.allMatch(devs, "Facu");

        assertFalse(devsBool);

    }

    @Test
    public void anyMatch() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        boolean devsBool = devsDao.anyMatch(devs, "Santi");

        assertTrue(devsBool);

    }

    @Test
    public void noneMatch() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        boolean devsBool = devsDao.noneMatch(devs, "Guss");

        assertFalse(devsBool);

    }

    @Test
    public void findAny() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        Dev dev = devsDao.findAny(devs);

        assertTrue(dev != null);

    }

    @Test
    public void findFirts() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        Dev dev = devsDao.findFirst(devs);

        assertTrue(dev.equals(devs.get(0)));

    }

    @Test
    public void reduce() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        Dev dev = devsDao.reduce(devs);

        assertTrue(dev.equals(devs.get(8)));

    }

    @Test
    public void min() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        Dev dev = devsDao.min(devs);

        assertTrue(dev.equals(devs.get(0)));

    }

    @Test
    public void max() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        Dev dev = devsDao.max(devs);

        assertTrue(dev.equals(devs.get(6)));

    }

    @Test
    public void parallelStream() {
        DevsDAO devsDao = new DevsDAO();
        List<Dev> devs = readFileAndGetList("option1.json");
        long count = devsDao.parallelStream(devs);
        
        assertEquals(count, 8L);
        
    }

    public List<Dev> readFileAndGetList(String option) {
        JSONParser json = new JSONParser();
        List<Dev> devs = new ArrayList();

        try {
            int i = 0;
            FileReader reader = new FileReader("src/json-test/" + option);
            JSONArray jsonArr = (JSONArray) json.parse(reader);

            while (i < jsonArr.size()) {
                JSONObject jsonO = (JSONObject) jsonArr.get(i++);
                String name = (String) jsonO.get("name");
                Integer age = Integer.parseInt(jsonO.get("age").toString());

                Dev dev = new Dev(name, age);
                devs.add(dev);

            }
            reader.close();
            return devs;

        } catch (Exception ex) {
            ex.printStackTrace(System.out);

        }
        return devs;
    }

}
