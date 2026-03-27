package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

        // DONE: Find the BigMac entry for Canada in the year 2022

//        bigMacs.stream().filter(b -> b.country().equals("Canada") && b.year() == 2022 ).forEach(b -> IO.println(b)); ;

        /* To explain:
        1. Start with bigMacs.stream()
        2. Filter each item; to where country is equal to Canada and year is equal to 2022. We don't use .equals() for ints because they're primitive.
        3. From there we can (optionally) run a print on each to verify it works.
        */

        // DONE: Create a list containing only the data for Canada

//        List<BigMac> canadaOnlyList = bigMacs.stream().filter(b -> b.country().equals("Canada")).toList(); ;
//        canadaOnlyList.forEach(b -> IO.println(b) );

        /* To explain:
        1. New list of BigMac's.
        2. To initialize that list we start with bigMacs.stream()
        3. Filter each item; to where country is equal to Canada.
        4. Convert that stream to list.
        5. From there we can (optionally) run a print on each to verify it works.
        */

        // DONE: Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD" There must be no duplicates, and the items must be sorted alphabetically

//        List<String> l = bigMacs.stream().map(b -> b.country() + " : " + b.currency() ).distinct().sorted().toList();
//        l.forEach(IO::println);

//        bigMacs.stream().distinct().forEach(b -> IO.println(b.toString()));

        /* To explain:
        1. New list of String's, call it l.
        2. To initialize l we start with bigMacs.stream()
        3. Map each item, we're modifying it to have a string in format <country> : <currency> .
        4. Make the items distinct, no duplicates.
        5. Sort them alphabetically.
        6. Convert that to a list of strings.
        7. From there we can (optionally) run a print on each to verify it works.
        */

        // DONE: Print the most recent 5 years of data for Canada

//        bigMacs.stream().filter(b -> b.year() >= 2018 && b.country().equals("Canada") ).forEach(IO::println);

        /* To explain:
            1. Start with the stream.
            2. Filter it to where the year is >= 2018 (recent 5 years) and country is Canada.
            3. Print all.
        */

        // DONE: Print the data for countries with a 2022 BigMac price less than $2 USD

//        bigMacs.stream().filter(b -> b.year() == 2022).filter(b -> b.usdPrice() < 2.00).forEach(IO::println);

        /* To explain:
            1. Start with bigMacs.stream()
            2. Filter that to where year is 2022.
            3. Filter by price, less than 2 USD.
            4. Print for each.
        */

        // DONE: Calculate the average USD price of BigMacs in 2022 over all countries

//        double avgPrice = bigMacs.stream().filter(b -> b.year() == 2022).mapToDouble(b -> b.usdPrice()).average().getAsDouble() ;
//        IO.println(avgPrice);

        /* To explain:
        1. New double.
        2. Initialize that double, start with bigMacs.stream()
        3. Filter each item; to where year is 2022.
        4. Map that to a doubleStream. Thankfully Java has that type; a stream of double's.
        5. Get the average of all those.
        6. Since that returns an optional double, we must use the getAsDouble method to get a concrete value.
        7. From there we can (optionally) run a print on each to verify it works.
        */

    }


    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
//            return lines.map(line -> {return parseCsvLine(line);} ).toList();
            return lines.map(Main::parseCsvLine).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse a CSV line into a BigMac record. The expected format is:
     * year,currency,country,localPrice,exchangeRate,usdPrice
     * @param line the CSV line to parse
     * @return a BigMac record containing the data from the CSV line
     */
    public static BigMac parseCsvLine(String line) {
        var values = line.split(",");
        return new BigMac(
                Integer.parseInt(values[0].substring(0,4)),
                values[1],
                values[2],
                Double.parseDouble(values[3]),
                Double.parseDouble(values[4]),
                Double.parseDouble(values[5]));
    }
}
