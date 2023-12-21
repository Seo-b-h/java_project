/** FileUtils.java */
/*
 * Programmed by 서보혁
 * 파일 업로드 등을 하는데 쓰이는 소스 코드이다.
 * Date : 2023.12.15.
 * Last Update : 2023.12.15.
 * Major update content : Source code 최초 작성 by 서보혁
 */
package com.example.board.util;

import com.example.board.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.*;

@Component
public class FileUtils {
    private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치

    public List<Map<String, Object>> parseInsertFileInfo(Board board, MultipartHttpServletRequest mpRequest) throws Exception{

		/*
			Iterator은 데이터들의 집합체? 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다.
			List나 배열은 순차적으로 데이터의 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다.
			Iterator을 이용하여 Map에 있는 데이터들을 while문을 이용하여 순차적으로 접근합니다.
		*/

        Iterator<String> iterator = mpRequest.getFileNames();

        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;

        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;

        int boardNumber = board.getBoardNumber();

        File file = new File(filePath);
        if(file.exists() == false) {
            file.mkdirs();
        }

        while(iterator.hasNext()) {
            multipartFile = mpRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false) {
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;

                file = new File(filePath + storedFileName);
                multipartFile.transferTo(file);
                listMap = new HashMap<String, Object>();
                listMap.put("BoardNumber", boardNumber);
                listMap.put("ORG_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                list.add(listMap);
            }
        }
        return list;
    }

    public List<Map<String, Object>> parseUpdateFileInfo(Board board, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception{
        Iterator<String> iterator = mpRequest.getFileNames();
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;
        int BoardNumber = board.getBoardNumber();
        while(iterator.hasNext()){
            multipartFile = mpRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;
                multipartFile.transferTo(new File(filePath + storedFileName));
                listMap = new HashMap<String,Object>();
                listMap.put("IS_NEW", "Y");
                listMap.put("BoardNumber", BoardNumber);
                listMap.put("ORG_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                list.add(listMap);
            }
        }
        if(files != null && fileNames != null){
            for(int i = 0; i<fileNames.length; i++) {
                listMap = new HashMap<String,Object>();
                listMap.put("IS_NEW", "N");
                listMap.put("FILE_NO", files[i]);
                list.add(listMap);
            }
        }
        return list;
    }
    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
