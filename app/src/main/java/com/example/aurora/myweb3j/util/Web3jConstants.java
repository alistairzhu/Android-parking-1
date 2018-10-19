package com.example.aurora.myweb3j.util;

import java.math.BigInteger;

/**
 * Created by yuan.
 */

public class Web3jConstants {
    //connection port
    public static final String CLIENT_IP = "10.0.2.2"; //"10.0.75.1";
    public static final String CLIENT_PORT = "8545";

    // see https://www.reddit.com/r/ethereum/comments/5g8ia6/attention_miners_we_recommend_raising_gas_limit/
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);

    // http://ethereum.stackexchange.com/questions/1832/cant-send-transaction-exceeds-block-gas-limit-or-intrinsic-gas-too-low
    public static final BigInteger GAS_LIMIT_ETHER_TX = BigInteger.valueOf(1_000_000);
    public static final BigInteger GAS_LIMIT_WITHBALANCE_TX = BigInteger.valueOf(500_000L);


    public static final int CONFIRMATION_ATTEMPTS = 40;
    public static final int SLEEP_DURATION = 1000;
    //public static final String CONTRACT_ADDRESS = "0xd6a40d6f7406412c24b10d02192233f5c13f986c";
    public static final String CONTRACT_ADDRESS = "0xf6d07ff9871e79fb826006d0c4d55d95c28f5c6a";

}
