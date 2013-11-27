package twmjv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TaiwanMjframe31 extends JFrame {

    TaiwanMjevent31 mj = new TaiwanMjevent31(this);
    twmjCal calFrame = new twmjCal();
    TwmjFanTable fanTable = new TwmjFanTable();
    JRadioButton eat;
    JRadioButton selfeat;
    JRadioButton oneLoseThree;
    JRadioButton oneWinThree;
    ButtonGroup selectiongroup;
    JRadioButton[] winButton;
    JRadioButton[] loseButton;
    JRadioButton[] masterButton;
    JTextField roundNumber, cfanText;
    JTextField[] fanNumber;
    JButton addButton;
    JButton dresultButton;
    JButton endButton;
    JButton clearButton;
    JButton undo;
    ButtonGroup wingroup;
    ButtonGroup losegroup;
    ButtonGroup mastergroup;
    JTextField[] result;
    JTextField[] numberOfLose;
    JLabel space, space2, space3, round, cfan;
    JTabbedPane tab = new JTabbedPane();
    JPanel pane1 = new JPanel();
    JPanel pane2 = new JPanel();
    JPanel pane3 = new JPanel();
    JPanel pane4 = new JPanel();
    JTable table;
    Vector data = new Vector();
    Vector columnNumbers = new Vector();
    JScrollPane scroll;
    String[] player = new String[424];
    JScrollBar sb = new JScrollBar();
    Color bg = new Color(53, 208, 45);

    public TaiwanMjframe31() {
        super("奕雲台牌三缺一V3.13");
        setLookAndFeel();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String ObjButtons[] = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "離開?", "台灣麻雀", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //set player
        player[0] = JOptionPane.showInputDialog(null, "第一位參賽者", "Player 1", JOptionPane.QUESTION_MESSAGE);
        player[1] = JOptionPane.showInputDialog(null, "第二位參賽者", "Player 2", JOptionPane.QUESTION_MESSAGE);
        player[2] = JOptionPane.showInputDialog(null, "第三位參賽者", "Player 3", JOptionPane.QUESTION_MESSAGE);
        player[3] = JOptionPane.showInputDialog(null, "第四位參賽者", "Player 4", JOptionPane.QUESTION_MESSAGE);


        //tabs
        GridLayout grid = new GridLayout(8, 1);
        pane1.setLayout(grid);
        pane1.setBackground(bg);


        //Row 1
        JPanel row1 = new JPanel();
        row1.setLayout(new FlowLayout(FlowLayout.CENTER));
        round = new JLabel("局數:");
        row1.add(round);
        roundNumber = new JTextField("1", 2);
        row1.add(roundNumber);
        roundNumber.setEditable(false);
        roundNumber.setFocusable(false);
        eat = new JRadioButton("食糊");
        eat.addActionListener(mj);
        selfeat = new JRadioButton("自摸");
        selfeat.addActionListener(mj);
        oneLoseThree = new JRadioButton("俾3家");
        oneLoseThree.addActionListener(mj);
        oneWinThree = new JRadioButton("收3家");
        oneWinThree.addActionListener(mj);
        selectiongroup = new ButtonGroup();
        selectiongroup.add(eat);
        selectiongroup.add(selfeat);
        selectiongroup.add(oneLoseThree);
        selectiongroup.add(oneWinThree);
        row1.add(eat);
        row1.add(selfeat);
        row1.add(oneLoseThree);
        row1.add(oneWinThree);
        row1.setBackground(bg);
        pane1.add(row1);


        // Row 2
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1, 5));
        JLabel winner = new JLabel("贏家: ", SwingConstants.RIGHT);
        winButton = new JRadioButton[4];
        winButton[0] = new JRadioButton(player[0]);
        winButton[0].setActionCommand("W1");
        winButton[1] = new JRadioButton(player[1]);
        winButton[1].setActionCommand("W2");
        winButton[2] = new JRadioButton(player[2]);
        winButton[2].setActionCommand("W3");
        winButton[3] = new JRadioButton(player[3]);
        winButton[3].setActionCommand("W4");
        row2.add(winner);
        wingroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            winButton[i].addActionListener(mj);
            winButton[i].setEnabled(false);
            wingroup.add(winButton[i]);
            row2.add(winButton[i]);
        }
        row2.setBackground(bg);
        pane1.add(row2);


        //Row 3
        JPanel row3 = new JPanel();
        row3.setLayout(new GridLayout(1, 5));
        JLabel loser = new JLabel("出沖: ", SwingConstants.RIGHT);
        loseButton = new JRadioButton[4];
        loseButton[0] = new JRadioButton(player[0]);
        loseButton[0].setActionCommand("L1");
        loseButton[1] = new JRadioButton(player[1]);
        loseButton[1].setActionCommand("L2");
        loseButton[2] = new JRadioButton(player[2]);
        loseButton[2].setActionCommand("L3");
        loseButton[3] = new JRadioButton(player[3]);
        loseButton[3].setActionCommand("L4");
        row3.add(loser);
        losegroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            losegroup.add(loseButton[i]);
            loseButton[i].addActionListener(mj);
            loseButton[i].setEnabled(false);
            row3.add(loseButton[i]);
        }
        row3.setBackground(bg);
        pane1.add(row3);

        //Row4
        JPanel row4 = new JPanel();
        row4.setLayout(new GridLayout(1, 5));

        JLabel master = new JLabel("莊:", SwingConstants.RIGHT);
        masterButton = new JRadioButton[4];
        masterButton[0] = new JRadioButton(player[0]);
        masterButton[0].setActionCommand("M1");
        masterButton[1] = new JRadioButton(player[1]);
        masterButton[1].setActionCommand("M2");
        masterButton[2] = new JRadioButton(player[2]);
        masterButton[2].setActionCommand("M3");
        masterButton[3] = new JRadioButton(player[3]);
        masterButton[3].setActionCommand("M4");
        mastergroup = new ButtonGroup();
        row4.add(master);
        for (int i = 0; i < 4; i++) {
            mastergroup.add(masterButton[i]);
            masterButton[i].addActionListener(mj);
            masterButton[i].setEnabled(false);
            row4.add(masterButton[i]);
        }
        row4.setBackground(bg);
        pane1.add(row4);


        //Row 5
        JPanel row5 = new JPanel();
        JLabel fanLabel = new JLabel("番數");
        row5.add(fanLabel);
        fanNumber = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            fanNumber[i] = new JTextField(4);
            fanNumber[i].setBackground(null);
            fanNumber[i].setFocusable(false);
            row5.add(fanNumber[i]);
        }
        row5.setBackground(bg);
        pane1.add(row5);

        //row 6
        JPanel row6 = new JPanel();
        cfan = new JLabel("莊連番:");
        cfanText = new JTextField("0", 2);
        cfanText.setEditable(false);
        cfanText.setBackground(null);
        addButton = new JButton("入賬");
        addButton.addActionListener(mj);
        dresultButton = new JButton("預覽");
        dresultButton.addActionListener(mj);
        endButton = new JButton("結算");
        endButton.addActionListener(mj);
        clearButton = new JButton("清除");
        clearButton.addActionListener(mj);
        undo = new JButton("Undo");
        undo.addActionListener(mj);
        undo.setEnabled(false);
        row6.add(cfan);
        row6.add(cfanText);
        row6.add(addButton);
        row6.add(dresultButton);
        row6.add(endButton);
        row6.add(clearButton);
        row6.add(undo);
        row6.setBackground(bg);
        pane1.add(row6);

        //Row7
        JPanel row7 = new JPanel();
        JLabel loseRound = new JLabel("被拉:", SwingConstants.RIGHT);
        row7.add(loseRound);
        numberOfLose = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            numberOfLose[i] = new JTextField("0", 4);
            numberOfLose[i].setEditable(false);
            numberOfLose[i].setFocusable(false);
            row7.add(numberOfLose[i]);
        }
        space3 = new JLabel("");
        row7.add(space3);
        row7.setBackground(bg);
        pane1.add(row7);



        //Row 8
        JPanel row8 = new JPanel();
        JLabel totalLabel = new JLabel("結果: ");
        row8.add(totalLabel);
        result = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            result[i] = new JTextField(4);
            result[i].setEditable(false);
            result[i].setFocusable(false);
            row8.add(result[i]);
        }
        row8.setBackground(bg);
        pane1.add(row8);


//Table 
        columnNumbers.add("局數");
        columnNumbers.add(player[0]);
        columnNumbers.add(player[1]);
        columnNumbers.add(player[2]);
        columnNumbers.add(player[3]);
        table = new JTable(data, columnNumbers);
        table.setRowHeight(30);
        table.setFocusable(false);
        table.setEnabled(false);
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(600, 650));
        pane2.setBackground(bg);
        pane2.add(scroll);


        // add TMMJ Calculator
        pane3.setBackground(bg);
        pane3.add(calFrame);

        //add TWMJ FAN Table
        pane4.setBackground(bg);
        pane4.add(fanTable);


        // Decoration
        addButton.registerKeyboardAction(addButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        addButton.registerKeyboardAction(addButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
        dresultButton.registerKeyboardAction(dresultButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        dresultButton.registerKeyboardAction(dresultButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
        endButton.registerKeyboardAction(endButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        endButton.registerKeyboardAction(endButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);



        clearButton.registerKeyboardAction(clearButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        clearButton.registerKeyboardAction(clearButton.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);

        tab.add("賽果計算", pane1);
        tab.add("記錄", pane2);
        tab.add("拉莊計算機", pane3);
        tab.add("番數表", pane4);
        add(tab);
        setLookAndFeel();
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.getLookAndFeelDefaults()
                    .put("defaultFont", new Font(Font.SANS_SERIF, Font.BOLD, 28));
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            System.err.println("Couldn't use the system " + "look and feel: " + e);
        }
    }

    public static void main(String[] args) {
        new TaiwanMjframe31();
    }
}