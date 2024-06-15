import javax.swing.*;

public class CustomLabel extends JLabel {
    public CustomLabel(int x, int y, int width, int height) {
        super("0", null, LEADING);
        setBounds(x, y, width, height);
    }
}
