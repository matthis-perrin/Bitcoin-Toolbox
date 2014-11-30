/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.params.MainNetParams;

/**
 * @author Matthis
 */
public class MainFrameController {

  private String privateKey = "";
  private String address = "";

  private final MainFrame view;

  public MainFrameController (MainFrame view) {
    this.view = view;
  }

  public void addressChanged (String newAddress) {
    address = newAddress;
    privateKey = "";
    view.updatePrivateKey(privateKey);
  }
  public void privateKeyChanged (String newPrivateKey) {
    privateKey = newPrivateKey;
    try {
      address = ECKey.fromPrivate(Base58.decodeChecked(privateKey)).toAddress(MainNetParams.get()).toString();
      view.updateAddress(address);
    } catch (Exception e) {
      System.out.println("Invalid private key");
    }
  }

}
