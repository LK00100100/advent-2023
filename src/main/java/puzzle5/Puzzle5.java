package puzzle5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.LongStream;

public class Puzzle5 {

    public static GardenMap readTokens(File file) throws FileNotFoundException {
        return readTokens(file, false);
    }

    /**
     * part2Mode parses seeds for part2
     *
     * @param file assume valid
     * @return list of rows
     */
    public static GardenMap readTokens(File file, boolean part2Mode) throws FileNotFoundException {
        GardenMap gardenMap = GardenMap.create();

        Consumer<SrcDstAmount> srcDstfunc = (a) -> {
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
                if(part2Mode) {
                    int colonIdx = line.indexOf(':');
                    String[] seeds = line.substring(colonIdx + 2).split(" ");

                    //seed + range
                    for(int i = 0; i < seeds.length; i += 2) {
                        long seed = Long.parseLong(seeds[i]);
                        long range = Long.parseLong(seeds[i + 1]);

                        gardenMap.seedRange().add(new SeedRange(seed, range));
                    }

                    continue;
                }

                int colonIdx = line.indexOf(':');
                String[] seeds = line.substring(colonIdx + 2).split(" ");
                Arrays.stream(seeds)
                        .map(Long::parseLong)
                        .forEach(seedStr -> gardenMap.seeds().add(seedStr));

                continue;
            } else if (line.contains("seed-to-soil")) {
                srcDstfunc = (rec) -> gardenMap.seedToSoil().add(rec);
                continue;
            } else if (line.contains("soil-to-fertilizer")) {
                srcDstfunc = (rec) -> gardenMap.soilToFertilizer().add(rec);
                continue;
            } else if (line.contains("fertilizer-to-water")) {
                srcDstfunc = (rec) -> gardenMap.fertilizerToWater().add(rec);
                continue;
            } else if (line.contains("water-to-light")) {
                srcDstfunc = (rec) -> gardenMap.waterToLight().add(rec);
                continue;
            } else if (line.contains("light-to-temperature")) {
                srcDstfunc = (rec) -> gardenMap.lightToTemperature().add(rec);
                continue;
            } else if (line.contains("temperature-to-humidity")) {
                srcDstfunc = (rec) -> gardenMap.temperatureToHumidity().add(rec);
                continue;
            } else if (line.contains("humidity-to-location")) {
                srcDstfunc = (rec) -> gardenMap.humidityToLocation().add(rec);
                continue;
            }

            //parse (destination, source, amount)
            String[] values = line.split(" ");
            long destination = Long.parseLong(values[0]);
            long source = Long.parseLong(values[1]);
            long amount = Long.parseLong(values[2]);

            srcDstfunc.accept(new SrcDstAmount(source, destination, amount));
        }

        return gardenMap;
    }

}
