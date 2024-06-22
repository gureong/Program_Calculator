import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {
    private String tempStr;
    private char tempChar;
    private double tempDouble;
    private final String errorDivide0 = "0으로 나눌 수 없습니다";

    private final JLabel labelTextArea;

    private String getTextStr() {
        return labelTextArea.getText();
    }

    private double getTextDouble() {
        return Double.parseDouble(labelTextArea.getText());
    }

    private void setText(String text) {
        labelTextArea.setText(text);
    }

    private void setText(Double text) {
        labelTextArea.setText(String.valueOf(text));
    }

    private void setText(int text) {
        labelTextArea.setText(String.valueOf(text));
    }

    private char getLastLetter() {
        return labelTextArea.getText().trim().charAt(labelTextArea.getText().trim().length() - 1);
    }

    private boolean notAddSubMulDiv() {
        tempChar = getLastLetter();
        return tempChar != '+' && tempChar != '-' && tempChar != '×' && tempChar != '÷';
    }

    private boolean notAddSubMulDivDot() {
        tempChar = getLastLetter();
        return tempChar != '+' && tempChar != '-' && tempChar != '×' && tempChar != '÷' && tempChar != '.';
    }

    private boolean tempDoubleDiv1Remainder0() {
        return tempDouble % 1 == 0;
    }

    private final JButton[] buttonNum = new CustomButton[10];

    public MyFrame() {
        setTitle("Calculator");
        setSize(385 + 16, 490 + 39); // 프레임 내부 사이즈 385 * 490 -> 프레임 전체 사이즈 (385 + 16) * (490 + 39)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 꺼졌을 때 프로그램 종료할건지
        setLocationRelativeTo(null); // 실행시에 화면 가운데에 뜨게 할건지
        setResizable(false); // 프레임 사이즈 조절 불가능
        getContentPane().setLayout(null);
        getContentPane().setBackground(CustomColor.LIGHT_GRAY_LABEL);

        JPanel panelMain = new CustomPanel(0, 0, getWidth(), 100, CustomColor.LIGHT_GRAY_LABEL);
        JPanel panelContent = new CustomPanel(0, 100, getWidth(), 390, CustomColor.LIGHT_GRAY_LABEL);

        labelTextArea = new CustomLabel(5, 10, 375, 80);
        labelTextArea.setOpaque(true);
        labelTextArea.setFont(new Font("Dialog", Font.PLAIN, 38));
        labelTextArea.setHorizontalAlignment(SwingConstants.RIGHT);
        labelTextArea.setBackground(CustomColor.LIGHT_GRAY_LABEL);

        JButton buttonPercent = new CustomButton("%", 5, 0, CustomColor.LIGHT_GRAY_BUTTON);
        JButton buttonCE = new CustomButton("CE", 100, 0, CustomColor.LIGHT_GRAY_BUTTON);
        JButton buttonC = new CustomButton("C", 195, 0, CustomColor.LIGHT_GRAY_BUTTON);
        JButton buttonBackSpace = new CustomButton("⌫", 290, 0, CustomColor.LIGHT_GRAY_BUTTON);

        JButton button1x = new CustomButton("1/x", 5, 65, CustomColor.LIGHT_GRAY_BUTTON);
        JButton buttonPow = new CustomButton("x^2", 100, 65, CustomColor.LIGHT_GRAY_BUTTON);
        JButton buttonSqrt = new CustomButton("√x", 195, 65, CustomColor.LIGHT_GRAY_BUTTON);
        JButton buttonDivide = new CustomButton("÷", 290, 65, CustomColor.LIGHT_GRAY_BUTTON);

        buttonNum[7] = new CustomButton("7", 5, 130);
        buttonNum[8] = new CustomButton("8", 100, 130);
        buttonNum[9] = new CustomButton("9", 195, 130);
        JButton buttonMultiply = new CustomButton("×", 290, 130, CustomColor.LIGHT_GRAY_BUTTON);

        buttonNum[4] = new CustomButton("4", 5, 195);
        buttonNum[5] = new CustomButton("5", 100, 195);
        buttonNum[6] = new CustomButton("6", 195, 195);
        JButton buttonSubtract = new CustomButton("-", 290, 195, CustomColor.LIGHT_GRAY_BUTTON);

        buttonNum[1] = new CustomButton("1", 5, 260);
        buttonNum[2] = new CustomButton("2", 100, 260);
        buttonNum[3] = new CustomButton("3", 195, 260);
        JButton buttonAdd = new CustomButton("+", 290, 260, CustomColor.LIGHT_GRAY_BUTTON);

        JButton buttonPlusOrMinus = new CustomButton("+/-", 5, 325);
        buttonNum[0] = new CustomButton("0", 100, 325);
        JButton buttonDot = new CustomButton(".", 195, 325);
        JButton buttonEnter = new CustomButton("=", 290, 325, CustomColor.RED_ENTER);

        buttonPercent.addActionListener(e -> {
            if (!getTextStr().equals(errorDivide0)) {
                if (notAddSubMulDivDot()) {
                    tempDouble = getTextDouble() / 100;

                    if (tempDoubleDiv1Remainder0()) {
                        setText((int) tempDouble);
                    } else {
                        setText(tempDouble);
                    }
                }
            }
            panelContent.requestFocusInWindow();
        });

        buttonCE.addActionListener(e -> {
            if (!getTextStr().equals(errorDivide0)) {
                if (notAddSubMulDiv()) {
                    String[] temp = getTextStr().split(" ", 3);
                    if (temp.length == 3) setText((temp[0] + " " + temp[1] + " "));
                    else setText(0);
                }
            } else {
                setText(0);
            }
            panelContent.requestFocusInWindow();
        });

        buttonC.addActionListener(e -> {
            setText(0);
            panelContent.requestFocusInWindow();
        });

        buttonBackSpace.addActionListener(e -> {
            if (!getTextStr().equals(errorDivide0)) {
                if (notAddSubMulDiv()) {
                    String text = getTextStr();
                    if (text.length() > 1) {
                        setText(text.substring(0, text.length() - 1));
                    } else {
                        setText(0);
                    }
                }
            } else {
                setText(0);
            }

            panelContent.requestFocusInWindow();
        });

        button1x.addActionListener(e -> {
            if (notAddSubMulDivDot()) {
                tempDouble = 1 / getTextDouble();
                if (tempDoubleDiv1Remainder0()) {
                    setText((int) tempDouble);
                } else {
                    setText(tempDouble);
                }
            }
            panelContent.requestFocusInWindow();
        });

        buttonPow.addActionListener(e -> {
            if (notAddSubMulDivDot()) {
                tempDouble = Math.pow(getTextDouble(), 2);
                if (tempDoubleDiv1Remainder0()) {
                    setText((int) tempDouble);
                } else {
                    setText(tempDouble);
                }
            }
            panelContent.requestFocusInWindow();
        });

        buttonSqrt.addActionListener(e -> {
            if (notAddSubMulDivDot()) {
                tempDouble = Math.sqrt(getTextDouble());
                if (tempDoubleDiv1Remainder0()) {
                    setText((int) tempDouble);
                } else {
                    setText(tempDouble);
                }
            }
            panelContent.requestFocusInWindow();
        });

        buttonDivide.addActionListener(e -> {
            if (getTextStr().split(" ").length == 3) {
                buttonEnter.doClick();
            }
            if (notAddSubMulDivDot()) {
                setText(getTextStr() + " ÷ ");
            }

            panelContent.requestFocusInWindow();
        });

        buttonMultiply.addActionListener(e -> {
            if (getTextStr().split(" ").length == 3) {
                buttonEnter.doClick();
            }
            if (notAddSubMulDivDot()) {
                setText(getTextStr() + " × ");
            }

            panelContent.requestFocusInWindow();
        });

        buttonSubtract.addActionListener(e -> {
            if (getTextStr().split(" ").length == 3) {
                buttonEnter.doClick();
            }
            if (notAddSubMulDivDot()) {
                setText(getTextStr() + " - ");
            }

            panelContent.requestFocusInWindow();
        });

        buttonAdd.addActionListener(e -> {
            if (getTextStr().split(" ").length == 3) {
                buttonEnter.doClick();
            }
            if (notAddSubMulDivDot()) {
                setText(getTextStr() + " + ");
            }

            panelContent.requestFocusInWindow();
        });

        buttonPlusOrMinus.addActionListener(e -> {
            tempDouble = getTextDouble();
            if (tempDouble != 0) {
                if (tempDoubleDiv1Remainder0()) {
                    setText((int) -getTextDouble());
                } else {
                    setText(-getTextDouble());
                }
            }

            panelContent.requestFocusInWindow();
        });

        buttonDot.addActionListener(e -> {
            if (notAddSubMulDivDot()) {
                setText(getTextStr() + ".");
            }
        });

        // 넘버패드 0~9 까지 한번에 처리
        for (int i = 0; i < 10; i++) {
            final int tempInt = i;
            buttonNum[tempInt].addActionListener(e -> {
                tempStr = getTextStr();
                if (tempStr.equals("0")) {
                    setText(tempInt);
                } else {
                    setText(getTextStr() + tempInt);
                }
                panelContent.requestFocusInWindow();
            });
            panelContent.add(buttonNum[tempInt]);
        }

        buttonEnter.addActionListener(e -> {
            if (!getTextStr().equals(errorDivide0)) {
                String[] part = getTextStr().trim().split(" ", 3);
                double firstNumber = Double.parseDouble(part[0]);
                String operator = "";
                if (part.length >= 2) {
                    operator = part[1];
                }
                double result = 0;
                if (part.length == 3) {
                    double secondNumber = Double.parseDouble(part[2]);
                    switch (operator) {
                        case "+" -> result = firstNumber + secondNumber;
                        case "-" -> result = firstNumber - secondNumber;
                        case "×" -> result = firstNumber * secondNumber;
                        case "÷" -> {
                            if (secondNumber == 0) {
                                setText(errorDivide0);
                            } else {
                                result = firstNumber / secondNumber;
                            }
                        }
                    }
                    if (result % 1 == 0) {
                        setText((int) result);
                    } else {
                        setText(result);
                    }
                } else if (part.length == 2) {
                    switch (operator) {
                        case "+" -> result = firstNumber * 2;
                        case "-" -> result = 0.0;
                        case "×" -> result = firstNumber * firstNumber;
                        case "÷" -> result = 1.0;
                    }
                    if (result % 1 == 0) {
                        setText((int) result);
                    } else {
                        setText(result);
                    }
                }
            } else {
                setText(0);
            }
        });

        add(panelMain);
        add(panelContent);
        panelMain.add(labelTextArea);
        panelContent.add(buttonPercent);
        panelContent.add(buttonCE);
        panelContent.add(buttonC);
        panelContent.add(buttonBackSpace);

        panelContent.add(button1x);
        panelContent.add(buttonPow);
        panelContent.add(buttonSqrt);
        panelContent.add(buttonDivide);

        panelContent.add(buttonMultiply);
        panelContent.add(buttonSubtract);
        panelContent.add(buttonAdd);
        panelContent.add(buttonPlusOrMinus);
        panelContent.add(buttonDot);
        panelContent.add(buttonEnter);

        panelContent.setFocusable(true);
        panelContent.requestFocusInWindow();
        panelContent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tempInt = e.getKeyCode();
                if ((e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                    switch (tempInt) {
                        case KeyEvent.VK_1 -> buttonPlusOrMinus.doClick();
                        case KeyEvent.VK_5 -> buttonPercent.doClick();
                        case KeyEvent.VK_6 -> buttonPow.doClick();
                        case KeyEvent.VK_BACK_SPACE -> buttonC.doClick();
                    }
                } else {
                    switch (tempInt) {
                        case KeyEvent.VK_ADD -> buttonAdd.doClick();
                        case KeyEvent.VK_SUBTRACT -> buttonSubtract.doClick();
                        case KeyEvent.VK_MULTIPLY -> buttonMultiply.doClick();
                        case KeyEvent.VK_DIVIDE -> buttonDivide.doClick();
                        case KeyEvent.VK_DECIMAL -> buttonDot.doClick();
                        case KeyEvent.VK_ENTER -> buttonEnter.doClick();
                        case KeyEvent.VK_BACK_SPACE -> buttonBackSpace.doClick();
                        case KeyEvent.VK_DELETE -> buttonC.doClick();
                        default -> {
                            if (96 <= tempInt && tempInt <= 105) {
                                buttonNum[tempInt - 96].doClick(); // 넘버패드 0 => 96, 넘버패드 9 => 105
                            }
                        }
                    }
                }
            }
        });
    }
}
