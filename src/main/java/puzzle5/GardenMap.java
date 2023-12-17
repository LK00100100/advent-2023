package puzzle5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public record GardenMap(
        //source -> destination
        Set<String> seeds,
        Map<String, String> seedToSoil,
        Map<String, String> soilToFertilizer,
        Map<String, String> fertilizerToWater,
        Map<String, String> waterToLight,
        Map<String, String> lightToTemperature,
        Map<String, String> temperatureToHumidity,
        Map<String, String> humidityToLocation
) {

    public static GardenMap create() {
        return new GardenMap(new HashSet<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>());
    }

    public String getSeedToLocation(String seed) {
        var toSoil = seedToSoil.getOrDefault(seed, seed);
        var toFertilizer = soilToFertilizer.getOrDefault(toSoil, toSoil);
        var toWater = fertilizerToWater.getOrDefault(toFertilizer, toFertilizer);
        var toLight = waterToLight.getOrDefault(toWater, toWater);
        var toTemperature = lightToTemperature.getOrDefault(toLight, toLight);
        var toHumidity = temperatureToHumidity.getOrDefault(toTemperature, toTemperature);

        return humidityToLocation.getOrDefault(toHumidity, toHumidity);
    }
}
