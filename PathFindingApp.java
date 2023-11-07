import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Class representing a graph
class Graph {
    private int vertices;
    private List<List<Integer>> adjacencyList;


    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }


    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
    }


    public List<Integer> findPathDFS(int source, int destination) {
        boolean[] visited = new boolean[vertices];
        List<Integer> path = new ArrayList<>();


        DFS(source, destination, visited, path);


        Collections.reverse(path);
        return path;
    }


    private boolean DFS(int vertex, int destination, boolean[] visited, List<Integer> path) {
        visited[vertex] = true;


        if (vertex == destination) {
            path.add(vertex);
            return true;
        }


        for (int adjacent : adjacencyList.get(vertex)) {
            if (!visited[adjacent] && DFS(adjacent, destination, visited, path)) {
                path.add(vertex);
                return true;
            }
        }


        return false;
    }


    public int getVertices() {
        return vertices;
    }


    public List<List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}


// Main application class
public class PathFindingApp extends JFrame {
    private JTextField sourceField;
    private JTextField destinationField;
    private JButton findPathButton;
    private JLabel pathLabel;
    private JTextArea graphRepresentation;
    private Graph graph;


    public PathFindingApp() {
        // Setup JFrame properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Path Finding Application");
        this.setSize(500, 300);
        this.setLayout(new FlowLayout());


        // Construct the graph
        graph = new Graph(6);
        graph.addEdge(0, 1); // New York -> Los Angeles
        graph.addEdge(0, 4); // New York -> Phoenix
        graph.addEdge(1, 2); // Los Angeles -> Chicago
        graph.addEdge(1, 3); // Los Angeles -> Houston
        graph.addEdge(1, 4); // Los Angeles -> Phoenix
        graph.addEdge(3, 2); // Houston -> Chicago
        graph.addEdge(3, 4); // Houston -> Phoenix
        graph.addEdge(4, 5); // Phoenix -> Philadelphia


        // Construct components
        sourceField = new JTextField(10);
        destinationField = new JTextField(10);
        findPathButton = new JButton("Find Path");
        pathLabel = new JLabel("");
        graphRepresentation = new JTextArea(10, 40);
        graphRepresentation.setEditable(false);


        // Add components to the JFrame
        this.add(new JLabel("Your Location:"));
        this.add(sourceField);
        this.add(new JLabel("Destination:"));
        this.add(destinationField);
        this.add(findPathButton);
        this.add(pathLabel);
        this.add(graphRepresentation);


        // ActionListener for the Find Path button
        findPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int source = Integer.parseInt(sourceField.getText());
                int destination = Integer.parseInt(destinationField.getText());


                // Find path using DFS
                List<Integer> path = graph.findPathDFS(source, destination);


                if (!path.isEmpty()) {
                    StringBuilder sb = new StringBuilder("Path: ");
                    for (int i = 0; i < path.size(); i++) {
                        int city = path.get(i);
                        String cityName = getCityName(city);
                        sb.append(cityName);
                        if (i != path.size() - 1) {
                            sb.append(" -> ");
                        }
                    }
                    pathLabel.setText(sb.toString());
                } else {
                    pathLabel.setText("No path found.");
                }
            }
        });


        printGraph(); // Display the graph representation
    }


    // Get the city name based on the city number
    private static String getCityName(int city) {
        switch (city) {
            case 0:
                return "New York";
            case 1:
                return "Los Angeles";
            case 2:
                return "Chicago";
            case 3:
                return "Houston";
            case 4:
                return "Phoenix";
            case 5:
                return "Philadelphia";
            default:
                return "Unknown City";
        }
    }


    // Display the graph representation in the JTextArea
    private void printGraph() {
        StringBuilder sb = new StringBuilder();
        sb.append("Please enter the city number of your location and your destination.\n\n");
        for (int i = 0; i < graph.getVertices(); i++) {
            sb.append(i).append(" - ").append(getCityName(i)).append("\n");
        }
        graphRepresentation.setText(sb.toString());
    }


    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PathFindingApp().setVisible(true);
            }
        });
    }
}


