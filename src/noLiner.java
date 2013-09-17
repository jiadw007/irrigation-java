
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class noLiner {
	
	static double func(double x)                //the ln function
    {  
        return x-3.1929*Math.log(1+x/3.1929)-1.09;  
    }  
    static double dfunc(double x)               //the derivative of func
    {  
        return x/(3.1929+x);  
    }  
    static int newtonMethod(double[] x,int maxcyc,double precision)   
    {  
        double x0,x1;  
        int i=1;  
 
        x0=x[0];				//the initial value of the iterative var i=0 
        while(i<maxcyc)         //the maximum count of the loop  
        {  
            if(dfunc(x0)==0.0)				//if dfunc(x0)=0.0, the func is stable
            {  
                System.out.println("the derivate in the iterative process is 0!");  
                return 0;  
            }  
            x1=x0-func(x0)/dfunc(x0);	//the Newton method calculation
            //System.out.println(x0);
            //System.out.println(x1);
            if(Math.abs(x1-x0)<precision && Math.abs(func(x1))<precision)                                               //达到预设的结束条件   
            {  
                x[0]=x1;                            //x[0] is the result
                return 1;  
            }  
            else                                    //if it does not satisfy the precision condition
            {  
                x0=x1;                          //continue for the next Newton calculation
            }  
            i++;                                 
        }  
        System.out.println("the iterative times exceeds the initial setting, and we do not find the result !");  
        return 0;  
    }
    /**
     * the test function for the Newton iterative method
     * @param args
     */
	public static void main(String args[]){
		
		try { 
			
			File csv = new File("time-base-trial.csv");  

			BufferedReader br = new BufferedReader(new FileReader(csv)); 
			String line = br.readLine(); 
	        String item[]=line.split(",");
			br.readLine(); 
		    for(int i=0;i<item.length;i++){
	        	
	        	
	        	System.out.print(item[i]+" ");
	        	
	        	
	        }
		    System.out.println();
		    boolean flag=true;
			while (flag) { 
		        
				line = br.readLine(); 
		        item=line.split(",");
		        for(int i=0;i<item.length;i++){
		        	
		        	
		        	System.out.print(item[i]+" ");
		        	
		        	
		        }
		        System.out.println();
		    	flag=false;
			} 
			br.close(); 
		} catch (FileNotFoundException e) { 
		      e.printStackTrace(); 
		} catch (IOException e) { 
		      e.printStackTrace(); 
		}
	
       
        
        
        
		
	}
	

}
