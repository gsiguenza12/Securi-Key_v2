/***************************************
 * Developers: Conor, Ben, Cassandra, Gabriel
 *
 * Date Last Modified: 12.12.2021
 *
 * Controller.java part of Controller package for Securi-key
 *
 * Securi-Key: A java application to store user passwords for a variety of accounts. Encrypts text input using salt and hash md5 sha-256 encryption.
 * Created for SE-370 Introduction to Software Engineering Principles, a group project completed in a 16 week interval for California State University
 * San Marcos, Spring Semester 2021
 *
 * Project Owner: Conor Logorsdon
 * Instructor: Dr. Simon Fan
 *****************************************/
package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {
    private static final int STARTING_Y_POS = 15;
    private static final int SPACER_DISTANCE = 15;
    private static final ArrayList<ObjectBody> PassList = new ArrayList<>();
    private final static AtomicInteger CURRENT_Y_OFFSET = new AtomicInteger();
    private final JPanel panel;

    public Controller(JPanel sourcePanel) {
        this.panel = sourcePanel;
    }

    public void error(String... messages) {
        for (String s : messages) {
            somePanel(s, new Color(181, 59, 86));
        }
    }

    public void success(String... messages) {
        for (String s : messages) {
            somePanel(s, new Color(33, 181, 83));
        }
    }

    public void info(String... messages) {
        for (String s : messages) {
            somePanel(s, new Color(13, 116, 181));
        }
    }

    public void warn(String... messages) {
        for (String s : messages) {
            somePanel(s, new Color(181, 147, 10));
        }
    }

    private void somePanel(String message, Color bgColor) {
        final ObjectBody someBody;

        if (PassList.isEmpty()) {
            someBody = new ObjectBody(panel, message, bgColor, STARTING_Y_POS);
            CURRENT_Y_OFFSET.set(STARTING_Y_POS + someBody.getHeightOfItem());
        } else {
            someBody = new ObjectBody(panel, message, bgColor, CURRENT_Y_OFFSET.get() + SPACER_DISTANCE);
            CURRENT_Y_OFFSET.addAndGet(SPACER_DISTANCE + someBody.getHeightOfItem());
        }

        PassList.add(someBody);

        new Thread(new Runnable() {
            @Override
            public void run() {
                someBody.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        removeBody(someBody);
                    }
                });

                panel.add(someBody, 0);
                panel.repaint();

                try {
                    Thread.sleep(6000);
                    Controller.this.removeBody(someBody);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private synchronized void removeBody(ObjectBody objectBody1) {
        if (!objectBody1.getStopDisplaying()) {
            objectBody1.setStopDisplaying(true);

            PassList.forEach(objectBody -> {
                if (PassList.indexOf(objectBody) >= PassList.indexOf(objectBody1)) {
                    objectBody.setyPos(objectBody.getyPos() - objectBody1.getHeightOfItem() - SPACER_DISTANCE);
                }
            });

            PassList.remove(objectBody1);

            CURRENT_Y_OFFSET.set(CURRENT_Y_OFFSET.get() - SPACER_DISTANCE - objectBody1.getHeightOfItem());

            panel.remove(objectBody1);
            panel.repaint();
        }
    }
}
