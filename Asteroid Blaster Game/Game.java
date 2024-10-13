/*

 MODIFICATIONS:
  - stars in the background
  - red/blue text effect on "game over"
  - smiley/frown face on end screens
  - box around top
  - color of projectiles changes along with ship when losing lives

*/

import javax.swing.Timer;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.Color;




public class Game extends JComponent{
    //declare instance variables here, review UML diagram
    private JFrame frame;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Rectangle> enemyRectangles;
    private int shipX;
    private int shipY;
    private Rectangle playerRectangle;
    private int asteroidsHit;
    private Timer timer;
    private int totalAsteroids;
    private int lives;
    private boolean gameOver;
	private int time;




    public Game(JFrame frame){
        this.frame = frame;
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();
        enemyRectangles = new ArrayList<>();

        shipX = 150;
        shipY = 400;
        time = 60000;
        lives = 5;

        playerRectangle = new Rectangle (shipX, shipY, 100, 100);

        setFocusable(true);
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent event){
                handleKeyPress(event);
            }
        });

        timer = new Timer(10, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                //to be implemented
                if (gameOver == false){
					updateScreen();
					frame.repaint();
				}

            }
        });
        timer.start();
    }





    public void updateEnemyRectangles(){
		for (int i = 0; i < asteroids.size(); i ++){

			enemyRectangles.get(i).setLocation(asteroids.get(i).getX(),asteroids.get(i).getY());
		}

	}



	private void handleKeyPress(KeyEvent event){
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			if (shipY  >= 30){
				shipY -= 15;
			}
		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			if (shipY  <= 505){
				shipY += 15;
			}
		}
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (shipX <= 345)
				shipX += 15;
		}
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			if (shipX >= 40)
				shipX -= 15;
		}
		if (event.getKeyCode() == KeyEvent.VK_SPACE) {
			shoot();
		}
		playerRectangle.setLocation(shipX, shipY);

	}





	private void shoot(){
		Projectile bullet = new Projectile (shipX-4, shipY);
		projectiles.add(bullet);
	}



	private void checkForAsteroidCollisions(){
		for (int i = enemyRectangles.size()-1; i >= 0; i--){
			if (playerRectangle.intersects(enemyRectangles.get(i))){
				 removeAsteroids(i);
				 lives--;
			}
		}
	}



	private void generateNewAsteroid(){
		int randInt = (int) (Math.random() * 600);
		if (randInt <= 5){
			Asteroid a = new Asteroid (this);
			if (a.getX() >= 100 && a.getX() <= 340){
				totalAsteroids++;
				asteroids.add(a);
				enemyRectangles.add(new Rectangle(a.getX(), a.getY(), 30, 30));
			}
		}
	}



	private void removeAsteroids(int index){
		asteroids.get(index).changeAsteroidStatus();
		asteroids.remove(index);
		enemyRectangles.remove(index);
	}



	private void updateAsteroidLocation(){
		if (asteroids.size() > 0){
			for (int i = 0; i < asteroids.size(); i++){
				if(!asteroids.get(i).isAsteroidDestroyed())
					asteroids.get(i).updateAsteroid();
			}
		}
	}



	private void checkProjectileCollisions(){

		for(int j = 0; j < asteroids.size(); j++){

			for (int i = projectiles.size()-1; i >= 0; i--){
				if(projectiles.size() > 0){
					Projectile projectile = projectiles.get(i);
					projectile.updateProjectilePosition();

					if (projectile.getYPos() <= 0){
						projectiles.remove(i);
					}
					else if (asteroids.size() != 0 && projectiles.size() != 0){

						int xDisplacement = projectile.getXPos()-asteroids.get(j).getX();
						int yDisplacement = projectile.getYPos()-asteroids.get(j).getY();

						if( (xDisplacement > 0 && xDisplacement < 50) && ( yDisplacement <= 20 && yDisplacement > 0)){
							removeAsteroids(j);
							projectiles.remove(i);
							asteroidsHit++;

							frame.repaint();
							j--;
						}

					}
				}
			}

		}
	}



	private void updateProjectiles(){
		if (projectiles.size() > 0){
			for (int i = 0; i < projectiles.size(); i++){
				Projectile projectile = projectiles.get(i);
				projectile.updateProjectilePosition();
			}
		}
	}


	private void updateScreen(){

		checkForAsteroidCollisions();
		updateAsteroidLocation();
		generateNewAsteroid();
		checkProjectileCollisions();
		updateProjectiles();

	}


	private void drawShip(Graphics graphics){
		int[] x = {shipX, shipX+20, shipX+25, shipX, shipX-25, shipX-20, };
		int[] y = {shipY-20, shipY, shipY+30, shipY+15, shipY+30, shipY, };
		Color c;
		if (lives == 5)
			graphics.setColor(new Color(255, 38, 215));
		if (lives == 4)
			graphics.setColor(new Color(255, 112, 56));
		if (lives == 3)
			graphics.setColor(new Color(242, 255, 56));
		if (lives == 2)
			graphics.setColor(new Color(88, 235, 9));
		if (lives <= 1)
			graphics.setColor(new Color(0, 76, 255));
		graphics.fillPolygon(x, y, 6);
	}

	private void drawAsteroids(Graphics graphics){
		for (int i =  0; i < asteroids.size(); i++){
			Asteroid asteroid = asteroids.get(i);
			if (!asteroid.isAsteroidDestroyed()){

				graphics.setColor(Color.BLUE);
				graphics.fillOval(asteroid.getX(), asteroid.getY(), 30, 30 );
				updateEnemyRectangles();
			}

		}
	}

	private void drawProjectiles(Graphics graphics){
		for (int i = 0; i < projectiles.size(); i++){
			if (lives == 5)
				graphics.setColor(new Color(88, 235, 9));
			if (lives == 4)
				graphics.setColor(new Color(0, 76, 255));
			if (lives == 3)
				graphics.setColor(new Color(255, 38, 215));
			if (lives == 2)
				graphics.setColor(new Color(255, 112, 56));
			if (lives == 1)
				graphics.setColor(Color.BLACK);
			graphics.fillRect(projectiles.get(i).getXPos(), projectiles.get(i).getYPos(), 6,6);

		}

	}


	private void setEndScreenText(Graphics graphics, String str){
		graphics.setColor(Color.RED);
		graphics.drawString(str, 152, 310);
	}

	private void setGameOver(Graphics graphics){

		if (totalAsteroids == asteroidsHit){
			graphics.setColor(Color.GREEN);
			graphics.drawString("ALL ASTEROIDS DESTROYED, YOU WIN!", 90, 200);
			graphics.setColor(Color.RED);
			graphics.fillRect(150, 350, 15, 30);
			graphics.fillRect(190, 350, 15, 30);
			graphics.fillRect(120, 420, 90, 15);
			graphics.fillRect(120, 380, 15, 40);
			graphics.fillRect(210, 380, 15, 55);

		}
		else if (lives == 0){
			graphics.drawString("ALL LIVES LOST, YOU LOSE", 100, 200);
			graphics.setColor(Color.BLACK);
			graphics.fillRect(150, 350, 15, 30);
			graphics.fillRect(190, 350, 15, 30);
			graphics.fillRect(120, 420, 90, 15);
			graphics.fillRect(120, 420, 15, 40);
			graphics.fillRect(210, 420, 15, 40);

		}
		else if (time <= 0){
			graphics.drawString("OUT OF TIME, YOU LOSE", 120, 200);
			if (lives == 1)
				graphics.setColor(Color.BLACK);
			else
				graphics.setColor(Color.RED);
			graphics.fillRect(150, 350, 15, 30);
			graphics.fillRect(190, 350, 15, 30);
			graphics.fillRect(120, 420, 90, 15);
			graphics.fillRect(120, 420, 15, 40);
			graphics.fillRect(210, 420, 15, 40);

		}


		graphics.setColor(Color.BLUE);
		graphics.drawString("GAME OVER", 150, 312);
		setEndScreenText(graphics, "GAME OVER");

	}






	protected void paintComponent (Graphics graphics){
		if(lives > 1)
			graphics.setColor(Color.BLACK);
		else
			graphics.setColor(new Color (217, 60, 39));

		graphics.fillRect (0,0, 1000,3000);
		if (lives> 1)
			graphics.setColor(new Color(130, 121, 57));
		else
			graphics.setColor(Color.BLACK);
		graphics.drawString("o                                          .", 150, 317);
		graphics.drawString("            +          .", 40, 354);
		graphics.drawString("  .     *", 350,258 );
		graphics.drawString(".		                   +                                                                            '", 26, 107);
		graphics.drawString(".                        *", 20, 480);
		graphics.drawString("+                     ,", 147, 205);
		graphics.drawString("  .     *", 250, 403);
		graphics.drawString(".     o                                                                 * ", 0, 541);

		graphics.drawString("", 50, 100);

		if(lives > 1)
			graphics.setColor(Color.DARK_GRAY);
		else
			graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 1000, 15);
		graphics.setColor(Color.WHITE);
		graphics.drawString( "TIME: "+ time/1000, 10,10 );

		graphics.setColor(Color.YELLOW);
		graphics.drawString("LIVES: " + lives+"", 100, 10);

		graphics.setColor(Color.YELLOW);
		graphics.drawString("ASTEROIDS HIT: " + asteroidsHit+"", 250, 10);


		if (gameOver == false){
			drawShip(graphics);
			drawAsteroids(graphics);
			drawProjectiles(graphics);
			time-= 10;
		}

		if(time <= 0 || lives <= 0){
			gameOver = true;
			setGameOver(graphics);
		}


	}
	    public static void main(String[]args){
	    JFrame frame = new JFrame("Asteroid Shooter");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.add(new Game(frame));
	    frame.setVisible(true);
    }


}