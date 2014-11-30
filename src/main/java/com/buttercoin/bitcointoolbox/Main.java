/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

/**
 * @author Matthis
 */
public class Main {

  public static void main (String[] args) {
    MainFrame mainFrame = new MainFrame();
    MainFrameController mainFrameController = new MainFrameController(mainFrame);
    mainFrame.setController(mainFrameController);

    mainFrame.setVisible(true);
  }

}
