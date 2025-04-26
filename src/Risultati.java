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
 * with this class are diplayed all results of elaboration
 * It's also possible to print to a pdf file the txt results.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.print.*;
import java.util.Vector;
import org.jfree.report.*;//visualizzatore di report
import org.jfree.report.modules.misc.tablemodel.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Risultati extends JPanel 
{
	private JLabel _title;
	private JButton _stampa;
	private JButton _reportButton;
	private JTextPane _panel;
	private GridBagConstraints _constraints = new GridBagConstraints();
	private String _spesTot = "";
	private String _masSup = "";
	private String _fatMass = "";
	private String _gradiGiorno = "";
	private String _capTerFront = "";
	private String _trasmitt = "";
	private String _calRelAcc = "";
	private String _inerzTerm = "";
	private String _smorz = "";
	private String _sfas = "";
	private String _potFonoiso250 = "";
	private String _potFonoiso500 = "";
	
	private String _descrizParete = "";	
	private Vector _lineDescriz;
	private Vector _descrizStrati;
	private String _nomeProgetto = "";
	
	private RisultatiParziali _risParziali;
	private FinalReport _report;

/**
 * Constructor 
 */ 
	public Risultati() 
	{		
		_risParziali = null;
		_report = new FinalReport(this);
		
		setLayout(new GridBagLayout());	
		SkeletonGraphic skgraph = new SkeletonGraphic(_constraints);				
	// title
		initTitle();	
		//skgraph.occupation(1,1);	
		skgraph.spacing(0,0);	
		skgraph.grid(0,0);			
		add(_title, _constraints);
	// text pane
		initPanel();
		skgraph.spacing(10,0);
		skgraph.grid(0,1);	
		add(_panel, _constraints);
	// print button		
		initStampa();
		skgraph.grid(0,2);	
		add(_stampa, _constraints);
		revalidate();
	// report button
		initReport();
		skgraph.grid(0,3);	
		add(_reportButton, _constraints);
		revalidate();
	}
	

	private void initTitle() 
	{
		this._title = new JLabel();
		_title.setName("_title");
		_title.setToolTipText(null);
		_title.setText("RISULTATI DELL'ELABORAZIONE:");
		_title.setFont(new Font("verdana", Font.BOLD, 15));
		_title.setForeground(new Color(0, 0, 0, 255));
		_title.setEnabled(true);
		_title.setVerticalAlignment(JLabel.CENTER);
		_title.setHorizontalAlignment(JLabel.CENTER);
		_title.setVerticalTextPosition(JLabel.CENTER);
		_title.setHorizontalTextPosition(JLabel.TRAILING);
		_title.setRequestFocusEnabled(true);
		_title.setMinimumSize(null);
		_title.setMaximumSize(new Dimension(2147483647, 2147483647));
		_title.setPreferredSize(new Dimension(305, 29));
		_title.setForeground(new Color (0,0,108,255));		
	}	
	
	private void initPanel() 
	{
		this._panel = new JTextPane();
		_panel.setName("panel");
		_panel.setToolTipText("I risultati si possono copiare con il solito 'copia e incolla'");
		_panel.setText("    	    ...elaborazione non ancora eseguita...");
		_panel.setFont(new Font("verdana", Font.PLAIN, 11));
		_panel.setForeground(new Color(0, 0, 0, 255));
		_panel.setEnabled(true);
		_panel.setRequestFocusEnabled(true);
		_panel.setMinimumSize(null);
		_panel.setMaximumSize(new Dimension(2147483647, 2147483647));
		_panel.setPreferredSize(new Dimension(400, 180));
		_panel.setEditable(false);
		_panel.setAutoscrolls(true);		
	}
	
	private void initReport() 
	{
		this._reportButton = new JButton();
		_reportButton.setName("report");
       	_reportButton.setToolTipText(null);		
		_reportButton.setText("Report Dettagliato");
		_reportButton.setEnabled(false);
		_reportButton.setSelected(false);
		_reportButton.setFont(new Font("verdana", Font.BOLD, 12));
		_reportButton.setVerticalAlignment(AbstractButton.CENTER);
		_reportButton.setHorizontalAlignment(AbstractButton.CENTER);
		_reportButton.setVerticalTextPosition(AbstractButton.CENTER);
		_reportButton.setHorizontalTextPosition(AbstractButton.TRAILING);
		_reportButton.setIconTextGap(4);
		_reportButton.setRequestFocusEnabled(true);
		_reportButton.setBackground(new Color(18, 175, 184, 255));
		_reportButton.setMinimumSize(null);
		_reportButton.setMaximumSize(new Dimension(2147483647, 2147483647));
		_reportButton.setPreferredSize(new Dimension(200,30));
		_reportButton.setDefaultCapable(true);
		
	// perfom the action
		_reportButton.addActionListener
		(	new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// richiama jfreereport
					_report.Show();						
				}
			}
		);
	}
			
	
	private void initStampa() 
	{
		this._stampa = new JButton();
		_stampa.setName("stampa");
       	_stampa.setToolTipText(null);
		//_stampa.setText("Stampa i risultati");
		_stampa.setText("Stampa i risultati");
		_stampa.setEnabled(false);
		_stampa.setSelected(false);
		_stampa.setFont(new Font("verdana", Font.BOLD, 12));
		_stampa.setVerticalAlignment(AbstractButton.CENTER);
		_stampa.setHorizontalAlignment(AbstractButton.CENTER);
		_stampa.setVerticalTextPosition(AbstractButton.CENTER);
		_stampa.setHorizontalTextPosition(AbstractButton.TRAILING);
		_stampa.setIconTextGap(4);
		_stampa.setRequestFocusEnabled(true);
		_stampa.setBackground(new Color(18, 175, 184, 255));
		_stampa.setMinimumSize(null);
		_stampa.setMaximumSize(new Dimension(2147483647, 2147483647));
		_stampa.setPreferredSize(new Dimension(200,30));
		_stampa.setDefaultCapable(true);
		
	// perfom the action
		_stampa.addActionListener
		(	new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// manda a stampante					
					try
					{
						PrinterJob pjob = PrinterJob.getPrinterJob();
						pjob.setJobName("EcoParete_Risultati");
						pjob.setCopies(1);
						pjob.setPrintable // set the printing work
						(
							new Printable()
							{
							// what this program print
								public int print(Graphics pg, PageFormat pf, int pagenum)
								{		
								// get the date
									DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.ITALY);
									String date = df.format(new Date());		
									
									if (pagenum>0) return Printable.NO_SUCH_PAGE;
								// type of font used
									Font fontTitle = new Font("Times New Roman", Font.BOLD, 14);
									Font fontBody = new Font("Times New Roman", Font.PLAIN, 12);
									Font fontIta = new Font("Times New Roman", Font.ITALIC, 12);
								// string of program presentation
									pg.setFont(fontIta);
									pg.drawString("Claudiosoft EcoParete 1.0",395,100);
								// title of page
									pg.setFont(fontTitle);								
									pg.drawString("Risultati dell'elaborazione " + date,100,120);  
									pg.drawString("________________________________________________",100,130);  
								// description of wall
									pg.setFont(fontIta);
									pg.drawString("* Descrizione della parete",100,170);									
									pg.setFont(fontBody);									
								// put the description in some lines									
									_lineDescriz = putInLine(_descrizParete);
									int i;
									for (i = 0; i < _lineDescriz.size(); i++)
									{
										pg.drawString((String)_lineDescriz.elementAt(i),100,200+(14*i));
									}
									int bottom = 200+(14*i)+30;														
								// description of stratus' wall
									pg.setFont(fontIta);								
									pg.drawString("* Descrizione strati",100,bottom);																		
									pg.setFont(fontBody);
									int j = 0;									
									for (j = 0; j < _descrizStrati.size(); j++)
									{
										pg.drawString("Strato n. "+ (j+1) + ": " + (String)_descrizStrati.elementAt(j), 100,bottom+30+(j*14));
									}
									bottom = bottom+(14*j)+60;														
								// results of elaborazion
									pg.setFont(fontIta);
									pg.drawString("* Risultati",100,bottom);																		
									pg.setFont(fontBody);
								 	bottom = bottom + 20;
									pg.drawString("     - Spessore totale: "+ getSpesTot()+" m", 100,bottom+14);
									pg.drawString("     - Massa superficiale: "+ getMasSup()+" Kg/m^2", 100,bottom+(2*14));
									pg.drawString("     - Fattore di massa: "+ getFatMass() + "  Gradi Giorno: "+getGradiGiorno(), 100,bottom+(3*14));									
									pg.drawString("     - Capacità termica frontale: "+ getCapTerFront() +" KJ/m^2 K", 100,bottom+(4*14));
									pg.drawString("     - Trasmittanza: "+ getTrasmitt() +" W/m^2 K", 100,bottom+(5*14));
									pg.drawString("     - Calore relativo di accumulo: "+ getCalRelAcc() +" KJ/m^2", 100,bottom+(6*14));
									pg.drawString("     - Indice di inerzia termica: "+ getInerzTerm(), 100,bottom+(7*14));
									pg.drawString("     - Smorzamento: "+ getSmorz(), 100,bottom+(8*14));
									pg.drawString("     - Sfasamento: "+ getSfas()+" h", 100,bottom+(9*14));
									pg.drawString("     - Indice potere fonoisolante (250 Hz): "+ getPotFonoiso250() +" dB", 100,bottom+(10*14));
									pg.drawString("     - Indice potere fonoisolante (500 Hz): "+ getPotFonoiso500() +" dB", 100,bottom+(11*14));
									pg.setFont(fontTitle);
									pg.drawString("________________________________________________",100,bottom+(14*14));  
									return Printable.PAGE_EXISTS;									
								}
							}
						);
						if (pjob.printDialog() == false)
						{
							return; // user has cancelled
						}
						pjob.print();
					}
					catch (PrinterException ex)
					{
						JOptionPane.showMessageDialog(new JPanel(), "Errore di stampa: " + ex, "Errore di stampa", JOptionPane.ERROR_MESSAGE);							
					}
				}
			}
		);
	}

/**
 * reset text and buttons's state
 */
 public void Reset()
 {	
	_panel.setText(""); 		
	_stampa.setEnabled(false);
	_reportButton.setEnabled(false); 
 }
	
/**
 * put DescrizParete into several lines
 */
 private Vector putInLine(String descr)
 {
 	Vector line = new Vector();
 	int i = 0;
 	int j = 0;
 	int last = 0;
 	while (i < descr.length())
 	{
		if (j > 45 && descr.charAt(i) == ' ')
		{
			line.addElement(descr.substring(last,last+j));
			last = i+1;
			j = -1;			
		}		
 		j++;
 		i++; 		
 	} 	
 	line.addElement(descr.substring(last,last+j));
 	return line;
 }
 
/**
 *	set the name of project
 */
 	public void setNomeProgetto(String nome)
 	{
 		_nomeProgetto = nome;
 	}	

/**
 *	get the name of project
 */
 	public String getNomeProgetto()
 	{
 		return _nomeProgetto;
 	}	
 		
/**
 *	set the result in the panel
 */
 	public void setResults(String str)
 	{
 		_panel.setText(str); 		
 		_stampa.setEnabled(true);
 		_reportButton.setEnabled(true); 		
 	}	
 	
/**
 *	set the result for the printer
 */
 	public void setSpesTot(String str)
 	{
 		_spesTot = str;
 	}
 
/**
 *	set the result for the printer
 */
 	public void setMasSup(String str)
 	{
 		_masSup = str;
 	}

/**
 *	set the result for the printer
 */
 	public void setFatMass(String str)
 	{
 		_fatMass = str;
 	}
 	

/**
 *	set the result for the printer
 */
 	public void setGradiGiorno(String str)
 	{
 		_gradiGiorno = str;
 	}

/**
 *	set the result for the printer
 */
 	public void setCapTerFront(String str)
 	{
 		_capTerFront = str;
 	}

/**
 *	set the result for the printer
 */
 	public void setTrasmitt(String str)
 	{
 		_trasmitt = str;
 	}
 	
/**
 *	set the result for the printer
 */
 	public void setCalRelAcc(String str)
 	{
 		_calRelAcc = str;
 	}

/**
 *	set the result for the printer
 */
 	public void setInerzTerm(String str)
 	{
 		_inerzTerm = str;
 	}

/**
 *	set the result for the printer
 */
 	public void setSmorz(String str)
 	{
 		_smorz = str;
 	}
	
/**
 *	set the result for the printer
 */
 	public void setSfas(String str)
 	{
 		_sfas = str;
 	}
 	
/**
 *	set the result for the printer
 */
 	public void setPotFonoiso250(String str)
 	{
 		_potFonoiso250 = str;
 	}
 	
/**
 *	set the result for the printer
 */
 	public void setPotFonoiso500(String str)
 	{
 		_potFonoiso500 = str;
 	}
 	
/**
 *	set the result for the printer
 */
 	public void setDescrizParete(String str)
 	{
 		_descrizParete = str;
 	}	
 	
/**
 *	set the result for the printer
 */
 	public void setDescrizStrati(Vector v)
 	{
 		_descrizStrati = new Vector();
 		for (int i = 0; i < v.size(); i++)
 		{
 			_descrizStrati.addElement((String)v.elementAt(i));
 		}
 	}	
 	
/**
 *	get the result for the printer
 */
 	public String getSpesTot()
 	{
 		return _spesTot;
 	}
 
/**
 *	get the result for the printer
 */
 	public String getMasSup()
 	{
 		return _masSup;
 	}

/**
 *	get the result for the printer
 */
 	public String getFatMass()
 	{
 		return _fatMass;
 	}


/**
 *	get the result for the printer
 */
 	public String getGradiGiorno()
 	{
 		return _gradiGiorno;
 	}

/**
 *	get the result for the printer
 */
 	public String getCapTerFront()
 	{
 		return _capTerFront;
 	}

/**
 *	get the result for the printer
 */
 	public String getTrasmitt()
 	{
 		return _trasmitt;
 	}
 	
/**
 *	get the result for the printer
 */
 	public String getCalRelAcc()
 	{
 		return _calRelAcc;
 	}

/**
 *	get the result for the printer
 */
 	public String getInerzTerm()
 	{
 		return _inerzTerm;
 	}

/**
 *	get the result for the printer
 */
 	public String getSmorz()
 	{
 		return _smorz;
 	}
	
/**
 *	get the result for the printer
 */
 	public String getSfas()
 	{
 		return _sfas;
 	}
 	
/**
 *	get the result for the printer
 */
 	public String getPotFonoiso250()
 	{
 		return _potFonoiso250;
 	}
 	
/**
 *	get the result for the printer
 */
 	public String getPotFonoiso500()
 	{
 		return _potFonoiso500;
 	}
 	
/**
 *	set risultati parziali filled by elaborazione
 */
	public void setRisultatiParziali(RisultatiParziali risParz) 
	{
		_risParziali = risParz;
	}
	
/**
 *	get risultati parziali filled by elaborazione
 */
	public RisultatiParziali getRisultatiParziali() 
	{
		return _risParziali;
	}
}
