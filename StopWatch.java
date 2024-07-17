import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StopWatch extends JFrame implements ActionListener{

	JLabel label;
	JButton startButton;
	JButton resetButton;
	boolean isStarted = false;
	Timer timer;
	int seconds;
	int minutes;
	int hours;

	void labelPreset(JLabel label) {
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setFont(new Font("Consolas",Font.PLAIN,45));
		label.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.WHITE));
	}

	void buttonPreset(JButton button) {
		button.addActionListener(this);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.setVerticalAlignment(JButton.CENTER);
		button.setHorizontalAlignment(JButton.CENTER);
		button.setFont(new Font("Consolas",Font.PLAIN,20));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.WHITE));
		button.setFocusable(false);
	}
	
	StopWatch() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.BLACK);
		this.setSize(500,500);
		this.setResizable(false);
		this.setTitle("Stopwatch");

		label = new JLabel(String.format("%02d:%02d:%02d",hours,minutes,seconds));
		label.setBounds(120,140,250,70);
		labelPreset(label);

		startButton = new JButton("START");
		startButton.setBounds(120,240,120,50);
		buttonPreset(startButton);

		resetButton = new JButton("RESTART");
		resetButton.setBounds(250,240,120,50);
		buttonPreset(resetButton);

		this.add(label);
		this.add(resetButton);
		this.add(startButton);
		this.setVisible(true);
	}
	void start() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				seconds++;
				if(seconds >=60) {
					seconds = 0;
					minutes++;
				} else if (minutes >= 60) {
					seconds = 0;
					minutes = 0;
					hours++;
				}
				label.setText(String.format("%02d:%02d:%02d", hours,minutes,seconds));
			}
		});
		timer.start();
	}
	void stop() {
		timer.stop();
	}
	void restart() {
		seconds = 0;
		minutes = 0;
		hours = 0;
		label.setText(String.format("%02d:%02d:%02d",hours,minutes,seconds));
		timer.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==startButton) {
			if(isStarted==false) {
				isStarted = true;
				startButton.setText("STOP");
				start();
			} else {
				isStarted = false;
				startButton.setText("START");
				stop();
			}
		}
		if (e.getSource()==resetButton) {
			isStarted = false;
			startButton.setText("START");
			restart();
		}
	}
	public static void main(String[] args) {
		new StopWatch();
	}
	
}
