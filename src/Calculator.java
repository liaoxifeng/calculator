
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
public class Calculator  extends JFrame implements ActionListener{
	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 private Stack<String> sk=new Stack<String>();
	 private double num;
	 private String str;
	 private String str1;
	 private String str2;
	 private String ch;
	 private String []KEYS={"AC","7","8","9","/","back","4","5","6","*","x2","1","2","3","+","sqrt","0",".","=","-",};
	 private JButton []keys=new JButton[KEYS.length];
	 private JTextField txt=new JTextField("0");

	 
	 public Calculator(){
	     JPanel p1=new JPanel(new GridLayout(4,5));
		 for(int i=0;i<KEYS.length;i++){
			keys[i]=new JButton(KEYS[i]);
			
			p1.add(keys[i]);
			keys[i].addActionListener(this);	
			
		}
	
		this.add(txt,BorderLayout.NORTH);
		this.add(p1,BorderLayout.CENTER);
	 }	
	 
	 
      public static void main (String[]args ){
    	      Calculator frame=new Calculator();
              frame.setTitle("计算器");
              frame.setSize(400,200);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
             
         }
      

	@Override
		public void actionPerformed (ActionEvent e) {
			// TODO Auto-generated method stub
	
			
			JButton jb	=(JButton)e.getSource();

			
			
	//		try{
         if(jb.getText().equals("0")||jb.getText().equals("1")||jb.getText().equals("2")||jb.getText().equals("3")||jb.getText().equals("4")||jb.getText().equals("5")||jb.getText().equals("6")||jb.getText().equals("7")||jb.getText().equals("8")||jb.getText().equals("9")){
        	 if(sk.empty()){
        		 sk.push(jb.getText()); txt.setText(jb.getText());
        	 }
        	     else if(sk.peek().equals("+")||sk.peek().equals("-")||sk.peek().equals("*")||sk.peek().equals("/")){
        		       sk.push(jb.getText());
        		       txt.setText(jb.getText());
        	        }
        	     else {
        	    	 str=sk.pop();
        	    	 str=str+jb.getText();
        	    	 sk.push(str);
        	    	 txt.setText(str);
        	     }
         }
        
         if(jb.getText().equals(".")){
        	 if(sk.empty()||sk.peek().equals("+")||sk.peek().equals("-")||sk.peek().equals("*")||sk.peek().equals("/")){
        		 txt.setText("小数点前要有数字");
        	 }
        	  else {
        	    	 str=sk.pop();
        	    	 boolean d=true;
        	    	 for(int i=0;i<str.length();i++){
        	    		if( str.substring(i, i+1).equals(".")){
        	    			d=false;
        	    			
        	    		}			 
        	    	 }
        	    	 if(d){
        	    	 str=str+jb.getText();
        	    	 sk.push(str);
        	    	 txt.setText(str);
        	    	 } else {
						txt.setText("一个数不能有两个小数点");
					}

        	     }
        	 
        	 
         }
			if(jb.getText().equals("AC")){
				str="0";
				txt.setText(str);
				sk=new Stack<String>();
				
			}
			
			
			if(jb.getText().equals("x2")){
				str=sk.pop();
				float num=Float.parseFloat(str);
				str=Float.toString(num*num);
				txt.setText(str);
				sk.push(str);		
			}
			
			
			if(jb.getText().equals("sqrt")){
				str=sk.pop();
			    num=Double.parseDouble(str);
			    num=Math.sqrt(num);
				str=Double.toString(num);
				txt.setText(str);
				sk.push(str);		
			}
			
			
			if(jb.getText().equals("back")){
				str=sk.pop();
				str=str.substring(0, str.length()-1);
				if(str.length()>0) {
					sk.push(str);txt.setText(str);	
				} else {
					txt.setText("0");
				}
		   }
			
		
			
			if(jb.getText().equals("+")||jb.getText().equals("-")){
				 str1=sk.pop();
				 if(sk.isEmpty()){
					 sk.push(str1);
					 sk.push(jb.getText());
				 }
				 else{
                 ch=sk.pop();
                 str2=sk.pop();
                 switch (ch){
                 case "+":
                	 num=Double.parseDouble(str2)+Double.parseDouble(str1);
                	 str=Double.toString(num);
                	 txt.setText(str);
                	 sk.push(str);
                	 sk.push(jb.getText());
                	 break;
                 case "-":
                	 num=Double.parseDouble(str2)-Double.parseDouble(str1);
                	 str=Double.toString(num);
                	 txt.setText(str);
                	 sk.push(str);
                	 sk.push(jb.getText());
                	 break;
                 case "*":
                	 num=Double.parseDouble(str2)*Double.parseDouble(str1);
                	 str=Double.toString(num);
                	 txt.setText(str);
                	 sk.push(str);
                	 sk.push(jb.getText());
                	 break;
                 case "/":
                	 if(Double.parseDouble(str1)==0){
                		 txt.setText("0不能做被除数");
                	 }
                	 else{
                	 num=Double.parseDouble(str2)/Double.parseDouble(str1);
                	 str=Double.toString(num);
                	 txt.setText(str);
                	 sk.push(str);
                	 sk.push(jb.getText());
                	 }
                	 break;
				default:
					break;	 
                 }
				 }
			}
	
			
			
			
		
			if(jb.getText().equals("*")||jb.getText().equals("/")) { 
				str1=sk.pop();
				if(sk.empty()){
					sk.push(str1);
					sk.push(jb.getText());
				}
				else{
                ch= sk.pop();
            switch (ch){
            case "+":
           	    sk.push(ch);
           	    sk.push(str1);
           	    sk.push(jb.getText());
           	    break;
            case "-":
            	 sk.push(ch);
              	 sk.push(str1);
              	 sk.push(jb.getText());
           	     break;
            case "*":
           	    str2=sk.pop();
           	    num=Double.parseDouble(str2)*Double.parseDouble(str1);
           	    str=Double.toString(num);
           	    sk.push(str);
           	    sk.push(jb.getText());
           	    txt.setText(str);
           	    
           	    break;
            case "/":
           	    str2=sk.pop();
           	    if(Double.parseDouble(str1)==0.0) {
           	    
           	    	txt.setText("0不能做被除数");
           	    	
           	    }
           	    else{
           	    num=Double.parseDouble(str2)/Double.parseDouble(str1);
           	    str=Double.toString(num);
           	    txt.setText(str);
           	    sk.push(str);
           	    sk.push(jb.getText());
           	    }
           	    break;
			default:
				break;	 
            }
				}
		}
	
			
		            
	          
			
			
			
			if(jb.getText().equals("=")){
				
				str1= sk.pop();
				ch=sk.pop();
				str2=sk.pop();
				double num1=Double.parseDouble(str1);
	            double num2=Double.parseDouble(str2);
	            switch (ch){
	            case "+":
	            	
	            	if(sk.empty()){
                	num=num1+num2;
                	str=Double.toString(num);
                	txt.setText(str); 
                	sk.push(str);
	            	}
	            	else {
	            		ch=sk.pop();
	            		str=sk.pop();
	            		switch(ch){
	            		case "+":
	            			num=Double.parseDouble(str)+num2+num1;
	            		    str=Double.toString(num);
	            			txt.setText(str); 
	            			sk.push(str);
	            			break;
	            		case "-":
	            			num=Double.parseDouble(str)-num2+num1;
	            		    str=Double.toString(num);
	            			txt.setText(str); 
	            			sk.push(str);
	            			break;
						default:
							break;
	            		}
	            		
	            	}
	           	    break;
	            case "-":
	            	if(sk.empty()){
                	num=num2-num1;
                	str=Double.toString(num);
                	txt.setText(str); 
                	sk.push(str);
	            	}
	            	else {
	            		ch=sk.pop();
	            		str=sk.pop();
	            		switch(ch){
	            		case "+":
	            			num=Double.parseDouble(str)+num2-num1;
	            			 str=Double.toString(num);
	            			txt.setText(str);
	            			sk.push(str);
	            			break;
	            		case "-":
	            			num=Double.parseDouble(str)-num2-num1;
	            			 str=Double.toString(num);
	            			txt.setText(str); 
	            			sk.push(str);
	            			break;
						default:
							break;
	            		}
	            		
	            	}
	           	    break;
	            case "*":
	            	num=num2*num1;
	            	if(sk.empty()){
	            		str=Double.toString(num);
	            		txt.setText(str);  
	            		sk.push(str);
	            	}
                	  
	            	else {
					   ch=sk.pop();
					   str= sk.pop();
					   if(ch.equals("+")){
					      num=Double.parseDouble(str)+num;
					   }
					   if(ch.equals("-")){
						   num=Double.parseDouble(str2)-num;
					   }
					   str=Double.toString(num);
	                   txt.setText(str);
	                   sk.push(str);
	            	}
	           	    break;
	            
	            case "/":
	            	
	            	if(num1==0){
	            		txt.setText("0不能做被除数");
	            	}
	            	else{
	            	num=num2/num1;
	            	if(sk.empty()) {
	            		str=Double.toString(num);
                	    txt.setText(str);
                	    sk.push(str);
                	} 
	            	else {
	             	   ch=sk.pop();
					   str=sk.pop();
					   if(ch.equals("+")){
					   num=Double.parseDouble(str2)+num;
					   }
					   if(ch.equals("-")){
						   num=Double.parseDouble(str2)-num;
					   }
					   str=Double.toString(num);
	                   txt.setText(str); 
	                   sk.push(str);
	            	}
	           	    break;
	            }
				default:
					break;
	            }
			}
	}
		
		}
	