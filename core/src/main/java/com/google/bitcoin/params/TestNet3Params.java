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

import com.google.bitcoin.core.CoinDefinition;
import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Utils;

import org.spongycastle.util.encoders.Hex;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the testnet, a separate public instance of Bitcoin that has relaxed rules suitable for development
 * and testing of applications and new Bitcoin versions.
 */
public class TestNet3Params extends NetworkParameters {
		public TestNet3Params() {
        super(1 | (13677 << 16));
        id = ID_TESTNET;
        packetMagic = CoinDefinition.testnetPacketMagic;
        //interval = INTERVAL;
        targetTimespan = CoinDefinition.TESTNET_TARGET_TIMESPAN;
        proofOfWorkLimit = Utils.decodeCompactBits(CoinDefinition.testnetGenesisBlockDifficultyTarget);
        port = CoinDefinition.TestPort;
        addressHeader = CoinDefinition.testnetAddressHeader;
        p2shHeader = CoinDefinition.testnetp2shHeader;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        dumpedPrivateKeyHeader = CoinDefinition.dumpedPrivateKeyHeader;
        genesisBlock.setDifficultyTarget
      	  (CoinDefinition.testnetGenesisBlockDifficultyTarget);
        genesisBlock.setTime
      	  (CoinDefinition.testnetGenesisBlockTime);
        genesisBlock.setNonce
      	  (CoinDefinition.testnetGenesisBlockNonce);
        //spendableCoinbaseDepth = 100;
        //subsidyDecreaseBlockCount = 210000;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals(CoinDefinition.testnetGenesisHash));
        //alertSigningKey = Hex.decode("04302390343f91cc401d56d68b123028bf52e5fca1939df127f63c6467cdf9c8e2c14b61104cf817d0b780da337893ecc4aaff1309e536162dabbdb45200ca2b0a");

        dnsSeeds = new String[] {
        };
    }

    private static TestNet3Params instance;
    public static synchronized TestNet3Params get() {
        if (instance == null) {
            instance = new TestNet3Params();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_TESTNET;
    }
}
