package com.sample;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;

import com.sample.AnimeRecommendation.Answer;
import com.sample.AnimeRecommendation.Question;


public class GUI implements ActionListener{
	
	public ArrayList<JButton> buttons;
	public Question question;
	public KieSession kSession;
	public KieRuntimeLogger kLogger;
	JPanel panel = new JPanel(new GridLayout(2, 1));
    JPanel labelPanel = new JPanel(new FlowLayout());
    JPanel answerPanel = new JPanel(new FlowLayout());
    JFrame frame = new JFrame("Chat Frame");
	JLabel label;
	
	public void setupGUI (Question question) {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400, 200);
		this.question = question;
		this.label = new JLabel(question.getQuestion());
		this.buttons = createButtons();
		this.labelPanel.add(label);
		this.addButtons();
		this.panel.add(labelPanel);
        this.panel.add(answerPanel);
        this.frame.getContentPane().add(panel);
        this.frame.setVisible(true);
	}
	
	public GUI(KieSession ks, KieRuntimeLogger kLogger) {
		this.kSession = ks;
		this.kLogger = kLogger;
	}
	
	public ArrayList<JButton> createButtons() {
		ArrayList<JButton> newButtons = new ArrayList<>();
		for (int i = 0; i < this.question.getAnswers().size(); i++) {
			JButton jButton = new JButton(this.question.getAnswers().get(i).getAnswer());
			jButton.addActionListener(this);
			newButtons.add(jButton);
		}
		return newButtons;
	}
	
	public void addButtons() {
		for (int i = 0; i < this.buttons.size(); i++) {
			this.answerPanel.add(this.buttons.get(i));
		}
	}
	
	public JLabel createLabel() {
		return new JLabel(question.getQuestion());
	}
	
	public void updateLayout() {
		this.panel.revalidate();
	}
	
	public void addElementsToLayout () {
		this.labelPanel.removeAll();
		this.answerPanel.removeAll();
		this.panel.removeAll();
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
		this.label = createLabel();
		this.buttons = createButtons();
		this.labelPanel.add(label);
		this.addButtons();
		this.panel.add(labelPanel);
		this.panel.add(answerPanel);
        this.frame.getContentPane().add(panel);
        this.frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		for (int i = 0; i < this.buttons.size(); i++) {
			if (source == this.buttons.get(i)) {
				this.kSession.insert(new Answer(this.buttons.get(i).getText()));
				this.kSession.fireAllRules();
				break;
			}
		}
	}
	
}
