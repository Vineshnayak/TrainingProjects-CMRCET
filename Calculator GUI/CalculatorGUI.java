import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CalculatorGUI extends JFrame implements ActionListener{
    private JTextField display;
    private String operator;
    private double firstOperand;

    public CalculatorGUI(){
        setTitle("Calculator");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial",Font.BOLD,24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display,BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4,10,10));

        String[] buttons={"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};

        for(String buttonText:buttons){
            JButton button=new JButton(buttonText);
            button.setFont(new Font("Arial",Font.BOLD,24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel,BorderLayout.CENTER);

        JButton clearButton=new JButton("C");
        clearButton.setFont(new Font("Arial",Font.BOLD,24));
        clearButton.addActionListener(e->clearDisplay());
        add(clearButton,BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command=e.getActionCommand();
        if(command.charAt(0)>='0'&&command.charAt(0)<='9'||command.equals(".")){
            display.setText(display.getText()+command);
        }else if(command.equals("+")||command.equals("-")||command.equals("*")||command.equals("/")){
            firstOperand=Double.parseDouble(display.getText());
            operator=command;
            display.setText("");
        }else if(command.equals("=")){
            double secondOperand=Double.parseDouble(display.getText());
            double result=calculateResult(firstOperand,secondOperand,operator);
            display.setText(String.valueOf(result));
            operator=null;
        }
    }

    private double calculateResult(double firstOperand,double secondOperand,String operator){
        switch(operator){
            case "+": return firstOperand+secondOperand;
            case "-": return firstOperand-secondOperand;
            case "*": return firstOperand*secondOperand;
            case "/": if(secondOperand==0){
                          JOptionPane.showMessageDialog(this,"Cannot divide by zero","Error",JOptionPane.ERROR_MESSAGE);
                          return 0;
                      }
                      return firstOperand/secondOperand;
            default: return 0;
        }
    }

    private void clearDisplay(){
        display.setText("");
        firstOperand=0;
        operator=null;
    }

    public static void main(String[] args){
        new CalculatorGUI();
    }
}
