package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPLayerImage();
    }

    public void setDefaultValues() {

        entityX = gp.tileSize * 20;
        entityY = gp.tileSize * 46;
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
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/D1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/D2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

    if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
        
        if (keyH.upPressed) {
            direction = "up";
        } else if (keyH.downPressed) {
            direction = "down";
        } else if (keyH.leftPressed) {
            direction = "left";
        } else if (keyH.rightPressed) {
            direction = "right"; 
        }

        collisionOn = false;
        gp.collisionChecker.checktail(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up":    entityY -= speed; break;
                case "down":  entityY += speed; break;
                case "left":  entityX -= speed; break;
                case "right": entityX += speed; break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) { 
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    } 
    else {
        spriteNum = 1; 
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
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
    }
}
