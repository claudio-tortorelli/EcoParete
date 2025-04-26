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
 * this class is a JPanel that hold an image that can be
 * added in a layout
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphicsX extends JComponent
{
	private Image _img;
	private int _x; // location
	private int _y; // location
	private int _dimX; // dimension
	private int _dimY; // dimension
	
	public GraphicsX(Image img, int x, int y, int dimX, int dimY)
	{			
		_img = img;				
		_x = x;
		_y = y;
		_dimX = dimX;
		_dimY = dimY;	
		this.setPreferredSize(new Dimension(_dimX,_dimY));												
	}			
	
	public void paint(Graphics g)
	{		
	//paint background
		super.paint(g); 		
    //Draw image 
    	g.drawImage(_img, _x, _y, _dimX, _dimY, this);     	
	}
};
