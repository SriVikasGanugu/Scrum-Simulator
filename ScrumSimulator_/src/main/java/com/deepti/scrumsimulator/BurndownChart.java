package com.deepti.scrumsimulator;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BurndownChart extends JPanel {
    private int[] xCoordinates;
    private int[] yCoordinates;
    private int[] xestimate;
    private int[] yestimate;
    private int numPoints;

    public BurndownChart() {
        // Initialize variables
        numPoints = 0;
        xCoordinates = new int[0];
        yCoordinates = new int[0];
        xestimate = new int[0];
        yestimate = new int[0];
        // Prompt user for input
        getUserInput();
    }

    private void getUserInput() {
        // Get the number of points from the user
        numPoints = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));

        // Initialize arrays based on the number of points
        xCoordinates = new int[numPoints];
        yCoordinates = new int[numPoints];
        xestimate = new int[numPoints];
        yestimate = new int[numPoints];
        // Get x and y coordinates for each point from the user
        for (int i = 0; i < numPoints; i++) {
            xCoordinates[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter actual remaining effort required for task (Starts with 100 and decreases as days go by)" + (i + 1) + ":"));
            xestimate[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter estimated remaining effort required for task (Starts with 100 and decreases as days go by)" + (i + 1) + ":"));

           // yCoordinates[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter days required for task " + (i + 1) + ":"));
        }
        int a=0;
        for(int i=0;i<xCoordinates.length;i++){
            
            yCoordinates[i]=a;
            yestimate[i]=a;
            a++;
            
        }
        
    }

    protected void paintComponent(Graphics grf) {
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get width and height
        int width = getWidth();
        int height = getHeight();

        // Draw axes
        graph.draw(new Line2D.Double(50, height - 50, width - 50, height - 50));
        graph.draw(new Line2D.Double(50, 50, 50, height - 50));

        // Find scaling factors
        double xScale = (double) (width - 100) / (getMax(xCoordinates) - getMin(xCoordinates));
        double yScale = (double) (height - 100) / (getMax(yCoordinates) - getMin(yCoordinates));

        // Set color for points and lines
        graph.setPaint(Color.BLUE);

        // Plot points on the graph and connect them with lines
        for (int i = 0; i < numPoints - 1; i++) {
            double x1 = 50 + (xCoordinates[i] - getMin(xCoordinates)) * xScale;
            double y1 = height - 50 - (yCoordinates[i] - getMin(yCoordinates)) * yScale;
            double x2 = 50 + (xCoordinates[i + 1] - getMin(xCoordinates)) * xScale;
            double y2 = height - 50 - (yCoordinates[i + 1] - getMin(yCoordinates)) * yScale;

            // Plot points
            graph.fill(new Ellipse2D.Double(x1 - 3, y1 - 3, 6, 6));
            graph.fill(new Ellipse2D.Double(x2 - 3, y2 - 3, 6, 6));

            // Connect points with lines
            graph.draw(new Line2D.Double(x1, y1, x2, y2));
        }
         // Draw x-axis label
    graph.drawString("Days", width - 30, height - 30);

    // Draw y-axis label
    graph.rotate(-Math.PI / 2); // Rotate for vertical text
    graph.drawString("Remaining Effort", -height / 2, 20);
    graph.rotate(Math.PI / 2); // Restore rotation
                graph.setPaint(Color.RED);

        for (int i = 0; i < numPoints - 1; i++) {
            double x1 = 50 + (xestimate[i] - getMin(xestimate)) * xScale;
            double y1 = height - 50 - (yestimate[i] - getMin(yestimate)) * yScale;
            double x2 = 50 + (xestimate[i + 1] - getMin(xestimate)) * xScale;
            double y2 = height - 50 - (yestimate[i + 1] - getMin(yestimate)) * yScale;

            // Plot points
            graph.fill(new Ellipse2D.Double(x1 - 3, y1 - 3, 6, 6));
            graph.fill(new Ellipse2D.Double(x2 - 3, y2 - 3, 6, 6));

            // Connect points with lines
            graph.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }

    private int getMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max)
                max = value;
        }
        return max;
    }

    private int getMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int value : array) {
            if (value < min)
                min = value;
        }
        return min;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BurndownChart());
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}