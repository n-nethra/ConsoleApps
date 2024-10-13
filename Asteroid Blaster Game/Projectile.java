public class Projectile{
	private int xPosition;
	private int yPosition;
	final static int SPEED = 7;

	public Projectile (int xPos, int yPos){
		xPosition = xPos;
		yPosition = yPos;
	}

	public int getXPos(){
		return xPosition;
	}

	public int getYPos(){
		return yPosition;
	}

	public void updateProjectilePosition() {
		yPosition -= SPEED;
	}

}