import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
* [Runner.java]
* a frail, but nimble zombie
* subclass to Zombie
* contains overridden draw and attack methods
* @author Kyle Zhou
**/
class Runner extends Zombie{
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  /**
  * Runner constructor 
  * inherits xCoordinate, yCoordinate, health, damage, and speed from Zombie
  * sets hitbox
  */
  Runner(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprites();
    seteWidth(spriteRight.getWidth());
    seteHeight(spriteRight.getHeight());
    setxCentre(xCord + (geteWidth() / 2));
    setyCentre(yCord + (geteHeight() / 2));
    setHitbox(new Rectangle(xCord, yCord, geteWidth() - 10, geteHeight()));
  }
  
  /**
  * loadSprites 
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sheet = ImageIO.read(new File("runnerSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 52, 104);
      spriteLeft = sheet.getSubimage(52, 0, 52, 104);
    } catch(Exception e) { System.out.println("Error Loading 'runnerSheet.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    if (getDirection() == true) {
      g.drawImage(spriteRight, getxCord(), getyCord(), null);
    } else {
      g.drawImage(spriteLeft,getxCord(), getyCord(), null);
    }
    g.setColor(Color.red);
    g.fillRect(getxCord()+7, getyCord()-10, (getStartingHealth()) / 2, 7);
    g.setColor(Color.green);
    g.fillRect(getxCord()+7, getyCord()-10, (getHealth()) / 2, 7);
  }
  
  @Override
  public void attack(Human duber) {
    setCooldown(20);
    duber.setHealth(duber.getHealth() - getDamage());
  }
}