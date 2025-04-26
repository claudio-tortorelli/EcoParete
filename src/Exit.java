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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exit implements ActionListener
{
	public Exit()
	{		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int ris = JOptionPane.showConfirmDialog(new JPanel(),"Si vuol veramente uscire da EcoParete?");
						
		if (ris == JOptionPane.YES_OPTION)
		{		
			System.out.println ("Bye bye!");
			System.exit(0);
		}
		else
			return;
	}
	
};