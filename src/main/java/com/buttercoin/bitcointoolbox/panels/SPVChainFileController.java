/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox.panels;

import com.buttercoin.bitcointoolbox.SPVChainFileUtils;
import java.io.File;
import org.bitcoinj.store.BlockStoreException;

/**
 * @author Matthis
 */
public class SPVChainFileController {

  private final SPVChainFilePanel view;

  public SPVChainFileController (SPVChainFilePanel view) {
    this.view = view;
  }

  public void fileSelected (File f) {
    try {
      SPVChainFileUtils.loadSPVChainFile(f);
      view.updateData(SPVChainFileUtils.getLastBlock().getHeight());
    } catch (BlockStoreException | SPVChainFileUtils.BlockStoreNotLoadedException ex) {
      ex.printStackTrace();
    }
  }

}
