package Assignment2;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryUI extends JFrame {
    private JTextArea historyTextArea;

    public HistoryUI(List<String> calculationHistory) {
        init();
        addComponents(calculationHistory);
    }

    private void init() {
        setTitle("计算历史记录");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addComponents(List<String> calculationHistory) {
            historyTextArea = new JTextArea();
            historyTextArea.setEditable(false); // 设置为只读

            // 将历史记录文本添加到JTextArea中
            for (String calculation : calculationHistory) {
                historyTextArea.append(calculation + "\n");
            }

            JScrollPane scrollPane = new JScrollPane(historyTextArea);
            add(scrollPane, BorderLayout.CENTER);
    }

}
