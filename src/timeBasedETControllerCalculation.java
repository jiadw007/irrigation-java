

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;




public class timeBasedETControllerCalculation extends timeBasedCalculation{
	
	
	/*constructor fields with user input: soiltype, rd, area*/
	private ArrayList<Double> Ihret;
	private ArrayList<Double> Re;
	private ArrayList<Integer> Ick1;
	private ArrayList<Integer> Ick2;
	private ArrayList<Double> AWR;
	private ArrayList<Double> AWRstep1;
	private ArrayList<Integer> AWRstep2;
	private ArrayList<Double> AWRstep3;
	
	public timeBasedETControllerCalculation() {
		
		super();
		Ihret=new ArrayList();
		Re=new ArrayList();
		Ick1=new ArrayList();
		Ick2=new ArrayList();
		AWR=new ArrayList();
		AWRstep1=new ArrayList();
		AWRstep2=new ArrayList();
		AWRstep3=new ArrayList();
		AWR.add(0.0);
	}
	
	public void calculation(){
		
		HashMap<String, Double> SOIL=b.soil.get(this.getSoilType());
		this.setWB(new ArrayList<Double>());
		
		
		for (int i =this.getStartIrrigationHour();i<=this.getLastIrrigationHour();i++){
			
			//calculate the ET
			double kc=b.Kc.get(this.getDistrict()).get(b.Month.get(i-1));
			double et=b.ET0.get(i-1);
			this.getET().add(et*kc);
			//calculation Re
			if(this.b.Rhr.get(i-1)>(this.getRD()*SOIL.get("FC")-this.getSWC().get(i-1))){
				
				double re=this.getRD()*SOIL.get("FC")-this.getSWC().get(i-1);
				this.Re.add(re);
				
			}else{
				
				this.Re.add(this.b.Rhr.get(i-1));
				
			}
			
			//check if irrigation is scheduled
			if(this.b.Ihrschedule.get(i-1)==1){
				
				Ick1.add(1);
			}else{
				
				Ick1.add(0);
			}
			
			//check if irrigation occurs
			if(this.AWR.get(i-1)>(SOIL.get("FC")-SOIL.get("WP"))*this.getRD()*SOIL.get("MAD")){
				
				
				Ick2.add(1);
				
				
			}else{
				
				Ick2.add(0);
				
			}
			
			//check if irrigation occurs
			
			if(Ick1.get(i-1)+Ick2.get(i-1)==2){
				
				this.Ihret.add(AWR.get(i-1));
			}else{
				
				this.Ihret.add(0.0);
				
			}
			
			//calcualte AWR
			if(Ick1.get(i-1)+Ick2.get(i-1)==2){
				
				double awrstep1=this.getET().get(i-1)-this.Re.get(i-1);
				this.AWRstep1.add(awrstep1);
				
			}else{
				
				double awrstep1=this.getET().get(i-1)-this.Re.get(i-1)+this.AWR.get(i-1);
				this.AWRstep1.add(awrstep1);
			}
			
			if(this.b.Rhr.get(i-1)>0&&this.Re.get(i-1)==0){
				
				this.AWRstep2.add(0);
				
				
			}else{
				
				this.AWRstep2.add(1);
				
			}
			
			if(this.AWRstep1.get(i-1)<0||this.AWRstep2.get(i-1)==0){
				
				this.AWR.add(0.0);
			}else{
				
				this.AWR.add(this.AWRstep1.get(i-1));
				
			}
			double wb=this.b.Rhr.get(i-1)+this.Ihret.get(i-1);
			this.getWB().add(wb);
			
			super.calculation(i);
				
			
		}
				
		
	}
	
	public void writeDataToFile(){
		
		
		try{
			File csv=new File("ETContorller-result.csv");
			BufferedWriter bw=new BufferedWriter(new FileWriter(csv,false));
			bw.write("hour"+","+"Rhr"+","+"Re"+","+"IhrSchedule"+","+"Ick1"+","+"Ick2"+","+"SWC"+","+"ET"+","+"AWRstep1"+","+"AWRstep2"+","+"AWR"+
					 ","+"Ihret"+","+"WB"+","+"delta"+","+"F"+","+"f"+","+
			         "Q"+","+"InF"+","+"PERC"+","+"wLostHr"+","+
					 "wLostDay"+","+"iLostHr"+","+"iLostDay");
			bw.newLine();
			for(int i =0;i<this.AWRstep1.size();i++){
				
				bw.write(this.b.Hour.get(i)+","+this.b.Rhr.get(i)+","+this.Re.get(i)+","+this.b.Ihrschedule.get(i)+","+this.Ick1.get(i)+","+
						this.Ick2.get(i)+","+this.getSWC().get(i+1)+","+this.getET().get(i)+","+this.AWRstep1.get(i)+","+this.AWRstep2.get(i)+","+this.AWR.get(i));
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
		
		
		timeBasedETControllerCalculation tecc=new timeBasedETControllerCalculation();
		tecc.calculation();
		tecc.writeDataToFile();
		
		
	}
	
	
	
	
}
