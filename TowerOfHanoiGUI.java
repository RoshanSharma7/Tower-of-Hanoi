import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TowerOfHanoi {
    private int numDisks;
    private String sourceTower;
    private String targetTower;
    private String auxiliaryTower;

    public TowerOfHanoi(int numDisks, String sourceTower, String targetTower, String auxiliaryTower) {
        this.numDisks = numDisks;
        this.sourceTower = sourceTower;
        this.targetTower = targetTower;
        this.auxiliaryTower = auxiliaryTower;
    }

    public void solve() {
        moveDisks(numDisks, sourceTower, targetTower, auxiliaryTower);
    }

    private void moveDisks(int disks, String source, String target, String auxiliary) {
        if (disks > 0) {
            moveDisks(disks - 1, source, auxiliary, target);
            System.out.println("Move Disk " + disks + " From " + source + " To " + target);
            moveDisks(disks - 1, auxiliary, target, source);
        }
    }
}

public class TowerOfHanoiGUI extends JFrame {
    private JTextField diskField;
    private JComboBox<String> sourceCombo;
    private JComboBox<String> targetCombo;
    private JButton solveButton;
    private JTextArea outputArea;

    public TowerOfHanoiGUI() {
        setTitle("Tower Of Hanoi (Roshan Kumar Sharma)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Disk number input
        JLabel diskLabel = new JLabel("Number Of Disks:");
        diskField = new JTextField(5);
        add(diskLabel);
        add(diskField);

        // Source tower selection
        JLabel sourceLabel = new JLabel("Source Tower:");
        sourceCombo = new JComboBox<>();
        sourceCombo.addItem("A");
        sourceCombo.addItem("B");
        sourceCombo.addItem("C");
        add(sourceLabel);
        add(sourceCombo);

        // Target tower selection
        JLabel targetLabel = new JLabel("Target Tower:");
        targetCombo = new JComboBox<>();
        targetCombo.addItem("A");
        targetCombo.addItem("B");
        targetCombo.addItem("C");
        add(targetLabel);
        add(targetCombo);

        // Solve button
        solveButton = new JButton("Solve");
        add(solveButton);

        // Output area
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane);

        // Solve button click event
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numDisks = Integer.parseInt(diskField.getText());
                String sourceTower = sourceCombo.getSelectedItem().toString();
                String targetTower = targetCombo.getSelectedItem().toString();
                String auxiliaryTower = getAuxiliaryTower(sourceTower, targetTower);
                TowerOfHanoi hanoi = new TowerOfHanoi(numDisks, sourceTower, targetTower, auxiliaryTower);
                hanoi.solve();
                updateOutput(numDisks, sourceTower, targetTower);
            }
        });

        pack();
        setVisible(true);
    }

    private String getAuxiliaryTower(String sourceTower, String targetTower) {
        if (!sourceTower.equals("A") && !targetTower.equals("A")) {
            return "A";
        } else if (!sourceTower.equals("B") && !targetTower.equals("B")) {
            return "B";
        } else {
            return "C";
        }
    }

    private void updateOutput(int numDisks, String sourceTower, String targetTower) {
        outputArea.setText(" Tower Of Hanoi Solution:\n\n");
        TowerOfHanoi hanoi = new TowerOfHanoi(numDisks, sourceTower, targetTower, getAuxiliaryTower(sourceTower, targetTower));
        StringBuilder sb = new StringBuilder();
        moveDisks(hanoi, numDisks, sourceTower, targetTower, sb);
        outputArea.append(sb.toString());
    }

    private void moveDisks(TowerOfHanoi hanoi, int disks, String source, String target, StringBuilder sb) {
        if (disks > 0) {
            String auxiliary = getAuxiliaryTower(source, target);
            moveDisks(hanoi, disks - 1, source, auxiliary, sb);
            sb.append(" Move Disk ").append(disks).append(" From ").append(source).append(" To ").append(target).append("\n");
            moveDisks(hanoi, disks - 1, auxiliary, target, sb);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TowerOfHanoiGUI();
            }
        });
    }
}
