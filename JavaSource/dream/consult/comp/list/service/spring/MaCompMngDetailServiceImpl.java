package dream.consult.comp.list.service.spring;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

import common.bean.MwareConfig;
import common.bean.User;
import common.file.FileUploadUtil;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.FileUtil;
import dream.consult.comp.list.dao.MaCompMngDetailDAO;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.dto.MaCompMngDetailDTO;
import dream.consult.comp.list.service.MaCompMngDetailService;
import dream.consult.comp.list.service.MaCompMngListService;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.doc.img.dto.MaDocImgCommonDTO;

/**
 * 회사설정 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaCompMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maCompMngDetailServiceTarget"
 * @spring.txbn id="maCompMngDetailService"
 * @spring.property name="maCompMngDetailDAO" ref="maCompMngDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 */
public class MaCompMngDetailServiceImpl implements MaCompMngDetailService
{
    private MaCompMngDetailDAO maCompMngDetailDAO = null;
    private MaDocImgDetailDAO maDocImgDetailDAO = null;

    public MaCompMngDetailDAO getMaCompMngDetailDAO() {
		return maCompMngDetailDAO;
	}

	public void setMaCompMngDetailDAO(MaCompMngDetailDAO maCompMngDetailDAO) {
		this.maCompMngDetailDAO = maCompMngDetailDAO;
	}

	public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	public MaCompMngDetailDTO findDetail(MaCompMngCommonDTO maCompMngCommonDTO,User user)throws Exception
    {
        return maCompMngDetailDAO.findDetail(maCompMngCommonDTO,user);
    }
    
	public int insertDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user) throws Exception
    {        
	    int rtnVal =   maCompMngDetailDAO.insertDetail(maCompMngDetailDTO, user);
	    
	    loadLoginImg();
	    
	    return rtnVal;
    }
	
	public int updateDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user) throws Exception
    {        
        int rtnVal =  maCompMngDetailDAO.updateDetail(maCompMngDetailDTO, user);
        
        loadLoginImg();
        
        return rtnVal;
    }
	
    @Override
    public List findSlideImage(MaCompMngCommonDTO maCompMngCommonDTO, MaCompMngDetailDTO maCompMngDetailDTO, User user) throws Exception
    {
        return fileCopyForDetail(maCompMngDetailDTO.getCompId(), "LOGO", maCompMngDetailDTO.getSubImgType());
    }
 
    public List fileCopyForDetail(String objectId, String objectType, String param) throws Exception
    {
        List imgList = maDocImgDetailDAO.getImgFileList(objectId, objectType, param);
        List rtnList = new ArrayList();
        
        //nfFilePath : 000\IMAGE\201812
        //[{imgdata_id=1410, FILE_NAME=1410, file_ext=PNG, nfFilePath=000\IMAGE\201812, image_id=1433}]
        String originFile = "";
        String targetFile = "";
        
        
        if(param.equals("LOGINTITLE")) {
            targetFile = MwareConfig.getWebAppPath()+ "common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"login_logo"+ File.separator +"main_logo.png";// + fileName;
        } else if (param.equals("LOGINSUBTITLE")) {
            targetFile = MwareConfig.getWebAppPath()+ "common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"sub_logo"+ File.separator +"sub_logo.png";// + fileName;
        } else if (param.equals("MAINTITLE")) {
            targetFile = MwareConfig.getWebAppPath()+ "common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"main_logo"+ File.separator +"main_logo.png";// + fileName;
        }
        
//      for(int i = 0; imgList.size() > i ; i++)
        if(imgList != null && imgList.size() > 0)
        {
           for(int i = 0; imgList.size() > i ; i++)
           {
               Map map2 = (Map)imgList.get(0);
               
               String fileNo2 = String.valueOf(map2.get("IMGDATA_ID"));
               String fileName2 = param + "_" + String.valueOf(map2.get("FILE_NAME"));
               String filePath2 = String.valueOf(map2.get("NFFILEPATH"));
               
               String comOriginFile = MwareConfig.getFileDir() + filePath2 + File.separator + fileNo2;
               String comTargetFile = MwareConfig.getWebAppPath()+"dream"+ File.separator +"imgSlide" + File.separator + fileNo2;

               fileName2 = FileUploadUtil.fileCopy(comOriginFile, comTargetFile, MwareConfig.getWebAppPath()+"dream"+ File.separator +"imgSlide"+ File.separator);
               
               map2.put("FILE_NAME", fileName2);
               rtnList.add(map2);
           }
           
        }
        else
        {
             File file = new File(targetFile);
             file.delete();
        }
        // file 복사
        //deleteTempResultFiles();
        
        loadLoginImg();
        
        return rtnList;
    }
    
	public List fileCopy(String objectId, String objectType, String param) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
		List imgList = maDocImgDetailDAO.getImgFileList(objectId, objectType, param);

		//nfFilePath : 000\IMAGE\201812
		//[{imgdata_id=1410, FILE_NAME=1410, file_ext=PNG, nfFilePath=000\IMAGE\201812, image_id=1433}]
        String originFile = "";
        String targetFile = "";

        if(param.equals("LOGINTITLE")) {
            targetFile = MwareConfig.getWebAppPath()+ "common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"login_logo"+ File.separator +"main_logo.png";// + fileName;
        } else if (param.equals("LOGINSUBTITLE")) {
            targetFile = MwareConfig.getWebAppPath()+ "common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"sub_logo"+ File.separator +"sub_logo.png";// + fileName;
        } else if (param.equals("MAINTITLE")) {
            targetFile = MwareConfig.getWebAppPath()+ "common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"main_logo"+ File.separator +"main_logo.png";// + fileName;
        }
        
//		for(int i = 0; imgList.size() > i ; i++)
		if(imgList != null && imgList.size() > 0)
		{
           Map map = (Map)imgList.get(0);
 
           String fileNo = String.valueOf(map.get("IMGDATA_ID"));
           String fileName = param + "_" + String.valueOf(map.get("FILE_NAME"));
           String filePath = String.valueOf(map.get("NFFILEPATH"));

           originFile = MwareConfig.getFileDir() + filePath + File.separator + fileNo;
           
           try
           {
        	   if(param.equals("LOGINTITLE")) {
        	       FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"login_logo"+ File.separator);
        	   } else if (param.equals("LOGINSUBTITLE")) {
        	       FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"sub_logo"+ File.separator);
        	   } else if(param.equals("MAINTITLE")) {
        	       FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"common"+ File.separator +"images"+ File.separator +"client"+ File.separator +"main_logo"+ File.separator);
        	   }
           } catch (IOException e) {}
           
		}
		else
		{
	         File file = new File(targetFile);
	         file.delete();
		}

        // file 복사
        //deleteTempResultFiles();
		
		
        return imgList;
    }
    
	/**
	 * context에 복사한 파일 삭제
	 */
    public void deleteTempResultFiles()
    {
        //=======================================================
        // 임시 파일 dir 에 모든 파일 을 삭제한다.
    	File dirFile = new File(MwareConfig.getWebAppPath()+"dream"+ File.separator +"imgSlide"+ File.separator);

        File [] files = dirFile.listFiles();
        
        if(files == null) return;
        
        for (int i=0; i<files.length; i++)
        {
            // 일자가 오늘날짜 이전에 생성된것들은 모두 삭제한다.
            long fileDateLong = files[i].lastModified();
            Date fileDate = new Date(fileDateLong);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            
            // 파일 수정일자
            String madeDate = dateFormat.format(fileDate);
            
            // 현재일자
            String currentDate = DateUtil.getDateTime("yyyyMMdd");
            
            try
            {
                // 파일생성일자 < 현재일자 라면 삭제한다.
                if (DateUtil.compareDate(madeDate, null, "<", currentDate, null))
                {
                    files[i].delete();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	@Override
	public MaCompMngDetailDTO findImage(MaCompMngCommonDTO maCompMngCommonDTO, MaCompMngDetailDTO maCompMngDetailDTO, User user) throws Exception {
		
		List loginTitleImageList = fileCopyForDetail(maCompMngCommonDTO.getCompId(), "LOGO","LOGINTITLE");
		List loginSubTitleImageList = fileCopyForDetail(maCompMngCommonDTO.getCompId(), "LOGO","LOGINSUBTITLE");
		List mainTitleImageList = fileCopyForDetail(maCompMngCommonDTO.getCompId(), "LOGO","MAINTITLE");
		
		maCompMngDetailDTO.setLoginTitleLogo(loginTitleImageList);
		maCompMngDetailDTO.setLoginSubTitleLog(loginSubTitleImageList);
		maCompMngDetailDTO.setMainTitleLogo(mainTitleImageList);
		
		return maCompMngDetailDTO;
	}

	@Override
	public String[][] getImageCount(MaCompMngCommonDTO maCompMngCommonDTO, MaCompMngDetailDTO maCompMngDetailDTO, User user) {
		
		MaDocImgCommonDTO maDocImgCommonDTO = new MaDocImgCommonDTO();
		maDocImgCommonDTO.setObjectType("LOGO");
		maDocImgCommonDTO.setObjectId(maCompMngCommonDTO.getCompId());
		user.setCompNo(maCompMngDetailDTO.getCompNo());
		
		String totalCount = maDocImgDetailDAO.getImageCount(maDocImgCommonDTO, user);
		
		String[][] resultList = new String[1][1];
		resultList[0][0] = totalCount;
		
		return resultList;
	}

    @Override
    public void loadLoginImg() throws Exception
    {
        MaCompMngCommonDTO maCompMngCommonDTO = new MaCompMngCommonDTO();
        maCompMngCommonDTO.setFilterInitCtPathYn("Y");
        maCompMngCommonDTO.setFilterIsUse("Y");
        User user = new User();
        
        MaCompMngListService maCompMngListService = (MaCompMngListService)CommonUtil.getBean("maCompMngListService");
        List resultList = maCompMngListService.findCompList(maCompMngCommonDTO, user);
        
        if(resultList != null && resultList.size() > 0)
        {
            Map rsltMap = (Map) resultList.get(0);

            MaCompMngDetailDTO maCompMngDetailDTO=  (MaCompMngDetailDTO) CommonUtil.makeDTO(rsltMap, MaCompMngDetailDTO.class);
            
            maCompMngDetailDTO.setSubImgType("LOGINTITLE");
            fileCopy(maCompMngDetailDTO.getCompId(), "LOGO", maCompMngDetailDTO.getSubImgType());
            
            maCompMngDetailDTO.setSubImgType("LOGINSUBTITLE");
            fileCopy(maCompMngDetailDTO.getCompId(), "LOGO", maCompMngDetailDTO.getSubImgType());
            
            maCompMngDetailDTO.setSubImgType("MAINTITLE");
            fileCopy(maCompMngDetailDTO.getCompId(), "LOGO", maCompMngDetailDTO.getSubImgType());
        }
    }

}

