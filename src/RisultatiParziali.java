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
 * this class contains all partial results of elaborations 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.print.*;
import java.util.Vector;
import java.math.BigDecimal;

public class RisultatiParziali 
{
	private double[] 	_massaSupStrato;
	private double[] 	_resistenzaLiminareStrato;
	private double 		_resistenzaLiminareInt;
	private double 		_resistenzaLiminareEst;
	private double[]	_tempSuperficiStrati;
	private double[]	_tempMediaStrati;
	private double[]	_ammittanzaStrati;
	private double[]	_inerziaTermicaStrato;
	private double[] 	_ammittanzaEffettivaStrati; 		 		
	private int			_numStrati;
	
/**
 * Constructor 
 */ 
	public RisultatiParziali(int numStrati) 
	{	
		_numStrati = numStrati;
		_massaSupStrato = new double[numStrati];
		_resistenzaLiminareStrato = new double[numStrati];
		_resistenzaLiminareInt = 0;
		_resistenzaLiminareEst = 0;
		_tempSuperficiStrati = new double[numStrati+1];
		_tempMediaStrati = new double[numStrati];
		_ammittanzaStrati = new double[numStrati];
		_inerziaTermicaStrato = new double[numStrati];
		_ammittanzaEffettivaStrati = new double[numStrati];
	}	
/*
 * Operations
 */	
 	public void SetMassaSupStrato(double val, int strato)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 		
 		_massaSupStrato[strato] = val;
 	}
	
	public double GetMassaSupStrato(int strato)
 	{ 		
 		return _massaSupStrato[strato];
 	}
 	
 	public void SetResLimStrato(double val, int strato)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_resistenzaLiminareStrato[strato] = val;
 	}
	
	public double GetResLimStrato(int strato)
 	{
 		return _resistenzaLiminareStrato[strato];
 	}
 	
 	public void SetResLimEst(double val)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_resistenzaLiminareEst = val;
 	}
	
	public double GetResLimEst()
 	{
 		return _resistenzaLiminareEst;
 	}
 	
 	public void SetResLimInt(double val)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_resistenzaLiminareInt = val;
 	}
	
	public double GetResLimInt()
 	{
 		return _resistenzaLiminareInt;
 	}
 	
 	public void SetTempSuperficeStrato(double val, int superfice)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_tempSuperficiStrati[superfice] = val;
 	}
	
	public double GetTempSuperficeStrato(int superfice)
 	{ 		
 		return _tempSuperficiStrati[superfice];
 	}
 	
 	public void SetTempMediaStrato(double val, int strato)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_tempMediaStrati[strato] = val;
 	}
	
	public double GetTempMediaStrato(int strato)
 	{ 			
 		return _tempMediaStrati[strato];
 	}
 	
 	public void SetAmmittanzaStrato(double val, int strato)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_ammittanzaStrati[strato] = val;
 	}
	
	public double GetAmmittanzaStrato(int strato)
 	{ 		
 		return _ammittanzaStrati[strato];
 	}
 	
 	public void SetInerziaTermStrato(double val, int strato)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_inerziaTermicaStrato[strato] = val;
 	}
	
	public double GetInerziaTermStrato(int strato)
 	{ 	
 		return _inerziaTermicaStrato[strato];
 	}
 	
 	public void SetAmmittanzaEffettivaStrato(double val, int strato)
 	{
 		BigDecimal bd = new BigDecimal(val);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		val = bd.doubleValue();	 	
 		_ammittanzaEffettivaStrati[strato] = val;
 	}
	
	public double GetAmmittanzaEffettivaStrato(int strato)
 	{ 		
 		return _ammittanzaEffettivaStrati[strato];
 	}
 	
 	public int GetNumStrati()
 	{ 	 	
 		return _numStrati;
 	}
}