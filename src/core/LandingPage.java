package core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import backend.core.TerrainHexController;
import gui.core.Hex;


@SuppressWarnings("serial")
public class LandingPage extends JFrame{
	TerrainHexController terHexCont;

	public LandingPage(String title) {
		//Set up the window
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screen.getWidth();
		double height = screen.getHeight();
		double dimension = Math.min(width, height);
		this.setSize((int)dimension, (int)dimension);
		
		//Add menu options for interacting with battle maps
		JMenuBar menu = new JMenuBar();
		
		JMenu edit = new JMenu("Edit");
		JMenu save = new JMenu("Save");
		JMenu load = new JMenu("Load");
		JMenu generate = new JMenu("New");
		menu.add(generate);
		menu.add(save);
		menu.add(load);
		menu.add(edit);
		
		//Sub menu options
		load.add(new JMenuItem("Load"));
		save.add(new JMenuItem("Save"));
		save.add(new JMenuItem("Save As"));
		
		generate.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//Do nothing
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//Do nothing
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				//Do nothing
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				//Do nothing
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				for(Component potentialHex : getContentPane().getComponents()) {
		        	if(potentialHex.getClass().equals(Hex.class)) {
		        		getContentPane().remove(potentialHex);
		        	}
		        }
				terHexCont= new TerrainHexController(new BattleMap());
				setDisplay();
				generate.setSelected(false);
			}
		});
		
		this.getContentPane().add(BorderLayout.NORTH, menu);
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
                for(Component potentialHex : getContentPane().getComponents()) {
                	if(potentialHex.getClass().equals(Hex.class)) {
                		setHexHeight((Hex)potentialHex);
                	}
                }
                revalidate();
            }
		});
	}
	
	public static void main(String[] args) {
		LandingPage window = new LandingPage("Jenkins Map Generator");
		window.setVisible(true);
	}
	
	private void setDisplay() {
		try {
			Method method = LandingPage.class.getDeclaredMethod("drawHex", TerrainHexController.class, int.class, int.class);
			
			Object[] parameters = new Object[3];
			
			terHexCont.getBoard().applyToAllHexes(method, parameters, this);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Could not create the drawHex method for application accross the whole battle map.");
			e.printStackTrace();
			System.exit(-2);
		}
		this.repaint();
	}

	protected void drawHex(TerrainHexController controller, int column, int row) {
		Hex toDisplay = new Hex(column, row, controller);
		setHexHeight(toDisplay);
		this.getContentPane().add(toDisplay);
		this.revalidate();
	}

	private void setHexHeight(Hex hex) {
		hex.setHeight((int) (getHeight()*.90/(terHexCont.getBoardRadius()*2 - 1)));
	}
}
