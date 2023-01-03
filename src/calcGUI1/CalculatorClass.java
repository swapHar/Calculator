package calcGUI1;

// methods to perform mathematical operatioins (addition, subtraction, multiplication, division)
// are part of this class

public class CalculatorClass {
	private double firstNum,secondNum,result;
	
	
	public double addition(double firstNum,double secondNum) {
		this.firstNum=firstNum;
		this.secondNum=secondNum;
		result=firstNum+secondNum;
		return result;
	}
	
	public double subtraction(double firstNum,double secondNum) {
		this.firstNum=firstNum;
		this.secondNum=secondNum;
		result=firstNum-secondNum;
		return result;
	}
	
	public double multiplication(double firstNum,double secondNum) {
		this.firstNum=firstNum;
		this.secondNum=secondNum;
		result=firstNum*secondNum;
		return result;
	}
	public double division(double firstNum,double secondNum) {
		this.firstNum=firstNum;
		this.secondNum=secondNum;
		result= firstNum/secondNum;
		return result;
	}
	
	

}
