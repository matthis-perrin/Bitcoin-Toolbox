/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * @author Matthis
 */
public class CenteredFrame extends JFrame {

  public final void center() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    GraphicsConfiguration frameConf = new JFrame().getGraphicsConfiguration();
    Insets screenInset = toolkit.getScreenInsets(frameConf);
    Dimension screenSize = toolkit.getScreenSize();

    int actualWidth = screenSize.width - screenInset.left - screenInset.right;
    int actualHeight = screenSize.height - screenInset.top - screenInset.bottom;

    int x = screenInset.left + (actualWidth - getWidth()) / 2;
    int y = screenInset.top + (actualHeight - getHeight()) / 2;

    setBounds(x, y, getWidth(), getHeight());
  }

}
