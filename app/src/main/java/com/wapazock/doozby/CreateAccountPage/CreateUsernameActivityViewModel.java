package com.wapazock.doozby.CreateAccountPage;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Utils.Codes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class CreateUsernameActivityViewModel extends ViewModel {

    // Static Variables
    private final static String serverWebSocketServiceURL = "ws://" + DoozbyRepository.getServerDomain() +
            "/ws/service" ;

    // Variables
    private MutableLiveData<Codes> mUsernameErrorCode;
    private MutableLiveData<Boolean> mUsernameValidUsername;
    private OkHttpClient client;
    private WebSocket socketConnection;

    // initialize view model
    private void init(){
        // Initialize Socket
        if (client == null) {
            initializeSocketConnection();
        }

        //
        //initialize mUsernameResult
        if (mUsernameErrorCode == null){
            mUsernameErrorCode = new MutableLiveData<>();
        }

        //initialize mUsernameValidUsername
        if (mUsernameValidUsername == null){
            mUsernameValidUsername = new MutableLiveData<>();
        }
    }

    // initializes socket connection with the server
    private void initializeSocketConnection() {
        // Initialize socket connection
        // Client
        client = new OkHttpClient();

        // Request
        Request request = new Request.Builder()
                .url(serverWebSocketServiceURL + "/anonymous/")
                .build();

        // Socket Connection
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                mUsernameErrorCode.postValue(Codes.CONNECTION_ERROR);

                // retry connection after 2 seconds
                android.os.Handler reconnectHandler = new android.os.Handler(Looper.getMainLooper());
                reconnectHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        client = null;
                        // Re-initialze client
                        init();
                    }
                },2000);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
                // Parse the message into JSON
                try {
                    JSONObject object = new JSONObject(text);

                    // switch the response type
                    switch (object.getString("type")){
                        // Check Username Response
                        case "check_username":
                            // If True, password is valid
                            if (object.getBoolean("result")){
                                mUsernameValidUsername.postValue(true);
                            }
                            // If False, password is invalid
                            else {
                                mUsernameErrorCode.postValue(Codes.USERNAME_TAKEN);
                            }

                    }

                    // Catch Exceptions
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);

                // Assign WebSocket
                socketConnection = webSocket;
            }
        });

    }

    // Get Username Error : Returns username error if any
    public LiveData<Codes> getUsernameError(){
        //initialize
        init();

        // return error
        return mUsernameErrorCode;
    }

    // Submit Username Text : Receives text from the Username Input
    //      and checks if valid
    public void submitUsername(String username){

        // If username is >= 6 chars then check the username
        if (username.length() >= 6){

            // JSON object to send
            JSONObject requestJSON = new JSONObject();
            try {
                requestJSON.put("type", "check_username");
                requestJSON.put("username", username);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            // send if socketConnection is not null
            if (socketConnection != null) {
                socketConnection.send(requestJSON.toString());
            }
        }

        // Username is shorter than 6 chars
        else {
            mUsernameValidUsername.postValue(false);
        }
    }


    // Get Valid Username : Returns True if the username is valid
    public LiveData<Boolean> isValidUsername(){
        init();
        return mUsernameValidUsername;
    }

    // Activity Stopped -- Close connection
    public void activityStopped(){
        if (socketConnection != null){
            socketConnection.close(1000,null);
        }
    }

    // Activity Resumed -- Open connection
    public void activityResumed(){
        if (socketConnection != null){
            client = null;
            init();
        }
    }

}
