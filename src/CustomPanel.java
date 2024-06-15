import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    public CustomPanel(int x, int y, int width, int height, Color br) {
        setBounds(x, y, width, height);
        setBackground(br);
        setLayout(null);
    }
}
