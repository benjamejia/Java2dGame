package main;

import entity.Entity;

public class CollisionChecker{
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checktail(Entity entity){

        int entityLeftX = entity.entityX + entity.solidArea.x;
        int entityRigthX = entity.entityX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.entityY + entity.solidArea.y;
        int entityBottomY = entity.entityY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRigthCol = entityRigthX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = entityBottomY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                // Predecimos dónde estará la fila superior después de movernos
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [Fila][Col]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRigthCol];
                if(gp.tileM.tile[tileNum1].colission || gp.tileM.tile[tileNum2].colission) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                // Predecimos la fila inferior
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRigthCol];
                if(gp.tileM.tile[tileNum1].colission || gp.tileM.tile[tileNum2].colission) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                // Predecimos la columna izquierda
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if(gp.tileM.tile[tileNum1].colission || gp.tileM.tile[tileNum2].colission) {
                    entity.collisionOn = true;
                }
                break;

            case "right": // Corregido de "rigth"
                // Predecimos la columna derecha
                entityRigthCol = (entityRigthX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRigthCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRigthCol];
                if(gp.tileM.tile[tileNum1].colission || gp.tileM.tile[tileNum2].colission) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
