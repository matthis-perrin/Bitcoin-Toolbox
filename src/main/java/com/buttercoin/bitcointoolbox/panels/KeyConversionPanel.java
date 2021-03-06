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
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Matthis
 */
public class KeyConversionPanel extends JPanel {

  private KeyConversionController controller;

  private final JLabel privateKeyLabel = new JLabel("Private Key");
  private final JTextField privateKeyField = new JTextField();

  private final JLabel uncompressedPrivateKeyLabel = new JLabel("Uncompressed Private Key");
  private final JTextField uncompressedPrivateKeyField = new JTextField();

  private final JLabel addressLabel = new JLabel("Address");
  private final JTextField addressTextField = new JTextField();

  private final JLabel uncompressedAddressLabel = new JLabel("Uncompressed Address");
  private final JTextField uncompressedAddressTextField = new JTextField();

  private final JLabel publicKeyLabel = new JLabel("Public Key");
  private final JTextField publicKeyTextField = new JTextField();

  private final JLabel uncompressedPublicKeyLabel = new JLabel("Uncompressed Public Key");
  private final JTextField uncompressedPublicKeyTextField = new JTextField();

  private boolean preventEvents = false;


  public KeyConversionPanel () {
    initElements();
    initLayout();
  }

  private void initLayout () {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    Insets leftInsets = new Insets(0, 20, 0, 10);
    Insets rightInsets = new Insets(0, 10, 0, 20);

    // Private Key
    c.gridx = 0;
    c.gridy = 0;
    c.insets = leftInsets;
    c.weightx = 0;
    add(privateKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    c.weightx = 1;
    add(privateKeyField, c);

    // Uncompressed Private Key
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    c.weightx = 0;
    add(uncompressedPrivateKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    c.weightx = 1;
    add(uncompressedPrivateKeyField, c);

    // Address
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    c.weightx = 0;
    add(addressLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    c.weightx = 1;
    add(addressTextField, c);

    // Uncompressed Address
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    c.weightx = 0;
    add(uncompressedAddressLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    c.weightx = 1;
    add(uncompressedAddressTextField, c);

    // Public Key
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    c.weightx = 0;
    add(publicKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    c.weightx = 1;
    add(publicKeyTextField, c);

    // Uncompressed Public Key
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    c.weightx = 0;
    add(uncompressedPublicKeyLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    c.weightx = 1;
    add(uncompressedPublicKeyTextField, c);
  }

  private void initElements () {

    privateKeyField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate (DocumentEvent e) { privateKeyChanged(); }
      @Override public void removeUpdate (DocumentEvent e) { privateKeyChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { privateKeyChanged(); }
    });
    uncompressedPrivateKeyField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate (DocumentEvent e) { uncompressedPrivateKeyChanged(); }
      @Override public void removeUpdate (DocumentEvent e) { uncompressedPrivateKeyChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { uncompressedPrivateKeyChanged(); }
    });
    addressTextField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate (DocumentEvent e) { addressChanged(); }
      @Override public void removeUpdate (DocumentEvent e) { addressChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { addressChanged(); }
    });
    uncompressedAddressTextField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate (DocumentEvent e) { privateKeyChanged(); }
      @Override public void removeUpdate (DocumentEvent e) { privateKeyChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { privateKeyChanged(); }
    });
    publicKeyTextField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate (DocumentEvent e) { publicKeyChanged(); }
      @Override public void removeUpdate (DocumentEvent e) { publicKeyChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { publicKeyChanged(); }
    });
    uncompressedPublicKeyTextField.getDocument().addDocumentListener(new DocumentListener() {
      @Override public void insertUpdate (DocumentEvent e) { uncompressedPublicKeyChanged(); }
      @Override public void removeUpdate (DocumentEvent e) { uncompressedPublicKeyChanged(); }
      @Override public void changedUpdate(DocumentEvent e) { uncompressedPublicKeyChanged(); }
    });

    JLabel[] labels = new JLabel[] { privateKeyLabel, uncompressedPrivateKeyLabel,
      addressLabel, uncompressedAddressLabel, publicKeyLabel, uncompressedPublicKeyLabel };
    JTextField[] textfields = new JTextField[] { privateKeyField, uncompressedPrivateKeyField,
      addressTextField, uncompressedAddressTextField, publicKeyTextField, uncompressedPublicKeyTextField };

    for (JLabel label : labels) {
      label.setHorizontalAlignment(JTextField.RIGHT);
    }

    Dimension fieldSize = new Dimension(0, 35);
    for (JTextField textfield : textfields) {
      textfield.setPreferredSize(fieldSize);
    }

  }

  private void updateTextFieldWithValue (final JTextField textField, final String value) {
    SwingUtilities.invokeLater(new Runnable() { @Override public void run() {
      preventEvents = true;
      textField.setText(value);
      preventEvents = false;
    }});
  }

  public void updatePrivateKey (String newPrivateKey) {
    updateTextFieldWithValue(privateKeyField, newPrivateKey);
  }
  public void updateUncompressedPrivateKey (String newUncompressedPrivateKey) {
    updateTextFieldWithValue(uncompressedPrivateKeyField, newUncompressedPrivateKey);
  }
  public void updateAddress (String newAddress) {
    updateTextFieldWithValue(addressTextField, newAddress);
  }
  public void updateUncompressedAddress (String newUncompressedAddress) {
    updateTextFieldWithValue(uncompressedAddressTextField, newUncompressedAddress);
  }
  public void updatePublicKey (String newPublicKey) {
    updateTextFieldWithValue(publicKeyTextField, newPublicKey);
  }
  public void updateUncompressedPublicKey (String newUncompressedPublicKey) {
    updateTextFieldWithValue(uncompressedPublicKeyTextField, newUncompressedPublicKey);
  }

  private void uncompressedPrivateKeyChanged () {
    if (controller != null && !preventEvents) {
      controller.uncompressedPrivateKeyChanged(uncompressedPrivateKeyField.getText());
    }
  }
  private void privateKeyChanged () {
    if (controller != null && !preventEvents) {
      controller.privateKeyChanged(privateKeyField.getText());
    }
  }
  private void addressChanged () {
    if (controller != null && !preventEvents) {
      controller.addressChanged(addressTextField.getText());
    }
  }
  private void uncompressedAddressChanged () {
    if (controller != null && !preventEvents) {
      controller.uncompressedAddressChanged(uncompressedAddressTextField.getText());
    }
  }
  private void publicKeyChanged () {
    if (controller != null && !preventEvents) {
      controller.publicKeyChanged(publicKeyTextField.getText());
    }
  }
  private void uncompressedPublicKeyChanged () {
    if (controller != null && !preventEvents) {
      controller.uncompressedPublicKeyChanged(uncompressedPublicKeyTextField.getText());
    }
  }

  public void setController (KeyConversionController controller) {
    this.controller = controller;
  }

}
