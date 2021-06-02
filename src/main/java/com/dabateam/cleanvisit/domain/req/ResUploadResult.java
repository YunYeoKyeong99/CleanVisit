package com.dabateam.cleanvisit.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class ResUploadResult implements Serializable {

    private String filename;

    private String uuid;

    private String folderPath;

    public String getImageURL(){
        try{
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+filename,"UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL(){
        try{
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+filename,"UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
