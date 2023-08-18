import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Cria a janela
        JFrame frame = new JFrame("Hello, World GUI");
        
        // Define o tamanho da janela
        frame.setSize(300, 200);
        
        // Define o comportamento ao fechar a janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Cria um rótulo com o texto "Hello, World!"
        JLabel label = new JLabel("Hello, World!");
        
        // Define a fonte, tamanho e cor do texto
        Font font = new Font("Arial", Font.BOLD, 24);
        label.setFont(font);
        label.setForeground(Color.BLUE); // Define a cor do texto
        
        // Define o alinhamento do texto para centralizar
        label.setHorizontalAlignment(JLabel.CENTER);
        
        // Adiciona o rótulo à janela
        frame.add(label);
        
        // Centraliza a janela na tela
        frame.setLocationRelativeTo(null);
        
        // Torna a janela visível
        frame.setVisible(true);
    }
}