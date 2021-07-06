package com.example.kashapp;

public class upload {
        private String iName;
        private String imageUri;

        public upload(){

        }

    public upload(String iName,String imageUri) {
            if(iName.trim().equals("")){
                iName = "No Name";
            }
            this.iName = iName;
        this.imageUri = imageUri;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
