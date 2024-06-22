import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomButton extends JButton {
    private final int radius;

    private final Color backgroundColor;

    private final boolean showBackground;

    private final int width = 90, height = 60;

    public CustomButton(String text, int x, int y) {
        super(text);
        this.radius = 20; // 기본 반지름 값
        this.backgroundColor = Color.WHITE; // 기본 배경색
        this.showBackground = true;
        setBounds(x, y, width, height); // 버튼의 가로와 세로의 길이는 90 * 60 으로 항상 같음
        setFont(new Font("Dialog", Font.PLAIN, 20));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(new RoundedBorder(radius));
    }

    public CustomButton(String text, int x, int y, Color br) {
        super(text);
        this.radius = 20; // 기본 반지름 값
        this.backgroundColor = br;
        this.showBackground = true;
        setBounds(x, y, width, height); // 버튼의 가로와 세로의 길이는 90 * 60 으로 항상 같음
        setFont(new Font("Dialog", Font.PLAIN, 20));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(new RoundedBorder(radius));
    }

    public CustomButton(String text, int x, int y, int width, int height, Color br) {
        super(text);
        this.radius = 20; // 기본 반지름 값
        this.backgroundColor = br;
        this.showBackground = true;
        setBounds(x, y, width, height);
        setFont(new Font("Dialog", Font.PLAIN, 20));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(new RoundedBorder(radius));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (showBackground) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 배경을 둥글게 그리기
            if (getModel().isArmed()) {
                g2.setColor(backgroundColor.darker());
            } else {
                g2.setColor(backgroundColor);
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }

        // 텍스트 그리기
        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }

    @Override
    public boolean contains(int x, int y) {
        int w = getWidth();
        int h = getHeight();
        return (new RoundRectangle2D.Float(0, 0, w, h, radius, radius)).contains(x, y);
    }

    private record RoundedBorder(int radius) implements Border {

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
}
