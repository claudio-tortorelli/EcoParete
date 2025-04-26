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
 * This class's objects hold all parameters needed by an elaboration
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;  
import java.util.Properties;
import java.util.Vector;
import com.stevesoft.xmlser.ObjectInputStream; //serializer XML
import com.stevesoft.xmlser.ObjectOutputStream;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Data implements Serializable
{

/**
 * fields
 */
	private String _descrizioneParete;
	private String _tempInterna;
	private String _tempEsterna;
	private String _coeffLiminareInterno;
	private String _coeffLiminareEsterno;		
	private String[][] _tabStrati;
	
	private InputGenerali _igen;
	private InputStrati _istra;
	private Risultati _ris;
	private boolean _IsDirty = false;
	
	
/**
 * constructor
 */ 	
	public Data(InputGenerali ig, InputStrati is, Risultati ris)
	{		
		_igen = ig;
		_istra = is;
		_ris = ris;
		_IsDirty = false;
	}

/**
 * copy constructor
 */
	public Data(Data other) 
	{
		if(this != other) 
		{
			this._descrizioneParete = other._descrizioneParete;
			this._tempInterna = other._tempInterna;
			this._tempEsterna = other._tempEsterna;
			this._coeffLiminareInterno = other._coeffLiminareInterno;
			this._coeffLiminareEsterno = other._coeffLiminareEsterno;
			this._tabStrati = other._tabStrati;
		}
	}
	
/**
 * set methods
 */	
	public void setDescrizioneParete(String descrizioneParete)
	{			
		_descrizioneParete = descrizioneParete;		
	}

	public void setTempInterna(String tempInterna)
	{
		_tempInterna = tempInterna;
	}

	public void setTempEsterna(String tempEsterna)
	{
		_tempEsterna = tempEsterna;
	}
	
	public void setCoeffLiminareInterno(String coeffLiminareInterno)
	{
		_coeffLiminareInterno = coeffLiminareInterno;
	}

	public void setCoeffLiminareEsterno(String coeffLiminareEsterno)
	{
		_coeffLiminareEsterno = coeffLiminareEsterno;
	}

	public void setTabStrati(String[][] tab)
	{
		_tabStrati = tab;
	}
	
	public void setCellaTab(String value, int i, int j)
	{
		try
		{
			_tabStrati[i][j] = value;
		}		
		catch (IndexOutOfBoundsException e)
		{
			System.out.println ("Error: index out of bounds in the table!");
		}
		catch (Exception e)
		{}
	}
	
	public void setDirty(boolean val)
	{
		_IsDirty = val;
	}
	
/**
 * get methods
 */
	public String getDescrizioneParete()
	{
		if (_descrizioneParete == null) _descrizioneParete = "";
		return _descrizioneParete;
	}

	public String getTempInterna()
	{
		if (_tempInterna == null) _tempInterna = "";
		return _tempInterna;
	}

	public String getTempEsterna()
	{
		if (_tempEsterna == null) _tempEsterna = "";
		return _tempEsterna;
	}

	public String getCoeffLiminareInterno()
	{
		if (_coeffLiminareInterno == null) _coeffLiminareInterno = "";
		return _coeffLiminareInterno;
	}

	public String getCoeffLiminareEsterno()
	{
		if (_coeffLiminareEsterno == null) _coeffLiminareEsterno = "";
		return _coeffLiminareEsterno;
	}

	public String[][] getTabStrati()
	{
		return _tabStrati;	
	}
	
	public String getCellaTab(int i, int j)
	{
		String res = "";
		try
		{
			res = _tabStrati[i][j];
		}		
		catch (IndexOutOfBoundsException e)
		{
			System.out.println ("Error: index out of bounds in the table!");
		}
		catch (Exception e)
		{}
		return res;
	}
	
	public boolean isDirty()
	{
		return _IsDirty;
	}
	
	public String getNomeProgetto()
	{
		return _igen.getNomeProgetto();
	}
/**
 * get one column from table
 */
 	public String[] getColumn(int j)
 	{
 		String[] tmp = new String[10];
 	// is in the range?
 		if (0 < j && j < 5)
 		{ 			
 			for (int i = 0; i < 10; i++)
 			{
 				tmp[i] = this.getCellaTab(i,j);
 			} 			
 		}
 		return tmp;
 	}
	
/**
 * get all InputGenerali
 */
 	public void getInputGenerali()
 	{ 		
 		Vector val = _igen.copyOut();
 		try
 		{
 			this.setDescrizioneParete((String)val.elementAt(0));
 			this.setTempInterna((String)val.elementAt(1));
 			this.setTempEsterna((String)val.elementAt(2));
 			this.setCoeffLiminareInterno((String)val.elementAt(3));
 			this.setCoeffLiminareEsterno((String)val.elementAt(4)); 			
 		}
 		catch (Exception e)
 		{}
 	}	

/**
 * get InputStrati table
 */
 	public void getInputStrati()
 	{ 	
 		try
 		{ 		
 			this.setTabStrati(_istra.copyOut());
 		}
 		catch (Exception e)
 		{}
	}
	
/**
 * set InputGenerali values
 */
 	public void setInputGenerali(Vector val)
 	{ 	 		
 		_igen.copyIn(val);
 	}

/**
 * set InputStrati values
 */
 	public void setInputStrati(String[][] tab)
 	{ 		
 		_istra.copyIn(tab);
 	}
 	
/**
 * load a data object from file
 */
 	public void loadData(String name)
 	{
 		try 
 	 	{ 	 		
 	 		File tmp = new File (name);	
 	 		if (!tmp.exists())
			{
				JOptionPane.showMessageDialog(new JPanel(), "Non trovo il file "+name);
			}
			else
			{	
				if (!tmp.canRead())
				{
					JOptionPane.showConfirmDialog(new JPanel(), "Non riesco a leggere i dati dal file! Ottenere eventuali privilegi di lettura");
				}					
				else
				{			
					FileInputStream fis = new FileInputStream(tmp);
	 				ObjectInputStream ois = new ObjectInputStream(fis); 					
	 				Vector vect = (Vector) ois.readObject();
	 				this.setInputGenerali(vect);
	 				String[][] tab = new String[10][5];
	 				int k = 6;
	 				for (int i = 0; i < 10; i++)
					{
						for (int j = 0; j < 5; j++)
						{
							tab[i][j] = (String)vect.elementAt(k);
							k ++;
						}
					}	 				
	 				this.setInputStrati(tab);		 								 					 				 					 				
	 				fis.close();
	 				ois.close();	 				
	 			}
	 		}
 	 	}
		catch (FileNotFoundException e)
		{}
		catch (IOException e)
		{}
		catch (Exception e)
		{}					
 	}
 	
/**
 * save a data object to file xml
 */
	public void saveData(String name)
	{ 	
		File tmp = new File (Studio.dirConf+name);
		try 
		{	
			if (tmp.exists())
			{
				int ris = JOptionPane.showConfirmDialog(new JPanel(), "Attenzione! Questo file è già esistente. Procedo?");
				if (ris == JOptionPane.CANCEL_OPTION || ris == JOptionPane.NO_OPTION) return;
			}
			tmp.createNewFile();
			if (!tmp.canWrite())
			{
				JOptionPane.showConfirmDialog(new JPanel(), "Non riesco a salvare il file su disco!");
			}					
			else
			{			
				FileOutputStream fos = new FileOutputStream(tmp);
 				ObjectOutputStream oos = new ObjectOutputStream(fos); 					
 				Vector vect = new Vector();
 				vect.addElement(_igen.getNomeProgetto());	 				
 				vect.addElement(_descrizioneParete);	 				
 				vect.addElement(_tempInterna);
 				vect.addElement(_tempEsterna);
 				vect.addElement(_coeffLiminareInterno);
 				vect.addElement(_coeffLiminareEsterno);
				for (int i = 0; i < 10; i++)
				{
					for (int j = 0; j < 5; j++)
					{
						vect.addElement(_tabStrati[i][j]);
					}
				}	 				
 				oos.writeObject(vect);
 				fos.close();
 				oos.close();
 			}
	 	} 		
		catch (FileNotFoundException e)
		{}
		catch (IOException e)
		{}
		catch (Exception e)
		{}			
	}
	
/**
 * this method delete the data on the forms
 */
 	public void deleteForms()
 	{
 		Vector vect = new Vector();
 		for (int i = 0; i < 6; i++)	
 			vect.addElement("");
 		this.setInputGenerali(vect);
		String[][] tab = new String[10][5];
		int k = 5;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				tab[i][j] = "";
				k ++;
			}
		}	 				
		this.setInputStrati(tab);

		_ris.Reset();				
 	}
	
	
/**
 * toString method
 */
	public String toString() 
	{
		String sep = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		buffer.append(sep);
		buffer.append("_descrizioneParete = ");
		buffer.append(_descrizioneParete);
		buffer.append(sep);
		buffer.append("_tempInterna = ");
		buffer.append(_tempInterna);
		buffer.append(sep);
		buffer.append("_tempEsterna = ");
		buffer.append(_tempEsterna);
		buffer.append(sep);
		buffer.append("_coeffLiminareInterno = ");
		buffer.append(_coeffLiminareInterno);
		buffer.append(sep);
		buffer.append("_coeffLiminareEsterno = ");
		buffer.append(_coeffLiminareEsterno);
		buffer.append(sep);	
		buffer.append("_tabStrati = ");
		buffer.append(_tabStrati);
		buffer.append(sep);
		
		return buffer.toString();
	}
};
