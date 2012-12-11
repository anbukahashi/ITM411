import java.text.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JApplet;
import javax.swing.JFrame;

import ui.CtrlGrid;
import ui.FrmMain;

import mysql.PopulationTable;

/**
 * Main Program thread. 
 * 
 * @author Brady Houseknecht
 */
public class Program extends JApplet implements ActionListener, Runnable
{
	private PopulationTable m_obj_data;
	/**
	    * Base class initialization
	    * method override. It is necessary 
	    * in order to configure
	    * the background and
	    * layout.
	    */
	    @Override
    public void init() {
        
	    System.out.println("Program.init fired.");

        mp2.PersistentObject recs;
        System.out.println("parsing CSV...");
        mp2.PersistentObject persistentObject = 
                new mp2.PersistentObject("C:\\CHProjects\\Code\\Java\\itm411\\FMP\\data\\NST_EST2011_ALLDATA.csv");
      
       FileOutputStream fileOut;
        try 
        {
            fileOut = new FileOutputStream("C:\\CHProjects\\Code\\Java\\itm411\\FMP\\data\\population-record.ser");
            ObjectOutputStream out =
                              new ObjectOutputStream(fileOut);
           out.writeObject(persistentObject);
           out.close();
            fileOut.close();
        } // end: try
        catch (FileNotFoundException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } // end:catch
        catch (IOException e) {
			e.printStackTrace();
		} // end:catch
        
        try 
        {
            Thread.currentThread().sleep(5000);//sleep for 1000 ms
        } // end:try
        catch (InterruptedException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } // end:catch
        
        try 
        {
            FileInputStream fileIn =
                          new FileInputStream("C:\\CHProjects\\Code\\Java\\itm411\\FMP\\data\\population-record.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            recs =(mp2.PersistentObject) 
                    in.readObject();
            Date postSerTimeStamp = new Date();
            double seconds = (postSerTimeStamp.getTime()-
                   recs.getDate().getTime())/1000;
            System.out.println(seconds + " seconds ellapsed.");
            
            for(mp2.PopulationRecord rec : recs.getPopulationRecords())
            {
                System.out.println(rec.getRegion().name() + " 2010 Population % Incr: " + rec.popPerIncr(2010));
                System.out.println(rec.getRegion().name() + " 2011 Population % Incr: " + rec.popPerIncr(2011));
            } // end:for
            
            this.m_obj_data= new mysql.PopulationTable(recs.getPopulationRecords());
            
            this.m_obj_data.insert();
            
            
            /*for(mp2.PopulationRecord rec : recs.getPopulationRecords())
            {
                if(rec.getSumlev() == mp2.Record.SumLevCode.State &&
                        rec.getState()!= mp2.Record.StateFIPCode.NA)
                {
                    System.out.println(rec.getState().name() + " 2010 Max / Min Births: " + rec.maxBirthPerYear(2010));
                    System.out.println(rec.getRegion().name() + " 2011 Max / Min Births: " + rec.maxBirthPerYear(2011));
                    System.out.println(rec.getState().name() + " 2010 Max / Min Deaths: " + rec.maxDeathPerYear(2010));
                    System.out.println(rec.getState().name() + " 2011 Max / Min Deaths: " + rec.maxDeathPerYear(2011));
                } // end:if
            } // end:for
            
            System.out.println("Count of States with Population Increases in 2011: " + recs.getCountOfEstPopIncr(2011));
			System.out.println("Count of States with Population Decreases in 2011: " + recs.getCountOfEstPopDecr(2011));
			
			System.out.println("State with the greatest population in 2011: " + recs.getStateWithMostPop(2011));
			System.out.println("State with the greatest population in 2010: " + recs.getStateWithMostPop(2010));
			System.out.println("State with the least population in 2011: " + recs.getStateWithLeastPop(2011));
			System.out.println("State with the least population in 2010: " + recs.getStateWithLeastPop(2010)); */
            in.close();
            fileIn.close();
    
        	
    	
    	} // end:try
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } // end:catch
        catch (FileNotFoundException e) {
			e.printStackTrace();
		} // end:catch
        catch (IOException e) {
        	e.printStackTrace();
		} // end:catch
    } // end:init

	@Override
    public void start() 
	{
		System.out.println("Program.start fired.");
		 /// Create main panel control
        
        CtrlGrid grid = new CtrlGrid(this.m_obj_data.getDataRows(), 
        		this.m_obj_data.getHeaderRow());
        
    	grid.setOpaque(true); 
    	//content panes must be opaque         
    	this.add(grid);
    
	} // end:start
	
	@Override
    public void run() 
	{
		System.out.println("Program.run fired.");
		this.start();
	} // end:start
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
} // end:class