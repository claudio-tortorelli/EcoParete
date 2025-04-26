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
 * This is a implementation of FileFilter abstract class
 * that accept only files declared in filter or all files
 * if no extension is declared. Note that this object permit
 * only the declaration of one type of extension to filter
 */
 
import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.filechooser.*;

public class MyFileFilter extends FileFilter 
{

//    private static String TYPE_UNKNOWN = "Type Unknown";
//    private static String HIDDEN_FILE = "Hidden File";

    private String _extension = null;
    private String _description = null;

    /**
     * Creates a file filter. If no filters are added, then all
     * files are accepted.
     *
     * @see #addExtension
     */
    public MyFileFilter() 
    {    	
    }

    /**
     * Creates a file filter that accepts files with the given extension.
     * Example: new ExampleFileFilter("jpg");
     *
     * @see #addExtension
     */
    public MyFileFilter(String extension) 
    {
		this(extension,null);
    }

    /**
     * Creates a file filter that accepts the given file type.
     * Example: new ExampleFileFilter("jpg", "JPEG Image Images");
     *
     * Note that the "." before the extension is not needed. If
     * provided, it will be ignored.
     *
     * @see #addExtension
     */
    public MyFileFilter(String extension, String description) 
    {		
		if(extension!=null) setExtension(extension);
 		if(description!=null) setDescription(description);
    }

    /**
     * Return true if this file should be shown in the directory pane,
     * false if it shouldn't.
     *
     * Files that begin with "." are ignored.
     *
     * @see #getExtension
     * @see FileFilter#accepts
     */
    public boolean accept(File f) 
    {
		if(f != null) 
		{
	    	if(f.isDirectory()) 
	    	{
				return true;
	    	}
	    	if(getExtensionFile(f).equals(this.getExtension())) 
	    	{
				return true;
	    	};
		}
		return false;
    }

    /**
     * Return the extension portion of the file's name .
     *
     * @see #getExtension
     * @see FileFilter#accept
     */
     public String getExtensionFile(File f) 
     {
		if(f != null) 
		{
    		String filename = f.getName();
    		int i = filename.lastIndexOf('.');
    		if(i>0 && i<filename.length()-1) 
    		{
				return filename.substring(i+1).toLowerCase();
    		};
		}
		return "no file";
     }

   /**
    * Adds a filetype "dot" extension to filter against.
    *
    * Note that the "." before the extension is not needed and will be ignored.
    */
    public void setExtension(String extension) 
    {
    	int ind = extension.lastIndexOf('.');
    	if (ind != -1) extension = extension.substring(ind,extension.length());
		_extension = extension.toLowerCase();		
    }
    
   /**
    * Return the actual extension that is filtered.
    *
    * Note that the "." before the extension is not needed and will be ignored.
    */
    public String getExtension() 
    {
		if (_extension != null) return _extension;
		return "No extension";
    }

    /**
     * Returns the human readable description of this filter. For
     * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     * @see FileFilter#getDescription
     */
    public String getDescription() 
    {
    	if (_description != null) 
    	{
    		return _description;
    	}
    	return "No description";
    }

    /**
     * Sets the human readable description of this filter. For
     * example: filter.setDescription("Gif and JPG Images");
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     */
    public void setDescription(String description) 
    {
		_description = description;		
    }
};