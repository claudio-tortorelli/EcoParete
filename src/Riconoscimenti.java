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
 * this class displays a panel with recognitions to Claudiosoft	
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

/**
 * 
 */
public class Riconoscimenti extends JPanel
{
/**
 * Fields
 */	
	private GridBagConstraints _constraints = new GridBagConstraints();
	private JLabel _titolo;
	private JLabel _nomeProg;
	private JLabel _ver;
	private JLabel _author;
	private JLabel _email;
	private JLabel _msg;
	private Image _img;	
	
	
/**
 *
 * Constructor
 */
	public Riconoscimenti()	 
	{
		setLayout(new GridBagLayout());	
		SkeletonGraphic skgraph = new SkeletonGraphic(_constraints);
	// title		
		_titolo = new JLabel("CLAUDIOSOFT");
		_titolo.setFont(new Font("verdana", Font.BOLD, 14));		
		_titolo.setForeground(new Color (0,0,108,255));		
		skgraph.spacing(0,0);
		skgraph.grid(0,0);
		add(_titolo, _constraints);
	// image
		this._img = Toolkit.getDefaultToolkit().getImage(Studio.dirImg+"claudiosoft.jpg");				
		GraphicsX photo = new GraphicsX(_img,10,10,180,180);												
		skgraph.grid(0,1);	
		add(photo,_constraints);		
	// name prog
		_nomeProg = new JLabel("EcoParete");
		_nomeProg.setFont(new Font("verdana", Font.BOLD, 13));		
		skgraph.spacing(10,0);
		skgraph.grid(0,2);				
		add(_nomeProg, _constraints);	
	// claudiosoft
		_ver = new JLabel("ver. 1.0 - April, 2004 - License GPL GNU");
		skgraph.spacing(0,0);
		skgraph.grid(0,3);
		add(_ver, _constraints);
	// author
		_author = new JLabel("Autore: Claudio Tortorelli");
		_author.setFont(new Font("verdana", Font.BOLD, 12));		
		//skgraph.spacing(10,0);
		skgraph.grid(0,4);
		add(_author, _constraints);
	// msg
		_msg = new JLabel("Per segnalazioni scrivere a");
		_msg.setFont(new Font("verdana", Font.PLAIN, 12));		
		//skgraph.spacing(20,0);
		skgraph.grid(0,5);
		add(_msg, _constraints);
	// email
		_email = new JLabel("torto@virgilio.it");
		_email.setFont(new Font("verdana", Font.BOLD, 12));		
		//skgraph.spacing(10,0);
		skgraph.grid(0,6);
		add(_email, _constraints);		
	}
}
