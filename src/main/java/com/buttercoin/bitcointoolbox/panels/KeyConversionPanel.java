/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Matthis
 */
public class KeyConversionPanel extends JPanel {

  private KeyConversionController controller;

  private final JLabel privateKeyLabel = new JLabel("Private Key");
  private final JTextField privateKeyField = new JTextField();

  private final JLabel compressedPrivateKeyLabel = new JLabel("Compressed Private Key");
  private final JTextField compressedPrivateKeyField = new JTextField();

  private final JLabel addressLabel = new JLabel("Address");
  private final JTextField addressTextField = new JTextField();

  private final JLabel publicKeyLabel = new JLabel("Public Key");
  private final JTextField publicKeyTextField = new JTextField();

  private boolean preventEvents = false;


  public KeyConversionPanel () {
    initElements();
    initLayout();
  }

  private void initLayout () {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    Insets leftInsets = new Insets(0, 20, 0, 10);
    Insets rightInsets = new Insets(0, 10, 0, 20);
    Dimension fieldSize = new Dimension(600, 30);

    // Private Key
    c.gridx = 0;
    c.gridy = 0;
    c.insets = leftInsets;
    add(privateKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    privateKeyField.setPreferredSize(fieldSize);
    add(privateKeyField, c);

    // Compressed Private Key
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    add(compressedPrivateKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    compressedPrivateKeyField.setPreferredSize(fieldSize);
    add(compressedPrivateKeyField, c);

    // Address
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    add(addressLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    addressTextField.setPreferredSize(fieldSize);
    add(addressTextField, c);

    // Public Key
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    add(publicKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    publicKeyTextField.setPreferredSize(fieldSize);
    add(publicKeyTextField, c);
  }

  private void initElements () {
    privateKeyField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate(DocumentEvent e) { privateKeyChanged(); }
      @Override public void removeUpdate(DocumentEvent e) { privateKeyChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { privateKeyChanged(); }
    });
    addressTextField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate(DocumentEvent e) { addressChanged(); }
      @Override public void removeUpdate(DocumentEvent e) { addressChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { addressChanged(); }
    });
  }

  public void updatePrivateKey (String newPrivateKey) {
    preventEvents = true;
    privateKeyField.setText(newPrivateKey);
    preventEvents = false;
  }
  private void privateKeyChanged () {
    if (controller != null && !preventEvents) {
      controller.privateKeyChanged(privateKeyField.getText());
    }
  }

  public void updateCompressedPrivateKey (String newCompressedPrivateKey) {
    preventEvents = true;
    compressedPrivateKeyField.setText(newCompressedPrivateKey);
    preventEvents = false;
  }
  private void compressedPrivateKeyChanged () {
    if (controller != null && !preventEvents) {
      controller.compressedPrivateKeyChanged(compressedPrivateKeyField.getText());
    }
  }

  public void updateAddress (String newAddress) {
    preventEvents = true;
    addressTextField.setText(newAddress);
    preventEvents = false;
  }
  private void addressChanged () {
    if (controller != null && !preventEvents) {
      controller.addressChanged(addressTextField.getText());
    }
  }

  public void updatePublicKey (String newPublicKey) {
    preventEvents = true;
    publicKeyTextField.setText(newPublicKey);
    preventEvents = false;
  }
  private void publicKeyChanged () {
    if (controller != null && !preventEvents) {
      controller.publicKeyChanged(publicKeyTextField.getText());
    }
  }

  public void setController (KeyConversionController controller) {
    this.controller = controller;
  }

}
