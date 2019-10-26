import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class GameController extends GraphicsProgram {

    public void createBoard() {

        this.resize(640, 640);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                GRect positionRect = new GRect(i * 80, j * 80, 80, 80);
                positionRect.setFilled(true);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) positionRect.setFillColor(Color.WHITE);
                else positionRect.setFillColor(Color.GRAY);

                add(positionRect);

            }
        }

    }

    public void run() {

        this.createBoard();

    }


}
