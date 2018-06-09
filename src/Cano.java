
public class Cano {
	public double x,y;
	//public int stage_height;
	public double vxcano;
	public static int HOLLESIZE = 120; 
	public Hitbox boxcima;
	public Hitbox boxbaixo;
	
	public Cano(double x,double y, double vx){
		this.x = x;
		this.vxcano = vx;
		//this.stage_height = sh;
		this.y = y;
		
		boxcima = new Hitbox(x,y-270,x+52,y);
		boxbaixo = new Hitbox(x,y+Cano.HOLLESIZE,x+52,y+Cano.HOLLESIZE+242);
		
	}

	public void atualiza(double dt){
		x  += vxcano*dt;
		boxcima.mover(vxcano*dt, 0);
		boxbaixo.mover(vxcano*dt, 0);
	}
	
	public void desenha(Tela t){
		t.imagem("flappy.png", 604, 0, 52, 270, 0, x, y-270);//cano de cima
		
		t.imagem("flappy.png", 660, 0, 52, 242, 0, x, y + Cano.HOLLESIZE);//cano de baixo
		
	}
	
}
