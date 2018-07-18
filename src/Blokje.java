
public class Blokje {
	
	private int xco;
	private int yco;
	private int zco;
	private char richting;
	
	//constructoren
	public Blokje() {
		xco=0;
		yco=0;
		zco=0;
		richting = 'O';
	}
	
	public void setXco(int x) {xco = x;}
	public void setYco(int y) {yco = y;}
	public void setZco(int z) {zco = z;}
	public void setRichting(char r) {richting = r;}

	public int getXco() {return xco;}
	public int getYco() {return yco;}
	public int getZco() {return zco;}
	public char getRichting() {return richting;}
	
	
	public Blokje(int xco, int yco, int zco) {
		this.xco = xco;
		this.yco = yco;
		this.zco = zco;
	}

	/**
	 * zet de xco, yco, en zco op 0
	 */
	public void reset() {
		xco = 0;
		yco = 0;
		zco = 0;
	}

	
	
	
	
	
}
