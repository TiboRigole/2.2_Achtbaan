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
		
		//voor elke achtbaan
		for(int achtbaanId=1 ; achtbaanId<aantalAchtbanen+1 ; achtbaanId++) {
		
		//variabelen resetten op beginwaarden
			vorigBlokje.reset();
			volgendBlokje.reset();
			huidigeRichting = 'O';
			waarde = 'S';
			matrix = new int [5][5];	//later nog naar 1,1 wijzigen zodat elke achtbaan er volledig opstaat
			
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
			//volgend blokje:
				//richting is zowieso O
				// waarde met eigen methode
				//xco +1
				//y en zco blijven
			
				returnBlokje.setXco(vorigBlokje.getXco() +1);
				returnBlokje.setYco(vorigBlokje.getYco());
				returnBlokje.setZco(vorigBlokje.getZco());
				returnBlokje.setRichting('O');
				returnBlokje.setWaarde('=');
			break;
			
		case 'V':	
				//gewoon rechtdoor
				//richting blijft deze van de vorige
				//adhv de richting van de vorige, x +-1 OF z+-1
			
			// yco +1 als vorig blokje een omhoog was
			//deze 3 nog bundelen in 1 verzamelmethode, we gebruiken ze namelijk 3x
			
			returnBlokje.genereerYcoAdhvVorigBlokje(vorigBlokje);			
			returnBlokje.setXenZco(vorigBlokje);
			returnBlokje.setRichting(vorigBlokje.getRichting());
			//niet zeker als het nu wel is aangepast
			
			//x,y en zco zijn nu ingevuld
			//richting is ook ingevuld
			
			//teken ervan bepalen
			returnBlokje.setWaarde('_');
			break;
			
		case 'L':
			
			break;
			
		case 'D':
			//yco moet moet met 1 verlaagd worden
			returnBlokje.setYco(vorigBlokje.getYco()-1);
			returnBlokje.setXenZco(vorigBlokje);
			returnBlokje.setRichting(vorigBlokje.getRichting());
			//x,y en zco zijn nu ingevuld
			//richting is ook ingevuld
			
			//teken ervan bepalen
			if(returnBlokje.getRichting()=='N' || returnBlokje.getRichting()=='Z') {
				returnBlokje.setWaarde('#');
			}
			else if(returnBlokje.getRichting()=='O') {returnBlokje.setWaarde('\\');}
			else if(returnBlokje.getRichting()=='W') {returnBlokje.setWaarde('/');}
			
			break;
			
		case 'U':
			// yco moet nog niet verhoogd woren
			returnBlokje.setYco(vorigBlokje.getYco());
			returnBlokje.setXenZco(vorigBlokje);
			returnBlokje.setRichting(vorigBlokje.getRichting());
			//x,y en zco zijn nu ingevuld
			//richting is ook ingevuld
			
			//teken ervan bepalen
			if(returnBlokje.getRichting()=='N' || returnBlokje.getRichting()=='Z') {
				returnBlokje.setWaarde('#');
			}
			else if(returnBlokje.getRichting()=='O') {returnBlokje.setWaarde('/');}
			else if(returnBlokje.getRichting()=='W') {returnBlokje.setWaarde('\\');}
			
			//adhv de richting van het vorige blokje kan je weten welke waarden er verhoogd moeten worden
			break;
			
		case 'R':
			break;
			
		default: 
			System.out.println(" we graken in default, oe oeee");
			break;
		
		}
		
		return returnBlokje;
	}

	/**
	 * als het vorig blokje een U was, dan is de Yco +1
	 * anders is het dezelfde yco waarde als het vorige blokje
	 * 
	 * @param vorigBlokje,
	 * @returnt de yco van het volgende blokje
	 */

}
