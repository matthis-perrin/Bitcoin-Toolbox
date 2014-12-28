/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.buttercoin.bitcointoolbox;

import java.util.HashMap;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.MainNetParams;

/**
 *
 * @author Matthis Perrin <matthis.perrin at gmail.com>
 */
public class ECKeyStore {

  public static final HashMap<String, ECKey> addresses = new HashMap<>();
  public static final HashMap<String, ECKey> uncompressedAddresses = new HashMap<>();
  public static final HashMap<String, ECKey> publicKeys = new HashMap<>();
  public static final HashMap<String, ECKey> uncompressedPublicKeys = new HashMap<>();

  public static void register (ECKey key) {
    // TODO - Do not erase a full ECKey with public only ECKey
    addresses.put(key.toAddress(MainNetParams.get()).toString(), key);
    uncompressedAddresses.put(key.decompress().toAddress(MainNetParams.get()).toString(), key);
    publicKeys.put(Utils.HEX.encode(key.getPubKey()), key);
    uncompressedPublicKeys.put(Utils.HEX.encode(key.decompress().getPubKey()), key);
  }

}
