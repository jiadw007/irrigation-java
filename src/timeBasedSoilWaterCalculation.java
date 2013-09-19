

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class timeBasedSoilWaterCalculation extends timeBasedCalculation{
	
	/*constructor field with user input: soiltype,RD,TH,Kc */
	private double soilMoistureSensorThreshold=0.7;				//user input, default:0.7
	private ArrayList<Double> Ihrsoil;
	
	
	public double getSoilMoistureSensorThreshold() {
		return soilMoistureSensorThreshold;
	}
	public void setSoilMoistureSensorThreshold(double soilMoistureSensorThreshold) {
		this.soilMoistureSensorThreshold = soilMoistureSensorThreshold;
	}
	public ArrayList<Double> getIhrsoil() {
		return Ihrsoil;
	}
	public void setIhrsoil(ArrayList<Double> ihrsoil) {
		Ihrsoil = ihrsoil;
	}
	
	
	
	public timeBasedSoilWaterCalculation() {
		super();
		this.Ihrsoil=new ArrayList<Double>();
		
	}
	public timeBasedSoilWaterCalculation(double soilMoistureSensorThreshold,
			ArrayList<Double> ihrsoil) {
		super();
		this.soilMoistureSensorThreshold = soilMoistureSensorThreshold;
		Ihrsoil = ihrsoil;
	}
	
	public void calculation(){
		
		HashMap<String, Double> SOIL=b.soil.get(this.getSoilType());
		this.setWB(new ArrayList<Double>());
		
		for(int i =this.getStartIrrigationHour();i<=this.getLastIrrigationHour();i++){
			
			//calculate the ET
			double kc=b.Kc.get(this.getDistrict()).get(b.Month.get(i-1));
			double et=b.ET0.get(i-1);
			this.getET().add(et*kc);
			
			
			//calculation the Ihrsoil
			if(this.getSWC().get(i-1)>this.soilMoistureSensorThreshold*SOIL.get("FC")*this.getRD()){
			
				this.Ihrsoil.add(0.0);
				
			}else{
				
				this.Ihrsoil.add(this.b.Ihr.get(i-1));
			}
			double wb=this.b.Rhr.get(i-1)+this.Ihrsoil.get(i-1);
			this.getWB().add(wb);
			super.calculation(i);
			
		}
		
		
		
		
		
	}
	
	public void writeDataToFile(){
		
		
		try{
			File csv=new File("soilwater-result.csv");
			BufferedWriter bw=new BufferedWriter(new FileWriter(csv,false));
			bw.write("hour"+","+"Rhr"+","+"Ihrsoil"+","+"ET0"+","+"ET"+","+"WB"+","+"SWC"+","+"DELTA"+","+"F"+","+"f"+","+"Q"+","+"InF"+","+"PERC"+","+"Loss"+","+"PerLoss"+","+"wLostHr"+","+
					 "wLostDay"+","+"iLostHr"+","+"iLostDay"+","+"Ihr");
			bw.newLine();
			for(int i =0;i<this.Ihrsoil.size();i++){
				
				bw.write(this.b.Hour.get(i)+","+this.b.Rhr.get(i)+","+this.getIhrsoil().get(i)+","+b.ET0.get(i)+","+this.getET().get(i)+","+this.getWB().get(i)+","+this.getSWC().get(i+1)+","+this.getDelta().get(i)+","
				        +this.getF().get(i)+","+this.getRateF().get(i)+","+this.getQ().get(i)+","+this.getInF().get(i)+","+this.getPERC().get(i)+","+this.getLoss().get(i)+","+this.getPerLoss().get(i)
				        +","+this.getwLostHr().get(i)+","+this.getwLostDay().get(i)+","+this.getiLostHr().get(i)+","+this.getiLostDay().get(i)+","+this.b.Ihr.get(i));
				bw.newLine();
				
			}
			bw.close();
			
		}catch (FileNotFoundException e) { 
		      // File exception
		      e.printStackTrace(); 
		} catch (IOException e) { 
		      // BufferedWriter exception
		      e.printStackTrace(); 
		} 
		
		
	}
	public static void main(String args[]){
		
		timeBasedSoilWaterCalculation tswc=new timeBasedSoilWaterCalculation();
		tswc.calculation();
		tswc.writeDataToFile();
		
		
		
		
	}
	

}
