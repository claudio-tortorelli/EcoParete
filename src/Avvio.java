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
 * In this class are displayed all swing object of
 * step three: delete forms button, save configuration button,
 * start elaboration button
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

/**
 * 
 */
public class Avvio extends JPanel 
{
/**
 * Fields
 */	
	private GridBagConstraints _constraints = new GridBagConstraints();
	private JLabel _titolo;	
	private JButton _salva;
	private JButton _avvio;
	private JButton _cancella;
	private Data _data;
	private Elaborazione _elaborazione;
	
/**
 *
 * Constructor
 */
	public Avvio(Data data,Elaborazione elab)	 
	{
		_data = data;
		_elaborazione = elab;
		setLayout(new GridBagLayout());	
		SkeletonGraphic skgraph = new SkeletonGraphic(_constraints);
	// title
		initTitolo();
		skgraph.spacing(0,60);
		skgraph.grid(0,0);
		add(_titolo, _constraints);
	// button to save
		skgraph.spacing(20,0);
		initSalva();
		skgraph.grid(0,1);
		add(_salva, _constraints);
	// button to delete
		skgraph.spacing(20,0);
		initCancella();
		skgraph.grid(0,2);
		add(_cancella, _constraints);
	// button to start
		initAvvio();
		skgraph.spacing(40,0);
		skgraph.grid(0,3);
		add(_avvio, _constraints);
		
		revalidate();
	}

// ------ START DESCRIPTION AND INITIALIZATION OF COMPONENTS

	private void initTitolo() 
	{
		this._titolo = new JLabel();
		_titolo.setName("Elaborazione");
		_titolo.setToolTipText(null);
		_titolo.setText("ELABORAZIONE");
		_titolo.setFont(new Font("verdana", Font.BOLD, 14));		
		_titolo.setEnabled(true);
		_titolo.setVerticalAlignment(JLabel.CENTER);
		_titolo.setHorizontalAlignment(JLabel.LEADING);
		_titolo.setVerticalTextPosition(JLabel.CENTER);
		_titolo.setHorizontalTextPosition(JLabel.TRAILING);
		_titolo.setRequestFocusEnabled(true);
		_titolo.setMinimumSize(null);
		_titolo.setMaximumSize(new Dimension(2147483647, 2147483647));
		_titolo.setPreferredSize(new Dimension(186, 24));
		_titolo.setForeground(new Color (0,0,108,255));		
	}	

	private void initSalva() 
	{
		this._salva = new JButton();
		_salva.setName("salva");
       	_salva.setToolTipText(null);
		_salva.setText("Salva la configurazione attuale");
		_salva.setEnabled(true);
		_salva.setSelected(false);
		_salva.setFont(new Font("verdana", Font.BOLD, 12));
		_salva.setVerticalAlignment(AbstractButton.CENTER);
		_salva.setHorizontalAlignment(AbstractButton.CENTER);
		_salva.setVerticalTextPosition(AbstractButton.CENTER);
		_salva.setHorizontalTextPosition(AbstractButton.TRAILING);
		_salva.setIconTextGap(4);
		_salva.setRequestFocusEnabled(true);
		_salva.setBackground(new Color(18, 175, 184, 255));
		_salva.setMinimumSize(null);
		_salva.setMaximumSize(new Dimension(2147483647, 2147483647));
		_salva.setPreferredSize(new Dimension(320,40));
		_salva.setDefaultCapable(true);
		
	// perfom the action
		_salva.addActionListener
		(	new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				// show input dialog					
					String name = "";
					boolean exit = false;										
				// repeat dialog while a name is inserted or cancel button is pressed
					while (name.equals("") && !exit)
					{					
						name = JOptionPane.showInputDialog(new JPanel(),"Immetti il nome del file che sarà salvato");				
						if (name != null) 
						{
							if (name.trim().equals("")) name = "";													
						}
		 				else
		 				{
		 					exit = true;
							name = "";	
						}
					}
				// if a name is inserted manage the extension									  
					if (!exit)
					{
						int ind = name.lastIndexOf('.');
						if (ind == -1) name = name + ".xml";
						else 
						{
							name = name.substring(0,ind);
							name = name + ".xml";
						}	
						// save the data object in xml
						_data.getInputGenerali();
						_data.getInputStrati();				
					 	_data.saveData(name);	
					 	_data.setDirty(false);				
					}							 	
				}
			}
		);
	}
		
	private void initAvvio() 
	{
		this._avvio = new JButton();
		_avvio.setName("avvio");
		_avvio.setToolTipText(null);
		_avvio.setText("Avvio elaborazione");
		_avvio.setEnabled(true);
		_avvio.setSelected(false);
		_avvio.setFont(new Font("verdana", Font.BOLD, 12));
		_avvio.setVerticalAlignment(AbstractButton.CENTER);
		_avvio.setHorizontalAlignment(AbstractButton.CENTER);
		_avvio.setVerticalTextPosition(AbstractButton.CENTER);
		_avvio.setHorizontalTextPosition(AbstractButton.TRAILING);
		_avvio.setIconTextGap(4);
		_avvio.setRequestFocusEnabled(true);
		_avvio.setBackground(new Color(0, 221, 221, 255));
		_avvio.setMinimumSize(null);
		_avvio.setMaximumSize(new Dimension(2147483647, 2147483647));
		_avvio.setPreferredSize(new Dimension(320, 40));
		_avvio.setDefaultCapable(true);		
		
		// perform the action
		_avvio.addActionListener(_elaborazione);				
	}
	
	private void initCancella() 
	{
		this._cancella = new JButton();
		_cancella.setName("cancella");
       	_cancella.setToolTipText(null);
		_cancella.setText("Cancella la configurazione attuale");
		_cancella.setEnabled(true);
		_cancella.setSelected(false);
		_cancella.setFont(new Font("verdana", Font.BOLD, 12));
		_cancella.setVerticalAlignment(AbstractButton.CENTER);
		_cancella.setHorizontalAlignment(AbstractButton.CENTER);
		_cancella.setVerticalTextPosition(AbstractButton.CENTER);
		_cancella.setHorizontalTextPosition(AbstractButton.TRAILING);
		_cancella.setIconTextGap(4);
		_cancella.setRequestFocusEnabled(true);
		_cancella.setBackground(new Color(18, 175, 184, 255));
		_cancella.setMinimumSize(null);
		_cancella.setMaximumSize(new Dimension(2147483647, 2147483647));
		_cancella.setPreferredSize(new Dimension(320,40));
		_cancella.setDefaultCapable(true);
		_data.setDirty(false);
		
	// perfom the action
		_cancella.addActionListener
		(	new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int ris = JOptionPane.showConfirmDialog(new JPanel(), "I dati attualmente presenti nei form saranno eliminati.\nProcedo?");
				// if the user want to delete the forms...
					if (ris == JOptionPane.YES_OPTION)
					{
						_data.deleteForms();						
					}
				}
			}
		);
	}
}
