package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.awt.BasicStroke;
import object.Key;

public class UI {
    GamePanel gp;
    Font MineCraft;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/Minecraft.ttf");
            MineCraft = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(MineCraft);
        g2.setColor(Color.white);
        // PLAY STATE
        if (gp.gameState == gp.playState) {

        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawdialogueScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);

        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawdialogueScreen() {
        // WINDOW
        int x = gp.titleSize * 2;
        int y = gp.titleSize / 2;
        int width = gp.screenWidth - (gp.titleSize * 4);
        int height = gp.titleSize * 4;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        x += gp.titleSize;
        y += gp.titleSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    private void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
