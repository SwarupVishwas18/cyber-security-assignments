import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class LineCoding extends JFrame implements ActionListener {
    public int x1 = 200, x2 = 200, y1 = 100, y2 = 100;
    int[] dig;
    int[] bit;
    JTextField inpData = new JTextField(12);
    JComboBox<String> techniques = null;
    JPanel drawPanel = new JPanel();


    public LineCoding() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setTitle("Line Coding Techniques");
        initComponents();
    }


    private void initComponents() {
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Input:"));
        panel1.add(inpData);
        String list[] = { "Unipolar", "Bipolar", "RZ", "Manchester", "NRZ-I", "NRZ-L", "Differential Manchester" };
        techniques = new JComboBox<String>(list);
        panel1.add(new JLabel("Technique:"));
        panel1.add(techniques);
        JButton encodeBtn = new JButton("Encode");
        JButton clrBtn = new JButton("Clear");
        panel1.add(encodeBtn);
        encodeBtn.addActionListener(this);
        panel1.add(clrBtn);
        clrBtn.addActionListener(this);
        drawPanel.setBackground(Color.WHITE);
        add(drawPanel);
        add(panel1, BorderLayout.SOUTH);


    }


    public static void main(String[] args) {
        LineCoding f = new LineCoding();
        f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Encode")) {
            encode();
        } else if (e.getActionCommand().equals("Clear")) {
            repaint();
        }


    }


    private void encode() {


        drawPanel.getGraphics().drawString("Implement " + techniques.getSelectedItem() + " Encoding Technique!", 400,
                40);
        char data[] = inpData.getText().toCharArray();


        if (techniques.getSelectedItem() == "Unipolar") {
            dig = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                if (data[i] == '1') {
                    dig[i] = 50;
                } else {
                    dig[i] = -50;
                }
            }


            draw(dig);
        }


        if (techniques.getSelectedItem() == "Bipolar") {
            dig = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                if (data[i] == '1') {
                    dig[i] = 50;
                } else {
                    dig[i] = -50;
                }
                dig[i] = dig[i] * 2 - 50;
            }


            draw(dig);
        }


        if (techniques.getSelectedItem() == "NRZ-L") {
            dig = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                if (data[i] == '1') {
                    dig[i] = 50;
                } else {
                    dig[i] = -50;
                }
            }


            draw(dig);
        }
        if (techniques.getSelectedItem() == "NRZ-I") {
            dig = new int[data.length];
            y1 = 50;
            if (data[0] == '1')
                dig[0] = -50;
            else
                dig[0] = 50;
            for (int i = 1; i < data.length; i++) {
                if (data[i] == '1') {
                    dig[i] = -dig[i - 1];
                } else {
                    dig[i] = dig[i - 1];
                }
            }


            draw(dig);
        }
        // RZ
        if (techniques.getSelectedItem() == "RZ") {
            dig = new int[data.length * 2];
            int j = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i] == '1') {
                    dig[j] = 50;
                    dig[j + 1] = 0;
                    j = j + 2;
                } else {
                    dig[j] = -50;
                    dig[j + 1] = 0;
                    j = j + 2;
                }
            }


            draw(dig);
        }


        // Manchester
        if (techniques.getSelectedItem() == "Manchester") {
            dig = new int[data.length * 2];
            int j = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i] == '1') {
                    dig[j] = 50;
                    dig[j + 1] = -50;
                } else {
                    dig[j] = -50;
                    dig[j + 1] = +50;
                }
                j = j + 2;
            }


            draw(dig);
        }
        // Differential Manchester
        if (techniques.getSelectedItem() == "Differential Manchester") {
            int j = 2;
            y1 = 50;
            dig = new int[data.length * 2];
            if (data[0] == '1') {
                dig[0] = 50;
            } else if (data[0] == '0') {
                dig[0] = -50;
            }
            dig[1] = dig[0] * (-1);
            for (int i = 1; i < data.length; i++) {
                if (data[i] == '1') {
                    dig[j] = dig[j - 1];
                } else {
                    dig[j] = dig[j - 1] * (-1);
                }
                dig[++j] = dig[j - 1] * (-1);
                j++;
            }


            draw(dig);
        }


    }


    private void draw(int[] dig) {


        for (int i = 0; i < dig.length; i++) {
            if (dig[i] == 100 - y1) {
                y2 = y1;
                x2 = x1 + 30;
                drawPanel.getGraphics().drawLine(x1, y1, x2, y2);
            } else {
                x2 = x1;
                y2 = 100 - dig[i];
                drawPanel.getGraphics().drawLine(x1, y1, x2, y2);
                y1 = y2;
                x2 = x1 + 30;
                drawPanel.getGraphics().drawLine(x1, y1, x2, y2);
            }
            x1 = x2;
            y1 = y2;
        }
    }
}
