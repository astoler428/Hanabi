import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private SouthHandPanel southHandPanel;
	private WestHandPanel westHandPanel;
	private NorthHandPanel northHandPanel;
	private EastHandPanel eastHandPanel;
	private List<HandPanel> listOfHandPanels;
	private Player selfPlayer;
	private Player[] listOfPlayers;

	private DisplayPanel displayPanel;

	public GamePanel(DisplayPanel displayPanel, Player selfPlayer, Player[] listPlayers) {
		this.displayPanel = displayPanel;
		this.selfPlayer = selfPlayer;
		
		this.setLayout(new BorderLayout(10, 5));
		this.setPreferredSize(new Dimension(950, 800));
		this.add(displayPanel, BorderLayout.CENTER);


		makeListOfPlayers(listPlayers); // takes the list and reorders it so self is first
		setHandLocations();
	}

	private void makeListOfPlayers(Player[] listPlayers) {

		listOfPlayers = new Player[listPlayers.length];

		int start = selfPlayer.getID();
		int numPlayers = listPlayers.length;

		for (int idx = 0; idx < numPlayers; idx++) {
			listOfPlayers[(idx + start) % numPlayers] = listPlayers[idx];
		}

	}

	private void setHandLocations() {

		listOfHandPanels = new ArrayList<>();
		listOfHandPanels.add(southHandPanel);

		southHandPanel = new SouthHandPanel(selfPlayer);
		this.add(southHandPanel, BorderLayout.SOUTH);
		
		switch (listOfPlayers.length) {

		case 2:
			northHandPanel = new NorthHandPanel(listOfPlayers[1]);
			this.add(northHandPanel, BorderLayout.NORTH);
			listOfHandPanels.add(northHandPanel);
			break;

		case 3:
			westHandPanel = new WestHandPanel(listOfPlayers[1]);
			northHandPanel = new NorthHandPanel(listOfPlayers[2]);
			this.add(westHandPanel, BorderLayout.WEST);
			this.add(northHandPanel, BorderLayout.NORTH);
			listOfHandPanels.add(westHandPanel);
			listOfHandPanels.add(northHandPanel);

			break;
			
		case 4:
			westHandPanel = new WestHandPanel(listOfPlayers[1]);
			northHandPanel = new NorthHandPanel(listOfPlayers[2]);
			eastHandPanel = new EastHandPanel(listOfPlayers[3]); 
			this.add(westHandPanel, BorderLayout.WEST);
			this.add(northHandPanel, BorderLayout.NORTH);
			this.add(eastHandPanel, BorderLayout.EAST);
			listOfHandPanels.add(westHandPanel);
			listOfHandPanels.add(northHandPanel);
			listOfHandPanels.add(eastHandPanel);
		}
	}
}