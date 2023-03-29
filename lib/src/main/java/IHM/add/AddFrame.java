package IHM.add;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class AddFrame extends JFrame {


	private final int WIDTH = 450;
	private final int HEIGHT = 500;
	private AddPanel contentPane;
	private String mode;
	private Object obj;
	
	/**
	 * Create the frame.
	 */
	public AddFrame(String type, Object obj) {
		this.obj = obj;
		this.setMode(type);
		this.setResizable(false);
		switch(type) {
			case "Player":{
				this.setMinimumSize(new Dimension(WIDTH, HEIGHT-150));
				break;
			}
			case "Team":{
				this.setMinimumSize(new Dimension(WIDTH, HEIGHT-200));
				break;
			}
			case "Orga":{
				this.setMinimumSize(new Dimension(WIDTH, HEIGHT-150));
				break;
			}
			case "Tournament":{
				this.setMinimumSize(new Dimension(WIDTH, HEIGHT-100));
				break;
			}
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		getContentPane().setLayout(gridBagLayout);
		contentPane = new AddPanel(type, obj);
		GridBagConstraints gbc_contentPane = new GridBagConstraints();
		gbc_contentPane.gridy = 0;
		gbc_contentPane.gridx = 0;
		gbc_contentPane.fill = GridBagConstraints.BOTH;
		getContentPane().add(contentPane, gbc_contentPane);		
	}


	public String getMode() {
		return mode;
	}

	public void setMode(String type) {
		this.mode = type;
	}
	
	public void setObject(Object obj) {
		this.obj = obj;
	}
	

}
