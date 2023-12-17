package puzzle5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Puzzle5 {

    /**
     * /**
     *
     * @param file assume valid
     * @return list of rows
     */
    public static GardenMap readTokens(File file) throws FileNotFoundException {
        GardenMap gardenMap = GardenMap.create();

        BiConsumer<String, String> srcDstfunc = (a, b) -> {
            throw new IllegalArgumentException();
        };

        Scanner in = new Scanner(file);

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            //set parsing mode
            if (line.contains("seeds:")) {
                int colonIdx = line.indexOf(':');
                String[] seeds = line.substring(colonIdx + 2).split(" ");
                Arrays.stream(seeds)
                        .map(String::trim)
                        .forEach(seedStr -> gardenMap.seeds().add(seedStr));

                continue;
            } else if (line.contains("seed-to-soil")) {
                srcDstfunc = (src, dst) -> gardenMap.seedToSoil().put(src, dst);
                continue;
            } else if (line.contains("soil-to-fertilizer")) {
                srcDstfunc = (src, dst) -> gardenMap.soilToFertilizer().put(src, dst);
                continue;
            } else if (line.contains("fertilizer-to-water")) {
                srcDstfunc = (src, dst) -> gardenMap.fertilizerToWater().put(src, dst);
                continue;
            } else if (line.contains("water-to-light")) {
                srcDstfunc = (src, dst) -> gardenMap.waterToLight().put(src, dst);
                continue;
            } else if (line.contains("light-to-temperature")) {
                srcDstfunc = (src, dst) -> gardenMap.lightToTemperature().put(src, dst);
                continue;
            } else if (line.contains("temperature-to-humidity")) {
                srcDstfunc = (src, dst) -> gardenMap.temperatureToHumidity().put(src, dst);
                continue;
            } else if (line.contains("humidity-to-location")) {
                srcDstfunc = (src, dst) -> gardenMap.humidityToLocation().put(src, dst);
                continue;
            }

            //parse (destination, source, amount)
            String[] values = line.split(" ");
            String destination = values[0].trim();
            String source = values[1].trim();
            int amount = Integer.parseInt(values[2]);

            var tempFunc = srcDstfunc; //to stop java from being goofy
            IntStream.range(0, amount)
                    .forEach(amt -> tempFunc.accept(source + amt, destination + amt));
        }

        return gardenMap;
    }

}
