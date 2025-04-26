package claudiosoft.studioBerti;
/**
 * ========================================
 * JFreeReport : a free Java report library
 * ========================================
 *
 * Project Info:  http://www.jfree.org/jfreereport/index.html
 * Project Lead:  Thomas Morgner;
 *
 * (C) Copyright 2000-2003, by Simba Management Limited and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * ---------------
 * HelloWorld.java
 * ---------------
 * (C)opyright 2002, 2003, by Simba Management Limited.
 *
 * Original Author:  David Gilbert (for Simba Management Limited);
 * Contributor(s):   -;
 *
 * $Id: HelloWorld.java,v 1.6 2003/11/07 18:33:48 taqua Exp $
 *
 * Changes
 * -------
 * 10-Dec-2002 : Version 1 (DG);
 *
 */
 
   /**
   * Creates and displays a simple report.
   */

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.jfree.report.Boot;
import org.jfree.report.ElementAlignment;
import org.jfree.report.JFreeReport;
import org.jfree.report.ReportProcessingException;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.modules.gui.base.PreviewDialog;
import org.jfree.ui.FloatDimension;


public class FinalReport
{
	//MEMBERS
	private Risultati _ris;
  
/**
 * Constructor 
 */ 

  public FinalReport(Risultati ris)
  {
	_ris = ris;    
  }

	public void Show()
	{	
		final TableModel data = createData();
	    final JFreeReport report = createReportDefinition();
	    report.setData(data);      
	    
	    try
	    {
	      final PreviewDialog preview = new PreviewDialog(report);	      
	      preview.pack();
	      preview.setVisible(true);
	    }
	    catch (ReportProcessingException e)        
	    {
	      System.out.println("Failed to generate report ");
	    }    
	}

  /**
   * Creates a small dataset to use in a report.  JFreeReport always reads data from a
   * <code>TableModel</code> instance.
   *
   * @return a dataset.
   */
  private TableModel createData()
  {
    final Object[] columnNames = new String[]
    	{"Col1", "Col2", "Col3",
    	 "Col4", "Col5", "Col6",
    	 "Col7", "Col8", "Col9"};
 	 	
    final DefaultTableModel result = new DefaultTableModel(columnNames, 40);
    result.setValueAt("Report Dettagliato\n"+_ris.getNomeProgetto(), 0, 0);
    result.setValueAt(this.giveTheDate(), 0, 1);    
    result.setValueAt("Risultati Finali", 2, 0);
	    result.setValueAt("Spessore Totale", 2, 1);
	    	result.setValueAt(_ris.getSpesTot(), 2, 2);
	    	result.setValueAt("m", 2, 3);
	    result.setValueAt("Massa Superficiale", 3, 1);
	    	result.setValueAt(_ris.getMasSup(), 3, 2);
	    	result.setValueAt("Kg/m^2", 3, 3);
	    result.setValueAt("Capacità Termica Frontale", 4, 1);
	    	result.setValueAt(_ris.getCapTerFront(), 4, 2);
	    	result.setValueAt("KJ/m^2 K", 4, 3);
	    result.setValueAt("Trasmittanza", 5, 1);
	    	result.setValueAt(_ris.getTrasmitt(), 5, 2);
	    	result.setValueAt("W/m^2 K", 5, 3);
	    result.setValueAt("Calore rel. Accumulo", 6, 1);
	    	result.setValueAt(_ris.getCalRelAcc(), 6, 2);
	    	result.setValueAt("KJ/m^2", 4, 3);
	    result.setValueAt("Fattore di Massa", 7, 1);
	    	result.setValueAt(_ris.getFatMass(), 7, 2);
	    result.setValueAt("Gradi giorno ", 8, 1);	    	
	    	result.setValueAt(_ris.getGradiGiorno(), 8, 2);
	    	result.setValueAt("K", 8, 3);
	    result.setValueAt("Indice di Inerzia Termica", 9, 1);
	    	result.setValueAt(_ris.getInerzTerm(), 9, 2);	    	
	    result.setValueAt("Smorzamento", 10, 1);
	    	result.setValueAt(_ris.getSmorz(), 10, 2);
	    result.setValueAt("Sfasamento", 11, 1);
	    	result.setValueAt(_ris.getSfas(), 11, 2);
	    	result.setValueAt("h", 11, 3);
	    result.setValueAt("Potere Fonoisolante (250 Hz)", 12, 1);
	    	result.setValueAt(_ris.getPotFonoiso250(), 12, 2);
	    	result.setValueAt("dB", 12, 3);
	    result.setValueAt("Potere Fonoisolante (500 Hz)", 13, 1);
	    	result.setValueAt(_ris.getPotFonoiso500(), 13, 2);
	    	result.setValueAt("dB", 13, 3);
	  
	RisultatiParziali risParz = _ris.getRisultatiParziali();  
	
    result.setValueAt("Risultati Intermedi", 15, 0);
	    result.setValueAt("Strato n.", 15, 1);
	    	result.setValueAt("Massa\nSuperf.", 15, 2);
	    	result.setValueAt("Resist.\nLimin.", 15, 3);	    	
	    	result.setValueAt("Temp.\nMedia", 15, 4);
	    	result.setValueAt("Ammitt.", 15, 5);
	    	result.setValueAt("Inerz.\nTermica", 15, 6);
	    	result.setValueAt("Ammitt.\nEffett. ", 15, 7);
	
	int numStrati = risParz.GetNumStrati();    	
	int maxRow = 16+numStrati;
	int strato = 0;
	for (int i = 16; i < maxRow; i++)
	{
		for (int j = 1; j < 8; j++)
		{
			switch (j)
			{
				case 1:
					result.setValueAt(String.valueOf(strato+1), i, j);
					break;		
				case 2:
					result.setValueAt(String.valueOf(risParz.GetMassaSupStrato(strato)), i, j);
					break;
				case 3:
					result.setValueAt(String.valueOf(risParz.GetResLimStrato(strato)), i, j);
					break;
				case 4:
					result.setValueAt(String.valueOf(risParz.GetTempMediaStrato(strato)), i, j);
					break;
				case 5:
					result.setValueAt(String.valueOf(risParz.GetAmmittanzaStrato(strato)), i, j);
					break;
				case 6:
					result.setValueAt(String.valueOf(risParz.GetInerziaTermStrato(strato)), i, j);
					break;
				case 7:
					result.setValueAt(String.valueOf(risParz.GetAmmittanzaEffettivaStrato(strato)), i, j);
					break;
			}
		}
		strato++;
	}
	    		    	

	maxRow++;
	int nextMaxRow = maxRow + numStrati + 1;
	result.setValueAt("Temperature\nSuperficiali", maxRow, 1);
	strato = 0;
	for (int i = maxRow; i < nextMaxRow; i++)
	{
		result.setValueAt(String.valueOf(risParz.GetTempSuperficeStrato(strato)), i, 2);
		strato++;
	}
	result.setValueAt("< Interno", maxRow, 3);
	result.setValueAt("< Esterno", nextMaxRow-1, 3);
	
	result.setValueAt("Resistenza\nLim. Int.", nextMaxRow+1, 1);
	result.setValueAt(String.valueOf(risParz.GetResLimInt()), nextMaxRow+1, 2);
	result.setValueAt("Resistenza\nLim. Est.", nextMaxRow+2, 1);
	result.setValueAt(String.valueOf(risParz.GetResLimEst()), nextMaxRow+2, 2);
	
	result.setValueAt("Fine del Report", nextMaxRow+3, 0);
	
    return result;
  }

  /**
   * Creates a report definition.
   *
   * @return a report definition.
   */
  private JFreeReport createReportDefinition()
  {
    final JFreeReport report = new JFreeReport();
    report.setName("EcoParete 1.0 - Report");
    
	TextFieldElementFactory element = new TextFieldElementFactory();
	element.setName("Colonna sezioni");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setBold(Boolean.TRUE);
	element.setFontSize(Integer.getInteger("21"));
	element.setFieldname("Col1");					
	element.setPreferredSize(new FloatDimension(100, 25));	
	element.setAbsolutePosition(new Point2D.Float(0, 0));
	
	report.getItemBand().addElement(0, element.createElement());	
	
	element = new TextFieldElementFactory();
	element.setName("Colonna sottosezioni");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("16"));
	element.setFieldname("Col2");					
	element.setPreferredSize(new FloatDimension(85, 25));
	element.setAbsolutePosition(new Point2D.Float(100, 0));
	
	report.getItemBand().addElement(1, element.createElement());
	
	element = new TextFieldElementFactory();
	element.setName("valori1");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("11"));
	element.setFieldname("Col3");					
	element.setPreferredSize(new FloatDimension(40, 25));
	element.setAbsolutePosition(new Point2D.Float(190, 0));
	
	report.getItemBand().addElement(2, element.createElement());
	
	element = new TextFieldElementFactory();
	element.setName("valori2");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("11"));
	element.setFieldname("Col4");					
	element.setPreferredSize(new FloatDimension(40, 25));
	element.setAbsolutePosition(new Point2D.Float(230, 0));
	
	report.getItemBand().addElement(3, element.createElement());
	
	element = new TextFieldElementFactory();
	element.setName("valori3");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("11"));
	element.setFieldname("Col5");					
	element.setPreferredSize(new FloatDimension(40, 25));
	element.setAbsolutePosition(new Point2D.Float(270, 0));
	
	report.getItemBand().addElement(4, element.createElement());
	
	element = new TextFieldElementFactory();
	element.setName("valori4");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("11"));
	element.setFieldname("Col6");					
	element.setPreferredSize(new FloatDimension(40, 25));
	element.setAbsolutePosition(new Point2D.Float(310, 0));
	
	report.getItemBand().addElement(5, element.createElement());
	
	element = new TextFieldElementFactory();
	element.setName("valori5");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("11"));
	element.setFieldname("Col7");					
	element.setPreferredSize(new FloatDimension(40, 25));
	element.setAbsolutePosition(new Point2D.Float(350, 0));
	
	report.getItemBand().addElement(6, element.createElement());
	
	element = new TextFieldElementFactory();
	element.setName("valori6");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("11"));
	element.setFieldname("Col8");					
	element.setPreferredSize(new FloatDimension(40, 25));
	element.setAbsolutePosition(new Point2D.Float(390, 0));
	
	report.getItemBand().addElement(7, element.createElement());
		
	element = new TextFieldElementFactory();
	element.setName("valori7");	
	element.setColor(Color.black);
	element.setHorizontalAlignment(ElementAlignment.LEFT);
	element.setVerticalAlignment(ElementAlignment.MIDDLE);
	element.setNullString("");			
	element.setFontSize(Integer.getInteger("11"));
	element.setFieldname("Col9");					
	element.setPreferredSize(new FloatDimension(50, 25));
	element.setAbsolutePosition(new Point2D.Float(430, 0));
	
	report.getItemBand().addElement(8, element.createElement());	
	
    return report;
  } 
  
/**
 *********************************************
 * This internal method return the current date 
 * and hour.
 *
 * IN: void;
 * OUT: string with the current date	
 */
	protected static String giveTheDate()
	{
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.ITALY);
		String date = df.format(new Date());		
		return date;
	}
}
