package anagram;

import javax.swing.*;

public class Anagram{
		
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Anagram displayer");
    	AnagramPanel mainPanel = new AnagramPanel();
    	
    	frame.setContentPane(mainPanel.makeAnagramPanel());

        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    	    	
    }

}
