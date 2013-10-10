package anagram;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AnagramPanel  implements  ActionListener{

	String[] allPieces={"First","Second","Third","Fourth","Fifth"};

	int index= 0;
	//This pair was found by http://anagramatron.tumblr.com/
	String firstPhrase="too many brownies";
	String secondPhrase="i won't sob anymore";
	AnagramPieceMaker mainPieceMaker;

	JButton backButton, forwardButton, textButton;
	JLabel anagramLabel;
	JTextField firstText, secondText;
	
	public JPanel makeAnagramPanel(){
    	JPanel tempGUI = new JPanel();
    	tempGUI.setLayout(null);
    	mainPieceMaker=new AnagramPieceMaker();
    	mainPieceMaker.setup(firstPhrase,secondPhrase);
    	
    	
    	anagramLabel=new JLabel("");
    	anagramLabel.setLocation(100,10);
    	anagramLabel.setSize(700,600);
    	anagramLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
    	tempGUI.add(anagramLabel);
    	
    	backButton=new JButton("Back");
    	backButton.setLocation(0,0);
    	backButton.setSize(200,50);
    	backButton.addActionListener(this);
    	tempGUI.add(backButton);
    	
    	forwardButton=new JButton("Forward");
    	forwardButton.setLocation(400,0);
    	forwardButton.setSize(200,50);
    	forwardButton.addActionListener(this);
    	tempGUI.add(forwardButton);

    	firstText=new JTextField(firstPhrase);
    	firstText.setLocation(0,50);
    	firstText.setSize(200,50);
    	tempGUI.add(firstText);
    	
    	secondText=new JTextField(secondPhrase);
    	secondText.setLocation(400,50);
    	secondText.setSize(200,50);
    	tempGUI.add(secondText);
    	
    	textButton=new JButton("Use new text");
    	textButton.setLocation(200,50);
    	textButton.setSize(200,50);
    	textButton.addActionListener(this);
    	tempGUI.add(textButton);
    	
    	updatePieceLabel();
    	return tempGUI;
	}
	
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==backButton){
			if (index>0){
				index-=1;
			}
			else{
				index=0;
			}
			updatePieceLabel();
		} else if (e.getSource()==forwardButton) {
				index+=1;
			updatePieceLabel();
		}
		else if (e.getSource()==textButton){
			firstPhrase=firstText.getText();
			secondPhrase=secondText.getText();
			mainPieceMaker.setup(firstPhrase,secondPhrase);
			index=0;
			updatePieceLabel();
		}
	}

	public void updatePieceLabel(){
		anagramLabel.setText(mainPieceMaker.makePiece(index));
	}
}
