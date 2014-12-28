/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox.panels;

import com.buttercoin.bitcointoolbox.ECKeyStore;
import org.bitcoinj.core.DumpedPrivateKey;
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
    ECKey compressedKey = key;
    ECKey uncompressedKey = key;
    if (key.isCompressed()) {
      uncompressedKey = ECKey.fromPrivate(key.getPrivKey(), false);
    } else {
      compressedKey = ECKey.fromPrivate(key.getPrivKey(), true);
    }
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
    String uncompressedPublicKey = Utils.HEX.encode(uncompressedKey.getPubKey());
    view.updateAddress(address);
    view.updateUncompressedAddress(uncompressedAddress);
    view.updatePublicKey(publicKey);
    view.updateUncompressedPublicKey(uncompressedPublicKey);
  }

  public void privateKeyChanged (String newPrivateKey) {
    try {
      updateModel(new DumpedPrivateKey(MainNetParams.get(), newPrivateKey).getKey());
    } catch (Exception e) {
      System.out.println("Invalid compressed private key");
    }
  }

  public void uncompressedPrivateKeyChanged (String newUncompressedPrivateKey) {
    try {
      updateModel(new DumpedPrivateKey(MainNetParams.get(), newUncompressedPrivateKey).getKey());
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

  public void uncompressedPublicKeyChanged (String newUncompressedPublicKey) {
    ECKey key = ECKeyStore.uncompressedPublicKeys.get(newUncompressedPublicKey);
    if (key == null) {
      // Do something smart
    } else {
      updateModel(key);
    }
  }

}
