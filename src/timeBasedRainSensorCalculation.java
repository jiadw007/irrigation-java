
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class timeBasedRainSensorCalculation extends timeBasedCalculation{
	
	
	/*constructor with user input : Kc, soil type, RSS,RD*/
	private double rainSensorSetting=1.27;			//user input, default:1.27
	private ArrayList<Double> rainSum;				//rainsum for last 24 hours
	//private ArrayList<Double> WB;					//WB=Rhr+IhrRain
	private ArrayList<Double> IhrRain;
	
	
	public ArrayList<Double> getIhrRain() {
		return IhrRain;
	}
	public void setIhrRain(ArrayList<Double> ihrRain) {
		IhrRain = ihrRain;
	}
	
	public timeBasedRainSensorCalculation() {
		super();
		rainSum=new ArrayList<Double>();
		//WB=new ArrayList<Double>();
		IhrRain=new ArrayList<Double>();
	}
	public timeBasedRainSensorCalculation(String soiltype,double rd) {
		super(soiltype,rd);
		rainSum=new ArrayList<Double>();
		//WB=new ArrayList<Double>();
		IhrRain=new ArrayList<Double>();
		
	}
	
	public void calculation(){
		
		this.setWB(new ArrayList<Double>());
		
		for(int i =this.getStartIrrigationHour();i<=this.getLastIrrigationHour();i++){
			
			//calculate the ET
			double kc=b.Kc.get(this.getDistrict()).get(b.Month.get(i-1));
			double et=b.ET0.get(i-1);
			this.getET().add(et*kc);
			
			
			if(i<24){
				
				this.rainSum.add(super.b.Rhr.get(i-1));
				
			}else{
				
				double sum=0;
				for(int j=i-24;j<i;j++){
					
					sum+=super.b.Rhr.get(j);
										
				}
				this.rainSum.add(sum);
				
			}
			if(this.rainSum.get(i-1)>this.rainSensorSetting){
				
				
				this.IhrRain.add(0.0);
				
			}
			else{
				this.IhrRain.add(super.b.Ihr.get(i-1));
				
			}
			double wb=this.b.Rhr.get(i-1)+this.IhrRain.get(i-1);
			this.getWB().add(wb);
			super.calculation(i);
			
		}
		
		//super.calculation();
		
	}
	
	public void writeDataToFile(){
		
		try{
			File csv=new File("sensor-result.csv");
			BufferedWriter bw=new BufferedWriter(new FileWriter(csv,false));
			bw.write("hour"+","+"Rhr"+","+"Rsum"+","+"Ihrrain"+","+"ET0"+","+"ET"+","+"WB"+","+"SWC"+","+"DELTA"+","+"F"+","+"f"+","+"Q"+","+"InF"+","+"PERC"+","+"Loss"+","+"PerLoss"+","+"Ihr"+","+"wLostHr"+","+
					 "wLostDay"+","+"iLostHr"+","+"iLostDay");
			bw.newLine();
			for(int i =0;i<this.IhrRain.size();i++){
				
				bw.write(this.b.Hour.get(i)+","+this.b.Rhr.get(i)+","+this.rainSum.get(i)+","+this.IhrRain.get(i)+","+b.ET0.get(i)+","+this.getET().get(i)+","+this.getWB().get(i)+","+this.getSWC().get(i+1)+","+this.getDelta().get(i)+","
				        +this.getF().get(i)+","+this.getRateF().get(i)+","+this.getQ().get(i)+","+this.getInF().get(i)+","+this.getPERC().get(i)+","+this.getLoss().get(i)+","+this.getPerLoss().get(i)+","+this.b.Ihr.get(i)
				        +","+this.getwLostHr().get(i)+","+this.getwLostDay().get(i)+","+this.getiLostHr().get(i)+","+this.getiLostDay().get(i));
				
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
	
	
	public static void main(String agrs[]){
		
		
		timeBasedRainSensorCalculation trc=new timeBasedRainSensorCalculation();
		trc.calculation();
		trc.writeDataToFile();
		
		
		
	}
	
	
	
	
	
	
	

}
