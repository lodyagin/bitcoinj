/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.bitcoin.params;

import java.math.BigInteger;

import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Sha256Hash;
import com.google.bitcoin.core.Utils;
import com.google.bitcoin.core.CoinDefinition;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super(1 | (13674 << 16));
        id = ID_MAINNET;
        packetMagic = CoinDefinition.PacketMagic;
        //interval = INTERVAL;
        targetTimespan = CoinDefinition.TARGET_TIMESPAN;
        proofOfWorkLimit = Utils.decodeCompactBits(CoinDefinition.genesisBlockDifficultyTarget);
        dumpedPrivateKeyHeader = CoinDefinition.dumpedPrivateKeyHeader;
        addressHeader = CoinDefinition.AddressHeader;
        p2shHeader = CoinDefinition.p2shHeader;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = CoinDefinition.Port;
        genesisBlock.setDifficultyTarget
        	(CoinDefinition.genesisBlockDifficultyTarget);
        genesisBlock.setTime
        	(CoinDefinition.genesisBlockTime);
        genesisBlock.setNonce
        	(CoinDefinition.genesisBlockNonce);
        id = ID_MAINNET;
        //subsidyDecreaseBlockCount = 210000;
        //spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals(CoinDefinition.genesisHash),
                genesisHash);

        dnsSeeds = CoinDefinition.dnsSeeds;
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
