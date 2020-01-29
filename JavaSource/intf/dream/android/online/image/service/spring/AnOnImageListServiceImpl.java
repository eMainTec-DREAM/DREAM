package intf.dream.android.online.image.service.spring;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import common.file.MwareFile;
import common.util.CommonUtil;
import intf.dream.android.online.image.dao.AnOnImageListDAO;
import intf.dream.android.online.image.service.AnOnImageListService;

/**
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnImageListServiceTarget"
 * @spring.txbn id="anOnImageListService"
 * @spring.property name="anOnImageListDAO" ref="anOnImageListDAO"
 */
public class AnOnImageListServiceImpl implements AnOnImageListService
{
    private AnOnImageListDAO anOnImageListDAO = null;
    
	public AnOnImageListDAO getAnOnImageListDAO() {
		return anOnImageListDAO;
	}
	public void setAnOnImageListDAO(AnOnImageListDAO anOnImageListDAO) {
		this.anOnImageListDAO = anOnImageListDAO;
	}
	
	public List findImageList(Map map) throws Exception
	{
		List list = anOnImageListDAO.findImageList(map);
		
		for (int i = 0; i < list.size(); i++) {
			Map listMap = (Map)list.get(i);
			
			String fileId = String.valueOf(listMap.get("IMGDATAID"));
			String filePath = String.valueOf(listMap.get("FILEPATH"));
			
			String originFile = MwareConfig.getFileDir() + filePath + File.separator + fileId;
			String targetFile = MwareConfig.getWebAppPath() + "dream"+File.separator+"android"+File.separator+"image"+File.separator+"" + fileId;
			
			try{
				File of = new File(originFile);
				File f = new File(targetFile);
				if (of.length() != f.length() || !f.isFile()) {
					FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream"+File.separator+"android"+File.separator+"image"+File.separator);
				}
			}catch (IOException e){}
		}
		
		return list;
	}
	
	public int insertImage(List list, FormFile[] fileList)  throws Exception
	{
		int result = 0;
		if (fileList == null||fileList[1] == null) return 0;
		
		Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        
		for(Object obj : list){
			Map map = (Map)obj;
			
			String imageId = anOnImageListDAO.findHeader(map);
			
			if("0".equals(imageId)){
				//헤더가 없으면 헤더 만들고 넣어야함.
				anOnImageListDAO.createHeader(map);
			}
			//저장 경로 저장
	        String nfFilePath =  CommonUtil.convertString(map.get("compNo")) + File.separator +"IMAGE" + File.separator + ft.format(date);
	        map.put("filePath", nfFilePath);
	        
	        for (int i=0; i < fileList.length; i++)
	        {
	        	if (fileList[i] == null || "".equals(fileList[i].getFileName())) continue;
	        
	        	// 첨부파일을 upload 한다.
	        	String imgDataId = String.valueOf(map.get("imgDataId"));
	        	MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], imgDataId, nfFilePath);
	        	result += anOnImageListDAO.createImgData(mwareFile, map);
	        }
		}
		
		return result;
	}
	@Override
	public int updateImage(List list, FormFile[] fileList) throws Exception {
		int result = 0;
		if (fileList == null||fileList[1] == null) return 0;
		
		Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
		
        for(Object obj : list){
        	Map map = (Map)obj;
        	deleteFile(anOnImageListDAO.findImgDataPath(map));
        	deleteFile(MwareConfig.getWebAppPath()+"dream"+File.separator+"android"+File.separator+"image"+File.separator+String.valueOf(map.get("imgDataId")));
        	anOnImageListDAO.deleteImgData(map);
        	
        	//데이터 넣기.
        	String imageId = anOnImageListDAO.findHeader(map);
        	
        	if("0".equals(imageId)){
				//헤더가 없으면 헤더 만들고 넣어야함.
				anOnImageListDAO.createHeader(map);
			}
			//저장 경로 저장
	        String nfFilePath =  CommonUtil.convertString(map.get("compNo")) + File.separator +"IMAGE" + File.separator + ft.format(date);
	        map.put("filePath", nfFilePath);
	        
	        for (int i=0; i < fileList.length; i++)
	        {
	        	if (fileList[i] == null || "".equals(fileList[i].getFileName())) continue;
	        
	        	// 첨부파일을 upload 한다.
	        	String imgDataId = String.valueOf(map.get("imgDataId"));
	        	MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], imgDataId, nfFilePath);
	        	result += anOnImageListDAO.createImgData(mwareFile, map);
	        }
        }
		return result;
	}
	
	public void deleteFile(String fileDir){
		String originFile = MwareConfig.getFileDir() + fileDir;
		File file = new File(originFile);
		file.delete();
	}

	@Override
	public int deleteImage(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnImageListDAO.deleteImage(map);
			anOnImageListDAO.deleteImgData(map);
			
		}
		return resultQty;
	}
	
}