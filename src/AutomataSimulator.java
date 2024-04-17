import java.io.*;
import java.util.*;

public class AutomataSimulator {
    public static void main(String[] args) {
        String inputFile = "src\\input.txt";

        String outputFile = "src\\output.txt";


        try {
            List<String> results = processInput(inputFile);
            writeOutput(outputFile, results);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    private static List<String> processInput(String filePath) throws IOException {
        List<String> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line,prev_l="";
            int problemNumber = 0;
            List<String> inputs = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.matches("\\d+")&& prev_l.isEmpty()) {
                    if (!inputs.isEmpty()) {
                        System.out.println("hhhhhhhhhhh"+inputs);
                        results.addAll(simulateAutomaton(problemNumber, inputs));
                        System.out.println("hhhhhhhhhhh"+results);
                        inputs.clear();
                    }
                    problemNumber = Integer.parseInt(line);
                    System.out.println("nummmmm"+problemNumber);

                } else if (!line.equals("end")&&!line.isEmpty()) {
                    inputs.add(line);
                }
                prev_l=line;
            }
            if (!inputs.isEmpty()) {
                System.out.println("hhhhhhhhhhh"+inputs);
                results.addAll(simulateAutomaton(problemNumber, inputs));
                System.out.println("hhhhhhhhhhh"+results);
            }
        }
        return results;
    }

    private static List<String> simulateAutomaton(int problemNumber, List<String> inputs) {
        switch (problemNumber) {
            case 1: return simulateDFA1(inputs);
            case 2: return simulateDFA2(inputs);
            case 3: return simulateDFA3(inputs);
            case 4: return simulateDFA4(inputs);
            case 5: return simulateDFA5(inputs);
            case 6: return simulateDFA6(inputs);
            case 7: return simulateNFA7(inputs);
            case 8: return simulateNFA8(inputs);
            case 9: return simulateNFA9(inputs);
            case 10: return simulateNFA10(inputs);
            default: return List.of("Unsupported problem number: " + problemNumber);
        }
    }

    private static List<String> simulateDFA1(List<String> inputs) {
        // Problem 1: DFA that accepts strings not containing "ba"
        List<String> outputs = new ArrayList<>();
        outputs.add("1");
        for (String input : inputs) {
            boolean containsBa = input.contains("ba");
            outputs.add(containsBa ? "False" : "True");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA2(List<String> inputs) {
        // Problem 2: DFA that accepts strings with an even number of 0's followed by a single 1
        List<String> outputs = new ArrayList<>();
        outputs.add("2");
        for (String input : inputs) {
            boolean valid = input.matches("0*(10*)?");
            outputs.add(valid ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA3(List<String> inputs) {
        // Problem 3: DFA that accepts strings with an odd number of x's
        List<String> outputs = new ArrayList<>();
        outputs.add("3");
        for (String input : inputs) {
            long countX = input.chars().filter(ch -> ch == 'x').count();
            outputs.add(countX % 2 != 0 ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA4(List<String> inputs) {
        // Problem 4: DFA that accepts strings starting and ending with the same character
        List<String> outputs = new ArrayList<>();
        outputs.add("4");
        for (String input : inputs) {
            if (input.length() > 0) {
                char first = input.charAt(0);
                char last = input.charAt(input.length() - 1);
                outputs.add(first == last ? "True" : "False");
            } else {
                outputs.add("True");  // Empty string is considered valid
            }
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA5(List<String> inputs) {
        // Problem 5: DFA that accepts binary integers divisible by 4
        List<String> outputs = new ArrayList<>();
        outputs.add("5");
        for (String input : inputs) {
            try {
                int num = Integer.parseInt(input, 2);
                outputs.add(num % 4 == 0 ? "True" : "False");
            } catch (NumberFormatException e) {
                outputs.add("False"); // Non-binary strings are invalid
            }
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA6(List<String> inputs) {
        // Problem 6: DFA that accepts all strings except "11" and "111"
        List<String> outputs = new ArrayList<>();
        outputs.add("6");
        for (String input : inputs) {
            outputs.add(!(input.equals("11") || input.equals("111")) ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateNFA7(List<String> inputs) {
        // Problem 7: NFA for equal occurrences of '01' and '10'
        List<String> outputs = new ArrayList<>();
        outputs.add("7");
        for (String input : inputs) {
            long count01 = input.split("01", -1).length - 1;
            long count10 = input.split("10", -1).length - 1;
            outputs.add(count01 == count10 ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateNFA8(List<String> inputs) {
        // Problem 8: NFA that contains "101" or "010"
        List<String> outputs = new ArrayList<>();
        outputs.add("8");
        for (String input : inputs) {
            boolean contains101 = input.contains("101");
            boolean contains010 = input.contains("010");
            outputs.add(contains101 || contains010 ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateNFA9(List<String> inputs) {
        // Problem 9: NFA where no two consecutive characters are the same
        List<String> outputs = new ArrayList<>();
        outputs.add("9");
        for (String input : inputs) {
            boolean valid = true;
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == input.charAt(i - 1)) {
                    valid = false;
                    break;
                }
            }
            outputs.add(valid ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateNFA10(List<String> inputs) {
        // Problem 10: NFA where every '0' is followed by at least one '1'
        List<String> outputs = new ArrayList<>();
        outputs.add("10");
        for (String input : inputs) {
            boolean valid = true;
            for (int i = 0; i < input.length() - 1; i++) {
                if (input.charAt(i) == '0' && input.charAt(i + 1) != '1') {
                    valid = false;
                    break;
                }
            }
            outputs.add(valid ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static void writeOutput(String filePath, List<String> results) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
        }
    }
}
