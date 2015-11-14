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

import static com.google.common.base.Preconditions.checkState;

import com.google.bitcoin.core.Block;
import com.google.bitcoin.core.CoinDefinition;
import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Utils;

import java.math.BigInteger;

/**
 * Network parameters used by the bitcoinj unit tests (and potentially your own). This lets you solve a block using
 * {@link com.google.bitcoin.core.Block#solve()} by setting difficulty to the easiest possible.
 */
public class UnitTestParams extends NetworkParameters {
    public UnitTestParams() {
        super(1 | (13677 << 16));
        id = ID_UNITTESTNET;
        packetMagic = CoinDefinition.testnetPacketMagic;
        //interval = INTERVAL;
        targetTimespan = CoinDefinition.TESTNET_TARGET_TIMESPAN;
        proofOfWorkLimit = Utils.decodeCompactBits(CoinDefinition.genesisBlockDifficultyTarget);
        port = 13677;
        addressHeader = CoinDefinition.testnetAddressHeader;
        p2shHeader = CoinDefinition.testnetp2shHeader;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        dumpedPrivateKeyHeader = 239;
        genesisBlock.setTime(1296688602L);
        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        genesisBlock.setNonce(414098458);
        //spendableCoinbaseDepth = 100;
        //subsidyDecreaseBlockCount = 210000;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("c58d5a7e6f7ced02428d0812cac1e3a1dfd79fe164bc51edeae1aaeca56d8ad8"));
        //alertSigningKey = Hex.decode("04302390343f91cc401d56d68b123028bf52e5fca1939df127f63c6467cdf9c8e2c14b61104cf817d0b780da337893ecc4aaff1309e536162dabbdb45200ca2b0a");

        dnsSeeds = new String[] {
        };
    }

    private static UnitTestParams instance;
    public static synchronized UnitTestParams get() {
        if (instance == null) {
            instance = new UnitTestParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return null;
    }
}
