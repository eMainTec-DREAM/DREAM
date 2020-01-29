package dream.asset.list.service.spring;

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
import dream.asset.list.dao.MaEqMstrMoldDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;
import dream.asset.list.service.MaEqMstrDetailService;
import dream.asset.list.service.MaEqMstrMoldDetailService;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;
import dream.doc.img.dao.MaDocImgDetailDAO;

/**
 * 설비마스터 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaEqMstrDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrMoldDetailServiceTarget"
 * @spring.txbn id="maEqMstrMoldDetailService"
 * @spring.property name="maEqMstrMoldDetailDAO" ref="maEqMstrMoldDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 * @spring.property name="maEqMstrDetailService" ref="maEqMstrDetailService"
 * @spring.property name="commRevService" ref="commRevService"
 */
public class MaEqMstrMoldDetailServiceImpl implements MaEqMstrMoldDetailService
{
    private MaEqMstrMoldDetailDAO maEqMstrMoldDetailDAO = null;
    
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
    private CommRevService commRevService = null;
    
    private MaEqMstrDetailService maEqMstrDetailService = null;
    
    
    public MaEqMstrDetailService getMaEqMstrDetailService()
    {
        return maEqMstrDetailService;
    }

    public void setMaEqMstrDetailService(
            MaEqMstrDetailService maEqMstrDetailService)
    {
        this.maEqMstrDetailService = maEqMstrDetailService;
    }

    public CommRevService getCommRevService() {
		return commRevService;
	}

	public void setCommRevService(CommRevService commRevService) {
		this.commRevService = commRevService;
	}

	public MaDocImgDetailDAO getMaDocImgDetailDAO()
    {
        return maDocImgDetailDAO;
    }

    public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO)
    {
        this.maDocImgDetailDAO = maDocImgDetailDAO;
    }

	public MaEqMstrMoldDetailDAO getMaEqMstrMoldDetailDAO() {
		return maEqMstrMoldDetailDAO;
	}

	public void setMaEqMstrMoldDetailDAO(MaEqMstrMoldDetailDAO maEqMstrMoldDetailDAO) {
		this.maEqMstrMoldDetailDAO = maEqMstrMoldDetailDAO;
	}

	public MaEqMstrDetailDTO findDetail(MaEqMstrCommonDTO maEqmstrCommonDTO, String compNo, User user)throws Exception
    {
	    List resultList = fileCopy(maEqmstrCommonDTO.getEquipId(), "EQMSTR");
	      
        MaEqMstrDetailDTO maEqMstrDetailDTO =  maEqMstrMoldDetailDAO.findDetail(maEqmstrCommonDTO.getEquipId(),compNo,user);
        
        maEqMstrDetailDTO.setSlideFileList(resultList);
        
        return maEqMstrDetailDTO;
    }
	
	public MaEqMstrMoldDetailDTO findMoldDetail(MaEqMstrCommonDTO maEqmstrCommonDTO, User user)throws Exception
    {
	      
	    MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO =  maEqMstrMoldDetailDAO.findMoldDetail(maEqmstrCommonDTO.getEquipId(),user);
        
        return maEqMstrMoldDetailDTO;
    }
	
	public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser) throws Exception
    {   
		maEqMstrMoldDetailDAO.insertDetail(maEqMstrDetailDTO);
		maEqMstrMoldDetailDAO.insertMoldDetail(maEqMstrDetailDTO,maEqMstrMoldDetailDTO, loginUser);
		maEqMstrMoldDetailDAO.insertEqAsmb(maEqMstrDetailDTO);
		maEqMstrMoldDetailDAO.insertEqHist(maEqMstrDetailDTO);
		maEqMstrMoldDetailDAO.SP_IF_UPD_TXEQUIPMENT(maEqMstrDetailDTO.getEquipId(), loginUser);
		
		if("N".equals(MwareConfig.getIsUseAssetRevision())){
		    maEqMstrDetailService.insertRevisionHistAndUpdateMstr(new MaEqMstrCommonDTO(), maEqMstrDetailDTO, loginUser);
        }
		
        return 0;
    }
	
	public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser) throws Exception
    {
		//예비설비또는 폐기설비로 변경 시
		if("S".equals(maEqMstrDetailDTO.getEqStatusId())||"X".equals(maEqMstrDetailDTO.getEqStatusId())){
			maEqMstrMoldDetailDAO.updatePmActive(maEqMstrDetailDTO);
			maEqMstrMoldDetailDAO.deletePmSched(maEqMstrDetailDTO);
		}
        maEqMstrMoldDetailDAO.updateDetail(maEqMstrDetailDTO);
        maEqMstrMoldDetailDAO.updateMoldDetail(maEqMstrDetailDTO,maEqMstrMoldDetailDTO, loginUser);
        maEqMstrMoldDetailDAO.insertEqAsmb(maEqMstrDetailDTO);
		maEqMstrMoldDetailDAO.insertEqHist(maEqMstrDetailDTO);
		maEqMstrMoldDetailDAO.SP_IF_UPD_TXEQUIPMENT(maEqMstrDetailDTO.getEquipId(), loginUser);
        
        return 0;
    }
	public int completeDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		
		maEqMstrMoldDetailDAO.updateRevisionHist(maEqMstrCommonDTO, maEqMstrDetailDTO, user);
		
		if("W".equals(maEqMstrDetailDTO.getRevisionStatusId())){
			//제정중
			
		}else if("P".equals(maEqMstrDetailDTO.getRevisionStatusId())){
			//개정중
			maEqMstrMoldDetailDAO.updateBeforeIsLastVersion(maEqMstrCommonDTO, maEqMstrDetailDTO, user);
		}
		
		maEqMstrMoldDetailDAO.updateIsLastVersion(maEqMstrCommonDTO, maEqMstrDetailDTO, user);
		
		return 0;
	}
	
	public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser) throws Exception
	{
		int result = 0;

		CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
        
    	//새로운 시퀀스 받기.
		String newSeq = maEqMstrDetailDTO.getEquipId();
		String revhistId = maEqMstrMoldDetailDAO.getNextSequence("SQAREVISIONHIST_ID");
		
		commRevCommonDTO.setCompNo(loginUser.getCompNo());
		commRevCommonDTO.setRevisionhistId(revhistId);
		commRevCommonDTO.setObjectId(newSeq);
		commRevCommonDTO.setObjectNo(newSeq);
		commRevCommonDTO.setRevisionObjType("ASSET");
		commRevCommonDTO.setDocNo("");
		commRevCommonDTO.setRevisionType("C");
		commRevCommonDTO.setRevNo("");
		commRevCommonDTO.setWrkDate(CommonUtil.convertDate(DateUtil.getDate()));
		commRevCommonDTO.setWrkEmpId(loginUser.getEmpId());
		commRevCommonDTO.setRevDesc("");
		
		String revisionStatus = "";
		if("N".equals(MwareConfig.getIsUseAssetRevision())){
    		commRevCommonDTO.setRevisionStatusId("C");
    		commRevCommonDTO.setRevisionStepStatusId("CMP");
    		revisionStatus = "C";
		}else{
    		commRevCommonDTO.setRevisionStatusId("W");
    		commRevCommonDTO.setRevisionStepStatusId("WRT");
    		revisionStatus = "W";
		}
		
		commRevService.insertRevHist(commRevCommonDTO, loginUser);
		
		//이미지 복사
		List resultList = fileCopy(maEqMstrDetailDTO.getOldEquipId(), "EQMSTR");
		maEqMstrDetailDTO.setSlideFileList(resultList);
		
		//금형설비 복사
		result = result + maEqMstrMoldDetailDAO.copyDetail(maEqMstrDetailDTO, revhistId, revisionStatus, loginUser);
		
		String newEquipId = maEqMstrDetailDTO.getEquipId();

		maEqMstrMoldDetailDAO.copyMoldDetail(maEqMstrDetailDTO, maEqMstrMoldDetailDTO, loginUser);
		maEqMstrMoldDetailDAO.insertCopyEqHist(maEqMstrDetailDTO, loginUser);
		maEqMstrMoldDetailDAO.SP_IF_UPD_TXEQUIPMENT(newEquipId, loginUser);
		
        return 0;
	}
	public List fileCopy(String objectId, String objectType) throws InvalidKeyException, URISyntaxException, StorageException
    {
       List imgList = maDocImgDetailDAO.getImgFileList(objectId, objectType,"");

       for(int i = 0; imgList.size() > i ; i++)
       {
           Map map = (Map)imgList.get(i);
           
           String fileNo = String.valueOf(map.get("IMGDATA_ID"));
           String fileName = String.valueOf(map.get("FILE_NAME"));
           String filePath = String.valueOf(map.get("NFFILEPATH"));
           
           String originFile = MwareConfig.getFileDir() + filePath + File.separator + fileNo;
           String targetFile = MwareConfig.getWebAppPath()+"imgSlide" + File.separator + fileName;
           
           try
           {
               FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"imgSlide"+ File.separator);
           }catch (IOException e){}
       }


        // file 복사
        
        deleteTempResultFiles();
        
        return imgList;
    }
    
    public void deleteTempResultFiles()
    {
        //=======================================================
        // 임시 파일 dir 에 모든 파일 을 삭제한다.
        File dirFile = new File(MwareConfig.getWebAppPath()+"imgSlide\\");
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
    
    public List findSlideImage(MaEqMstrCommonDTO maEqMstrCommonDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException
    {
        // TODO Auto-generated method stub
        return fileCopy(maEqMstrCommonDTO.getEquipId(), "EQMSTR");
    }

	public List getReportView(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		Map<String, Object> reportMap = null;
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		
		List detailList = maEqMstrMoldDetailDAO.findReportEquipDetail(maEqMstrDetailDTO);
     	reportMap = (Map)detailList.get(0);
     	reportMap.put("EQPART_LIST", maEqMstrMoldDetailDAO.findReportWoPartDetail(maEqMstrDetailDTO));
     	reportMap.put("REP_LIST", maEqMstrMoldDetailDAO.findReportWoRepDetail(maEqMstrDetailDTO));
     	reportMap.put("OIL_LIST", maEqMstrMoldDetailDAO.findReportWoOilDetail(maEqMstrDetailDTO));
//     	reportMap.put("IMG_LIST", maEqMstrMoldDetailDAO.findReportWoImgDetail(maEqMstrDetailDTO));
        
     	reportList.add((Map)reportMap);

	     return reportList;
	}
}
