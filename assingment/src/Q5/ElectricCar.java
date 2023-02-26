package Q5;

import java.util.ArrayList;

public class ElectricCar {

    /**
     * Calculates the number of times the car's battery needs to be replaced
     * based on the given service center locations, target miles, and start charge capacity
     */
    public int getNumBatteryReplacements(int[][] serviceCenters, int targetMiles, int startCapacity) {
        int count = 0;
        int currentMiles = startCapacity;
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> capacities = new ArrayList<>();

        // Extract distances and capacities from the service centers array
        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        // Calculate the number of battery replacements needed
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) > currentMiles) {
                currentMiles = capacities.get(i - 1);
                count++;
            }
        }

        if (currentMiles < targetMiles) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int [][] serviceCenters = {{10,60},{20,30},{30,30},{60,40}};
        ElectricCar car = new ElectricCar();
        int batteryReplacements = car.getNumBatteryReplacements(serviceCenters, 100, 10);
        System.out.println("The car's batteries need to be replaced " + batteryReplacements + " times.");
    }
}

