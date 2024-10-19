import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SpiderGame extends JPanel implements ActionListener {
    private int spiderX = 250;
    private int spiderY = 250;
    private final int STEP = 10;
    private Timer timer;
    private ArrayList<Bug> bugs;
    private int score = 0;
    private Random random;

    public SpiderGame() {
        setFocusable(true);
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveSpider(e);
            }
        });
        timer = new Timer(20, this);
        timer.start();
        bugs = new ArrayList<>();
        random = new Random();
        spawnBugs();
    }

    private void spawnBugs() {
        for (int i = 0; i < 10; i++) {
            int size = random.nextInt(20) + 10;
            int x = random.nextInt(480);
            int y = random.nextInt(480);
            bugs.add(new Bug(x, y, size));
        }
    }

    private void moveSpider(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                spiderY -= STEP;
                break;
            case KeyEvent.VK_DOWN:
                spiderY += STEP;
                break;
            case KeyEvent.VK_LEFT:
                spiderX -= STEP;
                break;
            case KeyEvent.VK_RIGHT:
                spiderX += STEP;
                break;
        }
        checkCollision();
        repaint();
    }

    private void moveBugs() {
        for (Bug bug : bugs) {
            int dx = random.nextInt(7) - 3; // Losowy ruch w zakresie -3 do 3
            int dy = random.nextInt(7) - 3;
            bug.move(dx, dy);
        }
    }

    private void checkCollision() {
        Iterator<Bug> iterator = bugs.iterator();
        while (iterator.hasNext()) {
            Bug bug = iterator.next();
            int distance = (int) Math.sqrt(Math.pow(spiderX - bug.getX(), 2) + Math.pow(spiderY - bug.getY(), 2));
            if (distance < 15 + bug.getSize() / 2) { // Collision detection
                score += bug.getSize();
                iterator.remove();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(spiderX, spiderY, 20, 20); // Rysowanie pajączka jako małego koła

        for (Bug bug : bugs) {
            if (bug.getSize() <= 15) {
                g.setColor(Color.GREEN);
            } else if (bug.getSize() <= 25) {
                g.setColor(Color.PINK);
            } else {
                g.setColor(Color.RED);
            }
            g.fillOval(bug.getX(), bug.getY(), bug.getSize(), bug.getSize());
        }

        g.setColor(Color.BLUE);
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBugs();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spider Game");
        SpiderGame game = new SpiderGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class Bug {
        private int x;
        private int y;
        private int size;

        public Bug(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }

        public void move(int dx, int dy) {
            x += dx;
            y += dy;
            if (x < 0) x = 0;
            if (x > 480) x = 480;
            if (y < 0) y = 0;
            if (y > 480) y = 480;
        }
    }
}