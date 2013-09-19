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

public class timeBasedResult {
	
	
	private ArrayList<Double> ET0;
	private ArrayList<Double> ET;
	private ArrayList<Double> WB;
	private ArrayList<Double> SWC;
	private ArrayList<Double> delta;
	private ArrayList<Double> F;
	private ArrayList<Double> f;
	private ArrayList<Double> Q;
	private ArrayList<Double> InF;
	private ArrayList<Double> PERC;
	private ArrayList<Double> Loss;
	private ArrayList<Double> PerLoss;
	private ArrayList<Double> wLostHr;
	private ArrayList<Double> wLostDay;
	private ArrayList<Double> iLostHr;
	private ArrayList<Double> iLostDay;
	
	private String fileName;

	public timeBasedResult(String Filename) {

		this.ET0=new ArrayList<Double>();
		this.ET=new ArrayList<Double>();
		this.WB=new ArrayList<Double>();
		this.SWC=new ArrayList<Double>();
		this.delta=new ArrayList<Double>();
		this.F=new ArrayList<Double>();
		this.f=new ArrayList<Double>();
		this.Q=new ArrayList<Double>();
		this.InF=new ArrayList<Double>();
		this.PERC=new ArrayList<Double>();
		this.Loss=new ArrayList<Double>();
		this.PerLoss=new ArrayList<Double>();
		wLostHr=new ArrayList<Double>();
		wLostDay=new ArrayList<Double>();
		iLostHr=new ArrayList<Double>();
		iLostDay=new ArrayList<Double>();
		this.fileName=Filename;
		//System.out.println(fileName);
		try{
			
			/*import jxl, get xls object*/
			InputStream is=new FileInputStream(this.fileName);
			jxl.Workbook rwb=Workbook.getWorkbook(is);
			jxl.Sheet rs=rwb.getSheet(0);
			
			/*read data from .xls*/
			int rowNum=rs.getRows();
			for(int i =1;i<rowNum;i++){
				
				Cell[] cell=rs.getRow(i);
				this.ET0.add(Double.parseDouble(cell[1].getContents()));
				this.ET.add(Double.parseDouble(cell[3].getContents()));
				this.WB.add(Double.parseDouble(cell[4].getContents()));
				this.SWC.add(Double.parseDouble(cell[5].getContents()));
				this.delta.add(Double.parseDouble(cell[6].getContents()));
				this.F.add(Double.parseDouble(cell[7].getContents()));
				this.f.add(Double.parseDouble(cell[8].getContents()));
				this.Q.add(Double.parseDouble(cell[9].getContents()));
				this.InF.add(Double.parseDouble(cell[10].getContents()));
				this.PERC.add(Double.parseDouble(cell[11].getContents()));
				this.Loss.add(Double.parseDouble(cell[12].getContents()));
				this.wLostHr.add(Double.parseDouble(cell[15].getContents()));
				this.wLostDay.add(Double.parseDouble(cell[16].getContents()));
				this.iLostHr.add(Double.parseDouble(cell[17].getContents()));
				this.iLostDay.add(Double.parseDouble(cell[18].getContents()));
			}
				
				
			}catch(Exception ex){
				
				ex.printStackTrace();
			}
	}
	
	public static void main(String args[]){
		
		
		timeBasedResult tbr=new timeBasedResult("timebasedresult.xls");
		
		
		int correctCount=0;  // the total number of all correct data
		
		try{
			
			File csv=new File("time-base-result.csv");			//csv result file from timeBasedSoilWaterCalculation.java 
			
			BufferedReader br = new BufferedReader(new FileReader(csv)); 
		    br.readLine();
		    /*test all data, if there is error, it will print out the index of data. Find it in the file*/
		    for(int i =0;i<tbr.ET0.size();i++){
				
		    	String line= br.readLine();
		    	String item[]=line.split(",");
				if(Math.abs(tbr.ET0.get(i)-Double.parseDouble(item[1]))>=0.001){
					
					System.out.println("The Data in ET0 "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.ET.get(i)-Double.parseDouble(item[2]))>=0.001){
					
					System.out.println("The Data in ET "+(i+1)+"has error ");
					
				}else if(Math.abs(tbr.WB.get(i)-Double.parseDouble(item[3]))>=0.001){
					
					System.out.println("The Data in WB "+(i+1)+"has error ");
					
				}else if(Math.abs(tbr.SWC.get(i)-Double.parseDouble(item[4]))>=0.001){
					//System.out.println(tbr.SWC.get(i));
					//System.out.println(Double.parseDouble(item[6]));
					System.out.println("The Data in SWC "+(i+1)+"has error ");
					
				}else if(Math.abs(tbr.delta.get(i)-Double.parseDouble(item[5]))>=0.001){
					
					System.out.println("The Data in DELTA "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.F.get(i)-Double.parseDouble(item[6]))>=0.001){
					
					System.out.println("The Data in F "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.f.get(i)-Double.parseDouble(item[7]))>=0.001){
					
					System.out.println("The Data in f "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.Q.get(i)-Double.parseDouble(item[8]))>=0.001){
					
					System.out.println("The Data in Q "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.InF.get(i)-Double.parseDouble(item[9]))>=0.001){
					
					System.out.println("The Data in InF "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.PERC.get(i)-Double.parseDouble(item[10]))>=0.001){
					
					System.out.println("The Data in PERC "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.Loss.get(i)-Double.parseDouble(item[11]))>=0.001){
					
					System.out.println("The Data in Loss "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.wLostHr.get(i)-Double.parseDouble(item[14]))>=0.001){
					
					System.out.println("The Data in wLostHr "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.wLostDay.get(i)-Double.parseDouble(item[15]))>=0.001){
					//System.out.println(tbr.wLostDay.get(i));
					//System.out.println(Double.parseDouble(item[15]));
					System.out.println("The Data in wLostDay "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.iLostHr.get(i)-Double.parseDouble(item[16]))>=0.001){
					
					System.out.println("The Data in iLostHr "+(i+1)+"has error");
					
				}else if(Math.abs(tbr.iLostDay.get(i)-Double.parseDouble(item[17]))>=0.001){
					
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
