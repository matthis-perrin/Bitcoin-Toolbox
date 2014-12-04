/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

import com.buttercoin.bitcointoolbox.panels.KeyConversionController;
import com.buttercoin.bitcointoolbox.panels.KeyConversionPanel;

/**
 * @author Matthis
 */
public class MainFrame extends CenteredFrame {

  private KeyConversionPanel keyConversionPanel;

  public MainFrame () {
    // Key Conversion
    keyConversionPanel = new KeyConversionPanel();
    KeyConversionController keyConversionController = new KeyConversionController(keyConversionPanel);
    keyConversionPanel.setController(keyConversionController);
    keyConversionController.randomize();

    setContentPane(keyConversionPanel);

    pack();
    center();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

}
