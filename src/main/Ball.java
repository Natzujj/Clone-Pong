package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x, y, dx, dy;
	public double speed = 1.7;
	public int  width, height;
	
	public Ball(int x, int y) { // Inicializa a posição da bola e o tamanho
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 5;
		
		int angle = new Random().nextInt(75) + 45; // Gera um ângulo aleatório entre 45 e 120 graus
		
		this.dx = Math.cos(Math.toRadians(angle)); // Calcula as componentes x e y da direção da bola
		this.dy = Math.sin(Math.toRadians(angle)); // Calcula as componentes x e y da direção da bola
	}
	
	public void init() { // Inicializa a bola na posição central da tela
	    x = Game.WIDTH / 2 - width / 2;
	    y = Game.HEIGHT / 2 - height / 2;
	    
	    int angle = new Random().nextInt(75) + 45; // Gera um ângulo aleatório entre 45 e 120 graus
	    dx = Math.cos(Math.toRadians(angle));
	    dy = Math.sin(Math.toRadians(angle));
	    
	    if (new Random().nextBoolean()) dx *= -1; // Inverte a direção horizontal da bola com 50% de probabilidade	        
	    
	    speed = 1.7; // Define a velocidade da bola
	}
	
	public void tick() { // Atualiza a posição da bola	
		if(x + (dx * speed) + width >= Game.WIDTH ) { // Verifica se a bola colidiu com as paredes laterais
			dx *= - 1;
		} else if(x + (dx * speed) < 0) {
			dx *= - 1;
		}		
		
		if(y >= Game.HEIGHT) { // Verifica se a bola saiu pela parte inferior ou superior da tela
			//Ponto do Inimigo;			
			init();
			Game.score.enemyScored(); //Adiciona o ponto do inimigo ao reiniciar a bola
			return;
		} else if(y < 0) {
			//Ponto do Jogador;
			init();
			Game.score.playerScored();
			return;
		}
		
		// Verifica se a bola colidiu com o jogador ou com o inimigo
		Rectangle bounds = new Rectangle((int)(x + (dx*speed)), (int)(y + (dy *speed)), width, height);		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) { // Gera um ângulo aleatório entre 45 e 120 graus ao bater no jogador
			int angle = new Random().nextInt(75) + 45;	
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy > 0) dy *= -1;					
			
		} else if(bounds.intersects(boundsEnemy)) { // Gera um ângulo aleatório entre 45 e 120 graus ao bater no inimigo
			int angle = new Random().nextInt(75) + 45;		
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy > 0) dy *= -1;	
			dy *= -1;
		}
		
		x += dx * speed;
		y += dy * speed;	
	}
	
	public void render(Graphics g) { //Desenha o objeto
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, width, height);
	}
	
}
