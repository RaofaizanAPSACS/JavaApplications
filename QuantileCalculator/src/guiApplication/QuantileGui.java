package guiApplication;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class QuantileGui {
	
	// members
	Vector<Float> data;
	float min, max, q1, q2, q3;
	JFrame f;
	JLabel title, inputLabel, summaryLabel, minLabel, minValue, maxLabel, maxValue, Q1Label, q1Value, Q2Label, q2Value, Q3Label, q3Value, iqrLabel, iqrValue;
	JTextField field;
	JButton calButton;
	
	// constructor
	public QuantileGui() {
		f= new JFrame("Quantiles Calculator");  
	}
	
	// member functions
	public void setTitle() {
	    // adding title label
	    title = new JLabel("CALCULATE QUANTILES"); 
	    title.setBounds(150,20, 600, 30);
	    title.setFont(new Font("Verdana", Font.CENTER_BASELINE, 20));
	    title.setForeground(Color.red);
	}
	public void addTextFieldWithActionListener() {
	    // adding the text field
	    field = new JTextField();  
	    field.setBounds(50,150, 490,30);
	    field.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent ke) {
	             if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == ' ' || (ke.getKeyChar() >= 37 && ke.getKeyChar() <= 40) || ke.getKeyChar() == 8) {
	            	 field.setEditable(true);
	             } 
	             else {
	            	 field.setEditable(false);
	             }
           }
	    });
	}
	public void setFiveNumberSummaryLabels() {
	    // five number summary label
	    summaryLabel = new JLabel("FIVE NUMBER SUMMARY OF THE DATA");
	    summaryLabel.setBounds(150, 250, 400, 30);
	    summaryLabel.setFont(new Font("Verdana", Font.CENTER_BASELINE, 12));
	    summaryLabel.setForeground(Color.blue);
	    
	    minLabel = new JLabel("Min: ");
	    minLabel.setBounds(50, 300, 100,30);
	    minValue = new JLabel("0.0");
	    minValue.setBounds(150, 300, 100, 30);
	    
	    maxLabel = new JLabel("Max: ");
	    maxLabel.setBounds(50, 350, 100,30);
	    maxValue = new JLabel("0.0");
	    maxValue.setBounds(150, 350, 100, 30);
	    
	    Q1Label = new JLabel("Quantile 1: ");
	    Q1Label.setBounds(50, 400, 100,30);
	    q1Value = new JLabel("0.0");
	    q1Value.setBounds(150, 400, 100, 30);
	    
	    Q2Label = new JLabel("Quantile 2: ");
	    Q2Label.setBounds(50, 450, 100,30);
	    q2Value = new JLabel("0.0");
	    q2Value.setBounds(150, 450, 100, 30);
	    
	    Q3Label = new JLabel("Quantile 3: ");
	    Q3Label.setBounds(50, 500, 100,30);
	    q3Value = new JLabel("0.0");
	    q3Value.setBounds(150, 500, 100, 30);
	    
	    iqrLabel = new JLabel("Inter-Quartile-Range: ");
	    iqrLabel.setBounds(300, 300, 200,30);
	    iqrValue = new JLabel("0.0");
	    iqrValue.setBounds(450, 300, 100, 30);
	}
	public void addColorsToLabels(Color clr) {
		minValue.setForeground(clr);
		maxValue.setForeground(clr);
		q1Value.setForeground(clr);
		q2Value.setForeground(clr);
		q3Value.setForeground(clr);
		iqrValue.setForeground(clr);
	}
	public void calculateButtonActionListener() {
	    
	    calButton.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent ae){
	    	      String textFieldValue = field.getText();
	    	      if(!textFieldValue.isEmpty()){
	    	    	  // initializing the summary variables
	    	    	  min = Integer.MAX_VALUE;
	    	    	  max = Integer.MIN_VALUE;
	    	    	  q1 = q2 = q3 = 0;
	    	    	  
	    	    	  // splitting the numbers 
		    	      String words[] = textFieldValue.split("\\s");
		    	      data = new Vector<Float>(words.length);
		    	      
		    	      // adding the numbers into the vector data
		    	      for(String word: words) {
		    	    	  float num = Float.parseFloat(word);
		    	    	  if(num < min)
		    	    		  min = num;
		    	    	  if(num >  max)
		    	    		  max = num;
		    	    	  data.add(num);
		    	      }
		    	      Collections.sort(data);
		    	      // calculating the quantiles
		    	      int index = (data.size()-1)  / 2;
		    	      q2 = data.get(index);
		    	      q1 = data.get(index / 2);
		    	      q3 = data.get(((index*2) + index) / 2);
		    	      
		    		    // add colors to them
		    		    addColorsToLabels(Color.magenta);
		    	      
		    	      // updating the values on the frame
		    	      minValue.setText(String.valueOf(min));
		    	      maxValue.setText(String.valueOf(max));
		    	      q1Value.setText(String.valueOf(q1));
		    	      q2Value.setText(String.valueOf(q2));
		    	      q3Value.setText(String.valueOf(q3));
		    	      iqrValue.setText(String.valueOf(q3-q1));
		    	   }
	    	      else {
	    	    	  // reseting the values on the frame
	    	    	  
	    	    	  addColorsToLabels(Color.black);
		    	      minValue.setText("0.0");
		    	      maxValue.setText("0.0");
		    	      q1Value.setText("0.0");
		    	      q2Value.setText("0.0");
		    	      q3Value.setText("0.0");
		    	      iqrValue.setText("0.0");
	    	      }
	    	   }
	    });
	}
	public void addIntoFrame() {
	    // adding all into frame
	    f.add(title);
	    f.add(inputLabel); 
	    f.add(field);
	    f.add(calButton);
	    f.add(summaryLabel);
	    f.add(minLabel);
	    f.add(maxLabel);
	    f.add(Q1Label);
	    f.add(Q2Label);
	    f.add(Q3Label);
	    f.add(minValue);
	    f.add(maxValue);
	    f.add(q1Value);
	    f.add(q2Value);
	    f.add(q3Value);
	    f.add(iqrLabel);
	    f.add(iqrValue);
	}
	
	// Main function of this class which calls all other to create the GUI
	public void createGui() {
		// set the app title
		setTitle();
		
	    // adding input label
	    inputLabel=new JLabel("ENTER LIST OF NUMBERS SEPARATED WITH SPACES: ");
	    inputLabel.setBounds(50,100, 500,30);
	    
	    // add text field with action listener
	    addTextFieldWithActionListener();
	        
	    // adding calculate button
	    calButton = new JButton("CALCULATE");
	    calButton.setBounds(420, 200, 120, 30 );
	    calButton.setBackground(Color.BLUE);
	    calButton.setForeground(Color.white);
	    // set the output labels 
	    setFiveNumberSummaryLabels();
	    
	    // add all widgets into the frame
	    addIntoFrame();
	    
	    // action listener of the calculate button which will perform operation
	    calculateButtonActionListener();
	    
	    // setting frame 
	    f.setSize(600,600);  
	    f.setLayout(null);  
	    f.setVisible(true);  
		
	}
}
