package com.sample;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;

public class NewGUI implements ActionListener{
    public JFrame frame = new JFrame("Anime Recommendations");
    public KieSession kSession;
	public KieRuntimeLogger kLogger;
	public int count = 0;

    public NewGUI(KieSession ks, KieRuntimeLogger kLogger) {
    	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400, 200);
		this.kSession = ks;
		this.kLogger = kLogger;
	}
    
    public void newFrame(String question, String[] answers) {
    	this.frame.getContentPane().removeAll();
		this.frame.repaint();
		JLabel label = new JLabel(question);
		JButton[] buttons = createButtons(answers);
		JPanel panel = new JPanel(new GridLayout(2, 1));
		JPanel labelPanel = new JPanel(new FlowLayout());
	    JPanel answerPanel = new JPanel(new FlowLayout());
	    labelPanel.add(label);
	    addButtons(answerPanel, buttons);
	    panel.add(labelPanel);
        panel.add(answerPanel);
        this.frame.getContentPane().add(panel);
        this.frame.setVisible(true);
        panel.revalidate();
    }
    
    public void finalFrame(String question, String[] answers) {
    	this.frame.getContentPane().removeAll();
		this.frame.repaint();
		JLabel label = new JLabel(question);
		JLabel[] labels = createLabels(answers);
		JPanel panel = new JPanel(new GridLayout(3, 1));
		JPanel labelPanel = new JPanel(new FlowLayout());
	    JPanel answerPanel = new JPanel(new FlowLayout());
	    labelPanel.add(label);
	    addLabels(answerPanel, labels);
	    panel.add(labelPanel);
        panel.add(answerPanel);
        this.frame.getContentPane().add(panel);
        this.frame.setVisible(true);
        panel.revalidate();
    }
    
    public JButton[] createButtons(String[] answers) {
		JButton[] newButtons = new JButton[answers.length];
		for (int i = 0; i < answers.length; i++) {
			JButton jButton = new JButton(answers[i]);
			jButton.addActionListener(this);
			newButtons[i] = jButton;
		}
		return newButtons;
	}
    
    public void addButtons(JPanel answerPanel, JButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			answerPanel.add(buttons[i]);
		}
	}
    
    public JLabel[] createLabels(String[] answers) {
		JLabel[] newLabels = new JLabel[answers.length];
		for (int i = 0; i < answers.length; i++) {
			JLabel jLabel = new JLabel(Integer.toString(i+1) + ". "  + answers[i]);
			newLabels[i] = jLabel;
		}
		return newLabels;
	}
    
    public void addLabels(JPanel answerPanel, JLabel[] labels) {
    	for (int i = 0; i < labels.length; i++) {
			answerPanel.add(labels[i]);
		}
    	
    	
	}
    
    public void addLabelsToPanel(JPanel panel, JPanel[] answerPanel) {
    	for (int i = 0; i < answerPanel.length; i++) {
    		panel.add(answerPanel[i]);
    	}
    }
	public void actionPerformed(ActionEvent e) {
		Object source = e.getActionCommand();
		kSession.insert(new String(source.toString()));
		kSession.fireAllRules();
	}
	
}
