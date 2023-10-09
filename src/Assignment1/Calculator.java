package Assignment1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator  extends JFrame implements ActionListener{
    //北面控件
    private JPanel jp_north = new JPanel();//声明一个面板
    private JTextField input_text = new JTextField();  //输入框
    private JButton c_Btn = new JButton("清空");//清空按钮

    //中间控件
    private JPanel jp_center = new JPanel(); //声明一个面板

    //主面板
    public Calculator() throws HeadlessException{
        this.init();//调用方法init
        this.addNorthComponent();//加载调用北面的控件
        this.addCenterButton();//加载调用中间的控件

    }
    //初始化方法
    public void init() {
        this.setTitle("计算器 1.0"); //标题
        this.setSize(400, 600);//大小
        this.setLayout(new BorderLayout());//边框布局
        this.setResizable(true);//窗体可以拉伸
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口的时候，退出程序

    }

    //添加北面的控件
    public void addNorthComponent() {
        this.input_text.setPreferredSize(new Dimension(230,30));
        jp_north.add(input_text);

        this.c_Btn.setForeground(Color.blue);
        jp_north.add(c_Btn);

        c_Btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                input_text.setText("");
            }
        });


        this.add(jp_north,BorderLayout.NORTH); //控件放到窗体北面
    }

    //添加中间的控件
    public void addCenterButton() {
        String bth_text = "123+456-789*.0=/";
//      String regex = "[\\+\\-*/.+]";  \\ 转译
        this.jp_center.setLayout(new GridLayout(4,4));//声明一个4x4的网格
        for(int i=0;i<16;i++) {
            String temp = bth_text.substring(i,i+1);
            JButton btn = new JButton();
            btn.setText(temp);
//          if(temp.matches(regex)) {
//              btn.setFont(new Font("粗体",Font.BOLD,20));
//              btn.setForeground(Color.red);
//          }
            if(temp.equals("+")||temp.equals("-")||temp.equals("*")||temp.equals("/")||temp.equals(".")||temp.equals("="))
            {
                btn.setFont(new Font("粗体",Font.BOLD,20));
                btn.setForeground(Color.red);
            }
            btn.addActionListener(this);
            jp_center.add(btn);
        }
        this.add(jp_center,BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true); //显示GUI组件
    }

    private String firstInput = null;//记录第一次输入的数
    private String symbol = null;//记录操作符
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String click = e.getActionCommand();//获取点击到的值
        if(".0123456789".indexOf(click)!=-1) {
            this.input_text.setText(input_text.getText()+click);//把按钮上的值赋值到文本框里  先点的数字在后面，后点的数字在前面
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);//数字从右边往左显示
            //JOptionPane.showMessageDialog(this, click);//把点击窗口的值显示出来显示出来
            // click.matches("[\\+\\-*/]{1}") 如果是加减乘除中的一个
        }else if(click.matches("[\\+\\-*/]{1}")) {
            symbol = click;//记录符号
            firstInput = this.input_text.getText();//记录数字
            this.input_text.setText("");//清空文本框内容
        }else if(click.equals("=")) {
            Double a = Double.valueOf(firstInput);//点运算符之前的值
            Double b = Double.valueOf(this.input_text.getText());//点运算符之后的值，即当前值
            Double result = null;
            switch (symbol) {
                case"+":result=a+b;break;
                case"-":result=a-b;break;
                case"*":result=a*b;break;
                case"/":
                    if(b!=0) {
                        result=a/b;
                    }break;
            }
            this.input_text.setText(result.toString());
        }
    }
}

