/***************************************
 * Developers: Conor, Ben, Cassandra, Gabriel
 *
 * Date Last Modified: 12.12.2021
 *
 * ObjectBody.java part of Controller package for Securi-key
 *
 * Securi-Key: A java application to store user passwords for a variety of accounts. Encrypts text input using salt and hash md5 sha-256 encryption.
 * Created for SE-370 Introduction to Software Engineering Principles, a group project completed in a 16 week interval for California State University
 * San Marcos, Spring Semester 2021
 *
 * Project Owner: Conor Logorsdon
 * Instructor: Dr. Simon Fan
 *****************************************/
package Controller;

import Utils.UIUtils;

import javax.swing.*;
import java.awt.*;

class ObjectBody extends JPanel {
    private static final int ITEM_PADDING = 15;
    private final int theWidth;
    private final String message;
    private final Color c;
    private volatile boolean stopDisplaying;
    private int heightOfItem, stringPosX, stringPosY, yPos;
    private JPanel panel2;

    public ObjectBody(final JPanel jPanel, String message, Color bgColor, final int yPos) {
        this.panel2 = jPanel;
        this.message = message;
        this.yPos = yPos;
        this.c = bgColor;

        FontMetrics metrics = getFontMetrics(UIUtils.FONT_GENERAL_UI);
        int stringWidth = metrics.stringWidth(this.message);

        theWidth = stringWidth + (ITEM_PADDING * 2);
        heightOfItem = metrics.getHeight() + ITEM_PADDING;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setBounds((jPanel.getWidth() - theWidth) / 2, (int) -(Math.round(heightOfItem / 10.0) * 10), theWidth, heightOfItem);

        stringPosX = (getWidth() - stringWidth) / 2;
        stringPosY = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (ObjectBody.this.getBounds().y < yPos) {
                    int i1 = (yPos - ObjectBody.this.getBounds().y) / 10;
                    i1 = i1 <= 0 ? 1 : i1;
                    ObjectBody.this.setBounds((jPanel.getWidth() - theWidth) / 2, ObjectBody.this.getBounds().y + i1, theWidth, heightOfItem);
                    ObjectBody.this.repaint();
                    try {
                        Thread.sleep(5);
                    } catch (Exception ignored) {
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = UIUtils.get2dGraphics(g);
        super.paintComponent(g2);

        //Background
        g2.setColor(c);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

        // Font
        g2.setFont(UIUtils.FONT_GENERAL_UI);
        g2.setColor(Color.white);
        g2.drawString(message, stringPosX, stringPosY);
    }

    public int getHeightOfItem() {
        return heightOfItem;
    }

    public synchronized boolean getStopDisplaying() {
        return stopDisplaying;
    }

    public synchronized void setStopDisplaying(boolean hasStoppedDisplaying) {
        this.stopDisplaying = hasStoppedDisplaying;
    }

    public void setyPos(final int yPos) {
        this.yPos = yPos;
//        setBounds((panel2.getWidth() - theWidth) / 2, yPos, theWidth, heightOfItem);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (ObjectBody.this.getBounds().y > yPos) {
                    int i1 = Math.abs((yPos - ObjectBody.this.getBounds().y) / 10);
                    i1 = i1 <= 0 ? 1 : i1;
                    ObjectBody.this.setBounds((panel2.getWidth() - theWidth) / 2, ObjectBody.this.getBounds().y - i1, theWidth, heightOfItem);
                    ObjectBody.this.repaint();
                    try {
                        Thread.sleep(5);
                    } catch (Exception ignored) {
                    }
                }
            }
        }).start();
    }

    public int getyPos() {
        return yPos;
    }
}
