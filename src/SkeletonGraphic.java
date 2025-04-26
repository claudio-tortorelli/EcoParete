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
 * this class furnishes some method to define layout's properties
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Questa classe serve da Scheletro per realizzare le interfacce 
 * Swing con GridBagLayout. E' provvista di metodi per 
 * la manipolazione delle componenti nel layout.
 */
public class SkeletonGraphic extends JPanel 
{
/**
 * Fields: componenti della GUI
 */	
	private GridBagConstraints _constraints;
	
/**
 * Constructor
 */
	public SkeletonGraphic(GridBagConstraints con) 
	{
		_constraints = con;				
	}
  
// ----- OPERAZIONI DI LAYOUT

/**
 * Questo metodo indica quanto gli oggetti che vengono
 * inseriti di seguito devono avere come padding orizzontale
 * o verticale. Il padding è lo spazio tra il bordo e il
 * il centro del componente.
 *
 * @param x = padding orizzontale
 * @param y = padding verticale
 */
	public void padding (int x, int y)
	{
		_constraints.ipadx = x;
		_constraints.ipady = y;				
	}

/**
 * Questo metodo indica l'altezza e la larghezza degli
 * oggetti che seguono in termini di colonne e righe.
 *
 * @param x = altezza
 * @param y = larghezza
 */
 	public void spanning (int x, int y)
	{
		_constraints.gridheight = x;
		_constraints.gridwidth = y;		
	}

/**
 * In questo metodo si imposta quanto l'oggetto occuperà
 * lo spazio della la cella a lui riservata nel senso della
 * riga e della colonna.
 *
 * @param x = da 0 a 1 il rapporto con lo spazio disponibile orizzontalmente
 * @param y = da 0 a 1 il rapporto con lo spazio disponibile verticalmente
 */
 	public void occupation (double x, double y)
	{
		_constraints.weightx = x;
		_constraints.weighty  = y;			
	}

/**
 * Questo metodo imposta lo spazio tra gli oggetti.
 *
 * @param x = pixel lasciati superiormente
 * @param y = pixel lasciati a sinistra
 */
	public void spacing (int up, int left)
	{
		_constraints.insets.top = up;
		_constraints.insets.left = left;
	}	
	
/**
 * Questo metodo consente di scegliere se un oggetto
 * deve riempire tutto lo spazio a sua disposizione 
 * in qualche direzione.
 *
 * @param c = 'h' -> il componente riempie orizzontalmente
 *		  c = 'v' -> il componente riempie verticalmente
 *		  c = 'b' -> il componente riempie in entrambe le direzioni	
 *		  c = 'n' -> il componente torna ad avere un'occupazione normale	
 */
	public void fill (char c)
	{		
		if (c == 'h' || c == 'H')
		{
			_constraints.fill = GridBagConstraints.HORIZONTAL;
		}
		else if (c == 'v' || c == 'V')
		{
			_constraints.fill = GridBagConstraints.VERTICAL;
		}
		else if (c == 'b' || c == 'B')
		{
			_constraints.fill = GridBagConstraints.BOTH;
		}
		else if (c == 'n' || c == 'N')
		{
			_constraints.fill = GridBagConstraints.NONE;
		}
	}

/**
 * Questo metodo aggiunge un componente al layout, 
 * specificando la sua posizione tramite le coordinate
 * della griglia.
 * 
 * @param x = numero della colonna
 * @param y = numero della riga
 */
	public void grid(int x, int y)
	{
		_constraints.gridx = x;
		_constraints.gridy = y;			
	}

}
