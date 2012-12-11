package ui;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import mysql.PopulationTable;

import java.net.*; 



/**
 * Main application screen.
 * 
 * @author Brady Houseknecht
 */
public class FrmMain extends  Applet implements ActionListener  {
	
	/**
    * Private variable controlling the state of the
    * applet where:
    * 0 = Debug
    * 1 = Release
    * NOTE - In DEBUG mode all log message are writen
    * to the console. In addition, the routine only runs
    * Scenario 1 and then stops.
    */
    private static int MODE = 1;
    
    private Graphics2D objGraphic2D;
    private int m_int_width;
    private int m_int_height;
    private int m_int_squareWidth;
    private int m_int_squareHeight;
    private int m_int_actualWidth;
    private int m_int_actualHeight;
    private int m_int_margin = 10;
    
    private PopulationTable m_obj_data;
    
	/**
	 * Class constructor.
	 */
	public FrmMain(int width, int height, PopulationTable data) {

		this.m_int_width = width;
	    this.m_int_height = height;
	    double _dblSquareWidth = (width-(this.m_int_margin*2)) / 8;
	    double _dblSquareHeight = (height-(this.m_int_margin*2)) / 8;
	    this.m_int_squareHeight =(int) _dblSquareHeight;
        this.m_int_squareWidth = (int) _dblSquareWidth;
	    this.m_int_actualHeight = (this.m_int_squareHeight * 8) + (this.m_int_margin * 2);
	    this.m_int_actualWidth = (this.m_int_squareWidth * 8) + (this.m_int_margin * 2);
	    
	    this.m_obj_data = data;
	    
	} // end:FrmMain

	/**
     * Base class initialization
     * method override. It is necessary 
     * in order to configure
     * the background and
     * layout.
     */
    @Override
    public void init()
    {
        
    } // end:init
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} // end:actionPerformed
	
	@Override
    public void paint( Graphics pG )
    {
      
        this.objGraphic2D = (Graphics2D) pG;
        this.objGraphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        this.objGraphic2D.setPaint(Color.gray);

        /// Create main panel control
        //JFrame contentFrame = new JFrame("FMP");
        //contentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       /* CtrlGrid grid = new CtrlGrid(this.m_obj_data.getDataRows(), 
        *		this.m_obj_data.getHeaderRow());
        */
    	//grid.setOpaque(true); 
    	//content panes must be opaque         
    	//contentFrame.setContentPane(grid);           
    	//Display the window.         
    	//contentFrame.pack();         
    	//contentFrame.setVisible(true);
    	
    	//this.add(grid);
    	
        
            
    } // end paintComponent function
	
	/**
     * @return the Width
     */
    public int getWidth() {
        return this.m_int_width;
    } // end:getWidth
   
    /**
     * @return the Height
     */
    public int getHeight() {
        return this.m_int_height;
    } // end:getHeight

    /**
     * @return the squareWidth
     */
    public int getSquareWidth() {
        return this.m_int_squareWidth;
    } // end:getSquareWidth

    /**
     * @return the squareHeight
     */
    public int getSquareHeight() {
        return this.m_int_squareHeight;
    } // end:getSquareHeight

    /**
     * @return the margin
     */
    public int getMargin() {
        return this.m_int_margin;
    } // end:getMargin

    /**
     * @return the actualWidth
     */
    public int getActualWidth() {
        return this.m_int_actualWidth;
    } // end:getActualWidth

    /**
     * @return the actualHeight
     */
    public int getActualHeight() {
        return this.m_int_actualHeight;
    } // end:getActualHeight
} //end:class
