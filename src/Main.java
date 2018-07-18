import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int aantalAchtbanen = sc.nextInt();
		
		//variabelen reserverern
		int lengteSpoor = 0;
		String spoorSequentie = null;
		char huidigeRichting = 'O';
		char waarde = 'S';
		Blokje vorigBlokje = new Blokje();
		Blokje volgendBlokje = new Blokje();
		
		
		int [][] matrix = new int [5][5];
		
		for(int achtbaanId=1 ; achtbaanId<aantalAchtbanen+1 ; achtbaanId++) {
			
			//variabelen resetten op beginwaarden
			vorigBlokje.reset();
			volgendBlokje.reset();
			huidigeRichting = 'O';
			waarde = 'S';
			matrix = new int [5][5];
			
			//input lezen
			lengteSpoor = sc.nextInt();
			spoorSequentie = sc.nextLine();
			
			//de eerste is een spatie, deze moet nog weg
			spoorSequentie = spoorSequentie.substring(1, spoorSequentie.length());
			
			
			//overlopen van de achtbaan
			for(int i=0 ; i<lengteSpoor; i++) {
				
				volgendBlokje = volgendBlokje(huidigeRichting,vorigBlokje,waarde);
				
				//teken blokje in de matrix
				
				//opt einde
				vorigBlokje = volgendBlokje;
				
			}

			
			
			
			
			
			
		}
		
		
		
		//inlezen van de input
		
		
		
	}
	
	public static Blokje volgendBlokje(char huidigeRichting, Blokje vorigBlokje, char waarde) {
		Blokje returnBlokje = new Blokje();
		
		switch(waarde) {
		case 'S':
				returnBlokje.setXco(vorigBlokje.getXco() +1);
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco());
				returnBlokje.setRichting('O');
			break;
			
		case 'V':	//gewoon rechtdoor
			if(huidigeRichting == 'O') {
				returnBlokje.setXco(vorigBlokje.getXco() +1);
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco());
				returnBlokje.setRichting('O');
			}
			else if(huidigeRichting == 'Z') {
				returnBlokje.setXco(vorigBlokje.getXco() -1);
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco());
				returnBlokje.setRichting('Z');
			}
			else if(huidigeRichting == 'N') {
				returnBlokje.setXco(vorigBlokje.getXco());
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco()-1);
				returnBlokje.setRichting('N');
			}
			else if(huidigeRichting == 'W') {
				returnBlokje.setXco(vorigBlokje.getXco());
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco()+1);
				returnBlokje.setRichting('W');
			}
			break;
			
		case 'L':
			break;
			
		case 'D':
			break;
			
		case 'U':
			if(huidigeRichting == 'O') {
				returnBlokje.setXco(vorigBlokje.getXco() +1);
				returnBlokje.setYco(vorigBlokje.getYco()+1);
				returnBlokje.setZco(vorigBlokje.getZco());
				returnBlokje.setRichting('O');
			}
			else if(huidigeRichting == 'Z') {
				returnBlokje.setXco(vorigBlokje.getXco() -1);
				returnBlokje.setYco(vorigBlokje.getYco()+1);
				returnBlokje.setZco(vorigBlokje.getZco());
				returnBlokje.setRichting('Z');
			}
			else if(huidigeRichting == 'N') {
				returnBlokje.setXco(vorigBlokje.getXco());
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco()-1);
				returnBlokje.setRichting('N');
			}
			else if(huidigeRichting == 'W') {
				returnBlokje.setXco(vorigBlokje.getXco());
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco()+1);
				returnBlokje.setRichting('W');
			}
			break;
			
		case 'R':
			break;
			
		default: 
			System.out.println(" we graken in default, oe oeee");
			break;
		
		}
		
		return returnBlokje;
	}

}
