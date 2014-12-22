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

  private final KeyConversionPanel view;

  public KeyConversionController (KeyConversionPanel view) {
    this.view = view;
  }

  public void randomize () {
    updateModel(new ECKey());
  }

  private void updateModel (ECKey key) {
    ECKey compressedKey = ECKey.fromPrivate(key.getPrivKey(), true);
    ECKey uncompressedKey = ECKey.fromPrivate(key.getPrivKey(), false);
    ECKeyStore.register(compressedKey);

    if (!compressedKey.isPubKeyOnly()) {
      String privateKey = compressedKey.getPrivateKeyEncoded(MainNetParams.get()).toString();
      String uncompressedPrivateKey = uncompressedKey.getPrivateKeyEncoded(MainNetParams.get()).toString();
      view.updatePrivateKey(privateKey);
      view.updateUncompressedPrivateKey(uncompressedPrivateKey);
    } else {
      view.updatePrivateKey("");
      view.updateUncompressedPrivateKey("");
    }

    String address = compressedKey.toAddress(MainNetParams.get()).toString();
    String uncompressedAddress = uncompressedKey.toAddress(MainNetParams.get()).toString();
    String publicKey = Utils.HEX.encode(compressedKey.getPubKey());
    view.updateAddress(address);
    view.updateUncompressedAddress(uncompressedAddress);
    view.updatePublicKey(publicKey);
  }

  public void privateKeyChanged (String newPrivateKey) {
    try {
      updateModel(ECKey.fromPrivate(Base58.decodeChecked(newPrivateKey), true));
    } catch (Exception e) {
      System.out.println("Invalid compressed private key");
    }
  }

  public void uncompressedPrivateKeyChanged (String newUncompressedPrivateKey) {
    try {
      updateModel(ECKey.fromPrivate(Base58.decodeChecked(newUncompressedPrivateKey), false));
    } catch (Exception e) {
      System.out.println("Invalid uncompressed private key");
    }
  }

  public void addressChanged (String newAddress) {
    ECKey key = ECKeyStore.addresses.get(newAddress);
    if (key == null) {
      // Do something smart
    } else {
      updateModel(key);
    }
  }

  public void uncompressedAddressChanged (String newUncompressedAddress) {
    ECKey key = ECKeyStore.uncompressedAddresses.get(newUncompressedAddress);
    if (key == null) {
      // Do something smart
    } else {
      updateModel(key);
    }
  }

  public void publicKeyChanged (String newPublicKey) {
    ECKey key = ECKeyStore.publicKeys.get(newPublicKey);
    if (key == null) {
      // Do something smart
    } else {
      updateModel(key);
    }
  }

}
