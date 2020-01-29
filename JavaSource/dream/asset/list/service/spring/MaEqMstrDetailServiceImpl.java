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
import common.util.FileUtil;
import dream.asset.list.dao.MaEqMstrAsmbDetailDAO;
import dream.asset.list.dao.MaEqMstrAssetDetailDAO;
import dream.asset.list.dao.MaEqMstrDetailDAO;
import dream.asset.list.dao.MaEqMstrPartDetailDAO;
import dream.asset.list.dao.MaEqMstrSpecDetailDAO;
import dream.asset.list.dto.AssetListITDetailDTO;
import dream.asset.list.dto.MaEqBuildMstrDetailDTO;
import dream.asset.list.dto.MaEqDeviceMstrDetailDTO;
import dream.asset.list.dto.MaEqMoldMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqToolMstrDetailDTO;
import dream.asset.list.service.MaEqMstrAsmbListService;
import dream.asset.list.service.MaEqMstrDetailService;
import dream.asset.list.service.MaEqMstrListService;
import dream.asset.list.service.MaEqMstrPartListService;
import dream.asset.list.service.MaEqMstrSpecListService;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.doc.img.dto.MaDocImgDetailDTO;

/**
 * 설비마스터 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaEqMstrDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrDetailServiceTarget"
 * @spring.txbn id="maEqMstrDetailService"
 * @spring.property name="maEqMstrDetailDAO" ref="maEqMstrDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 * @spring.property name="maEqMstrSpecDetailDAO" ref="maEqMstrSpecDetailDAO"
 * @spring.property name="maEqMstrPartDetailDAO" ref="maEqMstrPartDetailDAO"
 * @spring.property name="maEqMstrAsmbDetailDAO" ref="maEqMstrAsmbDetailDAO"
 * @spring.property name="maEqMstrAssetDetailDAO" ref="maEqMstrAssetDetailDAO"
 * @spring.property name="commRevService" ref="commRevService"
 */
public class MaEqMstrDetailServiceImpl implements MaEqMstrDetailService
{
    private MaEqMstrDetailDAO maEqMstrDetailDAO = null;
    
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    
    // 설비제원
    private MaEqMstrSpecDetailDAO maEqMstrSpecDetailDAO = null;
    // 구성부품
    private MaEqMstrPartDetailDAO maEqMstrPartDetailDAO = null;
    // 설비부위
    private MaEqMstrAsmbDetailDAO maEqMstrAsmbDetailDAO = null;
    // 관련자산
    private MaEqMstrAssetDetailDAO maEqMstrAssetDetailDAO = null;

    private CommRevService commRevService = null;
    
    

	public CommRevService getCommRevService()
    {
        return commRevService;
    }

    public void setCommRevService(CommRevService commRevService)
    {
        this.commRevService = commRevService;
    }

    public MaEqMstrPartDetailDAO getMaEqMstrPartDetailDAO() {
		return maEqMstrPartDetailDAO;
	}

	public MaEqMstrSpecDetailDAO getMaEqMstrSpecDetailDAO() {
		return maEqMstrSpecDetailDAO;
	}

	public void setMaEqMstrSpecDetailDAO(MaEqMstrSpecDetailDAO maEqMstrSpecDetailDAO) {
		this.maEqMstrSpecDetailDAO = maEqMstrSpecDetailDAO;
	}

	public void setMaEqMstrPartDetailDAO(MaEqMstrPartDetailDAO maEqMstrPartDetailDAO) {
		this.maEqMstrPartDetailDAO = maEqMstrPartDetailDAO;
	}

	public MaEqMstrAsmbDetailDAO getMaEqMstrAsmbDetailDAO() {
		return maEqMstrAsmbDetailDAO;
	}

	public void setMaEqMstrAsmbDetailDAO(MaEqMstrAsmbDetailDAO maEqMstrAsmbDetailDAO) {
		this.maEqMstrAsmbDetailDAO = maEqMstrAsmbDetailDAO;
	}

	public MaEqMstrAssetDetailDAO getMaEqMstrAssetDetailDAO() {
		return maEqMstrAssetDetailDAO;
	}

	public void setMaEqMstrAssetDetailDAO(MaEqMstrAssetDetailDAO maEqMstrAssetDetailDAO) {
		this.maEqMstrAssetDetailDAO = maEqMstrAssetDetailDAO;
	}

    public MaDocImgDetailDAO getMaDocImgDetailDAO()
    {
        return maDocImgDetailDAO;
    }

    public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO)
    {
        this.maDocImgDetailDAO = maDocImgDetailDAO;
    }

    public MaEqMstrDetailDAO getMaEqMstrDetailDAO() {
		return maEqMstrDetailDAO;
	}

	public void setMaEqMstrDetailDAO(MaEqMstrDetailDAO maEqMstrDetailDAO) {
		this.maEqMstrDetailDAO = maEqMstrDetailDAO;
	}

	public MaEqMstrDetailDTO findDetail(MaEqMstrCommonDTO maEqmstrCommonDTO, User user)throws Exception
    {
//	    List resultList = fileCopy(maEqmstrCommonDTO.getEquipId(), "EQMSTR");
	      
        MaEqMstrDetailDTO maEqMstrDetailDTO =  maEqMstrDetailDAO.findDetail(maEqmstrCommonDTO.getEquipId(),user);
        
//        maEqMstrDetailDTO.setSlideFileList(resultList);
        
        return maEqMstrDetailDTO;
    }
	
	public MaEqMoldMstrDetailDTO findMoldDetail(MaEqMstrCommonDTO maEqmstrCommonDTO, User user)throws Exception
    {
	    MaEqMoldMstrDetailDTO MaEqMoldMstrDetailDTO =  maEqMstrDetailDAO.findMoldDetail(maEqmstrCommonDTO.getEquipId(),user);
        
        return MaEqMoldMstrDetailDTO;
    }
	public MaEqToolMstrDetailDTO findToolDetail(MaEqMstrCommonDTO maEqmstrCommonDTO, User user)throws Exception
    {
	    MaEqToolMstrDetailDTO maEqToolMstrDetailDTO =  maEqMstrDetailDAO.findToolDetail(maEqmstrCommonDTO.getEquipId(),user);
        
        return maEqToolMstrDetailDTO;
    }
	public MaEqBuildMstrDetailDTO findBuildDetail(MaEqMstrCommonDTO maEqmstrCommonDTO, User user)throws Exception
    {
		MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO =  maEqMstrDetailDAO.findBuildDetail(maEqmstrCommonDTO.getEquipId(),user);
        
        return maEqBuildMstrDetailDTO;
    }
	
	public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception
    {   
		
		if("".equals(maEqMstrDetailDTO.getPlant()))
			maEqMstrDetailDTO.setPlant(maEqMstrDetailDAO.findEqPlant(maEqMstrDetailDTO));
		
		maEqMstrDetailDAO.insertDetail(maEqMstrDetailDTO,loginUser);
		if(!"".equals(maEqMstrDetailDTO.getEqCtgId()))
		{
		    MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", loginUser);
		    MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) CommonUtil.getBean("maEqMstrPartListService", loginUser);
		    MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) CommonUtil.getBean("maEqMstrSpecListService", loginUser);
		    
		    //설비종류별부위
		    maEqMstrAsmbListService.inputAsmbList(maEqMstrDetailDTO.getEqCtgId(), maEqMstrDetailDTO.getEquipId(), loginUser);
		    //설비종류별부품
		    maEqMstrPartListService.inputEqCtgPartList(maEqMstrDetailDTO.getEqCtgId(), maEqMstrDetailDTO.getEquipId(), loginUser);
		    //설비종류별제원
		    maEqMstrSpecListService.inputEqCtgSpecList(maEqMstrDetailDTO.getEqCtgId(), maEqMstrDetailDTO.getEquipId(), loginUser);
		}
		maEqMstrDetailDAO.insertEqHist(maEqMstrDetailDTO,loginUser);
		maEqMstrDetailDAO.SP_IF_UPD_TXEQUIPMENT(maEqMstrDetailDTO.getEquipId(),loginUser);
		
		// TAEQASSLIST default 데이터 생성
		maEqMstrDetailDAO.insertAssDetail(loginUser, maEqMstrDetailDTO.getEquipId());

        return 0;
    }
	
	
	public int insertMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User loginUser) throws Exception
	{   
		maEqMstrDetailDAO.mergeMoldDetail(maEqMstrDetailDTO, maEqMoldMstrDetailDTO, loginUser);
		return 0;
	}
	
	
	public int insertToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User loginUser) throws Exception
	{   
		maEqMstrDetailDAO.mergeToolDetail(maEqMstrDetailDTO, maEqToolMstrDetailDTO, loginUser);
		return 0;
	}
	
	
	public int insertBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User loginUser) throws Exception
	{   
		maEqMstrDetailDAO.mergeBuildDetail(maEqMstrDetailDTO, maEqBuildMstrDetailDTO, loginUser);
		return 0;
	}
	
	
	public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception
    {
	    //S:예비설비
	    //X:폐기설비
	    //D:불용설비
	    //P:수리대기설비
	    //O:수리중설비
	    //T:이관대기설비
	    //변경 시
		if("S".equals(maEqMstrDetailDTO.getEqStatusId())||"D".equals(maEqMstrDetailDTO.getEqStatusId())||"X".equals(maEqMstrDetailDTO.getEqStatusId())
		        ||"P".equals(maEqMstrDetailDTO.getEqStatusId())||"O".equals(maEqMstrDetailDTO.getEqStatusId())||"T".equals(maEqMstrDetailDTO.getEqStatusId())){
			//폐기일때 예방작업일정을 전체 삭제해야 함.
			maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
			//스케쥴 재 생성 해야 함.
			maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);
			
			//해당 계측기 검색 해서 status 업데이트 그리고 updatePmActive와 deleteSchedAllPmEquip 해야 함. 
			MaEqMstrListService maEqMstrListService = (MaEqMstrListService)CommonUtil.getBean("maEqMstrListService", loginUser);
			MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
			maEqMstrCommonDTO.setFilterIsLastVersionId("Y");
			maEqMstrCommonDTO.setEqStatusId("R");
			maEqMstrCommonDTO.setFilterPEqId(maEqMstrDetailDTO.getEquipId());

			List<Map> toolList = maEqMstrListService.findEqToolMstrList(maEqMstrCommonDTO, loginUser);
			MaEqMstrDetailDTO dto = new MaEqMstrDetailDTO();
			String toolEquipId;
			for(Map toolMap : toolList)
			{ 
			    toolEquipId = String.valueOf(toolMap.get("equipId"));
			    dto.setEquipId(toolEquipId);
			    dto.setCompNo(maEqMstrDetailDTO.getCompNo());
			    
			    //계측기 상태 변경 
			    maEqMstrDetailDAO.updateStatus(toolEquipId, maEqMstrDetailDTO.getCompNo(), maEqMstrDetailDTO.getEqStatusId());
			    //폐기일때 예방작업일정을 전체 삭제해야 함.
	            maEqMstrDetailDAO.updatePmActive(dto);
	            //스케쥴 재 생성 해야 함.
	            maEqMstrDetailDAO.deleteSchedAllPmEquip(dto);
			}
		}
		
		if("".equals(maEqMstrDetailDTO.getPlant()))
			maEqMstrDetailDTO.setPlant(maEqMstrDetailDAO.findEqPlant(maEqMstrDetailDTO));
		
		MaEqMstrDetailDTO equipDetail = maEqMstrDetailDAO.findDetail(maEqMstrDetailDTO.getEquipId(), loginUser);
		String itemNo = equipDetail.getItemNo();
		
		maEqMstrDetailDAO.updateDetail(maEqMstrDetailDTO, loginUser);
		
        //설비종류를 지웠을 때 unlink
        if("".equals(maEqMstrDetailDTO.getEqCtgId()) && !"".equals(maEqMstrDetailDTO.getOriginEqCtgId()))
        {
            MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", loginUser);
            MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) CommonUtil.getBean("maEqMstrPartListService", loginUser);
            MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) CommonUtil.getBean("maEqMstrSpecListService", loginUser);
            
            //설비부위
            maEqMstrAsmbListService.unlinkOldEqAsmb(maEqMstrDetailDTO.getEquipId(), loginUser);
            //설비부품
            maEqMstrPartListService.unlinkOldEqPart(maEqMstrDetailDTO.getEquipId(), loginUser);
            //설비제원
            maEqMstrSpecListService.unlinkOldEqSpec(maEqMstrDetailDTO.getEquipId(), loginUser);
        }
        else if(!maEqMstrDetailDTO.getEqCtgId().equals(maEqMstrDetailDTO.getOriginEqCtgId())) // 설비종류 변경
        {
            MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", loginUser);
            MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) CommonUtil.getBean("maEqMstrPartListService", loginUser);
            MaEqMstrSpecListService maEqMstrSpecListService = (MaEqMstrSpecListService) CommonUtil.getBean("maEqMstrSpecListService", loginUser);
            
            //설비종류별부위
            maEqMstrAsmbListService.inputAsmbList(maEqMstrDetailDTO.getEqCtgId(), maEqMstrDetailDTO.getEquipId(), loginUser);
            //설비종류별부품
            maEqMstrPartListService.inputEqCtgPartList(maEqMstrDetailDTO.getEqCtgId(), maEqMstrDetailDTO.getEquipId(), loginUser);
            //설비종류별제원
            maEqMstrSpecListService.inputEqCtgSpecList(maEqMstrDetailDTO.getEqCtgId(), maEqMstrDetailDTO.getEquipId(), loginUser);
        }

        maEqMstrDetailDAO.insertEqHist(maEqMstrDetailDTO, loginUser);

        if(!itemNo.equals(maEqMstrDetailDTO.getItemNo())) {
			maEqMstrDetailDAO.updRevObjectNo(itemNo, maEqMstrDetailDTO, loginUser);
			maEqMstrDetailDAO.updEqHistItemNo(itemNo, maEqMstrDetailDTO, loginUser);
			maEqMstrDetailDAO.updEqItemNo(itemNo, maEqMstrDetailDTO, loginUser);
		}
		
		maEqMstrDetailDAO.SP_IF_UPD_TXEQUIPMENT(maEqMstrDetailDTO.getEquipId(), loginUser);
        
        return 0;
    }
   
	public int updateMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User loginUser) throws Exception
	{
		maEqMstrDetailDAO.mergeMoldDetail(maEqMstrDetailDTO, maEqMoldMstrDetailDTO, loginUser);
		return 0;
	}
	
	public int updateToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User loginUser) throws Exception
	{
		maEqMstrDetailDAO.mergeToolDetail(maEqMstrDetailDTO, maEqToolMstrDetailDTO, loginUser);
		return 0;
	}
	
	public int updateBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User loginUser) throws Exception
	{
		maEqMstrDetailDAO.mergeBuildDetail(maEqMstrDetailDTO, maEqBuildMstrDetailDTO, loginUser);
		return 0;
	}
	
	public List fileCopyUpload(MaEqMstrDetailDTO maEqMstrDetailDTO, String objectType, User user) throws InvalidKeyException, URISyntaxException, StorageException
	{
	//	List imgDataList = maDocImgDetailDAO.getImgFileList(maEqMstrDetailDTO.getEquipId(), objectType,"");
		
		List imgDataList = null;

		switch(objectType)
		{
			case "EQMSTR" :	// 설비 상세
				imgDataList = maDocImgDetailDAO.getImgFileList(maEqMstrDetailDTO.getOldEquipId(), objectType,"");
				break;
			case "EQASMB" :	// 설비부위 상세
				imgDataList = maDocImgDetailDAO.getImgFileList(maEqMstrDetailDTO.getOldEqAsmbId(), objectType,"");
				break;
			default : 
				imgDataList = maDocImgDetailDAO.getImgFileList(maEqMstrDetailDTO.getOldEqAsmbId(), objectType,"");
				break;
		}
 
		
        //월별로 폴더를 만들어 복사한다.
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        //저장 경로 저장
        String nfFilePath = user.getCompNo() + File.separator+ "IMAGE" +File.separator + ft.format(date);
		String copyFilePath = nfFilePath;
        
		for(int i = 0; imgDataList.size() > i ; i++)
		{
			Map map = (Map)imgDataList.get(i);
			
			String fileNo = String.valueOf(map.get("IMGDATA_ID"));
			String fileName = String.valueOf(map.get("FILE_NAME"));
			String filePath = String.valueOf(map.get("NFFILEPATH"));
			String imageId = String.valueOf(map.get("IMAGE_ID"));

			//복사
			MaDocImgDetailDTO maDocImgDetailDTO = maDocImgDetailDAO.findDetail(imageId, user);
			String copyFileName = maDocImgDetailDAO.getNextSequence("SQAIMGDATA_ID")+"";
			String copyimageId = maDocImgDetailDAO.getNextSequence("SQAIMAGE_ID")+"";
			
			maDocImgDetailDTO.setNfFilePath(copyFilePath);
			maDocImgDetailDTO.setImgDataId(copyFileName);
			maDocImgDetailDTO.setImageId(copyimageId);

			String originFile = MwareConfig.getFileDir() + filePath + File.separator + fileNo;
			String targetFile = MwareConfig.getFileDir() + copyFilePath + File.separator + copyFileName;

			
			try
			{

				FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream"+File.separator+"imgSlide"+File.separator);

				maDocImgDetailDAO.insertCopyFileInfo(fileNo, maDocImgDetailDTO, user);
				
				switch(objectType)
				{
					case "EQMSTR" :	// 설비 상세
						maDocImgDetailDAO.insertCopyDetail(copyimageId, imageId, maEqMstrDetailDTO.getEquipId(), user);
						break;
					case "EQASMB" : // 설비부위 상세
						maDocImgDetailDAO.insertCopyDetail(copyimageId, imageId, maEqMstrDetailDTO.getEqAsmbId(), user);
						break;
					default : 
						maDocImgDetailDAO.insertCopyDetail(copyimageId, imageId, maEqMstrDetailDTO.getEquipId(), user);
						break;
				}	
				
			}catch (IOException e){}
		}
		

		switch(objectType)
		{
			case "EQMSTR" :	// 설비 상세
				imgDataList = maDocImgDetailDAO.getImgFileList(maEqMstrDetailDTO.getEquipId(), objectType, "");
				break;
			case "EQASMB" : // 설비부위 상세
				imgDataList = maDocImgDetailDAO.getImgFileList(maEqMstrDetailDTO.getEqAsmbId(), objectType, "");
				break;
			default : 
				imgDataList = maDocImgDetailDAO.getImgFileList(maEqMstrDetailDTO.getEquipId(), objectType, "");
				break;
		}
		
		return imgDataList;
	}
    
    public List findSlideImage(MaEqMstrCommonDTO maEqMstrCommonDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException
    {
        return FileUtil.makeSlideImg(maEqMstrCommonDTO.getEquipId(), "EQMSTR");
    }
    

	public List getReportView(MaEqMstrDetailDTO maEqMstrDetailDTO,User user) {
		Map<String, Object> reportMap = null;
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		
		List detailList = maEqMstrDetailDAO.findReportEquipDetail(maEqMstrDetailDTO, user);
     	reportMap = (Map)detailList.get(0);
     	reportMap.put("EQPART_LIST", maEqMstrDetailDAO.findReportWoPartDetail(maEqMstrDetailDTO));
     	reportMap.put("REP_LIST", maEqMstrDetailDAO.findReportWoRepDetail(maEqMstrDetailDTO));
//     	reportMap.put("OIL_LIST", maEqMstrDetailDAO.findReportWoOilDetail(maEqMstrDetailDTO));
     	reportMap.put("IMG_LIST", maEqMstrDetailDAO.findReportWoImgDetail(maEqMstrDetailDTO.getEquipId(), "EQMSTR"));
        
     	reportList.add((Map)reportMap);

     	return reportList;
	}

	@Override
	public MaEqDeviceMstrDetailDTO findDeviceDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) 
	{
		return maEqMstrDetailDAO.findDeviceDetail(maEqMstrCommonDTO ,user);
        
	}
	
	@Override
	public AssetListITDetailDTO findITDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) 
	{
		return maEqMstrDetailDAO.findITDetail(maEqMstrCommonDTO ,user);
		
	}

	@Override
	public void insertDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,
			User loginUser) {
		maEqMstrDetailDAO.mergeDeviceDetail(maEqMstrDetailDTO, maEqDeviceMstrDetailDTO, loginUser);
		
	}
	
	@Override
	public void insertITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User loginUser) {
		maEqMstrDetailDAO.mergeITDetail(maEqMstrDetailDTO, assetListITDetailDTO, loginUser);
		
	}

	@Override
	public void updateDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,
			User loginUser) {
		maEqMstrDetailDAO.mergeDeviceDetail(maEqMstrDetailDTO, maEqDeviceMstrDetailDTO, loginUser);
		
	}
	
	@Override
	public void updateITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User loginUser) {
		maEqMstrDetailDAO.mergeITDetail(maEqMstrDetailDTO, assetListITDetailDTO, loginUser);
	}
	
	public int completeDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user){
		
		maEqMstrDetailDAO.updateRevisionHist(maEqMstrCommonDTO, maEqMstrDetailDTO, user);
		
		if("W".equals(maEqMstrDetailDTO.getRevisionStatusId())){
			//제정중
			
		}else if("P".equals(maEqMstrDetailDTO.getRevisionStatusId())){
			//개정중
			maEqMstrDetailDAO.updateBeforeIsLastVersion(maEqMstrCommonDTO, maEqMstrDetailDTO, user);
		}
		
		maEqMstrDetailDAO.updateIsLastVersion(maEqMstrCommonDTO, maEqMstrDetailDTO, user);
		
		return 0;
	}

	@Override
	public int insertRevisionHistAndUpdateMstr(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception {
	    CommRevService commRevService = (CommRevService) CommonUtil.getBean("commRevService", user);
	    
		String histId = maEqMstrDetailDAO.getNextSequence("SQAREVISIONHIST_ID");
		int result = 0;
		
		CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
        commRevCommonDTO.setRevisionhistId(histId);
        commRevCommonDTO.setCompNo(user.getCompNo());
        commRevCommonDTO.setObjectId(maEqMstrDetailDTO.getEquipId());
        commRevCommonDTO.setObjectNo(maEqMstrDetailDTO.getItemNo());
        commRevCommonDTO.setRevisionObjType("ASSET");
        commRevCommonDTO.setRevisionStatusId("C");
        commRevCommonDTO.setRevisionStepStatusId("CMP");
        commRevCommonDTO.setRevisionType("C");
        commRevCommonDTO.setRevNo("1.00");
        commRevCommonDTO.setWrkDate(DateUtil.getDateTime("yyyyMMdd"));
        commRevCommonDTO.setWrkEmpId(user.getEmpId());
        commRevCommonDTO.setRevDesc(maEqMstrDetailDTO.getEquipDesc());
        commRevService.insertRevHist(commRevCommonDTO, user);
        
		result+= maEqMstrDetailDAO.updateEqMstrLastVersion(maEqMstrCommonDTO, maEqMstrDetailDTO, user, histId);
		
		return result;
	}

	public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User loginUser) throws Exception
	{
		int result = 0;

		CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
        
    	//새로운 시퀀스 받기.
		String newSeq = maEqMstrDetailDTO.getEquipId();
		String revhistId = maEqMstrDetailDAO.getNextSequence("SQAREVISIONHIST_ID");
		
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
		
		//이미지 파일 복사
		List resultList = fileCopyUpload(maEqMstrDetailDTO, "EQMSTR", loginUser);
		maEqMstrDetailDTO.setSlideFileList(resultList);
		
		//설비 데이터 복사
        result = result + maEqMstrDetailDAO.copyDetail(maEqMstrDetailDTO, revhistId, revisionStatus, loginUser);
		
		String oldEquipId = maEqMstrDetailDTO.getOldEquipId();
		String newEquipId = maEqMstrDetailDTO.getEquipId();
		String oldKeyId = "";
		String newKeyId = "";

		// 설비자산(상세) 탭
		maEqMstrSpecDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, loginUser);
		maEqMstrPartDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, loginUser);
		maEqMstrAsmbDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, loginUser);
		maEqMstrAssetDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, loginUser);
		maEqMstrDetailDAO.copyEqHist(maEqMstrDetailDTO,loginUser);
		maEqMstrDetailDAO.SP_IF_UPD_TXEQUIPMENT(newEquipId, loginUser);
		
		return 0;
	}
	
	public int insertCopyMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception
	{   
		maEqMstrDetailDAO.copyMoldDetail(maEqMstrDetailDTO, loginUser);
		return 0;
	}
	public int insertCopyToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception
	{   
		maEqMstrDetailDAO.copyToolDetail(maEqMstrDetailDTO, loginUser);
		return 0;
	}
	public int insertCopyBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception
	{   
		maEqMstrDetailDAO.copyBuildDetail(maEqMstrDetailDTO, loginUser);
		return 0;
	}
	public void insertCopyDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) 
	{
		maEqMstrDetailDAO.copyDeviceDetail(maEqMstrDetailDTO, loginUser);
	}
	public void insertCopyITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) 
	{
		maEqMstrDetailDAO.copyITDetail(maEqMstrDetailDTO, loginUser);
	}

    @Override
    public String validItemNo(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception
    {
    	String rsltVal = "";
    	MaEqMstrDetailDTO chkItemNo = maEqMstrDetailDAO.findDetail(maEqMstrDetailDTO.getEquipId(), user);
    	
    	if(maEqMstrDetailDTO.getItemNo().equals(chkItemNo.getItemNo())) {
    		rsltVal = "0";
    	} else {
    		rsltVal = maEqMstrDetailDAO.validItemNo(maEqMstrDetailDTO, user);
    	}
    	
    	return rsltVal;
    }

    public void delPmSchByStatus(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser)
    {
        //불용설비, 예비설비 또는  폐기설비로 변경 시
        if("S".equals(maEqMstrDetailDTO.getEqStatusId())||"D".equals(maEqMstrDetailDTO.getEqStatusId())||"X".equals(maEqMstrDetailDTO.getEqStatusId())){
            //폐기일때 예방작업일정을 전체 삭제해야 함.
            maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
            //스케쥴 재 생성 해야 함.
            maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);
            
            //해당 계측기 검색 해서 status 업데이트 그리고 updatePmActive와 deleteSchedAllPmEquip 해야 함. 
            MaEqMstrListService maEqMstrListService = (MaEqMstrListService)CommonUtil.getBean("maEqMstrListService", loginUser);
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            maEqMstrCommonDTO.setFilterIsLastVersionId("Y");
            maEqMstrCommonDTO.setEqStatusId("R");
            maEqMstrCommonDTO.setFilterPEqId(maEqMstrDetailDTO.getEquipId());

            List<Map> toolList = maEqMstrListService.findEqToolMstrList(maEqMstrCommonDTO, loginUser);
            MaEqMstrDetailDTO dto = new MaEqMstrDetailDTO();
            String toolEquipId;
            for(Map toolMap : toolList)
            { 
                toolEquipId = String.valueOf(toolMap.get("equipId"));
                dto.setEquipId(toolEquipId);
                dto.setCompNo(maEqMstrDetailDTO.getCompNo());
                
                //계측기 상태 변경 
                maEqMstrDetailDAO.updateStatus(toolEquipId, maEqMstrDetailDTO.getCompNo(), maEqMstrDetailDTO.getEqStatusId());
                //교정기준서에서 계측기 inactive.
                maEqMstrDetailDAO.updatePmActive(dto);
                //폐기일때 예방작업일정을 전체 삭제해야 함.
                maEqMstrDetailDAO.deleteSchedAllPmEquip(dto);
            }
        }
    }
	
}
