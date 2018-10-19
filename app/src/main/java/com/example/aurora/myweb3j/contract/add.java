package com.example.aurora.myweb3j.contract;

import android.util.Log;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;


/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.1.
 */
//contract wrapper
public final class add extends Contract {

    private static final String BINARY = "0x608060405234801561001057600080fd5b5060e68061001f6000396000f30060806040526004361060525763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416637afbe4f181146057578063c0558a82146081578063c3da42b8146093575b600080fd5b348015606257600080fd5b50606f60043560243560a5565b60408051918252519081900360200190f35b348015608c57600080fd5b50606f60ae565b348015609e57600080fd5b50606f60b4565b01600081905590565b60005490565b600054815600a165627a7a72305820f70ce333a70b3038b59fc29fb58b4ff1d1ba6a30309146ce723f14cd22f4e54b0029";

    private add(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private add(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }


}
