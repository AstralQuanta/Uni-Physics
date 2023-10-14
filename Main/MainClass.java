package Main;

import Milligan.Milligan;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainClass extends JFrame {

    private JFrame newFrame1;
    private JPanel jpanel1;
    private JButton button1, button2, button3, button4;
    private Milligan mill;
    private double averageQ = 0.0;
    private double tempQ = 0.0;
    private double fixQ = 0.0;
    private double totalAverage = 0;
    private int count = 0;
    private static double getValueFromAverage = 0;

    private double λ = 5.89 * 10E-7;
    private double nm_to_mm = 1 * 10E-9;
    private String strTemp = "";

    public MainClass() {
        setTitle("大学物理实验 Java 计算器");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();

        button1 = new JButton("密里根油滴实验计算");
        button2 = new JButton("牛顿环物理实验计算");
        button3 = new JButton("按钮3");
        button4 = new JButton("按钮4");

        button1.setPreferredSize(new Dimension(150, 80));
        button2.setPreferredSize(new Dimension(150, 80));
        button3.setPreferredSize(new Dimension(150, 80));
        button4.setPreferredSize(new Dimension(150, 80));

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewFrame("密里根油滴实验-Java计算器");

                JLabel label0 = new JLabel("同一油滴重复计算的次数和全部共要测量的油滴数量 输入例如：3，5");
                JLabel label1 = new JLabel("油滴平衡电压(V) 输入例如：100，101...");
                JLabel label2 = new JLabel("油滴下降时间(s) 输入例如：10.22，11.22...");
                JLabel label3 = new JLabel("油滴上升电压(V) 输入例如：300，400...");
                JLabel label4 = new JLabel("油滴上升时间(s) 输入例如：7.6，7.7...");

                JTextField input0 = new JTextField(10);
                JTextField input1 = new JTextField(10);
                JTextField input2 = new JTextField(10);
                JTextField input3 = new JTextField(10);
                JTextField input4 = new JTextField(10);
                JTextArea outputMessage = new JTextArea();

                outputMessage.setEditable(false);
                outputMessage.setFocusable(false);

                input0.setPreferredSize(new Dimension(130, 30));
                input1.setPreferredSize(new Dimension(130, 30));
                input2.setPreferredSize(new Dimension(130, 30));
                input3.setPreferredSize(new Dimension(130, 30));
                input4.setPreferredSize(new Dimension(130, 30));

                JButton calculateFinal = new JButton("求解");
                JButton getSumButton = new JButton("全部油滴的平均");
                getSumButton.setVisible(false);
                calculateFinal.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<String> results = new ArrayList<>();
                        ArrayList<String> myList0 = new ArrayList<>();
                        ArrayList<String> myList1 = new ArrayList<>();
                        ArrayList<String> myList2 = new ArrayList<>();
                        ArrayList<String> myList3 = new ArrayList<>();
                        ArrayList<String> myList4 = new ArrayList<>();

                        myList0.addAll(Arrays.asList(input0.getText().split(",")));
                        myList1.addAll(Arrays.asList(input1.getText().split(",")));
                        myList2.addAll(Arrays.asList(input2.getText().split(",")));
                        myList3.addAll(Arrays.asList(input3.getText().split(",")));
                        myList4.addAll(Arrays.asList(input4.getText().split(",")));
                        for (int i = 0; i < Integer.parseInt(myList0.get(0)); i++) {
                            mill = new Milligan(Integer.parseInt(myList1.get(i)), Double.parseDouble(myList2.get(i)), Integer.parseInt(myList3.get(i)), Double.parseDouble(myList4.get(i)));

                            if (input0.getText().equals("") && input1.getText().equals("") && input2.getText().equals("") && input3.getText().equals("") && input4.getText().equals("")) {
                                outputMessage.setText("");
                                outputMessage.setText("请输入你的数据！");
                            } else {
                                if (mill.finalAnswer == 0) {
                                    mill.calculateData();
                                    tempQ = tempQ + mill.finalAnswer;
                                    //System.out.println("millQ = " + mill.finalAnswer);
                                    results.addAll(Arrays.asList(mill.MessageFromCal() + "\n\n"));
                                }
                            }
                        }
                        if (fixQ == 0.0) {
                            fixQ = tempQ;
                            tempQ = 0;
                        } else {
                            fixQ = 0;
                            fixQ = tempQ;
                            tempQ = 0;
                        }
                        if (Integer.parseInt(myList0.get(0)) > 0) {
                            averageQ = (fixQ / Integer.parseInt(myList0.get(0)));
                            if (outputMessage.getText() != null) {
                                outputMessage.setText("");
                                getValueFromAverage += averageQ;
                                count += 1;
                                outputMessage.setText(results.toString() + "平均电荷量：" + averageQ + " 相对误差：" + ((averageQ / mill.StandardElectron) * 100) + " % " + "\n");
                            } else {
                                outputMessage.setText(results.toString() + "平均电荷量：" + averageQ + " 相对误差：" + ((averageQ / mill.StandardElectron) * 100) + " % " + "\n");
                            }
                        }
                        if (count == Integer.parseInt(myList0.get(1))) {
                            getSumButton.setVisible(true);
                            getSumButton.addActionListener((ActionEvent e1) -> {

                                totalAverage = getValueFromAverage / Integer.parseInt(myList0.get(1));
                                String strTempsMill = "油滴总和大小：" + getValueFromAverage + "\n" + "全部油滴的平均值：" + totalAverage;

                                JOptionPane.showMessageDialog(null, "油滴总和大小：" + getValueFromAverage + "\n" + "全部油滴的平均值：" + totalAverage, "最终结果", JOptionPane.INFORMATION_MESSAGE);
                            });

                        }
                    }
                });
                jpanel1.add(label0);
                jpanel1.add(input0);
                jpanel1.add(label1);
                jpanel1.add(input1);
                jpanel1.add(label2);
                jpanel1.add(input2);
                jpanel1.add(label3);
                jpanel1.add(input3);
                jpanel1.add(label4);
                jpanel1.add(input4);
                jpanel1.add(calculateFinal);
                jpanel1.add(getSumButton);
                jpanel1.add(outputMessage);
                pack();
                setLocationRelativeTo(null);
            }
        });

        button2.addActionListener(e -> newTonRings());

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewFrame("按钮3");
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewFrame("按钮4");
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        add(panel);

        setVisible(true);
    }

    private void newTonRings() {
        JFrame newFrame1 = new JFrame("牛顿环-Java计算器");
        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new GridLayout(11, 2));

        double[] anhuanValuesL20 = new double[21];
        double[] anhuanValuesL10 = new double[21];
        double[] RanhuanValuesR20 = new double[21];
        double[] RanhuanValuesR10 = new double[21];

        String[] labels = {
            "左边 20 环", "左边 19 环", "左边 18 环", "左边 17 环", "左边 16 环",
            "左边 10 环", "左边 9 环", "左边 8 环", "左边 7 环", "左边 6 环",
            "右边 20 环", "右边 19 环", "右边 18 环", "右边 17 环", "右边 16 环",
            "右边 10 环", "右边 9 环", "右边 8 环", "右边 7 环", "右边 6 环"
        };

        JTextField[] inputs = new JTextField[20];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            JTextField input = new JTextField(10);
            input.setPreferredSize(new Dimension(50, 10));
            inputs[i] = input;
            jpanel1.add(label);
            jpanel1.add(input);
        }

        JPanel buttonOutputPanel = new JPanel();
        buttonOutputPanel.setLayout(new BoxLayout(buttonOutputPanel, BoxLayout.PAGE_AXIS));

        JButton submit = new JButton("求解");
        JPanel submitPanel = new JPanel();
        submitPanel.add(submit);
        submitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonOutputPanel.add(submitPanel);
        buttonOutputPanel.add(Box.createVerticalGlue());

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalAver = 0.0;
                StringBuilder message = new StringBuilder();

                for (int i = 0; i < 5; i++) {
                    anhuanValuesL20[i] = Double.parseDouble(inputs[i].getText());
                    RanhuanValuesR20[i] = Double.parseDouble(inputs[i + 10].getText());
                    anhuanValuesL10[i] = Double.parseDouble(inputs[i + 5].getText());
                    RanhuanValuesR10[i] = Double.parseDouble(inputs[i + 15].getText());

                    double diameter = anhuanValuesL20[i] - RanhuanValuesR20[i];
                    double diameterLow = anhuanValuesL10[i] - RanhuanValuesR10[i];

                    double calsdm_dn = Math.pow(diameter, 2) - Math.pow(diameterLow, 2);
                    double radius = (calsdm_dn * nm_to_mm) / (0.04 * λ);
                    totalAver += radius;
                    message.append("第 ").append(i + 1).append(" 次的曲率半径为：").append(radius).append("\n");
                }
                message.append("\n").append("5 次总计算得到平均值为：").append(totalAver / 5);
                JOptionPane.showMessageDialog(null, message.toString(), "最终结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        jpanel1.add(buttonOutputPanel);

        newFrame1.add(jpanel1);
        newFrame1.pack();
        newFrame1.setLocationRelativeTo(null);
        newFrame1.setVisible(true);
    }

    private void openNewFrame(String buttonName) {
        int width = 800;
        int height = 750;

        newFrame1 = new JFrame(buttonName);
        newFrame1.setSize(width, height);
        jpanel1 = new JPanel(new GridBagLayout());

        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));
        newFrame1.add(jpanel1);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int x = (screenWidth - width) / 2;
        int y = (screenHeight - height) / 2;

        newFrame1.setLocation(x, y);

        newFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame1.setVisible(true);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> new MainClass());

    }

}
