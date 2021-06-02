package com.dabateam.cleanvisit.service;

import com.dabateam.cleanvisit.domain.req.ResUploadResult;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UploadService {

    @Value("${org.zerock.upload.path}") //application.properties의 변수
    private String uploadPath;

    public ResUploadResult uploadFile(MultipartFile uploadFile) {

        //이미지 파일만 업로드 가능
        if(uploadFile.getContentType().startsWith("image")==false) {
            log.warn("this file is not image type");
            throw new RuntimeException();
        }

        //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
        String originalName = uploadFile.getOriginalFilename();
        String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);

        log.info("fileName: "+fileName);

        //날짜 폴더 생성
        String folderPath = makeFolder();

        //UUID
        String uuid = UUID.randomUUID().toString();

        //저장할 파일 이름 중간에 "_"를 이용해서 구분
        String saveName = uploadPath+ File.separator+folderPath+File.separator+uuid+"_"+fileName;

        Path savePath = Paths.get(saveName);

        try{
            uploadFile.transferTo(savePath); //실제 이미지
            //섬네일 생성
            String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;

            //섬네일 파일 이름은 중간 s_로 시작하도록
            File thumbnailFile = new File(thumbnailSaveName);

            //섬네일 생성
            Thumbnailator.createThumbnail(savePath.toFile(),thumbnailFile,100,100);

            return new ResUploadResult(fileName,uuid,folderPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public List<ResUploadResult> uploadFile(MultipartFile[] uploadFiles) {

        List<ResUploadResult> uploadResultList = new ArrayList<>();

        for(MultipartFile file: uploadFiles){
            uploadResultList.add(uploadFile(file));
        }

        return uploadResultList;
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("//", File.separator);

        String monthFolderPath = folderPath.substring(0, folderPath.lastIndexOf('/'));

        File monthFolder = new File(uploadPath,monthFolderPath);

        if(monthFolder.exists()==false){
            monthFolder.mkdir();
        }

        //make folder ----------
        File dateFolder = new File(uploadPath,folderPath);

        if(dateFolder.exists()==false){
            dateFolder.mkdir();
        }

        return folderPath;
    }

    public boolean removeFile(String filename) {

        String srcFileName = null;
        try{
            srcFileName = URLDecoder.decode(filename, "UTF-8");
            File file = new File(uploadPath+File.separator+srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(),"s_"+file.getName());

            return thumbnail.delete();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}
