package window;
import game.Game;
import game.TopDownGame;

public class Main {
	
	private static final int TARGET_FPS = 60;
	
	public static void main(String[] args) {
		GraphicsApplicationWindow window = new GraphicsApplicationFrame();
		Game game = new TopDownGame();
		window.setGraphicsApplication(game);
		window.setInputListener(game);
		game.setGraphicsApplicationWindow(window);
		window.showWindow();
		
		double dT;
		long previousTime = System.currentTimeMillis();
		long targetMillis = (long)(1000.0f / TARGET_FPS);
		long elapsedMillis;
		
		while (true) {
			dT = (System.currentTimeMillis() - previousTime) / 1000.0f;
			previousTime = System.currentTimeMillis();
			
			game.update(dT);
			window.redraw();
			
			try {
				elapsedMillis = System.currentTimeMillis() - previousTime;
				if (elapsedMillis < targetMillis)
					Thread.sleep(targetMillis - elapsedMillis);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
}