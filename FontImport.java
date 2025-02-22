import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontImport {

    public  Font silkscreenTitle1;
    public  Font silkscreenTitle2;
    public  Font silkscreenTitle3;
    public Font silkscreenRegular1;
    public Font silkscreenRegular2;
    public Font silkscreenRegular3;
    public FontImport() {
        try {
            File fontFile1 = new File("./fonts/Silkscreen-Regular.ttf");
            silkscreenRegular1 = Font.createFont(Font.TRUETYPE_FONT, fontFile1).deriveFont(Font.BOLD, 30);
            silkscreenRegular2 = Font.createFont(Font.TRUETYPE_FONT, fontFile1).deriveFont(Font.BOLD, 25);
            silkscreenRegular3 = Font.createFont(Font.TRUETYPE_FONT, fontFile1).deriveFont(Font.BOLD, 20);
            File fontFile2 = new File("./fonts/Silkscreen-Bold.ttf");
            silkscreenTitle1 = Font.createFont(Font.TRUETYPE_FONT, fontFile2).deriveFont(Font.BOLD, 50);
            silkscreenTitle2 = Font.createFont(Font.TRUETYPE_FONT, fontFile2).deriveFont(Font.BOLD, 45);
            silkscreenTitle3 = Font.createFont(Font.TRUETYPE_FONT, fontFile2).deriveFont(Font.BOLD, 30);

        } catch (IOException | FontFormatException e) {
            silkscreenRegular1 = new Font("Arial", Font.BOLD, 30);
            silkscreenRegular2 = new Font("Arial", Font.BOLD, 25);
            silkscreenRegular3 = new Font("Arial", Font.BOLD, 20);
            silkscreenTitle1 = new Font("Arial", Font.BOLD, 50);
            silkscreenTitle2 = new Font("Arial", Font.BOLD, 40);
            silkscreenTitle3 = new Font("Arial", Font.BOLD, 30);

        }
    }
}
