/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

import com.buttercoin.bitcointoolbox.panels.KeyConversionController;
import com.buttercoin.bitcointoolbox.panels.KeyConversionPanel;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;

/**
 * @author Matthis
 */
public class MainFrame extends CenteredFrame {

  public MainFrame () {

    // Key Conversion Tab
    KeyConversionPanel keyConversionPanel = new KeyConversionPanel();
    KeyConversionController keyConversionController = new KeyConversionController(keyConversionPanel);
    keyConversionPanel.setController(keyConversionController);
    keyConversionController.randomize();

    // Tabs
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.add("Key Conversion", keyConversionPanel);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

    setContentPane(tabbedPane);

    // Frame related
    setSize(750, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    center();
  }

}
