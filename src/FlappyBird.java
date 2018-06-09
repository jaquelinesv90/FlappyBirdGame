import java.util.Set;
import java.util.ArrayList;
import java.util.Random;


public class FlappyBird implements Jogo{
	
	public double ground_offset = 0;
	public double gvx = 70;
	public int contador = 0;
	public boolean errou = false;
	
	public Passaro passaro;
	public Auxiliar auxiliar;
	public ArrayList<Cano> canos = new ArrayList<>();
	public Random gerador = new Random();
	
	public Timer timer_cano;
	
	public FlappyBird(){
		passaro = new Passaro(35, (getLargura()-112)/2 +24/2);
		timer_cano = new Timer(3, true, addCano());
		auxiliar = new Auxiliar(85, (getLargura()-112)/2 +24/2);//85 define a posição da mensagem na tela
		addCano().executa();
	}
	
	private Acao addCano(){
		return new Acao(){
			public void executa(){
				canos.add(new Cano(getLargura()+10, gerador.nextInt(getAltura()-112-Cano.HOLLESIZE), -gvx));
			}
		};
	}

	public static void main(String[] args) {
		roda();
	}
	
	public static void roda(){
		new Motor(new FlappyBird()); 
	}
	
	public void stop(){
		canos = new ArrayList<>();
		errou = true;
	}
	
	@Override
	public String getTitulo() {
		return "Flappy Bird ";
	}

	@Override
	public int getLargura() {
		return 384;
	}

	@Override
	public int getAltura() {
		return 512;
	}

	public void tique(Set<String> teclas, double dt) {
		ground_offset += dt*gvx;
		ground_offset = ground_offset % 308;
		
		timer_cano.tique(dt);
				
		passaro.atualiza(dt);
		
		if(passaro.y+24>=getAltura()-112){
			stop();
		}else if(passaro.y<=0){
			System.out.println("GAME OVER 2");
		}
		
		for(Cano cano: canos){
			cano.atualiza(dt);///onde faz o pássaro se movimentar
			if(passaro.box.intersecao(cano.boxcima) != 0 || passaro.box.intersecao(cano.boxbaixo)!= 0){
				stop();
			}
		}
		
		if(canos.size() > 0 && canos.get(0).x <=60 ){
			canos.remove(0);
			contador = contador + 1;
			System.out.println("cano removido");
			System.out.println("contador"+ contador);
		}
	}

	public void tecla(String tecla) {
		if(tecla.equals(" ")){
			passaro.flap();
		}
	}

	@Override
	public void desenhar(Tela tela) {
		if(contador >= 6 && contador <= 12){
			tela.imagem("flappy_night.png", 0, 0, 288, 512, 0, 0, 0);		
			tela.imagem("flappy_night.png", 0, 0, 288, 512, 0, 288, 0);	
			tela.imagem("flappy_night.png", 0, 0, 288, 512, 0, 288*2, 0);	
		}else{
			tela.imagem("flappy.png", 0, 0, 288, 512, 0, 0, 0);		
			tela.imagem("flappy.png", 0, 0, 288, 512, 0, 288, 0);	
			tela.imagem("flappy.png", 0, 0, 288, 512, 0, 288*2, 0);	
		}
		
		if(errou == false){
			auxiliar.desenharStart(tela);
			
			for(Cano cano : canos){
				cano.desenha(tela);
			}
					
			tela.imagem("flappy.png", 292, 0, 308, 112, 0, -ground_offset, getAltura()-112);
			tela.imagem("flappy.png", 292, 0, 308, 112, 0, 308  -ground_offset, getAltura()-112);
			tela.imagem("flappy.png", 292, 0, 308, 112, 0, 308*2  -ground_offset, getAltura()-112);
			
			passaro.desenhar(tela);
			
		}else{
			auxiliar.desenharGameOver(tela);
		}
	}
}
