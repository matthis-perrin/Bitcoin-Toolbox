/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Matthis
 */
public class MainFrame extends CenteredFrame {

  private final JLabel addressLabel = new JLabel("Address");
  private final JTextField addressTextField = new JTextField();

  private final JLabel privateKeyLabel = new JLabel("Private Key");
  private final JTextField privateKeyField = new JTextField();


  public MainFrame () {
    initLayout();
    pack();
    center();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void initLayout () {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    Insets leftInsets = new Insets(0, 20, 0, 10);
    Insets rightInsets = new Insets(0, 10, 0, 20);
    Dimension filedSize = new Dimension(300, 30);

    // Private Key
    c.gridx = 0;
    c.gridy = 0;
    c.insets = leftInsets;
    add(privateKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    privateKeyField.setPreferredSize(filedSize);
    add(privateKeyField, c);

    // Address
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    add(addressLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    addressTextField.setPreferredSize(filedSize);
    add(addressTextField, c);

  }

}
