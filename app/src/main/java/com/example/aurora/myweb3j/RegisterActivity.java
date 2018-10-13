package com.example.aurora.myweb3j;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.aurora.myweb3j.contract.ManageOrder;
import com.example.aurora.myweb3j.util.Alice;
import com.example.aurora.myweb3j.util.Web3jConstants;
import com.example.aurora.myweb3j.util.Web3jUtils;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

import static com.example.aurora.myweb3j.util.Web3jUtils.getBinaryOfContract;

//user registration
public class RegisterActivity extends AppCompatActivity {
    static final String ERROR = "Error";
    ManageOrder contract = null;
    ManageOrder manageOrder = null;
    static ECKeyPair KEY_PAIR =null;
    public static Credentials CREDENTIALS =null;
    public static String ADDRESS = null;

    private static final String TAG = "RegisterActivity--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // create new private/public key pair
        final ECKeyPair[] keyPair = {null};
        try {
            keyPair[0] = Keys.createEcKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e(ERROR,"No Such Algorithm Exception");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            Log.e(ERROR,"No Such Provider Exception");
        }

        //save the fey pair to the class for further use
        BigInteger publicKey = keyPair[0].getPublicKey();
        Alice.PUBLIC_KEY = Numeric.toHexStringWithPrefix(publicKey);

        BigInteger privateKey = keyPair[0].getPrivateKey();
        Alice.PRIVATE_KEY = Numeric.toHexStringWithPrefix(privateKey);
        saveFile(Alice.PUBLIC_KEY, "public_key.pem");
        Log.i("Public",Alice.PUBLIC_KEY);
        saveFile(Alice.PRIVATE_KEY, "private_key.pem");
        KEY_PAIR = new ECKeyPair(Numeric.toBigInt(Alice.PRIVATE_KEY), Numeric.toBigInt(Alice.PUBLIC_KEY));

        //generate the credantionals and address
        CREDENTIALS = Credentials.create(KEY_PAIR);
        ADDRESS = CREDENTIALS.getAddress();

    }

    //when clicking "register" button
    public void onRegister_ok(View view) {
        Log.i(TAG,"--+++----------------------------onRegister_ok------------------------------------" );
        //get user information
        EditText edit_username = (EditText) findViewById(R.id.edit_username);
        String username = edit_username.getText().toString();
        EditText edit_userphone = (EditText) findViewById(R.id.edit_userphone);
        String userphone = edit_userphone.getText().toString();
        Log.i(TAG,"--+++---------------------------------------------------username----------"  + username );

        final Intent intent_main2 = new Intent(getApplicationContext(), MainActivity.class);
        final Intent intent_register = new Intent(getApplicationContext(), RegisterActivity.class);

        //check if the key pair exists in the divice
       if((fileExistance("public_key.pem"))&&(fileExistance("private_key.pem"))){
        //    if(false){

            //transfer money from the wallet to the user account
            BigInteger amountWei = new BigInteger("500000000000000002");
            try {
                String txHash = MainActivity.transferWei(Web3jUtils.getCoinbase(), "0xc5478aa6d2f66f4a1fbffaa3a012a108824fee24", amountWei);
                Log.i(TAG,"--+++---------------------------------------------------userphon----------" + userphone );
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                manageOrder = loadContract();

                Log.i(TAG,"----111-----------------------------------------------" );
            } catch (Exception e) {
                Log.i(TAG,"----catch (Exception e)-----------------------------------------------" );
                e.printStackTrace();
            }

            //send the register request to the contract
            TransactionReceipt result = null;
            try {

                Log.i(TAG,"----1.55-----------------------------------------------" );
//                result = contract.newBuyer(new Utf8String(username),new Utf8String(userphone)).get();
                result = manageOrder.newBuyer(new Utf8String(username),new Utf8String(userphone)).get();
                Log.i(TAG,"----222---------------------------------------------------------" );

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
                Log.i(TAG,"--------------------------------------------------------------------------------Balance from the address: " +result.getTransactionHash());

            startActivity(intent_main2);
        }else{
            //startActivity(intent_register);
                startActivity(intent_main2);
        }






    }

    //save the data into internal storage
    public void saveFile(String message, String filename) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(message.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //load the contract using user credential
    private ManageOrder loadContract() throws Exception {
        System.out.println("// Deploy contract------------------------");

        contract = ManageOrder
                .load(Web3jConstants.CONTRACT_ADDRESS, LoginActivity.web3j, CREDENTIALS, Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_ETHER_TX.multiply(BigInteger.valueOf(2)));

        String contractAddress = contract.getContractAddress();
        System.out.println("Contract address: ------------------------------" + contractAddress);

       // System.out.println("Binary-----------------------" + getBinaryOfContract(contractAddress));
        return contract;
    }

    //chekc if the file exists
    public boolean fileExistance(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }



}


