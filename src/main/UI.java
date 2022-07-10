package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.BasicStroke;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import entity.Entity;
import entity.item.Coin;
import entity.item.Heart;
import entity.item.Item;
import entity.item.Mana;
import utilz.LoadSave;

public class UI {
    GamePanel gp;
    Font MineCraft;
    BufferedImage heart_full, heart_half, heart_empty, mana_full, mana_empty, coin;
    Image bg;
    Graphics2D g2;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<String>();
    ArrayList<Integer> messageCounter = new ArrayList<Integer>();

    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/japanese.ttf");
            MineCraft = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // CREATE HUD OBJECT
        Item heart = new Heart(gp);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_empty = heart.getImage3();
        Item mana = new Mana(gp);
        mana_full = mana.getImage();
        mana_empty = mana.getImage2();
        Item Coin = new Coin(gp);
        coin = Coin.getImage();
        try {
            bg = new ImageIcon(
                    getClass().getResource("/ui/bg.gif")).getImage();
        } catch (Exception e) {
        }

    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(MineCraft);
        g2.setColor(Color.white);
        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawdialogueScreen();
        }
        // INFORM STATE
        if (gp.gameState == gp.informState) {
            drawPopUpMessage();
        }
        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {

            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {

            drawGameOverScreen();
        }
        // LOADING STATE
        if (gp.gameState == gp.loadingState) {
            drawLoading();
        }
        // TRADING STATE
        if (gp.gameState == gp.tradingState) {
            drawTradeScreen();
        }
        // WIN STATE
        if (gp.gameState == gp.winState) {
            drawWinScreen();
        }
    }

    private void drawWinScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90f));
        int x;
        int y;
        String text;

        text = "おめでとうございます!!!";
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // text
        g2.setColor(Color.green);
        g2.drawString(text, x - 4, y - 4);
        // text
        g2.setColor(Color.green);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        text = "あなたは家に帰る道を見つけた";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        // return to title screen
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        text = "ENTERキーでメニューに戻る";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
    }

    private void drawTradeScreen() {
        switch (subState) {
            case 0:
                tradeSelect();
                break;

            case 1:
                tradeBuy();
                break;
            case 2:
                tradeSell();
                break;
        }
        gp.keyH.enterPressed = false;
    }

    private void tradeSelect() {
        drawdialogueScreen();
        // DRAW WINDOW
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);
        // DRAW TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        g2.setColor(Color.black);
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                subState = 1;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                subState = 2;
            }
        }
        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                // gp.ui.currentDialogue = "Come again soon";
                gp.ui.currentDialogue = "またのお越しをお待ちして\nおります";
            }
        }
    }

    private void tradeBuy() {
        g2.setColor(Color.black);
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        // DRAW NPC INVENTORY
        drawInventory(npc, true);
        g2.setColor(Color.black);
        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("[ESC] back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        g2.setColor(Color.black);
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexonSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = npc.inventory.get(itemIndex).getPrice();
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 20);
            g2.setColor(Color.black);
            g2.drawString(text, x, y + 32);

            // BUY ITEM
            if (gp.keyH.enterPressed == true) {
                if (npc.inventory.get(itemIndex).getPrice() > gp.player.coin) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    // gp.ui.currentDialogue = "You don't have enough coin";
                    gp.ui.currentDialogue = "コインが足りない。";
                    drawdialogueScreen();
                } else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    // gp.ui.currentDialogue = "You can't carry more items";
                    gp.ui.currentDialogue = "これ以上アイテムを持参できない。";
                } else {
                    gp.playSE(9);
                    gp.player.coin -= npc.inventory.get(itemIndex).getPrice();
                    gp.player.inventory.add(npc.inventory.get(itemIndex));

                }
            }
        }
    }

    private void tradeSell() {
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;
        // DRAW HINT WINDOW
        x = gp.tileSize * 2;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("[ESC] back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexonSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = (int) (gp.tileSize * 15.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = gp.player.inventory.get(itemIndex).getPrice() / 2;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 18 - 20);
            g2.setColor(Color.black);
            g2.drawString(text, x, y + 32);

            // SELL ITEM
            if (gp.keyH.enterPressed == true) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
                        || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    // gp.ui.currentDialogue = "You can't sell this item";
                    gp.ui.currentDialogue = "この商品は販売できない";
                } else {
                    gp.playSE(9);
                    gp.player.coin += price;
                    gp.player.inventory.remove(itemIndex);
                }

            }
        }

    }

    private void drawLoading() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.getTempMap();
            gp.player.worldX = gp.tileSize * gp.eHandler.getTempCol();
            gp.player.worldY = gp.tileSize * gp.eHandler.getTempRow();
            int preEventX = gp.player.worldX;
            int preEventY = gp.player.worldY;

            gp.eHandler.setPreviousEventX(preEventX);
            ;
            gp.eHandler.setPreviousEventY(preEventY);
            ;
        }

    }

    private void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90f));
        int x;
        int y;
        String text;

        text = "君は死んだ";
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // text
        g2.setColor(Color.red);
        g2.drawString(text, x - 4, y - 4);
        // retry
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        text = "リトライ";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }
        // quit
        text = "辞める";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    private void drawOptionsScreen() {

        g2.setFont(g2.getFont().deriveFont(35F));
        // SUB WINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState) {
            case 0:
                topOptions(frameX, frameY);
                break;
            case 1:
                fullScreenNotification(frameX, frameY);
                break;
            case 2:
                control(frameX, frameY);
                break;
            case 3:
                endGameConfirmation(frameX, frameY);
                break;
        }
        gp.keyH.enterPressed = false;
    }

    private void topOptions(int frameX, int frameY) {
        int textX;
        int textY;
        // TITLE
        g2.setColor(Color.black);
        String text = "オプション";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("フルスクリーン", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                if (gp.fullscreenOn == false) {

                    gp.fullscreenOn = true;
                } else if (gp.fullscreenOn == true) {
                    gp.fullscreenOn = false;
                }
                subState = 1;
            }

        }

        // MUSIC
        textY += gp.tileSize;
        g2.drawString("音楽", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }
        // SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }
        // CONTROL
        textY += gp.tileSize;
        g2.drawString("制御", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        // QUIT GAME
        textY += gp.tileSize;
        g2.drawString("ゲームを終了する", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;

            }
        }
        // BACK
        textY += gp.tileSize * 2;
        g2.drawString("バック", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }
        // FULL SCREEN CHECKBOX
        textX = frameX + gp.tileSize * 5;
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullscreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);

        }
        // MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        // SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        try {
            gp.config.saveConfig();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;
        g2.setColor(Color.black);
        // currentDialogue = "The change will \n take effect after \n restarting the
        // game.";
        currentDialogue = "この変更は\n、ゲームを再起動した\n後に有効になります。";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        // BACK
        textY += gp.tileSize * 6;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
            }

        }

    }

    private void control(int frameX, int frameY) {
        g2.setFont(g2.getFont().deriveFont(28F));
        g2.setColor(Color.black);
        int textX;
        int textY;
        // TITLE
        String text = "コントロール";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("移動", textX, textY);
        textY += gp.tileSize;
        g2.drawString("攻撃/相互作用", textX, textY);
        textY += gp.tileSize;
        g2.drawString("撃つ", textX, textY);
        textY += gp.tileSize;
        g2.drawString("キャラクター詳細", textX, textY);
        textY += gp.tileSize;
        g2.drawString("オプション", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 1.5;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.playSE(9);
                subState = 0;
                commandNum = 3;
            }
        }

    }

    private void endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + (int) (gp.tileSize * 1.5);
        int textY = frameY + gp.tileSize * 3;
        g2.setColor(Color.black);
        // currentDialogue = "Are you sure you \n want to quit?";
        currentDialogue = "本当に辞めたいのか？";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        // YES
        String text = "はい";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.playSE(9);
                gp.gameState = gp.titleState;
                gp.stopMusic();
                gp.playMusic(0);
            }
        }
        // NO
        text = "いいえ";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.playSE(9);
                commandNum = 4;
            }
        }

    }

    private void drawInventory(Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        if (entity == gp.player) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;

        }

        // FRAME

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        // Draw player's items
        for (int i = 0; i < entity.inventory.size(); i++) {
            // Equiped item
            if (entity.inventory.get(i) == entity.currentWeapon
                    || entity.inventory.get(i) == entity.currentShield) {
                g2.setColor(new Color(255, 148, 24));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14 || i == 19) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // CURSOR
        if (cursor == true) {
            int cursorX = slotXstart + slotCol * slotSize;
            int cursorY = slotYstart + slotRow * slotSize;
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
            // draw cursor
            g2.setColor(new Color(193, 120, 35));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;

            // description
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

            int itemIndex = getItemIndexonSlot(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for (String line : entity.inventory.get(itemIndex).getDescription().split("\n")) {
                    g2.setColor(Color.black);
                    g2.drawString(line, textX, textY);

                    textY += 32;
                }
            }
        }

    }

    public int getItemIndexonSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow * 5;
        return itemIndex;
    }

    private void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);

                g2.setColor(new Color(214, 214, 214));
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // messageCounter ++;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 120) {
                    message.remove(i);
                    messageCounter.remove(i);

                }

            }
        }
    }

    private void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;

        }
        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;

        }

        // DRAW MAX MANA
        x = (gp.tileSize / 2);
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(mana_empty, x, y, null);
            i++;
            x += 35;
        }
        // DRAW MANA
        x = (gp.tileSize / 2);
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(mana_full, x, y, null);
            i++;
            x += 35;
        }

    }

    private void drawTitleScreen() {
        if (titleScreenState == 0) {

            g2.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, null);
            g2.setColor(new Color(0, 0, 0, 130));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
            String text = "Bruh Quest";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // IMAGE
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.avatar, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "ニューゲーム";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5;
            if (commandNum == 0) {
                g2.setColor(Color.yellow);
                g2.drawString(">", x - gp.tileSize, y);
            } else {
                g2.setColor(Color.white);
            }
            g2.drawString(text, x, y);
            text = "ガイド";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            if (commandNum == 1) {
                g2.setColor(Color.yellow);
                g2.drawString(">", x - gp.tileSize, y);
            } else {
                g2.setColor(Color.white);
            }
            g2.drawString(text, x, y);
            text = "クィット";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            if (commandNum == 2) {
                g2.setColor(Color.yellow);
                g2.drawString(">", x - gp.tileSize, y);
            } else {
                g2.setColor(Color.white);
            }
            g2.drawString(text, x, y);
        } else if (titleScreenState == 1) {
            drawHowToPlay();

        }
    }

    private void drawHowToPlay() {
        BufferedImage image;
        g2.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setColor(new Color(0, 0, 0, 160));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        g2.setColor(Color.white);
        String text = "キーボード操作";
        int x = getXforCenteredText(text);
        int y = gp.tileSize;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // ESCAPE
        image = LoadSave.KB_B;
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));
        g2.drawImage(image, x, y, gp.tileSize / 2, gp.tileSize / 2, null);
        g2.setColor(Color.white);
        g2.drawString("バック (B)", x + 30, y + 18);

        // W,A,S,D
        image = LoadSave.KB_W;
        if (gp.keyH.upPressed == true) {
            image = LoadSave.KB_W_P;
        }
        x = gp.tileSize * 2 + 6;
        y = gp.tileSize * 2;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        image = LoadSave.KB_A;
        if (gp.keyH.leftPressed == true) {
            image = LoadSave.KB_A_P;
        }
        x = gp.tileSize;
        y = gp.tileSize * 3 + 6;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        image = LoadSave.KB_S;
        if (gp.keyH.downPressed == true) {
            image = LoadSave.KB_S_P;
        }
        x = gp.tileSize * 2 + 6;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        image = LoadSave.KB_D;
        if (gp.keyH.rightPressed == true) {
            image = LoadSave.KB_D_P;
        }
        x = gp.tileSize * 3 + 12;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // ATTACK
        image = LoadSave.KB_ENTER;
        if (gp.keyH.enterPressed == true) {
            image = LoadSave.KB_ENTER_P;
            gp.keyH.enterPressed = false;
        }
        x = gp.tileSize + 16;
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize * 3, gp.tileSize, null);

        // SHOOT
        image = LoadSave.KB_F;
        if (gp.keyH.shotKeyPressed == true) {
            image = LoadSave.KB_F_P;
        }
        x = gp.tileSize * 2 + 6;
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // Inventory
        image = LoadSave.KB_C;
        if (gp.keyH.cPressed == true) {
            image = LoadSave.KB_C_P;
        }
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // Settings
        x = gp.tileSize + 16;
        image = LoadSave.KB_ESC;
        if (gp.keyH.escPressed == true) {
            image = LoadSave.KB_ESC_P;
        }
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize, null);
        // TEXT
        x += gp.tileSize * 6;
        y = gp.tileSize * 3;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));
        g2.setColor(Color.white);
        g2.drawString(" - 移動", x, y);
        y += gp.tileSize * 2 + 28;
        g2.drawString(" - 攻撃/相互作用", x, y);
        y += gp.tileSize + 30;
        g2.drawString(" - インベントリ", x, y);
        y += gp.tileSize + 30;
        g2.drawString(" - 撃つ", x, y);
        y += gp.tileSize + 30;
        g2.drawString(" - オプション", x, y);
    }

    public void drawPopUpMessage() {
        // WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);
        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
        g2.setColor(Color.black);
        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawdialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);
        x += gp.tileSize;
        y += 32;

        g2.drawImage(npc.avatar, x, y, (int) (gp.tileSize * 2.5), (int) (gp.tileSize * 2.5), null);
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

        x += gp.tileSize * 3;
        y += 16;
        g2.drawString(npc.getName() + " :", x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }

    private void drawCharacterScreen() {

        // CREATE A FRAME
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(28F));
        int textX = frameX + 24;
        int textY = frameY + 40;
        final int lineHeight = 36;

        // NAMES
        g2.drawString("レベル", textX, textY);
        textY += lineHeight;
        g2.drawString("活力", textX, textY);
        textY += lineHeight;
        g2.drawString("精神", textX, textY);
        textY += lineHeight;
        g2.drawString("強さ", textX, textY);
        textY += lineHeight;
        g2.drawString("耐久性", textX, textY);
        textY += lineHeight;
        g2.drawString("攻撃力", textX, textY);
        textY += lineHeight;

        g2.drawString("防衛力", textX, textY);
        textY += lineHeight;

        g2.drawString("経験値", textX, textY);
        textY += lineHeight;
        g2.drawString("次のレベル", textX, textY);
        textY += lineHeight;

        g2.drawString("硬貨", textX, textY);
        textY += lineHeight + 12;

        g2.drawString("武器", textX, textY);
        textY += lineHeight + 8;
        g2.drawString("シールド", textX, textY);
        textY += lineHeight;

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + 40;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.endurance);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 25, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 25, null);

    }

    private void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(255, 255, 255, 210);
        g2.setColor(c);
        g2.setColor(new Color(255, 255, 255, 200));

        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(156, 100, 3);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        c = new Color(255, 148, 24);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 10, y + 10, width - 20, height - 20, 15, 15);

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforAlignToRightText(String text, int tailX) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

}
