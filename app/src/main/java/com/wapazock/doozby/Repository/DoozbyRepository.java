package com.wapazock.doozby.Repository;

import android.content.SharedPreferences;

import com.wapazock.doozby.Classes.Credentials;
import com.wapazock.doozby.HomeActivity.FetchStorageTokenInterface;
import com.wapazock.doozby.Utils.Codes;
import com.wapazock.doozby.CreateAccountPage.CreateNewAccountInterface;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DoozbyRepository {
    // Routes
    private static String serverDomain = "192.168.1.5:9000";
    private static String serverURL = "http://" + serverDomain ;
    private static String accountsURL = serverURL + "/api/accounts/";
    private static String storageTokenURL = serverURL + "/api/storage_token/";
    private static String TOKEN = null;
    private static String STORAGE_TOKEN = null;


    // Static Variables
    public static DoozbyRepository instance;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // Singleton Repository Implementation
    public static DoozbyRepository getInstance(){
        if (instance == null){
            instance = new DoozbyRepository();
        }
        return instance;
    }


    //Create Account : Creates and account, sends an HTTP request
    public void createNewAccount(Credentials newAccountCredentials, CreateNewAccountInterface createNewAccountInterface){
        //Client
        OkHttpClient client = new OkHttpClient();

        // Body
        RequestBody body = RequestBody.create(JSON,newAccountCredentials.asJSONObject().toString());

        // Request
        Request request = new Request.Builder()
                .url(accountsURL)
                .post(body)
                .build();


        //Send request
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //connection error
                createNewAccountInterface.createAccountResults(false,Codes.CONNECTION_ERROR,"Connection Error");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // Check if response was unsuccessful
                if (!response.isSuccessful()){
                    createNewAccountInterface.createAccountResults(false,Codes.REQUEST_FAILED,"Request Failed");
                }

                // Result
                String result = response.body().string();

                //Check result
                switch (result){
                    case "failed":
                        createNewAccountInterface.createAccountResults(false,Codes.REQUEST_FAILED,"Token");
                        break;
                    default:
                        createNewAccountInterface.createAccountResults(true,Codes.SUCCESS,result);
                        break;
                }

            }
        });
    }

    // Get Storage Token : Gets the storage token from the server
    public void getStorageToken(FetchStorageTokenInterface fetchStorageTokenInterface){
        // Client
        OkHttpClient client = new OkHttpClient();

        // Request
        Request clientRequest = new Request.Builder()
                .addHeader("Authorization","Token " + TOKEN)
                .url(storageTokenURL)
                .build();

        // Send the request
        client.newCall(clientRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                fetchStorageTokenInterface.result(false, Codes.CONNECTION_ERROR,null);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // Response was unsuccessful
                if (!response.isSuccessful()){
                    fetchStorageTokenInterface.result(false,Codes.REQUEST_FAILED,null);
                }

                // Result from the server
                String result = response.body().string();

                // Check result
                switch (result){
                    case "failed":
                        fetchStorageTokenInterface.result(false,Codes.STORAGE_TOKEN_FETCH_FAILED,null);
                        break;
                    default:
                        setStorageTOKEN(result);
                        fetchStorageTokenInterface.result(true,Codes.SUCCESS,result);

                }
            }
        });
    }

    // methods
    public static String getServerDomain() {
        return serverDomain;
    }

    public static void setTOKEN(String TOKEN) {
        DoozbyRepository.TOKEN = TOKEN;
    }

    // Get and set storage token
    public static String getStorageTOKEN() {
        return STORAGE_TOKEN;
    }

    public static void setStorageTOKEN(String storageToken) {
        STORAGE_TOKEN = storageToken;
    }
}
