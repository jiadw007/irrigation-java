


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class timeBasedCalculation {
	
	/*constructor with user input: district, soiltype, rd, area*/
	
	protected baseData b;
	private int startIrrigationHour=1;
	private int lastIrrigationHour=8784;
	private String soilType="sandy loam";	//user input, default="sandy loam"
	private String district="South Florida";
	private Double area=1.0;
	private Double RD=8.0;					//user input, default=30.0
	private ArrayList<Double> WB;
	private ArrayList<Double> SWC;			//from calculation function 
	private ArrayList<Double> ET;			//from calculation function
	private ArrayList<Double> delta;		//from calculation function 
	private ArrayList<Double> F;			//from calculation function 
	private ArrayList<Double> rateF;			//from calculation function 
	private ArrayList<Double> PERC;			//from calculation function 
	private ArrayList<Double> Q;			//from calculation function 
	private ArrayList<Double> InF;			//from calculation function 
	private ArrayList<Double> Loss;			//from calculation function 
	private ArrayList<Double> PerLoss;		//from calculation function 
	private ArrayList<Double> wLostHr;
	private ArrayList<Double> wLostDay;
	private ArrayList<Double> iLostHr;
	private ArrayList<Double> iLostDay;
	
	
	
	

	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public Double getRD() {
		return RD;
	}


	public void setRD(Double rD) {
		RD = rD;
	}


	public String getSoilType() {
		return soilType;
	}


	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}

	
	
	public int getStartIrrigationHour() {
		return startIrrigationHour;
	}


	public void setStartIrrigationHour(int startIrrigationHour) {
		this.startIrrigationHour = startIrrigationHour;
	}


	public int getLastIrrigationHour() {
		return lastIrrigationHour;
	}


	public void setLastIrrigationHour(int lastIrrigationHour) {
		this.lastIrrigationHour = lastIrrigationHour;
	}

	
	public ArrayList<Double> getWB() {
		return WB;
	}


	public void setWB(ArrayList<Double> wB) {
		WB = wB;
	}
	
	

	public ArrayList<Double> getSWC() {
		return SWC;
	}


	public void setSWC(ArrayList<Double> sWC) {
		SWC = sWC;
	}


	public ArrayList<Double> getET() {
		return ET;
	}


	public void setET(ArrayList<Double> eT) {
		ET = eT;
	}


	public ArrayList<Double> getDelta() {
		return delta;
	}


	public void setDelta(ArrayList<Double> delta) {
		this.delta = delta;
	}


	public ArrayList<Double> getF() {
		return F;
	}


	public void setF(ArrayList<Double> f) {
		F = f;
	}


	public ArrayList<Double> getRateF() {
		return rateF;
	}


	public void setRateF(ArrayList<Double> rateF) {
		this.rateF = rateF;
	}


	public ArrayList<Double> getPERC() {
		return PERC;
	}


	public void setPERC(ArrayList<Double> pERC) {
		PERC = pERC;
	}


	public ArrayList<Double> getQ() {
		return Q;
	}


	public void setQ(ArrayList<Double> q) {
		Q = q;
	}


	public ArrayList<Double> getInF() {
		return InF;
	}


	public void setInF(ArrayList<Double> inF) {
		InF = inF;
	}


	public ArrayList<Double> getLoss() {
		return Loss;
	}


	public void setLoss(ArrayList<Double> loss) {
		Loss = loss;
	}


	public ArrayList<Double> getPerLoss() {
		return PerLoss;
	}


	public void setPerLoss(ArrayList<Double> perLoss) {
		PerLoss = perLoss;
	}
	
	

	public ArrayList<Double> getwLostHr() {
		return wLostHr;
	}


	public void setwLostHr(ArrayList<Double> wLostHr) {
		this.wLostHr = wLostHr;
	}


	public ArrayList<Double> getwLostDay() {
		return wLostDay;
	}


	public void setwLostDay(ArrayList<Double> wLostDay) {
		this.wLostDay = wLostDay;
	}


	public ArrayList<Double> getiLostHr() {
		return iLostHr;
	}


	public void setiLostHr(ArrayList<Double> iLostHr) {
		this.iLostHr = iLostHr;
	}


	public ArrayList<Double> getiLostDay() {
		return iLostDay;
	}


	public void setiLostDay(ArrayList<Double> iLostDay) {
		this.iLostDay = iLostDay;
	}


	public timeBasedCalculation() {
		b=new baseData();
		WB=new ArrayList<Double>();
		SWC=new ArrayList<Double>();			//from calculation function 
		ET=new ArrayList<Double>();			//from calculation function
		delta=new ArrayList<Double>();		//from calculation function 
		F=new ArrayList<Double>();			//from calculation function 
		rateF=new ArrayList<Double>();			//from calculation function 
		PERC=new ArrayList<Double>();			//from calculation function 
		Q=new ArrayList<Double>();			//from calculation function 
		InF=new ArrayList<Double>();			//from calculation function 
		Loss=new ArrayList<Double>();			//from calculation function 
		PerLoss=new ArrayList<Double>();		//from calculation function
		wLostHr=new ArrayList<Double>();
		wLostDay=new ArrayList<Double>();
		iLostHr=new ArrayList<Double>();
		iLostDay=new ArrayList<Double>();
		for(int i =0;i<b.Rhr.size();i++){
			
			double wb=b.Rhr.get(i)+b.Ihr.get(i);
			this.WB.add(wb);
			
		}
		HashMap<String, Double> SOIL=b.soil.get(soilType);		//get the properties for the designated soil
		Double swc0=0.75*SOIL.get("FC")*this.RD;
		this.SWC.add(swc0);			//get the SWC0 value
		//System.out.println(this.SWC.lastIndexOf(swc0));
	}


	public timeBasedCalculation(String soiltype,double rd) {
		
		this.soilType=soiltype;
		this.RD=rd;
		b=new baseData();
		WB=new ArrayList<Double>();
		SWC=new ArrayList<Double>();			//from calculation function 
		ET=new ArrayList<Double>();			//from calculation function
		delta=new ArrayList<Double>();		//from calculation function 
		F=new ArrayList<Double>();			//from calculation function 
		rateF=new ArrayList<Double>();			//from calculation function 
		PERC=new ArrayList<Double>();			//from calculation function 
		Q=new ArrayList<Double>();			//from calculation function 
		InF=new ArrayList<Double>();			//from calculation function 
		Loss=new ArrayList<Double>();			//from calculation function 
		PerLoss=new ArrayList<Double>();		//from calculation function
		wLostHr=new ArrayList<Double>();
		wLostDay=new ArrayList<Double>();
		iLostHr=new ArrayList<Double>();
		iLostDay=new ArrayList<Double>();
		
		for(int i =0;i<b.Rhr.size();i++){
			
			double wb=b.Rhr.get(i)+b.Ihr.get(i);
			this.WB.add(wb);
			
		}
		HashMap<String, Double> SOIL=b.soil.get(soilType);		//get the properties for the designated soil
		Double swc0=0.75*SOIL.get("FC")*this.RD;
		this.SWC.add(swc0);			//get the SWC0 value
	}
	
	
	
	public void writeDataToFile(){
		
		
		try{
			File csv=new File("time-base-result.csv");
			BufferedWriter bw=new BufferedWriter(new FileWriter(csv,false));
			bw.write("hour"+","+"ET0"+","+"ET"+","+"WB"+","+"SWC"+","+"DELTA"+","+"F"+","+"f"+","+
			         "Q"+","+"InF"+","+"PERC"+","+"Loss"+","+"PerLoss"+","+"Rhr"+","+"wLostHr"+","+
					 "wLostDay"+","+"iLostHr"+","+"iLostDay");
			bw.newLine();
			for(int i =0;i<this.SWC.size()-1;i++){
				
				bw.write(this.b.Hour.get(i)+","+b.ET0.get(i)+","+this.ET.get(i)+","+this.WB.get(i)+","+this.SWC.get(i+1)+","+this.delta.get(i)+","
				        +this.F.get(i)+","+this.rateF.get(i)+","+this.Q.get(i)+","
				        +this.InF.get(i)+","+this.PERC.get(i)+","+this.Loss.get(i)+","+this.PerLoss.get(i)+","+this.b.Rhr.get(i)+","+this.wLostHr.get(i)+","
				        +this.wLostDay.get(i)+","+this.iLostHr.get(i)+","+this.iLostDay.get(i));
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
	public void calculation(){
		
		HashMap<String, Double> SOIL=b.soil.get(soilType);
					
		for(int i=this.startIrrigationHour;i<=this.lastIrrigationHour;i++){
			
			if(this.WB.get(i-1)>0){  //calculate the rate(f),Q and PERC
				//System.out.println(i);
				double delta=SOIL.get("theta")-this.SWC.get(i-1)/this.RD;  //get the value of delta for equation 2
				this.delta.add(i-1,delta);
				double psi=SOIL.get("psi");		//get the psi property of the soil
				double k=SOIL.get("K");			//get the K property of the soil
				newtonMethod nm=new newtonMethod(psi,delta,k);	
				//double F=0.0;
				if(nm.calculationMethod()){
					
					double F=nm.getResult();			//get the value of F for equation 1
					//System.out.println("F: "+F);
					this.F.add(i-1,F);
					
				}else{
					
					System.out.println("error calculation for F");
					
				}
				//calculation for the rate(f)
				
				double f=k*(1+(psi*delta/this.F.get(i-1)));
				this.rateF.add(i-1,f);
				
				//calculation for the Q
				if(this.WB.get(i-1)>f){
					double Q=this.WB.get(i-1)-f*1;
					this.Q.add(i-1,Q);
					this.InF.add(i-1,f*1);	
				}else{
					
					this.Q.add(i-1,0.0);
					this.InF.add(this.WB.get(i-1));
					
				}
				//calculation for the PERC
				double perc=this.SWC.get(i-1)+this.InF.get(i-1)-SOIL.get("FC")*this.RD;
				if(perc>0){
					
					this.PERC.add(perc);
					
				}else{
					
					this.PERC.add(0.0);
				}
				
				
				
			}else{
				this.delta.add(i-1,-1.0);		//we have no result for this property
				this.F.add(i-1,-1.0);		//we have no result for this property
				this.rateF.add(i-1,-1.0);		//we have no result for this property
				this.Q.add(i-1,0.0);
				this.PERC.add(i-1,0.0);
				this.InF.add(i-1,0.0);
				
				
			}
			//calculate the ET
			double kc=b.Kc.get(this.district).get(b.Month.get(i-1));
			double et=b.ET0.get(i-1);
			this.ET.add(et*kc);
			
			//calculate SWC
			
			if(this.PERC.get(i-1)>0){
				
				this.SWC.add(i,SOIL.get("FC")*this.RD);
			}else if(this.PERC.get(i-1)==0&&(this.SWC.get(i-1)-this.ET.get(i-1)+this.InF.get(i-1))<SOIL.get("WP")*this.RD*0.1){
				
				this.SWC.add(i,SOIL.get("WP")*this.RD*0.1);
				
				
			}else{
				
				this.SWC.add(i,this.SWC.get(i-1)+this.InF.get(i-1)-this.ET.get(i-1));
				
			}
			
			
			//calculate the water loss
			double wloss=(this.Q.get(i-1)+this.PERC.get(i-1)-b.Rhr.get(i-1))*this.area;
			this.Loss.add(i-1,Math.abs(wloss));
			double iloss=(this.Q.get(i-1)+this.PERC.get(i-1)-this.b.Rhr.get(i-1))/this.b.Ihr.get(i-1);
			this.PerLoss.add(i-1,Math.abs(iloss));
			//caculate the wLostHr
			if(wloss>0){
				this.wLostHr.add(i-1,wloss);
				
			}else{
				this.wLostHr.add(i-1, 0.0);
			}
			
			
			
			//calculate the iLostHr
			if(this.b.Ihr.get(i-1)>0){
				
				if(iloss>0){
					
					this.iLostHr.add(iloss);
				}else{
					
					this.iLostHr.add(0.0);
				}
			}else{
				this.iLostHr.add(0.0);
			}
			
			//calculate the wLostDay and iLostDay
			if(i>24){
						
				if(Integer.parseInt(this.b.Hour.get(i-1))==0){
					this.wLostDay.add(0.0);
					this.iLostDay.add(0.0);
							
				}else if(Integer.parseInt(this.b.Hour.get(i-1))%23==0){
					System.out.println(i);		
					double wsum=0;
					double isum=0;
					for(int j =i-23;j<=i;j++){
								
						wsum+=this.wLostHr.get(j-1);
						isum+=this.iLostHr.get(j-1);
					}
					this.wLostDay.add(wsum);
					this.iLostDay.add(isum);
				}else{
					this.wLostDay.add(0.0);
					this.iLostDay.add(0.0);
							
				}
						
			}else{
				this.wLostDay.add(0.0);
				this.iLostDay.add(0.0);
			}
			
		}
		
	}
	public void calculation(int i ){
		
		HashMap<String, Double> SOIL=b.soil.get(soilType);
		
		if(this.WB.get(i-1)>0){  //calculate the rate(f),Q and PERC
			//System.out.println(i);
			double delta=SOIL.get("theta")-this.SWC.get(i-1)/this.RD;  //get the value of delta for equation 2
			this.delta.add(i-1,delta);
			double psi=SOIL.get("psi");		//get the psi property of the soil
			double k=SOIL.get("K");			//get the K property of the soil
			newtonMethod nm=new newtonMethod(psi,delta,k);	
			//double F=0.0;
			if(nm.calculationMethod()){
				
				double F=nm.getResult();			//get the value of F for equation 1
				//System.out.println("F: "+F);
				this.F.add(i-1,F);
				
			}else{
				
				System.out.println("error calculation for F");
				
			}
			//calculation for the rate(f)
			
			double f=k*(1+(psi*delta/this.F.get(i-1)));
			this.rateF.add(i-1,f);
			
			//calculation for the Q
			if(this.WB.get(i-1)>f){
				double Q=this.WB.get(i-1)-f*1;
				this.Q.add(i-1,Q);
				this.InF.add(i-1,f*1);	
			}else{
				
				this.Q.add(i-1,0.0);
				this.InF.add(this.WB.get(i-1));
				
			}
			//calculation for the PERC
			double perc=this.SWC.get(i-1)+this.InF.get(i-1)-SOIL.get("FC")*this.RD;
			if(perc>0){
				
				this.PERC.add(perc);
				
			}else{
				
				this.PERC.add(0.0);
			}
			
			
			
		}else{
			this.delta.add(i-1,-1.0);		//we have no result for this property
			this.F.add(i-1,-1.0);		//we have no result for this property
			this.rateF.add(i-1,-1.0);		//we have no result for this property
			this.Q.add(i-1,0.0);
			this.PERC.add(i-1,0.0);
			this.InF.add(i-1,0.0);
			
			
		}
		//calculate the ET
		//double kc=b.Kc.get(this.district).get(b.Month.get(i-1));
		//double et=b.ET0.get(i-1);
		//this.ET.add(et*kc);
		
		//calculate SWC
		
		if(this.PERC.get(i-1)>0){
			
			this.SWC.add(i,SOIL.get("FC")*this.RD);
		}else if(this.PERC.get(i-1)==0&&(this.SWC.get(i-1)-this.ET.get(i-1)+this.InF.get(i-1))<SOIL.get("WP")*this.RD*0.1){
			
			this.SWC.add(i,SOIL.get("WP")*this.RD*0.1);
			
			
		}else{
			
			this.SWC.add(i,this.SWC.get(i-1)+this.InF.get(i-1)-this.ET.get(i-1));
			
		}
		
		
		//calculate the water loss
		double wloss=(this.Q.get(i-1)+this.PERC.get(i-1)-b.Rhr.get(i-1))*this.area;
		this.Loss.add(i-1,Math.abs(wloss));
		double iloss=(this.Q.get(i-1)+this.PERC.get(i-1)-this.b.Rhr.get(i-1))/this.b.Ihr.get(i-1);
		this.PerLoss.add(i-1,Math.abs(iloss));
		
		//caculate the wLostHr
		if(wloss>0){
			this.wLostHr.add(wloss);
			
		}else{
			this.wLostHr.add(0.0);
		}
		
		
		
		//calculate the iLostHr
		if(this.b.Ihr.get(i-1)>0){
			
			if(iloss>0){
				
				this.iLostHr.add(iloss);
			}else{
				
				this.iLostHr.add(0.0);
			}
		}else{
			this.iLostHr.add(0.0);
		}
		
		//calculate the wLostDay and iLostDay
		if(i>24){
					
			if(Integer.parseInt(this.b.Hour.get(i-1))==0){
				this.wLostDay.add(0.0);
				this.iLostDay.add(0.0);
						
			}else if(Integer.parseInt(this.b.Hour.get(i-1))%23==0){
						
				double wsum=0;
				double isum=0;
				for(int j =i-23;j<=i;j++){
							
					wsum+=this.wLostHr.get(j-1);
					isum+=this.iLostHr.get(j-1);
				}
				this.wLostDay.add(wsum);
				this.iLostDay.add(isum);
			}else{
				this.wLostDay.add(0.0);
				this.iLostDay.add(0.0);
						
			}
					
		}else{
			this.wLostDay.add(0.0);
			this.iLostDay.add(0.0);
		}
		
	}

	
	public static void main(String args[]){
		
		timeBasedCalculation tc=new timeBasedCalculation();
		tc.calculation();
		//System.out.println(tc.getiLostDay().size());
		//System.out.println(tc.getSWC().size()-1);
		tc.writeDataToFile();
		
	}
	
	

}
