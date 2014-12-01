/**
 *  Buttercoin Toolbox
 *  The MIT License (MIT)
 *  Copyright (c) 2014 Buttercoin
 */

package com.buttercoin.bitcointoolbox;

import java.io.File;
import java.util.Set;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.SPVBlockStore;

/**
 * @author Matthis
 */
public class SPVChainFileUtils {

  private static SPVBlockStore blockStore;

  public static class BlockStoreNotLoadedException extends Exception { }

  public static void loadSPVChainFile (File chainFile)
          throws BlockStoreException {
    blockStore = new SPVBlockStore(MainNetParams.get(), chainFile);

  }

  public static StoredBlock getLastBlock ()
          throws BlockStoreNotLoadedException, BlockStoreException {
    if (blockStore == null) {
      throw new BlockStoreNotLoadedException();
    } else {
      return blockStore.getChainHead();
    }
  }

  public static Set<Sha256Hash> getOrphanBlocks ()
          throws BlockStoreNotLoadedException, BlockStoreException {
    if (blockStore == null) {
      throw new BlockStoreNotLoadedException();
    } else {
      return new BlockChain(MainNetParams.get(), blockStore).drainOrphanBlocks();
//      return OrphanBlockExtractor.extractOrphanBlocks(new BlockChain(MainNetParams.get(), blockStore));
    }
  }

}
