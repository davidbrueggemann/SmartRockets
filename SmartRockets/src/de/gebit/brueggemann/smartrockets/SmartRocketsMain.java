/**
 * 
 */
package de.gebit.brueggemann.smartrockets;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;

import de.gebit.brueggemann.smartrockets.space.SpaceEnvironment;

/**
 * @author DavidWork
 *
 */
public class SmartRocketsMain extends JApplet {

	private static final long serialVersionUID = 1L;

	private final static int WINDOW_HEIGHT = 900;
	private final static int WINDOW_WIDTH = 900;
	private final static int SPACE_SIZE = 850;
	private final static int ROCKET_POOL_SIZE = 500;

	private JButton start_btn;
	private JButton reset_btn;
	private boolean started_bln = false;
	SpaceEnvironment space;

	@Override
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		space = new SpaceEnvironment(SPACE_SIZE, ROCKET_POOL_SIZE);

		start_btn = new JButton("Start the Rockets");
		reset_btn = new JButton("Reset");

		start_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (started_bln) {
					start_btn.setText("Start the Rockets");
					space.stopTime();
				} else {
					start_btn.setText("Stop the Rockets");
					space.startTime();
				}
				started_bln = !started_bln;
			}
		});

		reset_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(space);
				space = new SpaceEnvironment(SPACE_SIZE, ROCKET_POOL_SIZE);
				add(space, 0);
				start_btn.setText("Start the Rockets");
				started_bln = false;
				space.repaint();
			}
		});

		setLayout(new FlowLayout());
		add(space);
		add(start_btn);
		add(reset_btn);
	}
}
