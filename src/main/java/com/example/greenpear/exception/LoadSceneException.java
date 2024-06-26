package com.example.greenpear.exception;

public class LoadSceneException extends Exception{
        public LoadSceneException(){
            super("Can't upload Scene");
        }
        public LoadSceneException(String message) {
            super(message);
        }
}
