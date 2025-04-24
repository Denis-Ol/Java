package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day2 {
    public static List<int[]> getReports(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of("advent","data", filePath))) {
            return lines.map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray()).toList();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return List.of();
        }
    }

    public static boolean isReportSafe(int[] levels) {
        boolean isIncreasing = false;
        int startDiff = levels[1] - levels[0];
        if (startDiff == 0 || Math.abs(startDiff) > 3) return false;
        else if (startDiff > 0) isIncreasing = true;

        for (int i = 2; i < levels.length; i++) {
            int diff = levels[i] - levels[i - 1];
            if (diff == 0 || Math.abs(diff) > 3 || (diff > 0 && !isIncreasing) || (diff < 0 && isIncreasing))
                return false;
        }
        return true;
    }

    public static boolean isTolaratedReportSafe(int[] levels) {
        List<int[]> listOfCandidates = getArrayFamily(levels);
        for (int[] candidateArray : listOfCandidates) {
            if (isReportSafe(candidateArray)) return true;
        }
        return false;
    }

    public static int safeCount(List<int[]> reports) {
        int count = 0;
        for (int[] report : reports) {
            if (isReportSafe(report)) count++;
        }
        return count;
    }

    public static int safeCountWithDampener(List<int[]> reports) {
        int count = 0;
        for (int[] report : reports) {
            if (isReportSafe(report)) count++;
            else if (isTolaratedReportSafe(report)) count++;
        }
        return count;
    }

    public static List<int[]> getArrayFamily(int[] levels) {
        List<int[]> listArrays = new ArrayList<>(levels.length);

        for (int i = 0; i < levels.length; i++) {
            int[] temp = new int[levels.length - 1];
            int tempIndex = 0;
            for (int j = 0; j < levels.length; j++) {
                if (j != i) temp[tempIndex++] = levels[j];
            }
            listArrays.add(temp);
        }
        return listArrays;
    }

    public static void main(String[] args) {
//        String filePath = "Day2-test.txt";
//        List<int[]> reports = getReports(filePath);

//        System.out.println("Reports:");
//        for (int[] report : reports) {
//            System.out.println(Arrays.toString(report));
//        }

//        int count = 0;
//        for (int[] report : reports) {
//            if (isReportSafe(report)) count++;
//        }
//        System.out.println("count = " + count);
//
//        System.out.println("Test safe count = " + safeCount(getReports("Day2-test.txt")));
        System.out.println("Input safe count = " + safeCount(getReports("Day2-input.txt")));
        System.out.println("Input safe tolerated count = " + safeCountWithDampener(getReports("Day2-input.txt")));

//        for (int[] report : reports) {
//            System.out.println("Report = " + Arrays.toString(report) + ". Is it safe? " + isReportSafe(report) +
//                    ". Is it toleratable? " + isTolaratedReportSafe(report));
//            List<int[]> listOfCandidates = getArrayFamily(report);
//            for (int[] candidateArray : listOfCandidates) {
//                System.out.println("Candidate = " + Arrays.toString(candidateArray) + ". Is it safe? " + isReportSafe(candidateArray));
//            }
//            System.out.println();
//        }
//        System.out.println("Test safe count with Problem Dampener = " + safeCountWithDampener(getReports("Day2-test.txt")));


    }
}
