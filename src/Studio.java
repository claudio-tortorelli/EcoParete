package claudiosoft.studioBerti;

/**
 *	EcoParete ver. 1.0
 *  This program was written for Ing. Enrico Berti to calculate some environment 
 *  values of a wall stratus.
 *
 *  Copyright (C) 2003  Claudio Tortorelli - E-mail torto@virgilio.it
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA*
 *	
 */
 
/** 
 * This is the main class that hold the main method. 
 * It displays also the first page of the program
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;

public class Studio extends JPanel 
{
	private JLabel _title;
	private GraphicsX _riga;
	private JLabel _welcome;
	private JLabel _welcome2;
	private JButton _reloadConfButton;
	private JButton _exitButton;
	private GridBagConstraints _constraints = new GridBagConstraints();
	private JFileChooser _jfc;	
	private Data _data;
	
	static String dirImg = "images" + File.separatorChar;
	static String dirConf = "configurations" + File.separatorChar;
/**
 * Constructor 
 */ 
	public Studio(Data data) 
	{
		_data = data;	
			
		setLayout(new GridBagLayout());	
		SkeletonGraphic skgraph = new SkeletonGraphic(_constraints);				
	// title
		initTitle();	
		skgraph.occupation(1,1);	
		skgraph.spacing(0,0);
		skgraph.fill('h');			
		skgraph.grid(0,0);			
		add(_title, _constraints);
	// row
		initRiga();
		skgraph.fill('n');			
		skgraph.grid(0,1);			
		add(_riga, _constraints);		
	// welcome		
		initWelcome();			
		skgraph.grid(0,3);			
		add(_welcome, _constraints);		
	// welcome2		
		initWelcome2();
		skgraph.fill('h');				
		skgraph.grid(0,4);			
		add(_welcome2, _constraints);		
	// button to load old configurations
		initReloadConfButton();	
		initJfc();	
		skgraph.fill('n');
		skgraph.spacing(10,0);
		skgraph.grid(0,5);			
		add(_reloadConfButton, _constraints);		
	// button of exit
		initExitButton();
		skgraph.spacing(20,0);			
		skgraph.fill('n');
		skgraph.grid(0,7);			
		add(_exitButton, _constraints);		
			
		revalidate();
	}
	

// ------ START DESCRIPTION AND INITIALIZATION OF COMPONENTS

	private void initTitle() 
	{
		//ImageIcon ico = new ImageIcon(dirImg + "claIco.jpg");		
		this._title = new JLabel();
		_title.setName("_title");
		//_title.setIcon(ico);		
		_title.setToolTipText(null);
		_title.setText("Studio Ing. Enrico Berti - EcoParete ver. 1.0");
		_title.setFont(new Font("verdana", Font.BOLD, 15));
		_title.setForeground(new Color(0, 0, 0, 255));
		_title.setEnabled(true);
		_title.setVerticalAlignment(JLabel.CENTER);
		_title.setHorizontalAlignment(JLabel.CENTER);
		_title.setVerticalTextPosition(JLabel.CENTER);
		_title.setHorizontalTextPosition(JLabel.TRAILING);
		_title.setRequestFocusEnabled(true);
		_title.setMinimumSize(null);
		_title.setMaximumSize(new Dimension(2147483647, 2147483647));
		_title.setPreferredSize(new Dimension(305, 29));
	}
	
	public void initJfc()
	{
		this._jfc = new JFileChooser();
		MyFileFilter filter = new MyFileFilter("xml","XML configurations file");
		_jfc.addChoosableFileFilter(filter);
		_jfc.setCurrentDirectory(new File("." + File.separatorChar + dirConf));
	}
	
	private void initRiga() 
	{
		Image _img = Toolkit.getDefaultToolkit().getImage(Studio.dirImg+"linea1.jpg");				
		this._riga = new GraphicsX(_img,0,0,380,30);												
	}
	
	private void initWelcome() 
	{
		this._welcome = new JLabel();
		_welcome.setName("_welcome");
		_welcome.setToolTipText(null);
		_welcome.setText("Benvenuti!");
		_welcome.setFont(new Font("verdana", Font.BOLD, 11));
		_welcome.setForeground(new Color(0, 0, 0, 255));
		_welcome.setEnabled(true);
		_welcome.setVerticalAlignment(JLabel.CENTER);
		_welcome.setHorizontalAlignment(JLabel.CENTER);
		_welcome.setVerticalTextPosition(JLabel.CENTER);
		_welcome.setHorizontalTextPosition(JLabel.TRAILING);
		_welcome.setRequestFocusEnabled(true);
		_welcome.setMinimumSize(null);
		_welcome.setMaximumSize(new Dimension(2147483647, 2147483647));
		_welcome.setPreferredSize(new Dimension(305, 20));
	}
	
	private void initWelcome2() 
	{
		this._welcome2 = new JLabel();
		_welcome2.setName("_welcome2");		
		_welcome2.setText("Per creare una nuova configurazione completare in modo ordinato le tabelle.");
		_welcome2.setFont(new Font("verdana", Font.BOLD, 11));
		_welcome2.setEnabled(true);		
		_welcome2.setVerticalAlignment(JLabel.CENTER);
		_welcome2.setHorizontalAlignment(JLabel.CENTER);
		_welcome2.setVerticalTextPosition(JLabel.CENTER);
		_welcome2.setHorizontalTextPosition(JLabel.TRAILING);
		_welcome2.setRequestFocusEnabled(true);
		_welcome2.setMinimumSize(null);
		_welcome2.setMaximumSize(new Dimension(2147483647, 2147483647));
		_welcome2.setPreferredSize(new Dimension(305, 20));
	}

	private void initReloadConfButton() 
	{		
		this._reloadConfButton = new JButton();
		_reloadConfButton.setName("reloadConfButton");
		_reloadConfButton.setToolTipText(null);
		_reloadConfButton.setText("Carica una precedente configurazione");
		_reloadConfButton.setEnabled(true);
		_reloadConfButton.setSelected(false);
		_reloadConfButton.setFont(new Font("verdana", Font.BOLD, 12));
		_reloadConfButton.setVerticalAlignment(AbstractButton.CENTER);
		_reloadConfButton.setHorizontalAlignment(AbstractButton.CENTER);
		_reloadConfButton.setVerticalTextPosition(AbstractButton.CENTER);
		_reloadConfButton.setHorizontalTextPosition(AbstractButton.TRAILING);
		_reloadConfButton.setIconTextGap(4);
		_reloadConfButton.setRequestFocusEnabled(true);
		_reloadConfButton.setBackground(new Color(18, 175, 184, 255));
		_reloadConfButton.setMinimumSize(null);
		_reloadConfButton.setMaximumSize(new Dimension(217483647, 2147483647));
		_reloadConfButton.setPreferredSize(new Dimension(310,40));
		_reloadConfButton.setDefaultCapable(true);
	
	// load an XML configurations file and initialize the data reads
		_reloadConfButton.addActionListener
		(
			new ActionListener()
			{					
				public void actionPerformed(ActionEvent e)
				{					
					int num = JOptionPane.YES_OPTION;														
					if (_data.isDirty() == true)//non legge _data membro...
						num = JOptionPane.showConfirmDialog(new JPanel(),"Caricando una vecchia configurazione i dati attuali saranno sovrascritti.\nProcedere?");
						
					if (num == JOptionPane.YES_OPTION)
					{
						String confLoadPath = "";
						JFrame f = new JFrame();
						int returnVal = _jfc.showOpenDialog(f);
						if (returnVal == JFileChooser.APPROVE_OPTION)
						{
							confLoadPath = _jfc.getSelectedFile().getPath();
							_data.loadData(confLoadPath);
							_data.setDirty(true);						
						}
					}
				}
			}
		);
	}
		
	private void initExitButton() 
	{
		this._exitButton = new JButton();
		_exitButton.setName("_exitButton");
		_exitButton.setToolTipText(null);
		_exitButton.setText("ESCI");
		_exitButton.setEnabled(true);
		_exitButton.setSelected(false);
		_exitButton.setFont(new Font("verdana", Font.BOLD, 12));
		_exitButton.setVerticalAlignment(AbstractButton.CENTER);
		_exitButton.setHorizontalAlignment(AbstractButton.CENTER);
		_exitButton.setVerticalTextPosition(AbstractButton.CENTER);
		_exitButton.setHorizontalTextPosition(AbstractButton.TRAILING);
		_exitButton.setIconTextGap(4);
		_exitButton.setRequestFocusEnabled(true);
		_exitButton.setBackground(new Color(128, 255, 255, 255));
		_exitButton.setMinimumSize(null);
		_exitButton.setMaximumSize(new Dimension(2147483647, 2147483647));
		_exitButton.setPreferredSize(new Dimension(100, 40));
		_exitButton.setDefaultCapable(true);		
		
		_exitButton.addActionListener(new Exit());		
	}
	
/**
 * Main
 */ 
 	public static void main(String[] args)
 	{
 	// look and feel Windows
  		final String lookFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
 		try	{UIManager.setLookAndFeel(lookFeel);}
 		catch (Exception e)
 		{}
 	// create all object of interface 
 		JFrame f = new JFrame("Studio Ing. Enrico Berti - EcoParete - Claudiosoft 2004"); 		
 		InputGenerali igen = new InputGenerali();
 		InputStrati istra = new InputStrati();
 		Risultati ris = new Risultati();
 		Data data = new Data(igen,istra,ris);
 		data.setDirty(false);	
 		Studio studio = new Studio(data); 		
 		Elaborazione ela = new Elaborazione(data,ris);
 		Avvio avvio = new Avvio(data,ela);
 		Riconoscimenti ric = new Riconoscimenti();
 		JScrollPane jsp = new JScrollPane(studio);
 		JScrollPane jsp2 = new JScrollPane(igen); 		
 		JScrollPane jsp3 = new JScrollPane(istra); 		
 		JScrollPane jsp4 = new JScrollPane(avvio); 		
 		JScrollPane jsp5 = new JScrollPane(ris); 
 		JScrollPane jsp6 = new JScrollPane(ric); 
 		JTabbedPane jtp = new JTabbedPane();
 		jtp.add(jsp,"Prima pagina");
 		jtp.add(jsp2,"Passo 1");
 		jtp.add(jsp3,"Passo 2");
 		jtp.add(jsp4,"Passo 3");
 		jtp.add(jsp5,"Risultati");
 		jtp.add(jsp6,"Riconoscimenti");
				
	// add exit program listener 		
		f.setDefaultCloseOperation(0);//DO_NOTHING_ON_CLOSE
		f.addWindowListener(new CloseHandler());				
 		f.setVisible(true); 			
 		f.getContentPane().add (jtp, BorderLayout.CENTER);
    	f.setSize (550, 360);   
    	f.setLocation(50,50); 	   
    	f.show();
    	f.repaint();
    }
    
    /**
	 * Handles the window closing event.
	 *
	 * @param event  the window event.
	 */
  	protected static class CloseHandler extends WindowAdapter
  	{  			    
		public void windowClosing(final WindowEvent event)
	    {
	    	int ris = JOptionPane.showConfirmDialog(new JPanel(),"Si vuol veramente uscire da EcoParete?");
							
			if (ris != JOptionPane.YES_OPTION)
				return;		
	      	System.out.println ("Bye bye!");
			System.exit(0);		
	    }
	}
}
