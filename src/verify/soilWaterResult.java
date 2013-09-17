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

public class soilWaterResult {

	/**
	 * These attributes are according to your result .csv file from your function and .xls file from R.file function
	 * If there are some changes in the original file, please add or delete attribute
	 */
	//private ArrayList<Integer> hour;
	//private ArrayList<Integer> Rhr;
	//private ArrayList<Double> Ihrsoil;
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
	private String fileName;
	
	/**
	 * Constructor function for soilWaterResult
	 * Add all data into all according array list
	 * @param Filename, it is the result .xls file which is got from R file function
	 */
	public soilWaterResult(String Filename){
		
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
				this.ET0.add(Double.parseDouble(cell[3].getContents()));
				this.ET.add(Double.parseDouble(cell[4].getContents()));
				this.WB.add(Double.parseDouble(cell[5].getContents()));
				this.SWC.add(Double.parseDouble(cell[6].getContents()));
				this.delta.add(Double.parseDouble(cell[7].getContents()));
				this.F.add(Double.parseDouble(cell[8].getContents()));
				this.f.add(Double.parseDouble(cell[9].getContents()));
				this.Q.add(Double.parseDouble(cell[10].getContents()));
				this.InF.add(Double.parseDouble(cell[11].getContents()));
				this.PERC.add(Double.parseDouble(cell[12].getContents()));
				
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
		soilWaterResult swr=new soilWaterResult("soil.xls");		//The filename is important. Verify it correctly
		
		int correctCount=0;  // the total number of all correct data
		try{
			
			File csv=new File("soilwater-result.csv");			//csv result file from timeBasedSoilWaterCalculation.java 
			
			BufferedReader br = new BufferedReader(new FileReader(csv)); 
		    br.readLine();
		    /*test all data, if there is error, it will print out the index of data. Find it in the file*/
		    for(int i =0;i<swr.ET0.size();i++){
				
		    	String line= br.readLine();
		    	String item[]=line.split(",");
				if(Math.abs(swr.ET0.get(i)-Double.parseDouble(item[3]))>=0.001){
					
					System.out.println("The Data in ET0 "+(i+1)+"has error");
					
				}else if(Math.abs(swr.ET.get(i)-Double.parseDouble(item[4]))>=0.001){
					
					System.out.println("The Data in ET "+(i+1)+"has error ");
					
				}else if(Math.abs(swr.WB.get(i)-Double.parseDouble(item[5]))>=0.001){
					
					System.out.println("The Data in WB "+(i+1)+"has error ");
					
				}else if(Math.abs(swr.SWC.get(i)-Double.parseDouble(item[6]))>=0.001){
					//System.out.println(swr.SWC.get(i));
					//System.out.println(Double.parseDouble(item[6]));
					System.out.println("The Data in SWC "+(i+1)+"has error ");
					
				}else if(Math.abs(swr.delta.get(i)-Double.parseDouble(item[7]))>=0.001){
					
					System.out.println("The Data in DELTA "+(i+1)+"has error");
					
				}else if(Math.abs(swr.F.get(i)-Double.parseDouble(item[8]))>=0.001){
					
					System.out.println("The Data in F "+(i+1)+"has error");
					
				}else if(Math.abs(swr.f.get(i)-Double.parseDouble(item[9]))>=0.001){
					
					System.out.println("The Data in f "+(i+1)+"has error");
					
				}else if(Math.abs(swr.Q.get(i)-Double.parseDouble(item[10]))>=0.001){
					
					System.out.println("The Data in Q "+(i+1)+"has error");
					
				}else if(Math.abs(swr.InF.get(i)-Double.parseDouble(item[11]))>=0.001){
					
					System.out.println("The Data in InF "+(i+1)+"has error");
					
				}else if(Math.abs(swr.PERC.get(i)-Double.parseDouble(item[12]))>=0.001){
					
					System.out.println("The Data in PERC "+(i+1)+"has error");
					
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
