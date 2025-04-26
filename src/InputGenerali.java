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
 * This class is the interface for general inputs
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Vector;


public class InputGenerali extends JPanel implements Cloneable 
{
/**
 * Fields: componenti della GUI
 */	
	private GridBagConstraints _constraints = new GridBagConstraints();	
	private JLabel _inputGenerali;	
	private JLabel _nomeProgetto;
	private JTextField _nomeProgettoField;
	private JLabel _descrizParete;
	private JTextArea _textDescrizParete;
	private JScrollPane _descrizParetePanel;
	private JLabel _tempInterna;
	private JTextField _tempInternaField;
	private JLabel _tempEsterna;
	private JTextField _tempEsternaField;
	private JLabel _coeffLiminInt;
	private JTextField _coeffLiminIntField;
	private JLabel _coeffLiminEst;
	private JTextField _coeffLiminEstField;		
	private JButton _exitButton;
	private JButton _startButton;
	
	final String dirImg = "images" + File.separatorChar;

/**
 * Constructor
 */
	public InputGenerali() 
	{
		setLayout(new GridBagLayout());		
		SkeletonGraphic skgraph = new SkeletonGraphic(_constraints);
	// title
		initInputGenerali();				
		skgraph.grid(0,0);
		add(_inputGenerali, _constraints);
	// Nome progetto
		initNomeProgetto();
		skgraph.spanning(1,1);
		skgraph.spacing(15,0);
		skgraph.grid(0,1);
		add(_nomeProgetto, _constraints);
	// campo nome progetto
		initNomeProgettoField();
		skgraph.spacing(10,40);
		skgraph.grid(1,1);
		add(_nomeProgettoField, _constraints);	
	// Descrizione parete	
		initDescrizioneParete();	
		skgraph.spacing(10,0);	
		skgraph.grid(0,2);	
		add(_descrizParete, _constraints);	
	// Descrizione parete area testo			
		initDescrizParetePanel();
		skgraph.spacing(10,40);
		skgraph.grid(1,2);
		add(_descrizParetePanel, _constraints);	
	// temperatura interna					
		initTempInterna();
		skgraph.spacing(10,0);	
		skgraph.grid(0,3);
		add(_tempInterna, _constraints);
	// temperatura interna campo				
		initTempInternaField();
		skgraph.spacing(10,-160);
		skgraph.grid(1,3);
		add(_tempInternaField, _constraints);
	// temperatura esterna	
		initTempEsterna();
		skgraph.spacing(15,0);		
		skgraph.grid(0,4);
		add(_tempEsterna, _constraints);
	// temperatura esterna campo						
		initTempEsternaField();
		skgraph.spacing(10,-160);
		skgraph.grid(1,4);
		add(_tempEsternaField, _constraints);
	// coefficiente liminare interno
		initCoeffLiminInt();
		skgraph.spacing(10,0);	
		skgraph.grid(0,5);
		add(_coeffLiminInt, _constraints);
	// coefficiente liminare interno campo						
		initCoeffLiminIntField();
		skgraph.spacing(10,-160);
		skgraph.grid(1,5);
		add(_coeffLiminIntField, _constraints);
	// coefficiente liminare esterno
		initCoeffLiminEst();
		skgraph.spacing(10,0);	
		skgraph.grid(0,6);
		add(_coeffLiminEst, _constraints);
	// coefficiente liminare esterno campo						
		initCoeffLiminEstField();
		skgraph.spacing(10,-160);
		skgraph.grid(1,6);
		add(_coeffLiminEstField, _constraints);
			
		revalidate();
	}

// ------ START DESCRIPTION AND INITIALIZATION OF COMPONENTS

	private void initInputGenerali() 
	{
		this._inputGenerali = new JLabel();
		_inputGenerali.setName("Input Generali");
		_inputGenerali.setToolTipText(null);
		_inputGenerali.setText("INPUT GENERALI");
		_inputGenerali.setFont(new Font("verdana", Font.BOLD, 14));		
		_inputGenerali.setEnabled(true);
		_inputGenerali.setVerticalAlignment(JLabel.CENTER);
		_inputGenerali.setHorizontalAlignment(JLabel.LEADING);
		_inputGenerali.setVerticalTextPosition(JLabel.CENTER);
		_inputGenerali.setHorizontalTextPosition(JLabel.TRAILING);
		_inputGenerali.setRequestFocusEnabled(true);
		_inputGenerali.setMinimumSize(null);
		_inputGenerali.setMaximumSize(new Dimension(2147483647, 2147483647));
		_inputGenerali.setPreferredSize(new Dimension(186, 24));
		_inputGenerali.setForeground(new Color (0,0,108,255));		
	}	
	
	private void initNomeProgetto() 
	{
		this._nomeProgetto = new JLabel();
		_nomeProgetto.setIcon(new ImageIcon(dirImg+"bluball.gif"));
		_nomeProgetto.setIconTextGap(10);
		_nomeProgetto.setName("nomeProg");
		_nomeProgetto.setToolTipText(null);
		_nomeProgetto.setText("1.1 Nome progetto");
		_nomeProgetto.setFont(new Font("verdana", Font.BOLD, 12));
		_nomeProgetto.setForeground(new Color(0, 0, 0, 255));
		_nomeProgetto.setEnabled(true);
		_nomeProgetto.setVerticalAlignment(JLabel.CENTER);
		_nomeProgetto.setHorizontalAlignment(JLabel.LEADING);
		_nomeProgetto.setVerticalTextPosition(JLabel.CENTER);
		_nomeProgetto.setHorizontalTextPosition(JLabel.TRAILING);
		_nomeProgetto.setRequestFocusEnabled(true);
		_nomeProgetto.setMinimumSize(null);
		_nomeProgetto.setMaximumSize(new Dimension(2147483647, 2147483647));
		_nomeProgetto.setPreferredSize(new Dimension(200, 25));
	}
	
	private void initNomeProgettoField() 
	{
		_nomeProgettoField = new JTextField();
		_nomeProgettoField.setEnabled(true);
		_nomeProgettoField.setMinimumSize(new Dimension(30, 18));
		_nomeProgettoField.setMaximumSize(new Dimension(2147483647, 2147483647));
		_nomeProgettoField.setPreferredSize(new Dimension(250, 20));
		_nomeProgettoField.setName("");
		_nomeProgettoField.setToolTipText("Inserire il nome del progetto");
		_nomeProgettoField.setText("");
		_nomeProgettoField.setFont(new Font("verdana", Font.PLAIN, 11));			
	}
	
	private void initDescrizioneParete() 
	{
		this._descrizParete = new JLabel();
		_descrizParete.setIcon(new ImageIcon(dirImg+"bluball.gif"));
		_descrizParete.setIconTextGap(10);
		_descrizParete.setName("descrizParete");
		_descrizParete.setToolTipText(null);
		_descrizParete.setText("1.2 Descrizione parete");
		_descrizParete.setFont(new Font("verdana", Font.BOLD, 12));
		_descrizParete.setForeground(new Color(0, 0, 0, 255));
		_descrizParete.setEnabled(true);
		_descrizParete.setVerticalAlignment(JLabel.CENTER);
		_descrizParete.setHorizontalAlignment(JLabel.LEADING);
		_descrizParete.setVerticalTextPosition(JLabel.CENTER);
		_descrizParete.setHorizontalTextPosition(JLabel.TRAILING);
		_descrizParete.setRequestFocusEnabled(true);
		_descrizParete.setMinimumSize(null);
		_descrizParete.setMaximumSize(new Dimension(2147483647, 2147483647));
		_descrizParete.setPreferredSize(new Dimension(200, 25));
	}
	
	private void initDescrizParetePanel()
	{
		_textDescrizParete = new JTextArea();		
		_textDescrizParete.setName("textDescrizParete");
		_textDescrizParete.setToolTipText("Inserire qui la descrizione della parete");
		_textDescrizParete.setText("");
		_textDescrizParete.setFont(new Font("verdana", Font.PLAIN, 11));
		_textDescrizParete.setEnabled(true);				
		_textDescrizParete.setRequestFocusEnabled(true);
		_textDescrizParete.setMinimumSize(new Dimension(250,50));
		_textDescrizParete.setMaximumSize(new Dimension(2147483647, 2147483647));
		_textDescrizParete.setLineWrap(true);
		
		_descrizParetePanel = new JScrollPane(_textDescrizParete);
		_descrizParetePanel.setEnabled(true);
		_descrizParetePanel.setName("scrollPane");
		_descrizParetePanel.setMinimumSize(new Dimension(25,25));
		_descrizParetePanel.setMaximumSize(new Dimension(2147483647, 2147483647));
		_descrizParetePanel.setPreferredSize(new Dimension(250,50));	
	}
	
	private void initTempInterna() 
	{
		this._tempInterna = new JLabel();
		_tempInterna.setName("tempInterna");
		_tempInterna.setToolTipText(null);
		_tempInterna.setIcon(new ImageIcon(dirImg+"bluball.gif"));
		_tempInterna.setIconTextGap(10);
		_tempInterna.setText("1.3 Temperatura interna");
		_tempInterna.setFont(new Font("verdana", Font.BOLD, 12));
		_tempInterna.setForeground(new Color(0, 0, 0, 255));
		_tempInterna.setEnabled(true);
		_tempInterna.setVerticalAlignment(JLabel.CENTER);
		_tempInterna.setHorizontalAlignment(JLabel.LEADING);
		_tempInterna.setVerticalTextPosition(JLabel.CENTER);
		_tempInterna.setHorizontalTextPosition(JLabel.TRAILING);
		_tempInterna.setRequestFocusEnabled(true);
		_tempInterna.setMinimumSize(null);
		_tempInterna.setMaximumSize(new Dimension(2147483647, 2147483647));
		_tempInterna.setPreferredSize(new Dimension(200, 25));
	}
	
	private void initTempInternaField() 
	{
		_tempInternaField = new JTextField();
		_tempInternaField.setEnabled(true);
		_tempInternaField.setMinimumSize(new Dimension(30, 18));
		_tempInternaField.setMaximumSize(new Dimension(2147483647, 2147483647));
		_tempInternaField.setPreferredSize(new Dimension(50, 20));
		_tempInternaField.setName("tempInternaField");
		_tempInternaField.setToolTipText("Inserire la temperatura interna della parete");
		_tempInternaField.setText("");
		_tempInternaField.setFont(new Font("verdana", Font.PLAIN, 11));	
	}
	
	private void initTempEsterna() 
	{
		this._tempEsterna = new JLabel();
		_tempEsterna.setName("tempEsterna");
		_tempEsterna.setToolTipText(null);
		_tempEsterna.setIcon(new ImageIcon(dirImg+"bluball.gif"));
		_tempEsterna.setText("1.4 Temperatura esterna");
		_tempEsterna.setFont(new Font("verdana", Font.BOLD, 12));
		_tempEsterna.setForeground(new Color(0, 0, 0, 255));
		_tempEsterna.setEnabled(true);
		_tempEsterna.setIconTextGap(10);
		_tempEsterna.setVerticalAlignment(JLabel.CENTER);
		_tempEsterna.setHorizontalAlignment(JLabel.LEADING);
		_tempEsterna.setVerticalTextPosition(JLabel.CENTER);
		_tempEsterna.setHorizontalTextPosition(JLabel.TRAILING);
		_tempEsterna.setRequestFocusEnabled(true);
		_tempEsterna.setMinimumSize(null);
		_tempEsterna.setMaximumSize(new Dimension(2147483647, 2147483647));
		_tempEsterna.setPreferredSize(new Dimension(200, 25));
	}
	
	private void initTempEsternaField() 
	{
		_tempEsternaField = new JTextField();
		_tempEsternaField.setEnabled(true);
		_tempEsternaField.setMinimumSize(new Dimension(30, 18));
		_tempEsternaField.setMaximumSize(new Dimension(2147483647, 2147483647));
		_tempEsternaField.setPreferredSize(new Dimension(50, 20));
		_tempEsternaField.setName("tempInternaField");
		_tempEsternaField.setToolTipText("Inserire la temperatura esterna della parete");
		_tempEsternaField.setText("");
		_tempEsternaField.setFont(new Font("verdana", Font.PLAIN, 11));			
	}
	
	private void initCoeffLiminInt() 
	{
		this._coeffLiminInt = new JLabel();
		_coeffLiminInt.setName("coeffLiminInt");
		_coeffLiminInt.setToolTipText(null);
		_coeffLiminInt.setText("1.5 Coeff. liminare interno");
		_coeffLiminInt.setIcon(new ImageIcon(dirImg+"bluball.gif"));
		_coeffLiminInt.setFont(new Font("verdana", Font.BOLD, 12));
		_coeffLiminInt.setForeground(new Color(0, 0, 0, 255));
		_coeffLiminInt.setEnabled(true);
		_coeffLiminInt.setIconTextGap(10);
		_coeffLiminInt.setVerticalAlignment(JLabel.CENTER);
		_coeffLiminInt.setHorizontalAlignment(JLabel.LEADING);
		_coeffLiminInt.setVerticalTextPosition(JLabel.CENTER);
		_coeffLiminInt.setHorizontalTextPosition(JLabel.TRAILING);
		_coeffLiminInt.setRequestFocusEnabled(true);
		_coeffLiminInt.setMinimumSize(null);
		_coeffLiminInt.setMaximumSize(new Dimension(2147483647, 2147483647));
		_coeffLiminInt.setPreferredSize(new Dimension(200, 25));
	}
	
	private void initCoeffLiminIntField() 
	{
		_coeffLiminIntField = new JTextField();
		_coeffLiminIntField.setEnabled(true);
		_coeffLiminIntField.setMinimumSize(new Dimension(30, 20));
		_coeffLiminIntField.setMaximumSize(new Dimension(2147483647, 2147483647));
		_coeffLiminIntField.setPreferredSize(new Dimension(50, 20));
		_coeffLiminIntField.setName("coeffLiminInternoField");
		_coeffLiminIntField.setToolTipText("Inserire il coefficiente liminare interno");
		_coeffLiminIntField.setText("");
		_coeffLiminIntField.setFont(new Font("verdana", Font.PLAIN, 11));			
	}

	private void initCoeffLiminEst() 
	{
		this._coeffLiminEst = new JLabel();
		_coeffLiminEst.setName("coeffLiminEst");
		_coeffLiminEst.setToolTipText(null);
		_coeffLiminEst.setText("1.6 Coeff. liminare esterno");
		_coeffLiminEst.setFont(new Font("verdana", Font.BOLD, 12));
		_coeffLiminEst.setForeground(new Color(0, 0, 0, 255));
		_coeffLiminEst.setEnabled(true);
		_coeffLiminEst.setIcon(new ImageIcon(dirImg+"bluball.gif"));
		_coeffLiminEst.setVerticalAlignment(JLabel.CENTER);
		_coeffLiminEst.setIconTextGap(10);
		_coeffLiminEst.setHorizontalAlignment(JLabel.LEADING);
		_coeffLiminEst.setVerticalTextPosition(JLabel.CENTER);
		_coeffLiminEst.setHorizontalTextPosition(JLabel.TRAILING);
		_coeffLiminEst.setRequestFocusEnabled(true);
		_coeffLiminEst.setMinimumSize(null);
		_coeffLiminEst.setMaximumSize(new Dimension(2147483647, 2147483647));
		_coeffLiminEst.setPreferredSize(new Dimension(200, 25));
	}
	
	private void initCoeffLiminEstField() 
	{
		_coeffLiminEstField = new JTextField();
		_coeffLiminEstField.setEnabled(true);
		_coeffLiminEstField.setMinimumSize(new Dimension(30, 18));
		_coeffLiminEstField.setMaximumSize(new Dimension(2147483647, 2147483647));
		_coeffLiminEstField.setPreferredSize(new Dimension(50, 20));
		_coeffLiminEstField.setName("coeffLiminInternoField");
		_coeffLiminEstField.setToolTipText("Inserire il coefficiente liminare esterno");
		_coeffLiminEstField.setText("");
		_coeffLiminEstField.setFont(new Font("verdana", Font.PLAIN, 11));			
	}
// ----- ALTRI METODI

/**
 * It copy out the set of object that hold informations
 */
	public Vector copyOut()
	{
		Vector formVal = new Vector();
		if (_textDescrizParete.getText() == null) _textDescrizParete.setText("");
		if (_tempInternaField.getText() == null) _tempInternaField.setText("");
		if (_tempEsternaField.getText() == null) _tempEsternaField.setText("");
		if (_coeffLiminIntField.getText() == null) _coeffLiminIntField.setText("");
		if (_coeffLiminEstField.getText() == null) _coeffLiminEstField.setText("");
		
		formVal.addElement(_textDescrizParete.getText());
		formVal.addElement(_tempInternaField.getText());
		formVal.addElement(_tempEsternaField.getText());
		formVal.addElement(_coeffLiminIntField.getText());
		formVal.addElement(_coeffLiminEstField.getText());							
		return formVal;
	}

	public String getNomeProgetto()	
	{
		return _nomeProgettoField.getText();	
	}

/**
 * it take a vector with all values that will be stored in sequence.
 * The vector must hold string of data values
 */	
	public void copyIn(Vector val)
	{	
		try
		{	
			_nomeProgettoField.setText((String)val.elementAt(0));
			_textDescrizParete.setText((String)val.elementAt(1));
			_tempInternaField.setText((String)val.elementAt(2));
			_tempEsternaField.setText((String)val.elementAt(3));
			_coeffLiminIntField.setText((String)val.elementAt(4));
			_coeffLiminEstField.setText((String)val.elementAt(5));			
		}
		catch (ClassCastException e)
		{}		
	}

}
