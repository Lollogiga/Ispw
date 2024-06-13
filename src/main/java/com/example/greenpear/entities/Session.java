package com.example.greenpear.entities;


//Decidere se gestire il logout
//Decidere se aggiungere i metodi Get per gli attributi di userProfile

//Utilizziamo il pattern Singleton. L'applicazione deve essere monoutente:
public class Session {
    private static Session session = null;
    private UserProfile userProfile = null;

    private Session() {
    }

    //Otteniamo la sessione corrente:
    public static Session getInstance(){
        if(Session.session == null){
            Session.session = new Session();
        }
        return session;
    }

    //Scriviamo l'utente in sessione:
    public void setUserProfile(UserProfile user){
            if(session == null){
                this.userProfile = user;
            }
    }
    public UserProfile getUserProfile(){
        return this.userProfile;
    }









}
