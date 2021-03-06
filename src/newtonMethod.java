
public class newtonMethod {
	
	private double psi;
	private double delta;
	private double K;
	private double precision=0.00001;     //the calculation precision
	private int maxcyc=1000;			  //the maximum times for the loop
	private double[] x={8.0};			  // the initial value
	private double result;				  // the result
	public newtonMethod(double psi,double delta,double K){
		this.psi=psi;
		this.delta=delta;
		this.K=K;
		
	}
	
	public double getResult() {
		return result;
	}




	public void setResult(double result) {
		this.result = result;
	}




	public double func(double x)                //the ln function
    {  
        return x-this.psi*this.delta*Math.log(1+x/(this.psi*this.delta))-this.K;  
    }  
    public double dfunc(double x)               //the derivative of func
    {  
        return x/(this.psi*this.delta+x);  
    }  
    public boolean calculationMethod()   
    {  
        double x0,x1;  
        int i=1;  
 
        x0=this.x[0];				//the initial value of the iterative var i=0 
        while(i<this.maxcyc)         //the maximum count of the loop  
        {  
            if(dfunc(x0)==0.0)				//if dfunc(x0)=0.0, the func is stable
            {  
                System.out.println("the derivate in the iterative process is 0!");  
                return false;  
            }  
            x1=x0-func(x0)/dfunc(x0);	//the Newton method calculation
            //System.out.println(x0);
            //System.out.println(x1);
            if(Math.abs(x1-x0)<precision && Math.abs(func(x1))<precision)                                               //达到预设的结束条件   
            {  
                this.setResult(x1);                            //x[0] is the result
                //System.out.println("root is :"+this.result);
                return true;  
            }  
            else                                    //if it does not satisfy the precision condition
            {  
                x0=x1;                          //continue for the next Newton calculation
            }  
            i++;                                 
        }  
        System.out.println("the iterative times exceeds the initial setting, and we do not find the result !");  
        return false;
    }

}
