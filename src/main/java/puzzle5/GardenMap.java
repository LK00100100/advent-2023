package puzzle5;

import java.util.*;

public record GardenMap(
        Set<Long> seeds,
        List<SeedRange> seedRange,
        List<SrcDstAmount> seedToSoil,
        List<SrcDstAmount> soilToFertilizer,
        List<SrcDstAmount> fertilizerToWater,
        List<SrcDstAmount> waterToLight,
        List<SrcDstAmount> lightToTemperature,
        List<SrcDstAmount> temperatureToHumidity,
        List<SrcDstAmount> humidityToLocation
) {

    public static GardenMap create() {
        return new GardenMap(new HashSet<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());
    }

    //thought, could use some sort of interval tree...
    public long getSeedToLocation(long seed) {
        var toSoil = getOrDefault(seedToSoil, seed);
        var toFertilizer = getOrDefault(soilToFertilizer, toSoil);
        var toWater = getOrDefault(fertilizerToWater, toFertilizer);
        var toLight = getOrDefault(waterToLight, toWater);
        var toTemperature = getOrDefault(lightToTemperature, toLight);
        var toHumidity = getOrDefault(temperatureToHumidity, toTemperature);

        return getOrDefault(humidityToLocation, toHumidity);
    }

    /**
     * defaults to src
     * @param records -
     * @param source -
     * @return -
     */
    private long getOrDefault(List<SrcDstAmount> records, long source) {
        for(var record : records) {
            if(source < record.source() || source >= record.source() + record.amount()) {
                continue;
            }

            long diffSource = source - record.source();

            return record.destination() + diffSource;
        }

        return source;
    }
}
