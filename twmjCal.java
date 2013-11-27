package twmjv3;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class twmjCal extends JPanel implements ActionListener, KeyListener {

    ArrayList<Integer> list = new ArrayList<>();
    JLabel inputLabel = new JLabel("請輸入番數 :");
    JTextField input = new JTextField(4);
    JLabel displayLabel = new JLabel("番數 : ");
    JTextField[] display = new JTextField[20];
    JButton add = new JButton("加入");
    JButton finish = new JButton("完成");
    JButton cut = new JButton("Cut");
    JLabel answerLabel = new JLabel("結果: ");
    JTextField answer = new JTextField(4);
    int count, temp, total = 0;

    public twmjCal() {
        FlowLayout flo = new FlowLayout();
        GridLayout grid = new GridLayout(7, 1);
        JPanel pane = new JPanel();
        Color bg = new Color(53, 208, 45);
        pane.setBackground(bg);
        pane.setLayout(grid);

        //add listener
        input.addKeyListener(this);
        add.addActionListener(this);
        add.registerKeyboardAction(add.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        add.registerKeyboardAction(add.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
        finish.addActionListener(this);
        finish.registerKeyboardAction(finish.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        finish.registerKeyboardAction(finish.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
        cut.addActionListener(this);
        cut.registerKeyboardAction(cut.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        cut.registerKeyboardAction(cut.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);


        JPanel sub1 = new JPanel();
        sub1.add(inputLabel);
        sub1.add(input);
        sub1.add(add);
        sub1.add(finish);
        sub1.add(cut);
        sub1.setBackground(null);
        pane.add(sub1);

        JPanel sub2 = new JPanel();
        sub2.add(displayLabel);
        sub2.setBackground(null);
        pane.add(sub2);

        JPanel sub3 = new JPanel();
        sub3.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            display[i * 4] = new JTextField(4);
            display[i * 4].setEditable(false);
            display[i * 4].setFocusable(false);
            display[i * 4].setBackground(null);
            sub3.add(display[i * 4]);
        }
        sub3.setBackground(null);
        pane.add(sub3);

        JPanel sub4 = new JPanel();
        sub4.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            display[i * 4 + 1] = new JTextField(4);
            display[i * 4 + 1].setEditable(false);
            display[i * 4 + 1].setFocusable(false);
            display[i * 4 + 1].setBackground(null);
            sub4.add(display[i * 4 + 1]);
        }
        sub4.setBackground(null);
        pane.add(sub4);

        JPanel sub5 = new JPanel();
        sub5.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            display[i * 4 + 2] = new JTextField(4);
            display[i * 4 + 2].setEditable(false);
            display[i * 4 + 2].setFocusable(false);
            display[i * 4 + 2].setBackground(null);
            sub5.add(display[i * 4 + 2]);
        }
        sub5.setBackground(null);
        pane.add(sub5);

        JPanel sub6 = new JPanel();
        sub6.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            display[i * 4 + 3] = new JTextField(4);
            display[i * 4 + 3].setEditable(false);
            display[i * 4 + 3].setFocusable(false);
            display[i * 4 + 3].setBackground(null);
            sub6.add(display[i * 4 + 3]);
        }
        sub6.setBackground(null);
        pane.add(sub6);



        JPanel sub7 = new JPanel();
        sub7.add(answerLabel);
        answer.setEditable(false);
        sub7.add(answer);
        sub7.setBackground(null);
        pane.add(sub7);

        add(pane);
        input.requestFocusInWindow();
        setVisible(true);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (e.getSource() == input) {
            if (key == KeyEvent.VK_ENTER) {
                addnumber();
                input.setText(null);
                answer.setText(null);
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        // TODO: Do something for the keyTyped event
    }

    public void keyPressed(KeyEvent e) {
        // TODO: Do something for the keyPressed event
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        try {
            if (command.equals("加入")) {
                addnumber();

            }
            if (command.equals("完成")) {
                if (input.getText().length() != 0) {
                    addnumber();
                }
                answer.setText("" + calculation());
            }
            if (command.equals("Cut")) {
                answer.setText("" + total / 2);
                input.requestFocusInWindow();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addnumber() {
        int a = Integer.parseInt(input.getText());
        if (a > 0) {
            list.add(a);
            if (count == 0) {
                for (int i = 0; i < 20; i++) {
                    display[i].setText(null);
                    display[i].setBackground(null);
                }
            }
            display[count].setText(input.getText());
            display[count].setBackground(Color.YELLOW);
            input.setText(null);
            answer.setText(null);
            input.requestFocusInWindow();
            count++;
        }
    }

    public int calculation() {
        temp = list.get(0) / 2 + list.get(0);
        if (list.get(0) % 2 == 1) {
            temp = temp + 1;
        }
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                total = temp + list.get(i);
                temp = total / 2 + total;
                if (total % 2 == 1) {
                    temp = temp + 1;
                }
            }
        } else {
            total = list.get(0);
        }
        list.clear();
        count = 0;
        input.requestFocusInWindow();
        return total;
    }
}