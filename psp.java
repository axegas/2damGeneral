
import java.net.InetAddress;
import java.net.UnknownHostException;

public class psp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
		
        try{ //hola hola
	
			InetAddress[] addresses = new InetAddress[4];
			addresses[0] = InetAddress.getLoopbackAddress();
			addresses[1] = InetAddress.getByName("iesserpis.org"); 
			addresses[2] = InetAddress.getByName("PC15");
			addresses[3] = InetAddress.getByName("224.102.26.10");   
			   
			
			for (InetAddress adress : addresses) {
				
				if (adress.isLoopbackAddress()) {
					System.out.println("Nombre: " + adress.getHostName() + " té una adreça loopback");
				}else{
					System.out.println("Nombre: " + adress.getHostName() + " no té una adreça loopback");
				}
			}
		}catch(UnknownHostException e){
            System.out.println("exception");
        }
    }

    

}
