import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		Scanner sc = new Scanner(System.in);
		
		int aantalAchtbanen = sc.nextInt();
		
		//variabelen reserverern
		int lengteSpoor = 0;
		String spoorSequentie = null;
		char huidigeRichting = 'O';
		char waarde = 'S';
		
		Blokje vorigBlokje = new Blokje();
		Blokje volgendBlokje = new Blokje();
		
		//voor elke achtbaan
		for(int achtbaanId=1 ; achtbaanId<aantalAchtbanen+1 ; achtbaanId++) {

		//variabelen resetten op beginwaarden
			vorigBlokje.reset();
			volgendBlokje.reset();
			huidigeRichting = 'O';
			waarde = 'S';
			
		//input lezen
			lengteSpoor = sc.nextInt();
			spoorSequentie = sc.nextLine();
			
		//de eerste is een spatie, deze moet nog weg
			spoorSequentie = spoorSequentie.substring(1, spoorSequentie.length());
			
			vorigBlokje.setAlles(-1, 0, 0, 'O', '=');

			ArrayList<Blokje> blokjesLijst = new ArrayList<Blokje>();
			
			//overlopen van de achtbaan
			for(int i=0 ; i<lengteSpoor; i++) {
				waarde = spoorSequentie.charAt(i);
				
				volgendBlokje = volgendBlokje(huidigeRichting,vorigBlokje,waarde);
				
				//voeg het blokje toe aan de arrayList
				blokjesLijst.add(volgendBlokje);

				
				//opt einde
				vorigBlokje = volgendBlokje;
				//enkel van belang als het een up is
				if (waarde == 'U') {vorigBlokje.setSpoorTeken('U');}
				
			}
			
			//nu zitten alle blokjes in de blokjeslijst, de blokjes hebben x, y z coordinaten en het nodige teken
			
			//grenzen zetten van de matrix, verschuiven van de blokjes
			int minX =0;
			int minY =0;
			int maxX =0;
			int maxY =0;
			
			Blokje b = new Blokje();
			
			for(int i=0 ; i< blokjesLijst.size(); i++) {

				b = blokjesLijst.get(i);
				
				if(minX > b.getXco()) {minX = b.getXco();}
				if(maxX < b.getXco()) {maxX = b.getXco();}
				if(minY > b.getYco()) {minY = b.getYco();}
				if(maxY < b.getYco()) {maxY = b.getYco();}
			}
			
			/*System.out.println("minx: "+minX);
			System.out.println("maxx: "+maxX);
			System.out.println("miny: "+minY);
			System.out.println("maxy: "+maxY);
			System.out.println();*/
			
			//alles opschuiven zodat minimumwaarden 0 zijn
			
			//blokjes opschuiven
			for(int i=0 ; i< blokjesLijst.size(); i++) {
				blokjesLijst.get(i).verschuif(Math.abs(minX), Math.abs(minY));
			}
			
			//maximumwaarden opschuiven

			maxX = maxX +Math.abs(minX);
			maxY = maxY +Math.abs(minY);
			minX = 0;
			minY = 0;
			
			/*System.out.println("minx: "+minX);
			System.out.println("maxx: "+maxX);
			System.out.println("miny: "+minY);
			System.out.println("maxy: "+maxY);*/
			
			//arrayList sorteren op blokjes: degene met de laagste Zco eerst
			Collections.sort(blokjesLijst);
			
			/*for(Blokje x: blokjesLijst) {x.print();}
			
			System.out.println("maxX = "+maxX);
			System.out.println("maxY = "+maxY);
			*/
			
			//matrix opstellen
			char [][] matrix = new char[maxX+1][maxY+1];
			
			for(Blokje x: blokjesLijst) {matrix[x.getXco()][x.getYco()] = x.getWaarde();}
			for(int i=0; i<maxX+1; i++) {
				for (int j=0; j<maxY+1; j++) {
					if(matrix[i][j]== '\0') {matrix[i][j]='.';}
				}
			}
			
			for(int y= maxY; y>-1 ; y--) {
				sb.append(achtbaanId+" ");
				for(int x=0; x<maxX+1; x++) {
					//print 1 lijn
					sb.append(matrix[x][y]);
				}
				sb.append('\n');
			}
			
			
			
			
		
			

			
			
			
			
			
			
		}
		System.out.println(sb.toString());
		
		
		
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
			returnBlokje.genereerYcoAdhvVorigBlokje(vorigBlokje);
			returnBlokje.setXenZco(vorigBlokje);
			returnBlokje.setRichtingDraaiRechts(vorigBlokje.getRichting());
			
			//teken ervan bepalen
			returnBlokje.setWaarde('_');
			break;
			
		case 'L':
			returnBlokje.genereerYcoAdhvVorigBlokje(vorigBlokje);
			returnBlokje.setXenZco(vorigBlokje);
			returnBlokje.setRichtingDraaiLinks(vorigBlokje.getRichting());	
			returnBlokje.setWaarde('_');
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
