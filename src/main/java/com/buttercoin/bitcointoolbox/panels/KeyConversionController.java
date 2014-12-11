/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox.panels;

import com.buttercoin.bitcointoolbox.ECKeyStore;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;

/**
 * @author Matthis
 */
public class KeyConversionController {

  private String privateKey = "";
  private String compressedPrivateKey = "";
  private String address = "";
  private String publicKey = "";

  private final KeyConversionPanel view;

  public KeyConversionController (KeyConversionPanel view) {
    this.view = view;
  }

  public void randomize () {
    String key = new ECKey().getPrivateKeyEncoded(MainNetParams.get()).toString();
    view.updatePrivateKey(key);
    privateKeyChanged(key);
  }

  public void privateKeyChanged (String newPrivateKey) {
    privateKey = newPrivateKey;
    try {
      ECKey ecKey = ECKey.fromPrivate(Base58.decodeChecked(privateKey));
      address = ecKey.toAddress(MainNetParams.get()).toString();
      publicKey = Utils.HEX.encode(ecKey.getPubKey());
      compressedPrivateKey = ecKey.decompress().getPrivateKeyEncoded(MainNetParams.get()).toString();
      ECKeyStore.addresses.put(address, ecKey);
      ECKeyStore.publicKeys.put(publicKey, ecKey);
      view.updateAddress(address);
      view.updatePublicKey(publicKey);
      view.updateCompressedPrivateKey(compressedPrivateKey);
    } catch (Exception e) {
      System.out.println("Invalid private key");
    }
  }

  public void compressedPrivateKeyChanged (String newCompressedPrivateKey) {
    compressedPrivateKey = newCompressedPrivateKey;
  }

  public void addressChanged (String newAddress) {
    address = newAddress;
    ECKey key = ECKeyStore.addresses.get(address);
    privateKey = key == null ? "" : key.getPrivateKeyEncoded(MainNetParams.get()).toString();
    publicKey = key == null ? "" : Utils.HEX.encode(key.getPubKey());
    view.updatePrivateKey(privateKey);
    view.updatePublicKey(publicKey);
  }

  public void publicKeyChanged (String newPublicKey) {
    publicKey = newPublicKey;
    ECKey key = ECKeyStore.publicKeys.get(publicKey);
    privateKey = key == null ? "" : key.getPrivateKeyEncoded(MainNetParams.get()).toString();
    address = key == null ? "" : key.toAddress(MainNetParams.get()).toString();
    view.updatePrivateKey(privateKey);
    view.updateAddress(address);
  }

}
