import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class MouseClickerApp {
    private static boolean isRunning = false;
    private static int clickInterval = 1; // Default click interval in seconds

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Mouse Clicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel xLabel = new JLabel("X Coordinate:");
        JTextField xTextField = new JTextField();

        JLabel yLabel = new JLabel("Y Coordinate:");
        JTextField yTextField = new JTextField();

        JLabel intervalLabel = new JLabel("Click Interval (seconds):");
        JTextField intervalTextField = new JTextField(String.valueOf(clickInterval));

        JButton startButton = new JButton("Start Clicking");
        JButton stopButton = new JButton("Stop Clicking");
        stopButton.setEnabled(false);

        panel.add(xLabel);
        panel.add(xTextField);
        panel.add(yLabel);
        panel.add(yTextField);
        panel.add(intervalLabel);
        panel.add(intervalTextField);
        panel.add(startButton);
        panel.add(stopButton);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    isRunning = true;
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    String xText = xTextField.getText();
                    String yText = yTextField.getText();
                    String intervalText = intervalTextField.getText();

                    int xCoord = Integer.parseInt(xText);
                    int yCoord = Integer.parseInt(yText);
                    clickInterval = Integer.parseInt(intervalText);

                    RobotClicker robotClicker = new RobotClicker(xCoord, yCoord);
                    Thread clickerThread = new Thread(robotClicker);
                    clickerThread.start();

                    try {
                        Thread.sleep(5000); // Stop clicking after 5 seconds
                    } catch (InterruptedException ex) {
                        // Do nothing, just stop the clicking loop
                    }
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);
                    isRunning = false;
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });
    }

    private static class RobotClicker implements Runnable {
        private int x;
        private int y;

        public RobotClicker(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            try {
                Robot robot = new Robot();
                while (isRunning) {
                    robot.mouseMove(x, y);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    Thread.sleep(clickInterval * 1000); // Click with specified interval
                }
            } catch (AWTException | InterruptedException ex) {
                // Do nothing, just stop the clicking loop
            }
        }
    }
}
