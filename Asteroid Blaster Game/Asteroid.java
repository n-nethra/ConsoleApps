import javax.swing.JComponent;
public class Asteroid{
	private int asteroidX;
	private int asteroidY;
	private boolean isDestroyed;
	private JComponent component;

	public Asteroid(JComponent component){
		asteroidY = 0;
		asteroidX = (int)(Math.random()* 350) + 50;
		while (asteroidX >= component.getWidth()-50 || asteroidX <= 50){
			asteroidX = (int)(Math.random()* 350) + 50;
		}
		this.component = component;
		isDestroyed = false;
	}

	public void changeAsteroidStatus(){
		isDestroyed = !isDestroyed;
	}

	public void updateAsteroid (){

		if(asteroidY >= component.getHeight()){
			asteroidY = 0;
			asteroidX = (int)(Math.random()* 350) + 50;
		}

		if (!isDestroyed){
			asteroidY += 1;
		}


	}

	public int getX(){
		return asteroidX;
	}

	public int getY(){
		return asteroidY;
	}

	public boolean isAsteroidDestroyed(){
		return isDestroyed;
	}

}