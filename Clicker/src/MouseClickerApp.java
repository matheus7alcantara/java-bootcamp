import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MouseClickerApp {
    private static boolean isRunning = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Mouse Clicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel xLabel = new JLabel("X Coordinate:");
        JTextField xTextField = new JTextField();

        JLabel yLabel = new JLabel("Y Coordinate:");
        JTextField yTextField = new JTextField();

        JButton startButton = new JButton("Start Clicking");
        JButton stopButton = new JButton("Stop Clicking");
        stopButton.setEnabled(false);

        panel.add(xLabel);
        panel.add(xTextField);
        panel.add(yLabel);
        panel.add(yTextField);
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

                    int xCoord = Integer.parseInt(xText);
                    int yCoord = Integer.parseInt(yText);

                    RobotClicker robotClicker = new RobotClicker(xCoord, yCoord);
                    Thread clickerThread = new Thread(robotClicker);
                    clickerThread.start();

                    clickerThread.interrupt(); // Stop clicking after 5 seconds
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);
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
                long endTime = System.currentTimeMillis() + 5000;

                while (System.currentTimeMillis() < endTime && isRunning) {
                    robot.mouseMove(x, y);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    Thread.sleep(100); // Click every 100 milliseconds
                }
            } catch (AWTException | InterruptedException ex) {
                // Do nothing, just stop the clicking loop
            }
        }
    }
}
