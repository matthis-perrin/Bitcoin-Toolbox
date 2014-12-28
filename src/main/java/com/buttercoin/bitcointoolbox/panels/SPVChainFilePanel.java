/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Matthis
 */
public class SPVChainFilePanel extends JPanel {

  private SPVChainFileController controller;

  private final JButton openButton = new JButton("Load SPVChain File");
  private final JFileChooser fileChooser = new JFileChooser();
  private final JPanel buttonPanel = new JPanel();
  private final JPanel dataPanel = new JPanel();

  private final JLabel lastBlockTitle = new JLabel("Last Block");

  private final JLabel lastBlockHeightLabel = new JLabel("Height");
  private final JTextField lastBlockHeightTextField = new JTextField();


  public SPVChainFilePanel () {
    initElements();
    initLayout();
    resetData();
  }

  private void initLayout () {
    dataPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    Insets leftInsets = new Insets(0, 20, 0, 10);
    Insets rightInsets = new Insets(0, 10, 0, 20);

    // Last Block Title
    c.gridx = 0;
    c.gridy = 0;
    c.insets = leftInsets;
    c.weightx = 0;
    dataPanel.add(lastBlockTitle, c);

    // Last Block Height
    c.gridx = 0;
    c.gridy++;
    c.insets = leftInsets;
    c.weightx = 0;
    dataPanel.add(lastBlockHeightLabel, c);

    c.gridx++;
    c.insets = rightInsets;
    c.weightx = 1;
    dataPanel.add(lastBlockHeightTextField, c);

    // Buttons
    buttonPanel.add(openButton);

    setLayout(new BorderLayout());
    add(buttonPanel, BorderLayout.NORTH);
    add(dataPanel, BorderLayout.CENTER);
  }

  private void initElements () {
    JLabel[] labels = new JLabel[] { lastBlockTitle, lastBlockHeightLabel };
    JTextField[] textfields = new JTextField[] { lastBlockHeightTextField };

    for (JLabel label : labels) {
      label.setHorizontalAlignment(JTextField.RIGHT);
    }

    Dimension fieldSize = new Dimension(0, 35);
    for (JTextField textfield : textfields) {
      textfield.setPreferredSize(fieldSize);
      textfield.setEnabled(false);
    }

    Font f = lastBlockTitle.getFont();
    lastBlockTitle.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

    openButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          controller.fileSelected(file);
        }
      }
    });
  }

  private final DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
  public void updateData (int blockHeight) {
    lastBlockHeightTextField.setText(formatter.format(blockHeight));
  }

  private void resetData () {
    lastBlockHeightTextField.setText("Unknown");
  }

  public void setController (SPVChainFileController controller) {
    this.controller = controller;
  }

}
