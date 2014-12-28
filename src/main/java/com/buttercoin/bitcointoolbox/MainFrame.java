/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

import com.buttercoin.bitcointoolbox.panels.KeyConversionController;
import com.buttercoin.bitcointoolbox.panels.KeyConversionPanel;
import com.buttercoin.bitcointoolbox.panels.SPVChainFileController;
import com.buttercoin.bitcointoolbox.panels.SPVChainFilePanel;
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

    // SPVChain File Tab
    SPVChainFilePanel spvChainFilePanel = new SPVChainFilePanel();
    SPVChainFileController spvChainFileController = new SPVChainFileController(spvChainFilePanel);
    spvChainFilePanel.setController(spvChainFileController);

    // Tabs
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.add("Key Conversion", keyConversionPanel);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    tabbedPane.add("SPVChain File", spvChainFilePanel);
    tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
    setContentPane(tabbedPane);

    // Frame related
    setSize(850, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    center();
  }

}
