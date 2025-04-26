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
 * in this class are doing all calculations of elaboration
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.lang.Math;
import java.math.BigDecimal;


public class Elaborazione implements ActionListener
{
/**
 * fields
 */
	private Data 				_data;	
	private Risultati 			_ris;
	private double[] 			s; // spessore
 	private double[] 			mv; // massa volumica
 	private double[] 			ct; // conducibilità termica
 	private double[] 			c; // calore specifico 	
 	private double 				cli = 0; //coeff. liminare interno
 	private double 				cle = 0; // coeff. liminare esterno
 	private double 				ta = 0; // temeperatura interna
 	private	double 				te = 0; // temperatura esterna
	private RisultatiParziali 	_risParz; //risultati parziali dell'elaboraz 		
	private int					numStrat = 0;
 	 		
/**
 * Constructor
 */
	public Elaborazione(Data data, Risultati ris)
	{	
	// get a local reference of data object
		_data = data;
		_ris = ris;
		_risParz = null;
	}	
	
	public void actionPerformed(ActionEvent e)
	{			
	// get actual data from forms and update data object
		_data.getInputGenerali();
		_data.getInputStrati();	
	// check if all data needed are present				
		if ((_data.getDescrizioneParete()).equals("")) JOptionPane.showMessageDialog(new JPanel(), "Attenzione! Input 1.1 'Descrizione parete' mancante!");
		else if ((_data.getTempInterna()).equals("")) JOptionPane.showMessageDialog(new JPanel(), "Attenzione! Input 1.2 'Temperatura interna' mancante!");
		else if ((_data.getTempEsterna()).equals("")) JOptionPane.showMessageDialog(new JPanel(), "Attenzione! Input 1.3 'Temperatura esterna' mancante!");
		else if ((_data.getCoeffLiminareInterno()).equals("")) JOptionPane.showMessageDialog(new JPanel(), "Attenzione! Input 1.4 'Coeff. liminare interno' mancante!");
		else if ((_data.getCoeffLiminareEsterno()).equals("")) JOptionPane.showMessageDialog(new JPanel(), "Attenzione! Input 1.5 'Coeff. liminare esterno' mancante!");
		else if (checkTable(_data.getTabStrati()))
		{
			try
			{					
			 // initializes the common variables
			 	cli = Double.parseDouble(_data.getCoeffLiminareInterno());
 				cle = Double.parseDouble(_data.getCoeffLiminareEsterno());
 				ta = Double.parseDouble(_data.getTempInterna());
 				te = Double.parseDouble(_data.getTempEsterna());
 			
 			// it determines how many stratus there are.	
 				String[] col = new String[10];
				col = _data.getColumn(1);							
			 	while (numStrat < 10 && !col[numStrat].equals(""))
			 	{
			 		numStrat++;	 		
			 	}
 		
			 // it initializes the arrays of double doing a parsing from arrays of string
			 	s = new double[numStrat];
			 	mv = new double[numStrat];
			 	ct = new double[numStrat];
			 	c = new double[numStrat];				
				for (int j = 1; j < 5; j++)
				{				
					col = _data.getColumn(j);	
					for (int i = 0; i < numStrat; i++)
					{
						if (j == 1) s[i] = Double.parseDouble(col[i]);
						if (j == 2) mv[i] = Double.parseDouble(col[i]);
						if (j == 3) ct[i] = Double.parseDouble(col[i]);
						if (j == 4) c[i] = Double.parseDouble(col[i]);
					}
				}
				
			// create the object to retain all partial results
				_risParz = new RisultatiParziali(numStrat);
			
			// all data checked: start elaboration
				_ris.setDescrizParete(_data.getDescrizioneParete());// set the description of wall as result
				double tmp = 0; // variable that store the temporary result
				tmp = this.spessoreTotale();
				_ris.setSpesTot(String.valueOf(tmp));
				String strResult = "1) "+"Spessore totale: "+String.valueOf(tmp)+" (m)\n";
				
				tmp = this.massaSuperficiale();
				_ris.setMasSup(String.valueOf(tmp));				
				strResult = strResult + "2) "+"Massa superficiale: "+String.valueOf(tmp)+" (Kg/m^2)\n";				
				
				String fatt = JOptionPane.showInputDialog(new JPanel(), "La massa superficiale attualmente calcolata è \n"+tmp+"\nInserire ora il corrispondente Fattore di Massa","Fattore di Massa 1/2",JOptionPane.INFORMATION_MESSAGE);				
				if (fatt == null || fatt.equals("")) 
					fatt = "-";				
				_ris.setFatMass(fatt);			
				String gradiGiorno = "-";
				if (fatt !=	"-")
				{
					gradiGiorno = JOptionPane.showInputDialog(new JPanel(), "Inserire ora i Gradi Giorno","Fattore di Massa 2/2",JOptionPane.INFORMATION_MESSAGE);
					if (gradiGiorno == null || gradiGiorno.equals("")) 
						gradiGiorno = "-";				
				}
				_ris.setGradiGiorno(gradiGiorno);								
				strResult = strResult + "3) "+"Fattore di massa: "+fatt+"  Gradi Giorno: "+gradiGiorno+"\n";				
				
				tmp = this.capacitàTermFront();
				_ris.setCapTerFront(String.valueOf(tmp));				
				strResult = strResult + "4) "+"Capacità termica frontale: "+String.valueOf(tmp)+" (Kj/(m^2)K)\n";				
				
				tmp = this.trasmittanza();											
				_ris.setTrasmitt(String.valueOf(tmp));								
				strResult = strResult + "5) "+"Trasmittanza: "+String.valueOf(tmp)+" (W/(m^2)K)\n";
				
				tmp = this.caloreRelAccumulo();								
				_ris.setCalRelAcc(String.valueOf(tmp));				
				strResult = strResult + "6) "+"Calore relativo di accumulo: "+String.valueOf(tmp)+" (KJ/m^2)\n";
				
				tmp = this.inerziaTermica();				
				_ris.setInerzTerm(String.valueOf(tmp));															
				strResult = strResult + "7) "+"Indice di inerzia termica: "+String.valueOf(tmp)+"\n";
				
				tmp = this.smorzamento();
				_ris.setSmorz(String.valueOf(tmp));				
				strResult = strResult + "8) "+"Smorzamento: "+String.valueOf(tmp)+"\n";
				
				tmp = this.sfasamento();
				_ris.setSfas(String.valueOf(tmp));				
				strResult = strResult + "9) "+"Sfasamento: "+String.valueOf(tmp)+" (h)\n";
				
				tmp = this.potereFonoiso250();
				_ris.setPotFonoiso250(String.valueOf(tmp));				
				strResult = strResult + "10) "+"Potere fonoisolante (250Hz): "+String.valueOf(tmp)+" (dB)\n";
				
				tmp = this.potereFonoiso500();
				_ris.setPotFonoiso500(String.valueOf(tmp));				
				strResult = strResult + "11) "+"Potere fonoisolante (500Hz): "+String.valueOf(tmp)+" (dB)";				
				
				_ris.setResults(strResult);	
				Vector vTemp = new Vector();
				for (int i = 0; i < numStrat; i++)
				{
					vTemp.addElement(_data.getCellaTab(i,0));
				}
				_ris.setDescrizStrati(vTemp);
				JOptionPane.showMessageDialog(new JPanel(), "Elaborazione andata a buon fine: risultati disponibili");
				
				// set all partial results of risultati
				_ris.setRisultatiParziali(_risParz);
				
				//set the name of project
				_ris.setNomeProgetto(_data.getNomeProgetto());
				
				_data.setDirty(true);
			}		
			catch (NumberFormatException ex)
		 	{
		 		JOptionPane.showMessageDialog(new JPanel(),"Errore di formato!\nControllare il formato dei dati.\nNB. Per i decimali si usa il punto e non la virgola.");	 		
		 	}
		 	catch (Exception ex)
		 	{
		 		JOptionPane.showMessageDialog(new JPanel(),"Si è verificato un errore generico durante l'elaborazione.");	 		
		 		ex.printStackTrace();
		 	}
		 } 						
	}

/**
 * This method calculate the spessore totale
 */
 	private double spessoreTotale()
 	{ 		
 		double res = 0; 		
		for (int i = 0; i < numStrat; i++)
 		{
 			res = res + s[i];	 			
 		}
 		BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		
 	 	return res;
 	}

/**
 * This method calculate the massa superficiale (frontale)
 */
 	private double massaSuperficiale()
 	{
 		double res = 0; 		
 		double massaSupStrato = 0;
		for (int i = 0; i < numStrat; i++)
 		{
 		// sommatoria prodotti di spessore x massa volumica
 			massaSupStrato = s[i] * mv[i];
 			_risParz.SetMassaSupStrato(massaSupStrato, i);
 			res = res + (massaSupStrato);	 			
 		}
 		BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(2,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		 	 	
 		return res;
 	}

/**
 * This method calculate the capacità termica frontale
 */
 	private double capacitàTermFront()
 	{
 		double res = 0; 		
 		for (int i = 0; i < numStrat; i++)
	 	{
	 	// sommatoria prodotti di spessore x massa volumica x calore specifico
	 		res = res + (_risParz.GetMassaSupStrato(i) * c[i]);	 			
	 	}
	 	BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(2,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		 	 	
	 	return res;
 	}

/**
 * This method calculate the trasmittanza
 */
 	private double trasmittanza()
 	{
 		double res = 0; 		
		double sum = 0;
		double resLimStrato = 0;
 		for (int i = 0; i < numStrat; i++)
 		{
 		// sommatoria divisioni di spessore / conducibilità termica
 			resLimStrato = s[i]/ct[i];
 			_risParz.SetResLimStrato(resLimStrato, i);
 			sum = sum + (resLimStrato);	 			
 		}
 		double resLimInt = (1/cli);
 		double resLimEst = (1/cle);
 		
 		_risParz.SetResLimEst(resLimEst);
 		_risParz.SetResLimInt(resLimInt);
 		 		
 		res = 1/(resLimInt + resLimEst + sum);
 		BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(4,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		 	 	
	 	return res;
 	}

/**
 * This method calculate the calore relativo di accumulo
 */
 	private double caloreRelAccumulo()
 	{ 	 	
 		double sum = 0;
 		for (int i = 0; i < numStrat; i++)
 		{
 			sum = sum + _risParz.GetResLimStrato(i);			
 		}
 		double resistenzaLiminareTot = _risParz.GetResLimEst() + _risParz.GetResLimInt() + sum;
 		
 		double[] TempSuperfici = new double[numStrat+1];
 		TempSuperfici[0] = ta - (_risParz.GetResLimInt()/resistenzaLiminareTot)*(ta-te);
 		_risParz.SetTempSuperficeStrato(TempSuperfici[0], 0);
 		
 		for (int i = 1; i < numStrat+1; i++)
 		{
 			TempSuperfici[i] = TempSuperfici[i-1] - (_risParz.GetResLimStrato(i-1)/resistenzaLiminareTot)*(ta-te);
 			_risParz.SetTempSuperficeStrato(TempSuperfici[i], i);
 		}
 		
 		double[] TempMediaStrato = new double[numStrat];
 		for (int i = 0; i < numStrat; i++)
 		{
 			TempMediaStrato[i] = (TempSuperfici[i] + TempSuperfici[i+1])/2;
 			_risParz.SetTempMediaStrato(TempMediaStrato[i], i);
 		}
 		
		double res = 0; 
 		for (int i = 0; i < numStrat; i++)
 		{ 			
 			res = res + (c[i] * _risParz.GetMassaSupStrato(i) * _risParz.GetTempMediaStrato(i)); 			
 		}
 		
 		BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(2,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 
  	 		
	 	return res;
 	}

/**
 * This method calculate the inerzia termica
 */
 	private double inerziaTermica()
 	{ 		
 		double[] A = new double[numStrat]; 		
 		double res = 0; 		
 		for (int i = 0; i < numStrat; i++)
	 	{
	 		// ammittanza strato per strato
	 		A[i] = 0.269 * Math.sqrt(mv[i] * ct[i] * c[i]);	 				
	 		_risParz.SetAmmittanzaStrato(A[i], i);
	 	}
	 	double inerzTermStrato = 0;
	 	for (int i = 0; i < numStrat; i++)
	 	{
	 		inerzTermStrato = A[i] * (s[i]/ct[i]);
	 		_risParz.SetInerziaTermStrato(inerzTermStrato, i);
	 		res = res + (inerzTermStrato);	 		
	 	}	 	
	 	
	 	BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(1,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		 	 	
 		return res;
 	}
 	
/**
 * This method calculate the smorzamento
 */
 	private double smorzamento()
 	{ 		
 		double res = 0; 		
 		double x = (this.inerziaTermica()/Math.sqrt(2));
 		
 		this.ammittanzaEffettivaStrati();
 		
	 // produttoria finale
	 	double prod = 1;
	 	for (int i = 0; i < numStrat; i++)
	 	{
	 		if (i == 0) 
	 			prod = prod * ((_risParz.GetAmmittanzaStrato(i) + cli) 
	 						/ (_risParz.GetAmmittanzaStrato(i) + _risParz.GetAmmittanzaEffettivaStrato(i)));
	 		else 
	 			prod = prod * ((_risParz.GetAmmittanzaStrato(i) + _risParz.GetAmmittanzaEffettivaStrato(i-1)) 
	 						/ (_risParz.GetAmmittanzaStrato(i) + _risParz.GetAmmittanzaEffettivaStrato(i-1)));
	 	}
	 	prod = prod * ((cle + _risParz.GetAmmittanzaEffettivaStrato(numStrat-1))
	 					/ cli);	 	
	 					
	 	res = 0.9 * (Math.exp(x)) * prod;
	 	BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(3,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		
 	 	return res;
 	}

/**
 * This method calculate the ammittanza effettiva for all stratus
 */ 
 	private void ammittanzaEffettivaStrati()
 	{ 		 	
		// ammittanza effettiva 		 
	 	double[] B = new double[numStrat]; 		 		
	 	for (int i = 0; i < numStrat; i++)
	 	{
	 		if (_risParz.GetInerziaTermStrato(i) >= 1) 	 		
	 			B[i] = _risParz.GetAmmittanzaStrato(i);
	 		else
	 		{	 			
	 			if (i == 0) 
	 				B[i] = ((_risParz.GetResLimStrato(i) * Math.pow(_risParz.GetAmmittanzaStrato(i),2)) + cli)/
	 						(1 + (_risParz.GetResLimStrato(i) * cli));
	 			else 
	 				B[i] = ((_risParz.GetResLimStrato(i) * Math.pow(_risParz.GetAmmittanzaStrato(i),2)) + B[i-1])/
	 						(1 + (_risParz.GetResLimStrato(i) * B[i-1]));
	 		}
	 		_risParz.SetAmmittanzaEffettivaStrato(B[i],i);
	 	}
	 }

/**
 * This method calculate the sfasamento
 */
  	private double sfasamento()
 	{ 		
 		double res = 0; 		
 		double Dtot = this.inerziaTermica(); 		
 		
 		 		
 		double atan1 = Math.toDegrees(Math.atan(cli/(cli + (_risParz.GetAmmittanzaEffettivaStrato(0) * Math.sqrt(2)))));
 		double atan2 = Math.toDegrees(Math.atan(_risParz.GetAmmittanzaEffettivaStrato(numStrat-1)
 									/(_risParz.GetAmmittanzaEffettivaStrato(numStrat-1) * cle * Math.sqrt(2))));

 	 	res = ( (40.5 * Dtot) - atan1 + atan2 )/15;
 	 	
 	 	BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(2,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		
 	 	return res;
 	} 

/**
 * This method calculate the potere fonoisolante (250 Hz)
 */
 	private double potereFonoiso250()
 	{ 		
 		double res = 0; 		 		 	 		
 		res = (18 * (Math.log(this.massaSuperficiale() * 250)/Math.log(10))) - 44;
 		BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(2,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		
 		return res;
 	 	
 	}

/**
 * This method calculate the potere fonoisolante (500 Hz)
 */
 	private double potereFonoiso500()
 	{ 		
 		double res = 0; 		 		
	 	res = (18 * (Math.log(this.massaSuperficiale() * 500)/Math.log(10))) - 44;
	 	BigDecimal bd = new BigDecimal(res);
 		bd = bd.setScale(2,BigDecimal.ROUND_HALF_DOWN);
 		res = bd.doubleValue();	 		
 	 	return res;
 	}

/**
 * This method check if a vector of vector has in all
 * column the same number of elements
 */
	private boolean checkTable(String[][] table)
	{
		int code = 0; // if all ok code = 0
		int strEmpty = 0;		
		int strati = 0;
	// check if all stratum have all values 
	// row
		for (int i = 0; i < 10; i++)
		{			
		// col			
			for (int k = 0; k < 5; k++)
			{				
				if (table[i][k].equals("")) strEmpty ++;								
			}					
			if (0 < strEmpty && strEmpty < 5) code = 1;		
			if (strEmpty == 0) strati ++;
			strEmpty = 0;
		}
		if (code == 1) JOptionPane.showMessageDialog(new JPanel(), "Attenzione! Ricontrollare la presenza di tutti gli input degli strati (battere INVIO dopo l'ultimo inserimento).");	
	// check if there aren't any stratum
		int oldCode = code;
		if (strati == 0) code = 2;			
		if (code == 2 && oldCode != 1) JOptionPane.showMessageDialog(new JPanel(), "Ci deve essere almeno uno strato definito nella configurazione!");
	// check if there are empty line
		String old = "", oldold = "";
		for (int i = 0; i < 10; i++)
		{
			if (i == 0) oldold = table[i][0];
			else if (i == 1) old = table[i][0];
			else 
			{	// if there is a empty line
				if ((!oldold.equals("") && !table[i][0].equals("")) && old.equals("")) code = 3;
				oldold = old;
				old = table[i][0];
			}
		}
		if (code == 3) JOptionPane.showMessageDialog(new JPanel(), "Non si possono lasciare strati vuoti tra due strati pieni!");
		if (code == 0) return true;
		else return false;
	}
}