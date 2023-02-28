package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	
	// Define as constantes para a largura, altura e escala da janela jogo
	public static int WIDTH = 120, HEIGHT = 120, SCALE = 3;
	
	// Instância as classes Player, Enemy, Ball e Score
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public static Score score;
	
	// Cria uma imagem de buffer para o jogo
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Game() {
		// Define a dimensão do canvas(janela) do jogo com base nas constantes definidas acima
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		// Adiciona um KeyListener para o jogo, faz com que "leia o teclado".
		this.addKeyListener(this);
		
		// Instância as classes Player, Enemy, Ball e Score
		player = new Player(50, HEIGHT - 5);
		enemy = new Enemy(50, 0);
		ball = new Ball(60, HEIGHT/2 - 1);		
		score = new Score();
	}

	public static void main(String[] args) {
		Game game = new Game();		
		/* Cria uma nova janela para o jogo, 
		 * Define que a janela não pode ser redimensionada,
		 * Define que o programa deve ser encerrado quando a janela for fechada,
		 * Faz com que o frame se ajuste ao tamanho do canvas do jogo,
		 * Centraliza o frame na tela,
		 * Torna o frame visível,
		 * Inicia uma nova Thread para o jogo
		 */
		JFrame frame = new JFrame("Pong!");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	// Atualiza as informações do jogo
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	// Renderiza o jogo
	public void render() {
		// Cria um novo buffer strategy
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);	
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		score.render(g);
		
		// Desenha a imagem de buffer no canvas do jogo
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		// Mostra o buffer strategy na tela, sem ele o jogo não é renderizado
		bs.show();
	}
	
	// Método responsável por executar o jogo indefinidamente
	@Override
	public void run() {
		while(true) {
			requestFocus(); // Garante que o canvas está com o foco, possibilitando a interação do usuário com as teclas
			tick(); // Executa a lógica do jogo.
			render(); // Executa os graficos
			try {
				Thread.sleep(1000/60); // Faz uma pausa para manter a taxa de atualização do jogo em 60 FPS
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	// Método responsável por detectar a tecla pressionada
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
			
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			new Game();
			return;
		}
		
	}

	// Método responsável por detectar a tecla solta
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
			
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			return;
		}
	}

}
