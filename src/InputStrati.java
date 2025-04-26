package claudiosoft.studioBerti;

/**
 *	COIBENTAZIONE ver. 1.0
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
 * This class is the interface that gets stratus's specific inputs
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.table.*;
import java.util.Vector;

public class InputStrati extends JPanel 
{
/**
 * Fields
 */	
	private GridBagConstraints _constraints = new GridBagConstraints();
	private JLabel _inputStrati;
	private JLabel _interno;
	private JScrollPane _tabella;
	private JTable _tab; // this is the real table that is hold in JScrollPane
	private JLabel _esterno;
	
/**
 * Constructor
 */
	public InputStrati() 
	{
		setLayout(new GridBagLayout());
		SkeletonGraphic skgraph = new SkeletonGraphic(_constraints);			
	// titolo	
		initInputStrati();
		skgraph.spacing(0,50);
		skgraph.grid(0,0);
		add(_inputStrati, _constraints);			
	// strato interno	
		initInterno();
		skgraph.spacing(10,0);		
		skgraph.grid(0,1);
		add(_interno, _constraints);
	// tabella
		initTabella();
		skgraph.spanning(1,1);
		skgraph.grid(0,2);
		add(_tabella, _constraints);
	// strato esterno
		initEsterno();	
		skgraph.grid(0,3);			
		add(_esterno, _constraints);		
		
		revalidate();
	}

// ------ START DESCRIPTION AND INITIALIZATION OF COMPONENTS

	private void initInputStrati() 
	{
		this._inputStrati = new JLabel();
		_inputStrati.setName("inputStrati");
		_inputStrati.setToolTipText(null);
		_inputStrati.setText("INPUT SPECIFICI DEGLI STRATI");
		_inputStrati.setFont(new Font("verdana", Font.BOLD, 14));
		_inputStrati.setForeground(new Color(0, 0, 0, 255));
		_inputStrati.setEnabled(true);
		_inputStrati.setVerticalAlignment(JLabel.CENTER);
		_inputStrati.setHorizontalAlignment(JLabel.LEADING);
		_inputStrati.setVerticalTextPosition(JLabel.CENTER);
		_inputStrati.setHorizontalTextPosition(JLabel.TRAILING);
		_inputStrati.setRequestFocusEnabled(true);
		_inputStrati.setMinimumSize(null);
		_inputStrati.setMaximumSize(new Dimension(2147483647, 2147483647));
		_inputStrati.setPreferredSize(new Dimension(320, 25));		
		_inputStrati.setForeground(new Color (0,0,108,255));
	}	
	
	private void initInterno() 
	{
		this._interno = new JLabel();
		_interno.setName("interno");		
		_interno.setToolTipText(null);
		_interno.setText("----------- INTERNO -----------");
		_interno.setFont(new Font("verdana", Font.BOLD, 12));
		_interno.setForeground(new Color(0, 0, 0, 255));
		_interno.setEnabled(true);
		_interno.setVerticalAlignment(JLabel.CENTER);
		_interno.setHorizontalAlignment(JLabel.LEADING);
		_interno.setVerticalTextPosition(JLabel.CENTER);
		_interno.setHorizontalTextPosition(JLabel.TRAILING);
		_interno.setRequestFocusEnabled(true);
		_interno.setMinimumSize(null);
		_interno.setMaximumSize(new Dimension(2147483647, 2147483647));
		_interno.setPreferredSize(new Dimension(220, 20));						
	}	
	
	public void initTabella()
	{
		TableModel modelloTab = new ModelloTabella();
		_tab = new JTable(modelloTab);				
		JTableHeader tabHead = _tab.getTableHeader();
		tabHead.setReorderingAllowed(false);		
		_tab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);		
		_tab.setEnabled(true);
		_tab.setCellSelectionEnabled(true);
		_tab.setRowSelectionAllowed(false);
		_tab.setName("tabella");
		_tab.setColumnSelectionAllowed(false);
		_tab.setDragEnabled(true);
		_tab.setSelectionBackground(new Color(0,0,133,255));		
		_tab.setPreferredSize(new Dimension(500,175)); 						
		_tab.setSurrendersFocusOnKeystroke(true);	
		this._tabella = new JScrollPane(_tab);
		_tabella.setPreferredSize(new Dimension(500,200)); 				
	}
	
	private void initEsterno() 
	{
		this._esterno = new JLabel();
		_esterno.setName("interno");
		_esterno.setToolTipText(null);
		_esterno.setText("----------- ESTERNO -----------");
		_esterno.setFont(new Font("verdana", Font.BOLD, 12));
		_esterno.setForeground(new Color(0, 0, 0, 255));
		_esterno.setEnabled(true);
		_esterno.setVerticalAlignment(JLabel.CENTER);
		_esterno.setHorizontalAlignment(JLabel.LEADING);
		_esterno.setVerticalTextPosition(JLabel.CENTER);
		_esterno.setHorizontalTextPosition(JLabel.TRAILING);
		_esterno.setRequestFocusEnabled(true);
		_esterno.setMinimumSize(null);
		_esterno.setMaximumSize(new Dimension(2147483647, 2147483647));
		_esterno.setPreferredSize(new Dimension(220, 20));						
	}	
	
// inner class per la definizione del modello della tabella
	public static class ModelloTabella extends AbstractTableModel
	{
		private String[] headings = new String[]{"Descrizione","Spessore(m)","Massa volumica(Kg/m^3)","Conduc. termica(W/Mk)", "Calore specifico(Kj/KgK)"};
		private String[][] data = new String[][]		
		{
			{"","","","",""},
			{"","","","",""},
			{"","","","",""},
			{"","","","",""},
			{"","","","",""},
			{"","","","",""},
			{"","","","",""},
			{"","","","",""}, 
			{"","","","",""}, 
			{"","","","",""}
		};		
		
	// metodi da implementare per forza
		public int getRowCount(){return data.length;}
		public int getColumnCount(){return data[0].length;}
		public Object getValueAt(int row, int column) {return data[row][column];}
		public String getColumnName(int column){return headings[column];}		
		public Class getColumnClass(int column){return data[0][column].getClass();}		
		public boolean isCellEditable(int row, int column){return true;}
		public void setValueAt(Object value, int row, int col)
		{
			data[row][col] = (String)value;
		}						
	}
    
// ----- ALTRI METODI
 	

/**
 * return a table of 10 rows and 5 columns
 */
	public String[][] copyOut()
	{
		String[][] table = new String[10][5];
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				table[i][j] = (String)_tab.getValueAt(i,j);
			}
		}
		return table;
	}
	
/**
 * it take a table with all values that will be stored in sequence.
 * The table must hold string of data values
 */	
	public void copyIn(String[][] tab)
	{		
		try
		{
			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 5; j++)
				{
					_tab.setValueAt(tab[i][j],i,j);
				}
			}
		}
		catch (Exception e)
		{}		
	}
	

}
