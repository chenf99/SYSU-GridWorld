import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculator extends JFrame implements ActionListener {
	private JButton add0 = new JButton("+");
	private JButton sub0 = new JButton("-");
	private JButton mul0 = new JButton("*");
	private JButton div0 = new JButton("/");
	private JButton ok0  = new JButton("OK");
	
	private JTextField num1 = new JTextField("");
	private JTextField num2 = new JTextField("");
	private JLabel result0 = new JLabel("");
	private JLabel operator0 = new JLabel("");
	private JLabel eql0 = new JLabel("=");

	public Calculator(){
		//添加事件监听器
		add0.addActionListener(this);
		sub0.addActionListener(this);
		mul0.addActionListener(this);
		div0.addActionListener(this);
		ok0.addActionListener(this);
		//设置窗体属性
		setLayout(new GridLayout(2, 5, 5, 5));
		setTitle("Calculator");
		setSize(400,400);
		setLocation(100,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
		//给每个label设置边框
		result0.setBorder(BorderFactory.createLineBorder(Color.black));
		operator0.setBorder(BorderFactory.createLineBorder(Color.black));
		eql0.setBorder(BorderFactory.createLineBorder(Color.black));

		this.add(num1);
		this.add(operator0);
		this.add(num2);
		this.add(eql0);
		this.add(result0);
		this.add(add0);
		this.add(sub0);
		this.add(mul0);
		this.add(div0);
		this.add(ok0);
	}
	public void test() {

	}

	public static void main(String[] argv) {
		Calculator cal = new Calculator();
		cal.test();
	}
	
	//处理事件
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add0 || e.getSource() == sub0 || e.getSource() == mul0 || e.getSource() == div0) {
			operator0.setText(((JButton)e.getSource()).getText());
		}
		else  {
			double num3 = Double.parseDouble(num1.getText());
			double num4 = Double.parseDouble(num2.getText());
			String operator = operator0.getText();
			double result;
			switch (operator) {
				case "+":
						result = num3 + num4;
						break;
				case "-":
						result = num3 - num4;
						break;
				case "*":
						result = num3 * num4;
						break;
				case "/":
						result = num3 / num4;
						break;
				default:
						return;
			}
			result0.setText("" + result);
		}
	}
	
}
