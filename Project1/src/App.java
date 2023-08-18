import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // Create the window
        JFrame frame = new JFrame("Login GUI");
        
        // Set the window size (50% wider)
        frame.setSize(450, 200);
        
        // Set the close behavior of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create labels for login and password
        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");
        
        // Create text fields for login and password
        JTextField loginField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        
        // Set the dimensions of the fields in pixels
        int fieldWidth = 270;
        int fieldHeight = 30;
        loginField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        passwordField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        
        // Create a button for login
        JButton loginButton = new JButton("Login");
        
        // Set the dimensions of the button in pixels
        int buttonWidth = 100;
        int buttonHeight = 30;
        loginButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        
        // Create a panel to organize components
        JPanel panel = new JPanel();
        
        // Set the layout for the panel
        panel.setLayout(new GridBagLayout());
        
        // Create constraints to center elements vertically
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10); // Add padding
        
        // Add components to the panel with constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(loginLabel, constraints);
        
        constraints.gridx = 1;
        panel.add(loginField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);
        
        constraints.gridx = 1;
        panel.add(passwordField, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Span over two columns
        panel.add(loginButton, constraints);
        
        // Add the panel to the window
        frame.add(panel);
        
        // Center the window on the screen
        frame.setLocationRelativeTo(null);
        
        // Make the window visible
        frame.setVisible(true);
    }
}
