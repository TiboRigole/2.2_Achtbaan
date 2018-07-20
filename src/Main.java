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
			Collections.sort(blokjesLijst);	//vergemakkelijkt het printen
			
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
			
		//zorgen dat de output pas verschijnt bij een enter
		if(achtbaanId == aantalAchtbanen) {String a = sc.nextLine();}
			
		//1 achtbaan is afgewerkt
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
			returnBlokje.genereerYcoAdhvVorigBlokje(vorigBlokje);
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

}

class Blokje implements Comparable {
	
	private int xco;
	private int yco;
	private int zco;
	private char richting;
	private char waarde;
	private char spoorTeken;
	
	//constructoren
	public Blokje() {
		xco=0;
		yco=0;
		zco=0;
		richting = 'O';
	}
	
	public Blokje(int xco, int yco, int zco, char richting, char waarde) {
		this.xco = xco;
		this.yco = yco;
		this.zco = zco;
		this.richting= richting;
		this.waarde = waarde;
	}
	
	//setters
	public void setXco(int x) {xco = x;}
	public void setYco(int y) {yco = y;}
	public void setZco(int z) {zco = z;}
	public void setRichting(char r) {richting = r;}
	public void setWaarde(char w) {waarde = w;}
	
	public void setAlles(int x, int y, int z, char richting, char waarde) {
		xco = x;
		yco = y;
		zco = z;
		this.richting = richting;
		this.waarde = waarde;
	}
	
	//getters
	public int getXco() {return xco;}
	public int getYco() {return yco;}
	public int getZco() {return zco;}
	public char getRichting() {return richting;}
	public char getWaarde() {return waarde;}
	private char getSpoorTeken() {return spoorTeken;}
	


	/**
	 * zet de xco, yco, en zco op 0
	 * zet de characters naar de beginorientatie en het stationwaarde
	 */
	public void reset() {
		xco = 0;
		yco = 0;
		zco = 0;
		richting = 'O';
		waarde = 'S';
	}
	
	// adhv de huidige richting kijken welke waarde +-1 (x of z)
	public void setXenZco(Blokje vorigBlokje) {
		
		// oost ? x+1 , y ongewijzigd
		if(vorigBlokje.getRichting()=='O') {
			this.setXco(vorigBlokje.getXco()+1);
			this.setZco(vorigBlokje.getZco());
		}
		
		//west ? x-1 , y ongewijzigd
		else if(vorigBlokje.getRichting()=='W') {
			this.setXco(vorigBlokje.getXco()-1);
			this.setZco(vorigBlokje.getZco());
		}
		
		//noord ? z-1, x ongewijzigd
		else if(vorigBlokje.getRichting()=='N') {
			this.setXco(vorigBlokje.getXco());
			this.setZco(vorigBlokje.getZco()-1);
		}
		
		//zuid ? z+1, x ongewijzigd
		else if(vorigBlokje.getRichting()=='Z') {
			this.setXco(vorigBlokje.getXco());
			this.setZco(vorigBlokje.getZco()+1);
		}
		
	}

	public void genereerYcoAdhvVorigBlokje(Blokje vorigBlokje) {
		if(vorigBlokje.getSpoorTeken()=='U') {
			this.setYco(vorigBlokje.getYco()+1);
		}
		
		else {
			this.setYco(vorigBlokje.getYco());	
		}
		
	}
	
	public void setRichtingDraaiRechts(char vorigBlokjeRichting) {
		if(vorigBlokjeRichting=='N') {this.setRichting('O');}
		if(vorigBlokjeRichting=='O') {this.setRichting('Z');}
		if(vorigBlokjeRichting=='Z') {this.setRichting('W');}
		if(vorigBlokjeRichting=='W') {this.setRichting('N');}
	}

	public void setRichtingDraaiLinks(char vorigBlokjeRichting) {
		if(vorigBlokjeRichting=='N') {this.setRichting('W');}
		if(vorigBlokjeRichting=='O') {this.setRichting('N');}
		if(vorigBlokjeRichting=='Z') {this.setRichting('O');}
		if(vorigBlokjeRichting=='W') {this.setRichting('Z');}
	}

	public void print() {
		System.out.println(this.xco +","+ this.yco +","+this.zco +" ;;;"+this.richting);
		
	}

	public void setSpoorTeken(char c) {
		this.spoorTeken = c;
		
	}

	public void verschuif(int minX, int minY) {
		this.xco = this.xco+minX;
		this.yco = this.yco+minY;
		
	}
	//sorteren: laagste z waarden eerst

	@Override
	public int compareTo(Object tweedeBlokje) {
		
		int compareage=((Blokje)tweedeBlokje).getZco();
		return this.zco-compareage;
	}
	
}


