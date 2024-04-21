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
        String[] stateNames = {"q0", "q1"};
        String[][] transitions = {
                {"q0", "a", "q0"},
                {"q0", "b", "q1"},
                {"q1", "b", "q1"}
        };
        String startStateName = "q0";
        String[] acceptingStateNames = {"q0","q1"};

        DFA dfa = new DFA(stateNames, transitions, startStateName, acceptingStateNames);
        List<String> outputs = new ArrayList<>();
        outputs.add("1");
        for (String input : inputs) {
            boolean containsBa = dfa.accepts(input);
            outputs.add(containsBa ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA2(List<String> inputs) {
        // Problem 2: DFA that accepts strings with an even number of 0's followed by a single 1
        String[] stateNames = {"q0", "q1", "q2", "q3", "q4"};
        String[][] transitions = {
                {"q0", "1", "q3"},
                {"q0", "0", "q1"},
                {"q1", "0", "q2"},
                {"q2", "0", "q4"},
                {"q2", "1", "q3"},
                {"q4", "0", "q0"}
        };
        String startStateName = "q0";
        String[] acceptingStateNames = {"q3"};

        DFA dfa = new DFA(stateNames, transitions, startStateName, acceptingStateNames);

        List<String> outputs = new ArrayList<>();
        outputs.add("2");
        for (String input : inputs) {
            boolean valid = dfa.accepts(input);
            outputs.add(valid ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA3(List<String> inputs) {
        // Problem 3: DFA that accepts strings with an odd number of x's
        String[] stateNames = {"q0", "q1"};
        String[][] transitions = {
                {"q0", "y", "q0"},
                {"q0", "x", "q1"},
                {"q1", "x", "q0"},
                {"q1", "y", "q1"}
        };
        String startStateName = "q0";
        String[] acceptingStateNames = {"q1"};
        DFA dfa = new DFA(stateNames, transitions, startStateName, acceptingStateNames);
        List<String> outputs = new ArrayList<>();
        outputs.add("3");
        for (String input : inputs) {
            boolean valid = dfa.accepts(input);
            outputs.add(valid ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }


    private static List<String> simulateDFA4(List<String> inputs) {
        // Problem 4: DFA that accepts strings starting and ending with the same character
        String[] stateNames = {"q0","q1","q2","q3","q4"};
        String[][] transitions = {
                {"q0", "a", "q1"},
                {"q1", "a", "q3"},
                {"q1", "b", "q1"},
                {"q3", "b", "q1"},
                {"q3", "a", "q3"},
                {"q0", "b", "q2"},
                {"q2", "b", "q4"},
                {"q4", "b", "q4"},
                {"q4", "a", "q2"},
                {"q2", "a", "q2"}
        };
        String startStateName = "q0";
        String[] acceptingStateNames = {"q3","q4"};
        DFA dfa = new DFA(stateNames, transitions, startStateName, acceptingStateNames);
        List<String> outputs = new ArrayList<>();
        outputs.add("4");
        for (String input : inputs) {
            boolean valid = dfa.accepts(input);
            outputs.add(valid ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA5(List<String> inputs) {
        // Problem 5: DFA that accepts binary integers divisible by 4
        String[] stateNames = {"q0","q1","q2"};
        String[][] transitions = {
                {"q0", "0", "q0"},
                {"q1", "1", "q1"},
                {"q1", "0", "q2"},
                {"q0", "1", "q1"},
                {"q2", "1", "q1"},
                {"q2", "0", "q0"}
        };
        String startStateName = "q0";
        String[] acceptingStateNames = {"q0"};
        DFA dfa = new DFA(stateNames, transitions, startStateName, acceptingStateNames);
        List<String> outputs = new ArrayList<>();
        outputs.add("5");
        for (String input : inputs) {
            boolean valid = dfa.accepts(input);
            outputs.add(valid ? "True" : "False");
        }
        outputs.add("x");
        return outputs;
    }

    private static List<String> simulateDFA6(List<String> inputs) {
        // Problem 6: DFA that accepts all strings except "11" and "111"
        String[] stateNames = {"q0","q1","q2","q3","q4"};
        String[][] transitions = {
                {"q0", "0", "q1"},
                {"q0", "1", "q2"},
                {"q1", "1", "q1"},
                {"q1", "0", "q1"},
                {"q2", "1", "q3"},
                {"q2", "0", "q1"},
                {"q3", "1", "q4"},
                {"q3", "0", "q1"},
                {"q4", "1", "q1"},
                {"q4", "0", "q1"}
        };
        String startStateName = "q0";
        String[] acceptingStateNames = {"q1","q0"};
        DFA dfa = new DFA(stateNames, transitions, startStateName, acceptingStateNames);
        List<String> outputs = new ArrayList<>();
        outputs.add("6");
        for (String input : inputs) {
            boolean valid = dfa.accepts(input);
            outputs.add(valid ? "True" : "False");
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

    public static class DFA {
        private static class State {
            private final String name;

            public State(String name) {
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                State state = (State) obj;
                return Objects.equals(name, state.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name);
            }

            @Override
            public String toString() {
                return name;
            }
        }

        private State currentState;
        private State startState;
        private final Set<State> acceptingStates;
        private final Map<State, Map<Character, State>> transitionFunction;
        private final Map<String, State> states;



        public DFA(String[] stateNames, String[][] transitions, String startStateName, String[] acceptingStateNames) {
            states = new HashMap<>();
            for (String name : stateNames) {
                states.put(name, new State(name));
            }

            currentState = states.get(startStateName);
            startState = states.get(startStateName);
            acceptingStates = new HashSet<>();
            for (String name : acceptingStateNames) {
                acceptingStates.add(states.get(name));
            }

            transitionFunction = new HashMap<>();
            for (String[] transition : transitions) {
                State from = states.get(transition[0]);
                char input = transition[1].charAt(0);
                State to = states.get(transition[2]);

                transitionFunction.computeIfAbsent(from, k -> new HashMap<>()).put(input, to);
            }
        }

        public boolean accepts(String input) {
            currentState = startState; // Reset to start state for each new input
            for (char c : input.toCharArray()) {

                Map<Character, State> transitions = transitionFunction.get(currentState);
                //System.out.println("currentState "+currentState+" transitions "+"transitions.get(c)"+" c "+c);
                if (transitions == null || transitions.get(c) == null) {
                    return false;
                }

                currentState = transitions.get(c);
               // System.out.println("transitions "+currentState);
            }
            return acceptingStates.contains(currentState);
        }
    }
}

//public static class DFA1 {
//    private enum State {
//        q0, q1
//    }
//
//    private State currentState;
//    private final Set<State> acceptingStates;
//    private final Map<State, Map<Character, State>> transitionFunction;
//
//    public DFA1() {
//        currentState = State.q0;
//
//        // Define accepting states
//        acceptingStates = new HashSet<>(Arrays.asList(State.q0, State.q1));
//
//        // Transition function
//        transitionFunction = new HashMap<>();
//        Map<Character, State> q0Transitions = new HashMap<>();
//        q0Transitions.put('a', State.q0);
//        q0Transitions.put('b', State.q1);
//
//        Map<Character, State> q1Transitions = new HashMap<>();
//        q1Transitions.put('b', State.q1);
//
//
//        transitionFunction.put(State.q0, q0Transitions);
//        transitionFunction.put(State.q1, q1Transitions);
//
//    }
//
//    public boolean accepts(String input) {
//        currentState = State.q0;  // Reset to start state for each new input
//        for (char c : input.toCharArray()) {
//            currentState = transitionFunction.get(currentState).get(c);
//        }
//        return acceptingStates.contains(currentState);
//    }
//
//
//}
//public static class DFA2 {
//    private enum State {
//        q0, q1,q2,q3,q4
//    }
//
//    private State currentState;
//    private final Set<State> acceptingStates;
//    private final Map<State, Map<Character, State>> transitionFunction;
//
//    public DFA2() {
//        currentState = State.q0;
//
//        // Define accepting states
//        acceptingStates = new HashSet<>(Arrays.asList(State.q3));
//
//        // Transition function
//        transitionFunction = new HashMap<>();
//        Map<Character, State> q0Transitions = new HashMap<>();
//        q0Transitions.put('1', State.q3);
//        q0Transitions.put('0', State.q1);
//
//        Map<Character, State> q1Transitions = new HashMap<>();
//        q1Transitions.put('0', State.q2);
//
//        Map<Character, State> q2Transitions = new HashMap<>();
//        q2Transitions.put('0', State.q4);
//        q2Transitions.put('1', State.q3);
//
//        //Map<Character, State> q3Transitions = new HashMap<>();
//
//        Map<Character, State> q4Transitions = new HashMap<>();
//        q4Transitions.put('0', State.q0);
//
//        transitionFunction.put(State.q0, q0Transitions);
//        transitionFunction.put(State.q1, q1Transitions);
//        transitionFunction.put(State.q2, q2Transitions);
//        transitionFunction.put(State.q3, q4Transitions);
//        transitionFunction.put(State.q4, q4Transitions);
//
//
//
//    }
//
//    public boolean accepts(String input) {
//        currentState = State.q0;  // Reset to start state for each new input
//        for (char c : input.toCharArray()) {
//            currentState = transitionFunction.get(currentState).get(c);
//        }
//        return acceptingStates.contains(currentState);
//    }
//
//
//}