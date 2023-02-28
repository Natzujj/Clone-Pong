package main;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x, y; // posição do inimigo
	public int  width, height; // dimensões
	
	public Enemy(int x, int y) { // construtor do inimigo, recebe a posição x e y como parâmetro, e define o tamanho do objeto
		this.x = x;
		this.y = y;
		this.width = 25;
		this.height = 5;
	}
	
	public void tick() { // atualiza a posição do inimigo
		x += (Game.ball.x - x - 6) *0.07; // calcula a nova posição do inimigo baseado na posição da bola e a velocidade em que ele se move
				
		if(x + width > Game.WIDTH) { // verifica se o inimigo ultrapassou o limite da tela na direita e esquerda
			x = Game.WIDTH - width;
		} else if(x < 0) {
			x = 0;
		}
		
	}
	 
	public void render(Graphics g) { // desenha o inimigo na tela
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
	}
}
