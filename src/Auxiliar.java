

//classe para recortar as mensagens de tela
public class Auxiliar {
	
	public double x,y;
	public double vy = 0;
	
	public Auxiliar(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void desenharGameOver(Tela tela){
		tela.imagem("flappy.png", 289, 395, 195, 45, Math.atan(vy/150), x , y);
		tela.imagem("flappy.png", 483, 424, 84, 27, Math.atan(vy/150), 90, 195.75);//imagem de start
	}
	
	public void desenharStart(Tela tela){
		tela.imagem("flappy.png", 294, 342, 195, 45, Math.atan(vy/150), x , y);
	}
	
	public void desenharContador(Tela tela){
		//tela.imagem("flappy.png", 294, 342, 195, 45, Math.atan(vy/150), x , y);
	}

	
	
	
	public boolean calculaTempoExibicaoStart(){
		try {
			new Thread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
}
