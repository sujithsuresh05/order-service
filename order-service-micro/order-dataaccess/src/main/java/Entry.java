

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Entry {
     enum DAYS {
        SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7);

        int dayValue ;
        DAYS(int dayValue) {
            this.dayValue = dayValue;
        }
    };

    public static  String getDay(int actualValue) {
        TestInner test = new TestInner();
        TestInner.hai();
        return Arrays.stream(DAYS.values()).filter(day -> day.dayValue == actualValue).findFirst().get().toString();
    }

     private static class TestInner {
        public  TestInner(){

        }
        public static void hai() {

        }
    }

    public void main(String args[] ) {

        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat indiaFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        NumberFormat chinaFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat franceFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        int xx= 5;
        int y = xx++;
        int z= ++y;
        System.out.println(z);
       // double a = 10/-0;
        //
        //
        //
        //
        // Test2.test = "fdfd";
        Calendar cl = Calendar.getInstance();
        cl.set(2015, 8, 05);
        //System.out.println(cl.get(Calendar.DAY_OF_WEEK));
        String abc = "";
        String cat = "cwat";

        String s = "";
        s.isEmpty();
        // Write your code here.
        String[] token = s.trim().split("[^a-zA-Z0-9]+");
        System.out.println(token.length);
        for(String tkn: token) {
            System.out.println(tkn);
        }

        Function<List<Integer>, Integer> function = x ->x.stream().map(i-> i*2).mapToInt(i -> i).distinct().sum();
        Function<Integer, Integer> function1 = x -> x*10;
        Function<Integer, Integer> function2 = x -> x*100;

        int len = function.andThen(function1).andThen(function2).apply(Arrays.asList(1,2,2));

        System.out.println(len);

        Integer[] score = {1,2 ,6, 10, 3, 5, 20, 11};
        List<Integer> bestScore = Arrays.stream(score).sorted().skip(5).collect(Collectors.toList());
        //bestScore = IntStream.of(score).sorted().boxed().skip(4).collect(Collectors.toList());
        System.out.println(bestScore);
        Integer[] scores = {1,3,2};
        Arrays.stream(scores).mapToInt(i->i*2).sorted().forEach(System.out::println);
        System.out.println("######################");
        Arrays.stream(scores).sorted().forEach(System.out::println);
        int sum = Arrays.stream(scores).mapToInt(i->i*2).sum();
        int sum1 = Arrays.stream(scores).map(i->i*2).sorted().mapToInt(i -> i).sum();
        int sum2 = Arrays.stream(scores).mapToInt(i->i).map(i -> i*2).sum();
        int sum3 = Arrays.stream(scores).mapToInt(i->i).map(i -> i*2).reduce(0, Integer::sum);
        int sum4 = (int)Arrays.stream(scores).mapToInt(i-> i).map(i -> i*2).summaryStatistics().getSum();
        int sum5 = Arrays.stream(scores).map(i->i*2).reduce(0, (c,v) -> c+v);
        System.out.printf("%d %d %d %d %d %d \n", sum, sum1, sum2, sum3, sum4, sum5);
        HashMap<String, String> mapString = new HashMap<>();
        mapString.put("h", "h");
        mapString.put("e", "h");
        mapString.clear();


        String str = "kayak";

        boolean result = IntStream.range(0, str.length()).noneMatch(i->str.charAt(i) == str.charAt(str.length()-i-1));
        boolean result1 = IntStream.range(0, str.length()/2).noneMatch(i->str.charAt(i) != str.charAt(str.length()-i-1));
        boolean result2 = Stream.iterate(0, i-> i+1).limit(str.length()).anyMatch(i->str.charAt(i) == str.charAt(str.length() -1-i));
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);

        System.out.println(mapString.size());

        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(new Weather(12.5));
        weatherList.add(new Weather(35.5));
        weatherList.add(new Weather(1.5));

        weatherList.stream().map(Weather::getTemprature).sorted().forEach(System.out::println);
        System.out.println("######################");
        //weatherList.stream().sorted(Weather::getTemprature).forEach(System.out::println);
        weatherList.stream().map(Weather::getTemprature).sorted((p1,p2) -> p2.compareTo(p1)).forEach(System.out::println);
        System.out.println("######################");
        weatherList.stream().sorted((p1,p2) -> p2.getTemprature().compareTo(p1.getTemprature())).forEach(System.out::println);
        System.out.println("######################");
        weatherList.stream().map(Weather::getTemprature).sorted((p1,p2) -> p2.compareTo(p1)).forEach(System.out::println);
        Comparator<Weather> tempComparator = (wh, wh2) -> {

            return 0;
        };


        PriorityQueue<Weather> heap = new PriorityQueue<>(tempComparator);

    }
}
