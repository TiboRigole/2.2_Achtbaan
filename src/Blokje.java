
public class Blokje {
	
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
		System.out.println(+ this.xco +","+ this.yco +","+this.zco +" ;;;"+this.waarde);
		
	}

	public void setSpoorTeken(char c) {
		this.spoorTeken = c;
		
	}

	
	
	
	
	
}
