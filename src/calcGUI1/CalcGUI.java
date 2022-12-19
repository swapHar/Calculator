package calcGUI1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcGUI implements ActionListener{
	
	JFrame calFrame =new JFrame("Calculator");
	JLabel calLabel = new JLabel();
	JTextField calTextfld = new JTextField();
	JButton[] numButtons = new JButton[10];
	JButton[] funcButtons = new JButton[9];
	JButton addButton,subButton,mulButton,divButton;
	JButton decmlButton,equlButton,delButton,clrButton,negButton;
	JPanel calPanel = new JPanel();
	
	Font myFont = new Font("Arial",Font.BOLD,20);
	
	CalculatorClass calculator = new CalculatorClass();
	
	double num1=0,num2=0,result=0;
	String operator;
	boolean optrFlag=false; // flag to check if operator button is pressed 
		
		
	public CalcGUI() {
		prepareFrame();

	}
	
	public void prepareFrame() {
		calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calFrame.setSize(420, 550);
		calFrame.setLayout(null);
		calFrame.setResizable(false);
		calFrame.setLocationRelativeTo(null);
		
		prepareLabl_Text();
		prepareButtons();
		
		calFrame.setVisible(true);
		
	}
	
	public void prepareLabl_Text() {
		
		calLabel.setBounds(250, 0, 100, 25);
		calLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		calFrame.add(calLabel);
		
		calTextfld.setBounds(50,25, 300, 50);
		calTextfld.setFont(myFont);
		calTextfld.setEditable(false);
		calTextfld.setHorizontalAlignment(JTextField.RIGHT);
		calTextfld.setText("0");
		calFrame.add(calTextfld);
		
	}

	public void prepareButtons() {
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("X");
		divButton = new JButton("/");
		decmlButton = new JButton(".");
		equlButton = new JButton("=");
		clrButton = new JButton("Clear");
		delButton = new JButton("Delete");
		negButton = new JButton("+/-");
		
		funcButtons[0]= addButton;
		funcButtons[1]= subButton;
		funcButtons[2]= mulButton;
		funcButtons[3]= divButton;
		funcButtons[4]= decmlButton;
		funcButtons[5]= equlButton;
		funcButtons[6]= clrButton;
		funcButtons[7]= delButton;
		funcButtons[8]= negButton;
		
		for (int i=0; i<funcButtons.length; i++) {
			funcButtons[i].addActionListener(this);
			funcButtons[i].setFont(myFont);
			funcButtons[i].setFocusable(false);
		}
		
		for (int i=0; i<numButtons.length; i++) {
			numButtons[i]= new JButton(String.valueOf(i));
			numButtons[i].addActionListener(this);
			numButtons[i].setFont(myFont);
			numButtons[i].setFocusable(false);
		}
		
		calPanel.setBounds(50, 100, 300, 300);
		calPanel.setLayout(new GridLayout(4, 4, 5, 5));
		calPanel.setBackground(Color.black);

        calPanel.add(numButtons[7]);
        calPanel.add(numButtons[8]);
        calPanel.add(numButtons[9]);
        calPanel.add(divButton);
        
        calPanel.add(numButtons[4]);
        calPanel.add(numButtons[5]);
        calPanel.add(numButtons[6]);
        calPanel.add(mulButton);
 
        calPanel.add(numButtons[1]);
        calPanel.add(numButtons[2]);
        calPanel.add(numButtons[3]);
        calPanel.add(subButton);
        
        calPanel.add(negButton);
        calPanel.add(numButtons[0]);
        calPanel.add(decmlButton);
        calPanel.add(addButton);
		
		
		delButton.setBounds(50, 430, 100, 50);
		clrButton.setBounds(155, 430, 100, 50);
		equlButton.setBounds(260, 430, 100, 50);
		
		calFrame.add(calPanel);
		calFrame.add(delButton);
		calFrame.add(clrButton);
		calFrame.add(equlButton);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object actSource = e.getSource();
		String tmpTxt = calTextfld.getText();
				
		
		for (int i=0;i<10;i++) {          // code for number buttons
			
			if (actSource == numButtons[0]) {
				optrFlag=false;
				if (tmpTxt.equals("0")) {
					return;
				}else {
					calTextfld.setText(tmpTxt.concat("0"));
					return;
				}
				
			}else if(actSource==numButtons[i]) {
				optrFlag=false;
				if (tmpTxt.equals("0")) {
					calTextfld.setText(String.valueOf(i));
				}else {
					calTextfld.setText(tmpTxt.concat(String.valueOf(i)));
				}	
			}	
		}
		if (actSource == clrButton) { //code for clear button
			
			calLabel.setText("");
			calTextfld.setText("0");
			num1=0;
			num2=0;
			operator="";
			optrFlag=false;
			
		}else if (actSource == delButton) { //code for delete button
			
			if (optrFlag==false) {
				int txtLnt=tmpTxt.length();
				
				if (txtLnt>0) {
					
					calTextfld.setText(deleteChar(tmpTxt));
					
				}if(calTextfld.getText().equals("")) {
					
					calTextfld.setText("0");
					calLabel.setText("");
				}
			}else {
				return;
			}
			
		}else if(actSource==decmlButton) { //code for decimal button
			
			if (tmpTxt.contains(".")) {
				return;
			}else {
				if (tmpTxt.equals("")) {
					calTextfld.setText(tmpTxt.concat("0."));
					
				}else {
					calTextfld.setText(tmpTxt.concat("."));
				}
			}
		}else if(actSource==negButton) {
			if (tmpTxt.equals("")) {
				return;
			}else if (tmpTxt.equals("0")) {
					return;
			}else {
				double a;
				a=Double.parseDouble(tmpTxt)*-1;
				calTextfld.setText(roundDouble(a));					
			}			
		}else if (actSource==addButton){
			setOperator(tmpTxt, "+");
			
		}else if (actSource==subButton){
			setOperator(tmpTxt, "-");
			
		}else if (actSource==mulButton){
			setOperator(tmpTxt, "x");
			
		}else if (actSource==divButton){
			setOperator(tmpTxt, "/");
	
		}else if (actSource==equlButton) {
		    
			optrFlag=true;
		    
			num2=Double.parseDouble(tmpTxt);
			String s=String.valueOf(roundDouble(num1));
			calLabel.setText(s+ operator + tmpTxt +"=");
			
			switch(operator) {
			case"+":
				result=calculator.addition(num1, num2);
				break;
			case"-":
				result=calculator.subtraction(num1, num2);
				break;
			case"x":
				result=calculator.multiplication(num1, num2);
				break;
			case"/":
				result=calculator.division(num1, num2);
				break;
			}
			
				calTextfld.setText(roundDouble(result));
			
		}
	
	}
	
   	
    public String roundDouble(double resultTxt) {
    	if (Double.toString(resultTxt).endsWith(".0")) {
			String tmpStr= Double.toString(resultTxt).replace(".0", "");
			return tmpStr;
		}else {
			return Double.toString(resultTxt);
		}
    }
	
    public void setOperator(String txtFLD,String optr) {
    	
    	this.operator=optr;
		calTextfld.setText("");
		
		optrFlag=true;
		
		if (txtFLD.equals("")) {
			
			//code to replace last character from lable text to new operator
		    String txt=calLabel.getText();
		    
		    int txtLn=txt.length();
		    String str = null,reStr;
		    
		    if (txtLn>0) {
		    	str=deleteChar(txt);
		    }
		    reStr =str.concat(optr);
		    
			calLabel.setText(reStr);
			
		}else {
		    num1=Double.parseDouble(txtFLD);	
			calLabel.setText(txtFLD.concat(operator));
		}
    	
    }
    
    public String deleteChar(String text) {
		
		int txtLnt = text.length();
		String retext;
		StringBuilder newStrg = new StringBuilder(text);
		newStrg.deleteCharAt(txtLnt-1);
		retext= newStrg.toString();
		return retext;
		
	}
}
