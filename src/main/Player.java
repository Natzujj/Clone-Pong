package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left; // Determina se está se mexendo na direita ou esquerda.
	public int x, y, width, height; // coordenadas e dimensões
	
	// Construtor recebe coordenadas e define o tamanho do objeto
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 25;
		this.height = 5;
	}
	
	/*
	 * O método tick() atualiza a posição do jogador com base na direção em que ele está 
	 * se movendo, direita ou esquerda, e garante que o jogador não saia dos limites da tela.
	 * é verificado se a posição do jogador mais a largura width ultrapassa a largura total 
	 * da janela do jogo, Game.WIDTH. Se isso ocorrer, o jogador é reposicionado para a posição
	 * máxima possível dentro da janela subtraindo a largura do jogador da largura total da janela (x = Game.WIDTH - width).
	 * O mesmo é feito para evitar que o jogador saia para fora da janela pela esquerda (x = 0), caso sua posição seja menor que zero.
	 */
	public void tick() {
		if(right) {
			x++;
		} else if(left) {
			x--;
		}
		
		if(x + width > Game.WIDTH) {
			x = Game.WIDTH - width;
		} else if(x < 0) {
			x = 0;
		}
	}
	
	// Desenha o objeto
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
}
