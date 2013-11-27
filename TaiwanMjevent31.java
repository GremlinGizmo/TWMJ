package twmjv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TaiwanMjevent31 implements ActionListener {

    TaiwanMjframe31 gui;
    int winner, loser, master, selection, r, tempr;
    int fanValue, cfanValue;
    int[] sum = new int[4];
    Boolean[] currentWinner = new Boolean[4];
    Boolean[] historyWinner = new Boolean[4];
    Boolean[] backWinner = new Boolean[4];
    ArrayList<ArrayList<ArrayList<Integer>>> tempList = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<Integer>>> backtempList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> finalList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> backfinalList = new ArrayList<>();
    Vector<Vector<String>> record = new Vector();
    Vector<Vector<String>> backrecord = new Vector();

    public TaiwanMjevent31(TaiwanMjframe31 in) {
        gui = in;
        winner = loser = master = selection = -1;
        r = 0;
        for (int w = 0; w < 4; w++) {
            tempList.add(new ArrayList<ArrayList<Integer>>());
            backtempList.add(new ArrayList<ArrayList<Integer>>());
            finalList.add(new ArrayList<Integer>());
            finalList.get(w).add(0);
            backfinalList.add(new ArrayList<Integer>());
            backfinalList.get(w).add(0);
            currentWinner[w] = false;
            historyWinner[w] = false;
            for (int i = 0; i < 4; i++) {
                tempList.get(w).add(new ArrayList<Integer>());
                tempList.get(w).get(i).add(0);
                backtempList.get(w).add(new ArrayList<Integer>());
                backtempList.get(w).get(i).add(0);
            }
        }
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        switch (command) {
            case "食糊":
                selection = 0;
                selectionButton();
                break;
            case "自摸":
                selection = 1;
                selectionButton();
                break;
            case "俾3家":
                selection = 2;
                selectionButton();
                break;
            case "收3家":
                selection = 3;
                selectionButton();
                break;

            case "W1":
                winner = 0;
                winbuttonSelection();
                break;
            case "W2":
                winner = 1;
                winbuttonSelection();
                break;
            case "W3":
                winner = 2;
                winbuttonSelection();
                break;
            case "W4":
                winner = 3;
                winbuttonSelection();
                break;

            case "L1":
                loser = 0;
                losebuttonSelection();
                break;
            case "L2":
                loser = 1;
                losebuttonSelection();
                break;
            case "L3":
                loser = 2;
                losebuttonSelection();
                break;
            case "L4":
                loser = 3;
                losebuttonSelection();
                break;

            case "M1":
                master = 0;
                masterButton();
                break;
            case "M2":
                master = 1;
                masterButton();
                break;
            case "M3":
                master = 2;
                masterButton();
                break;
            case "M4":
                master = 3;
                masterButton();
                break;

            case "入賬":
                if (checkinput()) {
                    copyList();
                    if (selection == 0 & winner != -1 & loser != -1) {
                        setCurrentWinner();
                        for (int w = 0; w < 4; w++) {
                            if (historyWinner[w] != currentWinner[w] & currentWinner[w] == false) {
                                runCalculation(w);
                            }
                        }
                        clearTempList();
                        addNumber();
                        break;
                    } else if (selection == 1 & winner != -1) {
                        setCurrentWinner();
                        for (int w = 0; w < 4; w++) {
                            if (historyWinner[w] != currentWinner[w] & currentWinner[w] == false) {
                                runCalculation(w);
                            }
                        }
                        clearTempList();
                        addNumber();
                        break;
                    } else if (selection == 2 & loser != -1) {
                        addBonusToThree();
                        break;
                    } else if (selection == 3 & winner != -1) {
                        addBonusToOne();
                        break;
                    } else {
                        break;
                    }
                }
                break;
            case "預覽":
                showResult();
                break;
            case "結算":
                if (JOptionPane.showConfirmDialog(null, "確認結算?", "台灣麻雀", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    finalCalculation();
                    gui.endButton.setEnabled(false);
                    gui.addButton.setEnabled(false);
                    gui.dresultButton.setEnabled(false);
                    showResult();
                }
                break;
            case "清除":
                if (JOptionPane.showConfirmDialog(null, "清除資料?", "台灣麻雀", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    completeClear();
                    gui.endButton.setEnabled(true);
                    gui.addButton.setEnabled(true);
                    gui.dresultButton.setEnabled(true);
                }
                break;
            case "Undo":
                undo();
                break;
        }
    }

    public void selectionButton() {
        loser = -1;
        winner = -1;
        master = -1;
        gui.cfanText.setBackground(null);
        gui.cfanText.setEditable(false);
        gui.cfanText.setFocusable(false);
        gui.cfanText.setText("0");
        for (int i = 0; i < 4; i++) {
            gui.wingroup.add(gui.winButton[i]);
            gui.fanNumber[i].setEditable(false);
            gui.fanNumber[i].setBackground(null);
            gui.fanNumber[i].setFocusable(false);
            gui.fanNumber[i].setText(null);
            gui.wingroup.clearSelection();
            gui.losegroup.clearSelection();
            gui.mastergroup.clearSelection();
            gui.winButton[i].setEnabled(false);
            gui.loseButton[i].setEnabled(false);
            gui.fanNumber[i].setEditable(false);
            gui.masterButton[i].setEnabled(false);
            if (selection != 2 & selection != 0) {
                gui.winButton[i].setEnabled(true);
            } else {
                gui.loseButton[i].setEnabled(true);
            }
            if (selection == 0) {
                gui.loseButton[i].setEnabled(false);
                gui.wingroup.remove(gui.winButton[i]);
                gui.winButton[i].setEnabled(true);
            }
        }
    }

    public void winbuttonSelection() {
        if (selection == 0) {
            for (int i = 0; i < 4; i++) {
                gui.fanNumber[i].setEditable(false);
                gui.fanNumber[i].setBackground(null);
                gui.fanNumber[i].setFocusable(false);
                if (gui.winButton[i].isSelected() & gui.loseButton[i].isSelected()) {
                    gui.loseButton[i].setSelected(false);
                    gui.loseButton[i].setEnabled(false);
                    gui.losegroup.clearSelection();
                    gui.fanNumber[i].setEditable(false);
                    gui.fanNumber[i].setBackground(null);
                    gui.fanNumber[i].setFocusable(false);
                    loser = -1;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (gui.winButton[i].isSelected() & loser != -1 & !gui.loseButton[i].isSelected()) {
                    gui.fanNumber[i].setEditable(true);
                    gui.fanNumber[i].setBackground(Color.yellow);
                    gui.fanNumber[i].setFocusable(true);
                }
                if (gui.winButton[i].isSelected()) {
                    gui.loseButton[i].setEnabled(false);
                } else {
                    gui.loseButton[i].setEnabled(true);
                    gui.fanNumber[i].setText(null);
                }
            }
        }

        if (selection == 1) {
            for (int i = 0; i < 4; i++) {
                gui.loseButton[i].setEnabled(false);
                gui.fanNumber[i].setEditable(false);
                gui.fanNumber[i].setBackground(null);
                gui.fanNumber[i].setFocusable(false);
                gui.fanNumber[i].setText(null);
                gui.masterButton[i].setEnabled(true);
                if (gui.winButton[i].isSelected()) {
                    if (master != -1) {
                        gui.fanNumber[i].setEditable(true);
                        gui.fanNumber[i].setBackground(Color.yellow);
                        gui.fanNumber[i].setFocusable(true);
                    }
                }
            }
        }

        if (selection == 3) {
            for (int i = 0; i < 4; i++) {
                gui.loseButton[i].setEnabled(false);
                gui.fanNumber[i].setEditable(false);
                gui.fanNumber[i].setBackground(null);
                gui.fanNumber[i].setFocusable(false);
                gui.fanNumber[i].setText(null);
                if (gui.winButton[i].isSelected()) {
                    gui.fanNumber[i].setEditable(true);
                    gui.fanNumber[i].setBackground(Color.yellow);
                    gui.fanNumber[i].setFocusable(true);
                }
            }
        }
    }

    public void losebuttonSelection() {
        if (selection == 0) {
            for (int i = 0; i < 4; i++) {
                if (gui.winButton[i].isSelected() & loser != -1 & !gui.loseButton[i].isSelected()) {
                    gui.fanNumber[i].setEditable(true);
                    gui.fanNumber[i].setBackground(Color.yellow);
                    gui.fanNumber[i].setFocusable(true);
                }
                if (gui.loseButton[i].isSelected()) {
                    gui.winButton[i].setSelected(false);
                    gui.fanNumber[i].setEditable(false);
                    gui.fanNumber[i].setBackground(null);
                    gui.fanNumber[i].setFocusable(false);
                }
                if (gui.winButton[i].isSelected()) {
                    gui.fanNumber[i].setEditable(true);
                    gui.fanNumber[i].setBackground(Color.yellow);
                    gui.fanNumber[i].setFocusable(true);
                }
            }
        }
        if (selection == 2) {
            for (int i = 0; i < 4; i++) {
                gui.winButton[i].setEnabled(false);
                gui.fanNumber[i].setEditable(false);
                gui.fanNumber[i].setBackground(null);
                gui.fanNumber[i].setFocusable(false);
                gui.fanNumber[i].setText(null);
                gui.masterButton[i].setEnabled(false);
                if (gui.loseButton[i].isSelected()) {
                    gui.fanNumber[i].setEditable(true);
                    gui.fanNumber[i].setBackground(Color.yellow);
                    gui.fanNumber[i].setFocusable(true);
                }
            }
        }
    }

    public void masterButton() {
        if (selection == 1) {
            for (int i = 0; i < 4; i++) {
                if (gui.winButton[i].isSelected()) {
                    gui.fanNumber[i].setEditable(true);
                    gui.fanNumber[i].setBackground(Color.yellow);
                    gui.fanNumber[i].setFocusable(true);
                    gui.cfanText.setText("1");
                    gui.cfanText.setBackground(Color.yellow);
                    gui.cfanText.setEditable(true);
                    gui.cfanText.setFocusable(true);
                }
            }
        }
    }

    public boolean checkinput() {
        if (selection == -1) {
            return false;
        }
        if (selection == 0) {
            if (winner == -1 | loser == -1) {
                return false;
            }
            setCurrentWinner();
            for (int w = 0; w < 4; w++) {
                if (currentWinner[w]) {
                    try {
                        fanValue = Integer.parseInt(gui.fanNumber[w].getText());
                        if (fanValue < 0) {
                            return false;
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error : " + nfe.getMessage());
                        return false;
                    }
                }
            }
        }

        if (selection == 1) {
            if (winner == -1 | master == -1) {
                return false;
            }
            try {
                fanValue = Integer.parseInt(gui.fanNumber[winner].getText());
                cfanValue = Integer.parseInt(gui.cfanText.getText());
                if (fanValue < 0 | cfanValue < 0) {
                    return false;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error : " + nfe.getMessage());
                return false;
            }
        }

        if (selection == 2) {
            if (loser == -1) {
                return false;
            }
            try {
                fanValue = Integer.parseInt(gui.fanNumber[loser].getText());
                if (fanValue < 0) {
                    return false;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error : " + nfe.getMessage());
                return false;
            }
        }

        if (selection == 3) {
            if (winner == -1) {
                return false;
            }
            try {
                fanValue = Integer.parseInt(gui.fanNumber[winner].getText());
                if (fanValue < 0) {
                    return false;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error : " + nfe.getMessage());
                return false;
            }
        }

        return true;
    }

    public void addNumber() {
        if (selection == 0) {
            for (int w = 0; w < 4; w++) {
                if (currentWinner[w]) {
                    fanValue = Integer.parseInt(gui.fanNumber[w].getText());
                    tempList.get(w).get(loser).add(0 - fanValue);
                    historyWinner[w] = true;
                }
            }
        }

        if (selection == 1) {
            for (int i = 0; i < 4; i++) {
                if (i == master) {
                    tempList.get(winner).get(i).add(0 - (fanValue + cfanValue));
                } else {
                    tempList.get(winner).get(i).add(0 - fanValue);
                }
            }
            tempList.get(winner).get(winner).remove(tempList.get(winner).get(winner).size() - 1);
            historyWinner[winner] = true;
        }
        recordList();
        r++;
        gui.roundNumber.setText("" + (r + 1));
        cutOption();
        numberOfLose();
        clearStatus();
    }

    public void setCurrentWinner() {
        for (int i = 0; i < 4; i++) {
            if (gui.winButton[i].isSelected()) {
                currentWinner[i] = true;
            } else {
                currentWinner[i] = false;
            }
        }
    }

    public void clearTempList() {
        for (int w = 0; w < 4; w++) {
            if (currentWinner[w] != historyWinner[w] & currentWinner[w] == false) {
                for (int i = 0; i < 4; i++) {
                    tempList.get(w).get(i).clear();
                    tempList.get(w).get(i).add(0);
                }
                historyWinner[w] = false;
            }
        }
    }

    public void addBonusToThree() {
        for (int w = 0; w < 4; w++) {
            finalList.get(w).add(fanValue);
        }
        finalList.get(loser).remove(finalList.get(loser).size() - 1);
        finalList.get(loser).add(0 - (fanValue * 3));
        recordList();
        clearStatus();
    }

    public void addBonusToOne() {
        for (int w = 0; w < 4; w++) {
            finalList.get(w).add(0 - fanValue);
        }
        finalList.get(winner).remove(finalList.get(winner).size() - 1);
        finalList.get(winner).add(fanValue * 3);
        recordList();
        clearStatus();
    }

    public void cutOption() {
        for (int w = 0; w < 4; w++) {
            for (int i = 0; i < 4; i++) {
                if ((tempList.get(w).get(i).size() - 1) % 3 == 0 & tempList.get(w).get(i).size() != 1) {
                    int cutresponse = JOptionPane.showConfirmDialog(null, gui.player[i] + "俾" + gui.player[w] + "拉緊, 想CUT嘛?",
                            "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (cutresponse == 0) {
                        standaloneCalculation(w, i);
                    }
                }
            }
        }
    }

    public void runCalculation(int w) {
        int winnerwin = 0;
        for (int i = 0; i < 4; i++) {
            int temp = 0;
            int total = 0;
            if (tempList.get(w).get(i).size() > 1) {
                for (int j = 0; j < tempList.get(w).get(i).size(); j++) {
                    total = temp + tempList.get(w).get(i).get(j);
                    temp = total / 2 + total;
                    if (total % 2 == -1) {
                        temp = temp - 1;
                    }
                }
            }
            if (selection == 0) {
                if (historyWinner[w] & gui.loseButton[w].isSelected() & gui.winButton[i].isSelected()) {
                    total = total / 2;
                }
            }
            if (selection == 1) {
                if (historyWinner[w] & gui.winButton[i].isSelected()) {
                    total = total / 2;
                }
            }
            finalList.get(i).add(total);
            winnerwin += total;
        }
        finalList.get(w).add(0 - winnerwin);
    }

    public void standaloneCalculation(int w, int i) {
        int temp = 0;
        int total = 0;
        if (tempList.get(w).get(i).size() > 1) {
            for (int j = 0; j < tempList.get(w).get(i).size(); j++) {
                total = temp + tempList.get(w).get(i).get(j);
                temp = total / 2 + total;
                if (total % 2 == -1) {
                    temp = temp - 1;
                }
            }
        }
        finalList.get(i).add(total / 2);
        finalList.get(w).add(0 - (total / 2));
        tempList.get(w).get(i).clear();
        tempList.get(w).get(i).add(0);
    }

    public void finalCalculation() {
        for (int w = 0; w < 4; w++) {
            int winnerwin = 0;
            for (int i = 0; i < 4; i++) {
                int temp = 0;
                int total = 0;
                if (tempList.get(w).get(i).size() > 1) {
                    for (int j = 0; j < tempList.get(w).get(i).size(); j++) {
                        total = temp + tempList.get(w).get(i).get(j);
                        temp = total / 2 + total;
                        if (total % 2 == -1) {
                            temp = temp - 1;
                        }
                    }
                }
                finalList.get(i).add(total);
                winnerwin += total;
            }
            finalList.get(w).add(0 - winnerwin);
        }
    }

    public void showResult() {
        for (int w = 0; w < 4; w++) {
            sum[w] = 0;
            for (int i = 0; i < finalList.get(w).size(); i++) {
                sum[w] = sum[w] + finalList.get(w).get(i);
            }
            gui.result[w].setText("" + sum[w]);
        }
    }

    public void clearStatus() {
        for (int i = 0; i < 4; i++) {
            gui.winButton[i].setEnabled(false);
            gui.loseButton[i].setEnabled(false);
            gui.masterButton[i].setEnabled(false);
            gui.fanNumber[i].setText(null);
            gui.fanNumber[i].setEditable(false);
            gui.fanNumber[i].setBackground(null);
            gui.fanNumber[i].setFocusable(false);
            gui.wingroup.add(gui.winButton[i]);
        }
        gui.selectiongroup.clearSelection();
        gui.wingroup.clearSelection();
        gui.losegroup.clearSelection();
        gui.mastergroup.clearSelection();
        gui.cfanText.setBackground(null);
        gui.cfanText.setEditable(false);
        gui.cfanText.setFocusable(false);
        loser = -1;
        winner = -1;
        master = -1;
        selection = -1;
    }

    public void completeClear() {
        for (int w = 0; w < 4; w++) {
            for (int i = 0; i < 4; i++) {
                tempList.get(w).get(i).clear();
                tempList.get(w).get(i).add(0);
                master = -1;
                gui.result[w].setText(null);
                gui.numberOfLose[i].setText("0");
            }
            finalList.get(w).clear();
            finalList.get(w).add(0);
            gui.fanNumber[w].setText(null);
            sum[w] = 0;
        }
        r = 0;
        gui.roundNumber.setText("" + (r + 1));
        clearStatus();
        record.clear();
        gui.data.clear();
        gui.undo.setEnabled(false);
        gui.pane2.add(gui.scroll);
    }

    public void numberOfLose() {
        for (int w = 0; w < 4; w++) {
            if (currentWinner[w] == true) {
                for (int i = 0; i < 4; i++) {
                    gui.numberOfLose[i].setText("" + (tempList.get(w).get(i).size() - 1));
                }
            }
        }
    }

    public void recordList() {
        int[] tempfan = new int[4];
        int totaltempfan = 0;
        for (int i = 0; i < 4; i++) {
            if (currentWinner[i] == true & (selection == 0 | selection == 1)) {
                tempfan[i] = Integer.parseInt(gui.fanNumber[i].getText());
                totaltempfan = totaltempfan + tempfan[i];
            }
        }

        record.add(new Vector());
        for (int w = 0; w < 5; w++) {
            record.get(record.size() - 1).add("");
        }

        if (selection == 0 & loser != -1) {
            record.get(record.size() - 1).set(0, "" + (r + 1));
            record.get(record.size() - 1).set((loser + 1), "" + (0 - totaltempfan));
            for (int w = 0; w < 4; w++) {
                if (currentWinner[w] == true) {
                    record.get(record.size() - 1).set((w + 1), "(" + tempfan[w] + ")");
                }
            }
        } else if (selection == 1) {
            record.get(record.size() - 1).set(0, "" + (r + 1));
            for (int i = 1; i < 5; i++) {
                if (i == (master + 1)) {
                    record.get(record.size() - 1).set(i, "" + (0 - (fanValue + cfanValue)));
                } else {
                    record.get(record.size() - 1).set(i, "" + (0 - fanValue));
                }
            }
            for (int w = 0; w < 4; w++) {
                if (currentWinner[w] == true & w == master) {
                    record.get(record.size() - 1).set((w + 1), "(" + (tempfan[w] * 3) + ")");
                } else if (currentWinner[w] == true) {
                    record.get(record.size() - 1).set((w + 1), "(" + (tempfan[w] * 3 + cfanValue) + ")");
                }
            }
        }
        if (selection == 2) {
            for (int w = 0; w < 5; w++) {
                record.get(record.size() - 1).set(w, "" + fanValue);
            }
            record.get(record.size() - 1).set(0, "");
            record.get(record.size() - 1).set(loser + 1, "" + (0 - (fanValue * 3)));
        }
        if (selection == 3) {
            for (int w = 0; w < 5; w++) {
                record.get(record.size() - 1).set(w, "" + (0 - fanValue));
            }
            record.get(record.size() - 1).set(0, "");
            record.get(record.size() - 1).set(winner + 1, "" + fanValue * 3);
        }
        gui.undo.setEnabled(true);
        gui.data.add(record.get(record.size() - 1));
        gui.pane2.add(gui.scroll);
        gui.table.scrollRectToVisible(gui.table.getCellRect(gui.table.getRowCount() - 1, 0, true));



    }

    public void copyList() {
        tempr = r;
        //backrecord list clear and create
        backrecord.clear();
        for (int i = 0; i < record.size(); i++) {
            backrecord.add(new Vector());
            for (int w = 0; w < 5; w++) {
                backrecord.get(i).add("");
            }
        }

        //copy record list
        for (int i = 0; i < (record.size()); i++) {
            Collections.copy(backrecord.get(i), record.get(i));
        }


        //clear backtemplist and backfinal list
        for (int w = 0; w < 4; w++) {
            for (int i = 0; i < 4; i++) {
                backtempList.get(w).get(i).clear();
                backtempList.get(w).get(i).add(0);
            }
            backfinalList.get(w).clear();
            backfinalList.get(w).add(0);
        }

        //copy templist
        for (int w = 0; w < 4; w++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < tempList.get(w).get(i).size(); j++) {
                    backtempList.get(w).get(i).add(0);
                }
            }
            for (int i = 0; i < 4; i++) {
                Collections.copy(backtempList.get(w).get(i), tempList.get(w).get(i));
            }
        }

        //copy final list 
        for (int w = 0; w < 4; w++) {
            for (int i = 1; i < finalList.get(w).size(); i++) {
                backfinalList.get(w).add(0);
            }
            for (int i = 0; i < 4; i++) {
                Collections.copy(backfinalList.get(w), finalList.get(w));
            }
        }

        //copy winner
        for (int w = 0; w < 4; w++) {
            if (currentWinner[w] == true) {
                backWinner[w] = true;
            } else {
                backWinner[w] = false;
            }
        }

    }

    public void undo() {
        //record list clear
        record.clear();
        for (int i = 0; i < (backrecord.size()); i++) {
            record.add(new Vector());
            for (int w = 0; w < 5; w++) {
                record.get(i).add("");
            }
        }
        //copy record list
        for (int i = 0; i < (backrecord.size()); i++) {
            Collections.copy(record.get(i), backrecord.get(i));
        }


        //clear templist and final list
        for (int w = 0; w < 4; w++) {
            for (int i = 0; i < 4; i++) {
                tempList.get(w).get(i).clear();
                tempList.get(w).get(i).add(0);
                master = -1;
                gui.result[w].setText(null);
                gui.numberOfLose[i].setText("0");
            }
            finalList.get(w).clear();
            finalList.get(w).add(0);
            gui.fanNumber[w].setText(null);
            sum[w] = 0;
        }

        //restore  templist
        for (int w = 0; w < 4; w++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < backtempList.get(w).get(i).size(); j++) {
                    tempList.get(w).get(i).add(0);
                }
            }
            for (int i = 0; i < 4; i++) {
                Collections.copy(tempList.get(w).get(i), backtempList.get(w).get(i));
            }
        }

        //restore final list 
        for (int w = 0; w < 4; w++) {
            for (int i = 1; i < backfinalList.get(w).size(); i++) {
                finalList.get(w).add(0);
            }
            for (int i = 0; i < 4; i++) {
                Collections.copy(finalList.get(w), backfinalList.get(w));
            }
        }

        //restore winner
        for (int w = 0; w < 4; w++) {
            if (backWinner[w] == true) {
                currentWinner[w] = true;
            } else {
                currentWinner[w] = false;
            }
        }
        gui.undo.setEnabled(false);
        r = tempr;
        gui.roundNumber.setText("" + (r + 1));
        gui.data.remove(record.size());
        numberOfLose();
    }
}