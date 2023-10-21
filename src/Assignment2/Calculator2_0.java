
package Assignment2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Calculator2_0 extends JFrame implements ActionListener {

    // 北面控件
    private JPanel jp_north = new JPanel();// 声明一个面板
    private JTextField input_text = new JTextField(); // 输入框
    private JButton c_Btn = new JButton("清空");// 清空按钮

    // 中间控件
    private JPanel jp_center = new JPanel(); // 声明一个面板

    // 计算记录
    private List<String> calculationHistory = new ArrayList<>();

    // 主面板
    public Calculator2_0() throws HeadlessException {
        this.init();// 调用方法init
        this.addNorthComponent();// 加载调用北面的控件
        this.addCenterButton();// 加载调用中间的控件
    }

    // 初始化方法
    public void init() {
        this.setTitle("计算器 2.0"); // 标题
        this.setSize(400, 600);// 大小
        this.setLayout(new BorderLayout());// 边框布局
        this.setResizable(true);// 窗体可以拉伸
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口的时候，退出程序
    }

    // 添加北面的控件
    public void addNorthComponent() {
        this.input_text.setPreferredSize(new Dimension(230, 30));
        jp_north.add(input_text);

        this.c_Btn.setForeground(Color.blue);
        jp_north.add(c_Btn);

        c_Btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                input_text.setText("");
            }
        });

        this.add(jp_north, BorderLayout.NORTH); // 控件放到窗体北面
    }

    // 添加中间的控件
    public void addCenterButton() {
        String bth_text = "123+456-789*.0=/%";
        this.jp_center.setLayout(new GridLayout(5, 5));// 声明一个5x5的网格
        for (int i = 0; i < 17; i++) {
            String temp = bth_text.substring(i, i + 1);
            JButton btn = new JButton();
            btn.setText(temp);
            if (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/") || temp.equals(".")
                    || temp.equals("=") || temp.equals("%")) {
                btn.setFont(new Font("粗体", Font.BOLD, 20));
                btn.setForeground(Color.red);
            }
            btn.addActionListener(this);
            jp_center.add(btn);
        }
        // 单独为历史记录设置一个按钮
        JButton btn = new JButton();
        btn.setText("历史记录");
        btn.setFont(new Font("粗体", Font.BOLD, 10));
        btn.setForeground(Color.red);
        btn.addActionListener(this);
        jp_center.add(btn);

        this.add(jp_center, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Calculator2_0 calculator20 = new Calculator2_0();
        calculator20.setVisible(true); // 显示GUI组件
    }

    private String firstInput = null;// 记录第一次输入的数
    private String symbol = null;// 记录操作符

    @Override


    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String click = e.getActionCommand(); // 获取点击到的值

        if (".0123456789".indexOf(click) != -1) {
            this.input_text.setText(input_text.getText() + click);
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);
        } else if (click.matches("[\\+\\-*/]{1}")) {
            symbol = click;
            firstInput = this.input_text.getText();
            this.input_text.setText("");
        } else if (click.equals("=")) {
            Double a = Double.valueOf(firstInput);
            Double b = Double.valueOf(this.input_text.getText());
            Double result = null;
            switch (symbol) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b != 0) {
                        result = a / b;
                    }
                    break;
                case "±":  // 处理负数的情况
                    result = -b;
                    break;
            }
            this.input_text.setText(result.toString());

            String calculation = firstInput + symbol + b + "=" + result.toString(); // 构建计算表达式和结果的字符串
            calculationHistory.add(calculation); // 将计算历史记录添加到列表中

            HistoryUI historyUI = new HistoryUI(calculationHistory);
            historyUI.setVisible(true);
        }
    }

}

