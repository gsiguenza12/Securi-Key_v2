/***************************************
 * Developers: Conor, Ben, Cassandra, Gabriel
 *
 * Date Last Modified: 12.12.2021
 *
 * HyperlinkText.java part of Utils package for Securi-key
 *
 * Securi-Key: A java application to store user passwords for a variety of accounts. Encrypts text input using salt and hash md5 sha-256 encryption.
 * Created for SE-370 Introduction to Software Engineering Principles, a group project completed in a 16 week interval for California State University
 * San Marcos, Spring Semester 2021
 *
 * Project Owner: Conor Logorsdon
 * Instructor: Dr. Simon Fan
 *****************************************/
package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Utils.UIUtils.*;
import static java.awt.Cursor.*;

public class HyperlinkText extends JLabel {
    public HyperlinkText(String hyperlinkText, int xPos, int yPos, final Runnable hyperlinkAction) {
        super(hyperlinkText);
        setForeground(COLOR_OUTLINE);
        setFont(FONT_FORGOT_PASSWORD);
        setCursor(getPredefinedCursor(HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                hyperlinkAction.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(COLOR_OUTLINE.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(COLOR_OUTLINE);
            }
        });

        Dimension prefSize = getPreferredSize();
        setBounds(xPos, yPos, prefSize.width, prefSize.height);
    }
}
