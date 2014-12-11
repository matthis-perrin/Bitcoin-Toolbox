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
  private String uncompressedPrivateKey = "";
  private String address = "";
  private String uncompressedAddress = "";
  private String publicKey = "";

  private final KeyConversionPanel view;

  public KeyConversionController (KeyConversionPanel view) {
    this.view = view;
  }

  public void randomize () {
    updateModel(new ECKey());
  }

  private void updateModel (ECKey key) {
    ECKey uncompressedKey = key.decompress();
    ECKeyStore.register(key);

    privateKey = key.getPrivateKeyEncoded(MainNetParams.get()).toString();
    uncompressedPrivateKey = uncompressedKey.getPrivateKeyEncoded(MainNetParams.get()).toString();
    address = key.toAddress(MainNetParams.get()).toString();
    uncompressedAddress = uncompressedKey.toAddress(MainNetParams.get()).toString();
    publicKey = Utils.HEX.encode(key.getPubKey());

    view.updatePrivateKey(privateKey);
    view.updateUncompressedPrivateKey(uncompressedPrivateKey);
    view.updateAddress(address);
    view.updateUncompressedAddress(uncompressedAddress);
    view.updatePublicKey(publicKey);
  }

  public void privateKeyChanged (String newPrivateKey) {
    try {
      ECKey ecKey = ECKey.fromPrivate(Base58.decodeChecked(privateKey), true);
      updateModel(ecKey);
    } catch (Exception e) {
      System.out.println("Invalid private key");
    }
  }

  public void uncompressedPrivateKeyChanged (String newUncompressedPrivateKey) {
    try {
      ECKey ecKey = ECKey.fromPrivate(Base58.decodeChecked(privateKey), false);
      updateModel(ecKey);
    } catch (Exception e) {
      System.out.println("Invalid private key");
    }
  }

  public void addressChanged (String newAddress) {
    address = newAddress;
    ECKey key = ECKeyStore.addresses.get(address);
    privateKey = key == null ? "" : key.getPrivateKeyEncoded(MainNetParams.get()).toString();
    publicKey = key == null ? "" : Utils.HEX.encode(key.getPubKey());
    view.updatePrivateKey(privateKey);
    view.updatePublicKey(publicKey);
  }

  public void uncompressedAddressChanged (String newUncompressedAddress) {
    uncompressedAddress = newUncompressedAddress;
    ECKey key = ECKeyStore.uncompressedAddresses.get(address);
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
