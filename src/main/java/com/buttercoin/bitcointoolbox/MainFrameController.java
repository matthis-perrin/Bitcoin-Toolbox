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

  public void randomize () {
    String key = new ECKey().getPrivateKeyEncoded(MainNetParams.get()).toString();
    view.updatePrivateKey(key);
    privateKeyChanged(key);
  }

  public void addressChanged (String newAddress) {
    address = newAddress;
    ECKey key = ECKeyStore.keys.get(address);
    privateKey = key == null ? "" : key.getPrivateKeyEncoded(MainNetParams.get()).toString();
    view.updatePrivateKey(privateKey);
  }

  public void privateKeyChanged (String newPrivateKey) {
    privateKey = newPrivateKey;
    try {
      ECKey ecKey = ECKey.fromPrivate(Base58.decodeChecked(privateKey));
      address = ecKey.toAddress(MainNetParams.get()).toString();
      ECKeyStore.keys.put(address, ecKey);
      view.updateAddress(address);
    } catch (Exception e) {
      System.out.println("Invalid private key");
    }
  }

}
