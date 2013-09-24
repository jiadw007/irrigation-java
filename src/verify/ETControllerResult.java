package verify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Workbook;

public class ETControllerResult {
	
	/**
	 * These attributes are according to your result .csv file from your function and .xls file from R.file function
	 * If there are some changes in the original file, please add or delete attribute
	 */
	
	private ArrayList<Double> Re;
	private ArrayList<Double> IhrSchedule;
	private ArrayList<Double> Ick1;
	private ArrayList<Double> Ick2;
	private ArrayList<Double> SWC;
	private ArrayList<Double> ET;
	private ArrayList<Double> AWRStep1;
	private ArrayList<Double> AWRStep2;
	private ArrayList<Double> AWR;
	private ArrayList<Double> Ihret;
	private ArrayList<Double> WB;
	private ArrayList<Double> delta;
	private ArrayList<Double> F;
	private ArrayList<Double> f;
	private ArrayList<Double> Q;
	private ArrayList<Double> InF;
	private ArrayList<Double> PERC;
	private ArrayList<Double> wLostHr;
	private ArrayList<Double> wLostDay;
	private ArrayList<Double> iLostHr;
	private ArrayList<Double> iLostDay;
	
	private String fileName;
	
	/**
	 * Constructor function for soilWaterResult
	 * Add all data into all according array list
	 * @param Filename, it is the result .xls file which is got from R file function
	 */
	public ETControllerResult(String Filename){
		
		this.Re=new ArrayList<Double>();
		this.IhrSchedule=new ArrayList<Double>();
		this.Ick1=new ArrayList<Double>();
		this.Ick2=new ArrayList<Double>();
		this.SWC=new ArrayList<Double>();
		this.ET=new ArrayList<Double>();
		this.AWR=new ArrayList<Double>();
		this.AWRStep1=new ArrayList<Double>();
		this.AWRStep2=new ArrayList<Double>();
		this.Ihret=new ArrayList<Double>();
		this.WB=new ArrayList<Double>();
		this.delta=new ArrayList<Double>();
		this.F=new ArrayList<Double>();
		this.f=new ArrayList<Double>();
		this.Q=new ArrayList<Double>();
		this.InF=new ArrayList<Double>();
		this.PERC=new ArrayList<Double>();
		wLostHr=new ArrayList<Double>();
		wLostDay=new ArrayList<Double>();
		iLostHr=new ArrayList<Double>();
		iLostDay=new ArrayList<Double>();
		this.fileName=fileName;
		this.fileName=Filename;
		try{
			
			/*import jxl, get xls object*/
			InputStream is=new FileInputStream(fileName);
			jxl.Workbook rwb=Workbook.getWorkbook(is);
			jxl.Sheet rs=rwb.getSheet(0);
			
			/*read data from .xls*/
			int rowNum=rs.getRows();
			for(int i =1;i<rowNum;i++){
				
				Cell[] cell=rs.getRow(i);
				this.Re.add(Double.parseDouble(cell[2].getContents()));
				this.IhrSchedule.add(Double.parseDouble(cell[3].getContents()));
				this.Ick1.add(Double.parseDouble(cell[4].getContents()));
				this.Ick2.add(Double.parseDouble(cell[5].getContents()));
				this.SWC.add(Double.parseDouble(cell[6].getContents()));
				this.ET.add(Double.parseDouble(cell[7].getContents()));
				this.AWRStep1.add(Double.parseDouble(cell[8].getContents()));
				this.AWRStep2.add(Double.parseDouble(cell[9].getContents()));
				this.AWR.add(Double.parseDouble(cell[10].getContents()));
				this.Ihret.add(Double.parseDouble(cell[11].getContents()));
				this.WB.add(Double.parseDouble(cell[12].getContents()));
				this.delta.add(Double.parseDouble(cell[13].getContents()));
				this.F.add(Double.parseDouble(cell[14].getContents()));
				this.f.add(Double.parseDouble(cell[15].getContents()));
				this.Q.add(Double.parseDouble(cell[16].getContents()));
				this.InF.add(Double.parseDouble(cell[17].getContents()));
				this.PERC.add(Double.parseDouble(cell[18].getContents()));
				this.wLostHr.add(Double.parseDouble(cell[21].getContents()));
				this.wLostDay.add(Double.parseDouble(cell[22].getContents()));
				this.iLostHr.add(Double.parseDouble(cell[23].getContents()));
				this.iLostDay.add(Double.parseDouble(cell[24].getContents()));
			}
				
				
			}catch(Exception ex){
				
				ex.printStackTrace();
			}
	}
	
	
	/**
	 * test function to verify result from your function
	 * @param args
	 */
	public static void main(String args[]){
		
		/*change the value in the constructor field with the file name and path for the result file from R file*/
		
		ETControllerResult etcr=new ETControllerResult("ET.xls");	//The filename is important. Verify it correctly
		int correctCount=0;			// the total number of all correct data
		try{
			
			File csv=new File("ETController-result.csv");		//csv result file from timeBasedETControllerCalculation.java 
			
			BufferedReader br = new BufferedReader(new FileReader(csv)); 
		    br.readLine();
		    
		    /*test all data, if there is error, it will print out the index of data. Find it in the file*/
		    for(int i =0;i<etcr.ET.size();i++){
				
		    	String line= br.readLine();
		    	String item[]=line.split(",");
				if(Math.abs(etcr.Re.get(i)-Double.parseDouble(item[2]))>=0.001){
					
					System.out.println("The Data in Re "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.IhrSchedule.get(i)-Double.parseDouble(item[3]))>=0.001){
					
					System.out.println("The Data in IhrSchedule "+(i+1)+"has error ");
					
				}else if(Math.abs(etcr.Ick1.get(i)-Double.parseDouble(item[4]))>=0.001){
					
					System.out.println("The Data in Ick1 "+(i+1)+"has error ");
					
				}else if(Math.abs(etcr.Ick2.get(i)-Double.parseDouble(item[5]))>=0.001){
					//System.out.println(etcr.SWC.get(i));
					//System.out.println(Double.parseDouble(item[6]));
					System.out.println("The Data in Ick2 "+(i+1)+"has error ");
					
				}else if(Math.abs(etcr.SWC.get(i)-Double.parseDouble(item[6]))>=0.001){
					
					System.out.println("The Data in SWC "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.ET.get(i)-Double.parseDouble(item[7]))>=0.001){
					
					System.out.println("The Data in ET "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.AWRStep1.get(i)-Double.parseDouble(item[8]))>=0.001){
					
					System.out.println("The Data in AWRStep1 "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.AWRStep2.get(i)-Double.parseDouble(item[9]))>=0.001){
					
					System.out.println("The Data in AWRStep2 "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.AWR.get(i)-Double.parseDouble(item[10]))>=0.001){
					
					System.out.println("The Data in AWR "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.Ihret.get(i)-Double.parseDouble(item[11]))>=0.001){
					
					System.out.println("The Data in Ihret "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.WB.get(i)-Double.parseDouble(item[12]))>=0.001){
					
					System.out.println("The Data in WB "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.delta.get(i)-Double.parseDouble(item[13]))>=0.001){
					
					System.out.println("The Data in DELTA "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.F.get(i)-Double.parseDouble(item[14]))>=0.001){
					
					System.out.println("The Data in F "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.f.get(i)-Double.parseDouble(item[15]))>=0.001){
					
					System.out.println("The Data in f "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.Q.get(i)-Double.parseDouble(item[16]))>=0.001){
					
					System.out.println("The Data in Q "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.InF.get(i)-Double.parseDouble(item[17]))>=0.001){
					
					System.out.println("The Data in InF "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.PERC.get(i)-Double.parseDouble(item[18]))>=0.001){
					
					System.out.println("The Data in PERC "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.wLostHr.get(i)-Double.parseDouble(item[21]))>=0.001){
					
					System.out.println("The Data in wLostHr "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.wLostDay.get(i)-Double.parseDouble(item[22]))>=0.001){
					
					System.out.println("The Data in wLostDay "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.iLostHr.get(i)-Double.parseDouble(item[23]))>=0.001){
					
					System.out.println("The Data in iLostHr "+(i+1)+"has error");
					
				}else if(Math.abs(etcr.iLostDay.get(i)-Double.parseDouble(item[24]))>=0.001){
					
					System.out.println("The Data in iLostDay "+(i+1)+"has error");
					
				}else{
					
					correctCount++;
					
				}
		    }
				
		}catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		System.out.println("finish read file and verify");
		System.out.println("The total number of correct number is :"+correctCount);
			
	}

	
	
	

}
