

public class Main{
	
	private static int vector[] = {1,2,3,4,5,6,7,8};
	private static int resultado = 0;
	
	
	public static void main(String[] args) throws Exception{
		
		Sumador s1 = new Sumador(vector,0,2);
		Sumador s2 = new Sumador(vector,2,4);
		Sumador s3 = new Sumador(vector,4,6);
		Sumador s4 = new Sumador(vector,6,8);
		
		Thread t1 = new Thread(s1); //3
		Thread t2 = new Thread(s2); //7
		Thread t3 = new Thread(s3); //11
		Thread t4 = new Thread(s4); //15
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		
		/*
		System.out.println("Resultado s1: " + s1.getResultado());
		System.out.println("Resultado s2: " + s2.getResultado());
		System.out.println("Resultado s3: " + s3.getResultado());
		System.out.println("Resultado s4: " + s4.getResultado());
		*/
		
		
		System.out.println(s1.getResultado()  + s3.getResultado()+ s2.getResultado() + s4.getResultado());
		
		
    }
	
}

class Sumador implements Runnable{
	
	private int v[];
	private int a;
	private int b;
	private int res = 0;
	
	public Sumador(int[] v, int a, int b){
		this.v = v;
		this.a = a;
		this.b = b;		
	}
	
	public void run(){
		
		for(int i=a;i<b;i++){
			res += v[i]; 
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				System.out.println(e);
			}
		}	

		//System.out.println(Thread.currentThread().getName() + ". Resultado: " + res);
	}
	
	public int getResultado(){
		return res;
	}
	
}


