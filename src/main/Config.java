package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            // FULLSCREEN
            if (gp.fullscreenOn == true) {
                bw.write("On");

            }
            if (gp.fullscreenOn == false) {
                bw.write("Off");
            }
            bw.newLine();
            // MUSIC VOLUME
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();
            // SE VOLUME
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();
            // FULLSCREEN
            if (s.equals("On")) {
                gp.fullscreenOn = true;
            }
            if (s.equals("Off")) {
                gp.fullscreenOn = false;
            }
            // MUSIC VOLUME
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);
            // SE VOLUME
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
