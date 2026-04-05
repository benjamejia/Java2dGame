package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeigth / 2 - (gp.tileSize/2);

        setDefaultValues();
        getPLayerImage();
    }

    public void setDefaultValues() {

        entityX = gp.tileSize * 2;
        entityY = gp.tileSize * 11;
        speed = 4;
        direction = "down";
    }

    public void getPLayerImage() {

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/W1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/W2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/S1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/S2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/A1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/A2.png"));
            rigth1 = ImageIO.read(getClass().getResourceAsStream("/res/player/D1.png"));
            rigth2 = ImageIO.read(getClass().getResourceAsStream("/res/player/D2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true){
            direction = "up";
            entityY -= speed;
            spriteCounter++;
        }
        else if(keyH.downPressed == true){
            direction = "down";
            entityY += speed;
            spriteCounter++;
        }
        else if(keyH.leftPressed == true){
            direction = "left";
            entityX -= speed;
            spriteCounter++;
        }
        else if(keyH.rightPressed == true){
            direction = "rigth";
            entityX += speed;
            spriteCounter++;
        }
        if(spriteCounter > 5){
            if(spriteNum == 1){
                spriteNum = 2;
            }else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "rigth":
                if(spriteNum == 1){
                    image = rigth1;
                }
                if(spriteNum == 2){
                    image = rigth2;
                }
                break;
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
    }
}
