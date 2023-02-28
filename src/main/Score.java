package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
	private int playerScore;
	private int enemyScore;

	public Score() {
		playerScore = 0;
		enemyScore = 0;
	}

	public void playerScored() { // Adiciona ponto ao jogador
		playerScore++;
	}

	public void enemyScored() { // Adiciona ponto ao inimigo
		enemyScore++;
	}

	public void render(Graphics g) { // Desenha o texto escolhendo tamanho, letra e posição.
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("Player: " + playerScore, 0, 80); // posição x e y
		g.drawString("Enemy: " + enemyScore, 0, 40); // posição x e y
	}
}
