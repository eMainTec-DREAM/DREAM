package dream.asset.list.service.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.asset.list.dao.MaEqMstrDetailDAO;
import dream.asset.list.dao.MaEqMstrListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.service.MaEqMstrDetailService;
import dream.asset.list.service.MaEqMstrListService;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;

/**
 * 설비마스터 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaEqMstrListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrListServiceTarget"
 * @spring.txbn id="maEqMstrListService"
 * @spring.property name="maEqMstrListDAO" ref="maEqMstrListDAO"
 * @spring.property name="commRevService" ref="commRevService"
 */
public class MaEqMstrListServiceImpl implements MaEqMstrListService
{
    private MaEqMstrListDAO maEqMstrListDAO = null;
    private CommRevService commRevService = null;
    
	public CommRevService getCommRevService() {
		return commRevService;
	}

	public void setCommRevService(CommRevService commRevService) {
		this.commRevService = commRevService;
	}

	public MaEqMstrListDAO getMaEqMstrListDAO() {
		return maEqMstrListDAO;
	}

	public void setMaEqMstrListDAO(MaEqMstrListDAO maEqMstrListDAO) {
		this.maEqMstrListDAO = maEqMstrListDAO;
	}

	public List findEqMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
        return maEqMstrListDAO.findEqMstrList(maEqMstrCommonDTO, user);
    }
	
    /**생산설비FIND 
     * @throws IOException */
    public List findEqMachMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user) throws IOException
    {
       
        return maEqMstrListDAO.findEqMachMstrList(maEqMstrCommonDTO, user);
    }
    /**Utility Find*/
	public List findEqUtilMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)
	{
	    return maEqMstrListDAO.findEqUtilMstrList(maEqMstrCommonDTO, user);
	}
	/**토지,건물 Find*/
    public List findEqBuildMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)
    {
        return maEqMstrListDAO.findEqBuildMstrList(maEqMstrCommonDTO, user);
    }
    /**계측기 Find*/
    public List findEqToolMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)
    {
        return maEqMstrListDAO.findEqToolMstrList(maEqMstrCommonDTO, user);
    }
    /**금형 Find*/
    public List findEqMoldMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)
    {
        return maEqMstrListDAO.findEqMoldMstrList(maEqMstrCommonDTO, user);
    }
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findTotalCount(maEqMstrCommonDTO, user);
	}
	
	public int deleteEqMstr(String[] deleteRows, User loginUser) throws Exception{
        int result = 0;
        MaEqMstrDetailDAO maEqMstrDetailDAO = (MaEqMstrDetailDAO) CommonUtil.getBean("maEqMstrDetailDAO", loginUser);
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	//재개정 사용하지 않는 예방작업 삭제 로직
            	if("N".equals(MwareConfig.getIsUseAssetRevision()))
            	{
                    result = result + maEqMstrListDAO.updateDeleteTag(id,loginUser);
                    
                    MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
                    maEqMstrDetailDTO.setCompNo(loginUser.getCompNo());
                    maEqMstrDetailDTO.setEquipId(id);
                    //예방작업일정을 전체 삭제해야 함.
                    maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
                    //스케쥴 재 생성 해야 함.
                    maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);
        		}
            	//재개정 사용하는 예방작업 삭제 로직
            	else
            	{
            		// 최신버젼여부 확인을 위해 설비 조회
            		MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)CommonUtil.getBean("maEqMstrDetailService", loginUser);
            		MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            		maEqMstrCommonDTO.setEquipId(id);
            		MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailService.findDetail(maEqMstrCommonDTO, loginUser);
        				
    				// 삭제할 설비정보
    				String isLastVersion = maEqMstrDetailDTO.getIsLastVersion();
    				String equipId = maEqMstrDetailDTO.getEquipId();
    				
    				// 해당 Item 모든 설비 조회(List)
    				maEqMstrCommonDTO.setEquipId("");
    				maEqMstrCommonDTO.setFilterEquipNo(maEqMstrDetailDTO.getItemNo());
    				List eqItemList = maEqMstrListDAO.findEqMstrList(maEqMstrCommonDTO, loginUser);
    				
    				// 최신여부 Y인경우
    				// Item 삭제시 해당 Item 뿐 아니라 최신버젼여부가 "N"인 모든 History Item도 삭제 Flag 추가
    				if("Y".equals(isLastVersion))
    				{
    					// Item 삭제시 해당 Item, 최신버젼여부가 "N"인 모든 History Item도 삭제 Flag 추가
    					if(eqItemList.size() != 0)
    					{
    						for (int j = 0; j < eqItemList.size(); j++)
    						{
    							Map mapDto = (Map)eqItemList.get(j);
    							MaEqMstrDetailDTO eqMstrDetailDTO = (MaEqMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaEqMstrDetailDTO.class);
    							
    							// 최신여부 Y인 설비 삭제 Flag 추가 및 해당 Item, 최신버젼여부가 "N"인 모든 History Item도 삭제 Flag 추가
								result = result + maEqMstrListDAO.updateDeleteTag(eqMstrDetailDTO.getEquipId(), loginUser);
            					
            					maEqMstrDetailDTO.setCompNo(loginUser.getCompNo());
            					maEqMstrDetailDTO.setEquipId(eqMstrDetailDTO.getEquipId());
            					//예방작업일정을 전체 삭제해야 함.
            					maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
            					//스케쥴 재 생성 해야 함.
            					maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);
    						}
    					}
    				}
    				// 최신여부 N인경우 Item 삭제시 본인만 삭제 Flag 추가 
    				// (해당 Item의 항목 중 개정중 이면서 최신버젼여부 "Y"인 Item의 상태를 "재개정완료"로 변경 / 개정중인 항목이 있으면 최신버젼여부 "Y"인 Item 상태를 그냥 둔다 )
    				else if("N".equals(isLastVersion))
    				{
    					// 최신여부 N인경우 설비 삭제 Flag 추가 
    					result = result + maEqMstrListDAO.updateDeleteTag(equipId, loginUser);
    					
    					maEqMstrDetailDTO.setCompNo(loginUser.getCompNo());
    					maEqMstrDetailDTO.setEquipId(equipId);
    					//예방작업일정을 전체 삭제해야 함.
    					maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
    					//스케쥴 재 생성 해야 함.
    					maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);

    					// 해당 Item 중 최신여부 "N"이면서 개정중인 항목이 존재한다면 작업종료(이후 작업을 진행하지 않음)
    					boolean isFlag = false;
    					if(eqItemList.size() != 0)
    					{
    						for (int j = 0; j < eqItemList.size(); j++) 
    						{
    							Map mapDto = (Map)eqItemList.get(j);
    							MaEqMstrDetailDTO eqMstrDetailDTO = (MaEqMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaEqMstrDetailDTO.class);
    							
    							String eqId = eqMstrDetailDTO.getEquipId();
    							String revisionStatusId = eqMstrDetailDTO.getRevisionStatusId();
    							String isLastVer = eqMstrDetailDTO.getIsLastVersion();
    							
    							// 삭제된 해당 Item을 제외한 Item 중
    							// 개정중이면서 최신버젼여부 "N"인 Item이 존재하는지 확인
    							if(!equipId.equals(eqId) && "P".equals(revisionStatusId) && "N".equals(isLastVer))
    							{
    								isFlag = true;
    								break;
    							}
    						}
    						// 해당 Item 중 최신여부 "N"이면서 개정중인 항목이 존재하지 않으면서
    						// 최신버젼여부 "Y"인 Item의 상태를 "재개정완료"로 변경
    						if(!isFlag)
    						{
        						for (int j = 0; j < eqItemList.size(); j++) 
        						{
        							Map mapDto = (Map)eqItemList.get(j);
        							MaEqMstrDetailDTO eqMstrDetailDTO = (MaEqMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaEqMstrDetailDTO.class);
        							
        							String eqId = eqMstrDetailDTO.getEquipId();
        							String isLastVer = eqMstrDetailDTO.getIsLastVersion();
        							
        							// 삭제된 해당 Item을 제외한 Item 중 최신버젼여부 "Y"인 Item의 상태를 "재개정완료"로 변경
        							if(!equipId.equals(eqId) && "Y".equals(isLastVer))
        							{
        								String revisionhistId = eqMstrDetailDTO.getRevisionhistId();
        								String eqItemNo = eqMstrDetailDTO.getItemNo();
        								
        								// 제개정 완료처리 해당 equip_id
        								CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
        								
        								commRevCommonDTO.setRevisionhistId(revisionhistId);
        								commRevCommonDTO.setObjectId(eqId);
        								commRevCommonDTO.setObjectNo(eqItemNo);
        								commRevCommonDTO.setRevisionObjType("ASSET");
        								
        								commRevService.completeRegislate(commRevCommonDTO, loginUser);
        							}
        						}
    							
    						}
    					}
    					
    				}
            		
            	}
               
            }
        
        return result;
    }
	
	public int insertQrCode(String[] selectRows, String fileName, User loginUser) throws Exception{
	
		//일단 지운뒤에 insert하여 이전의  출력 데이터를 삭제한다.
		maEqMstrListDAO.deleteQrCode(loginUser, fileName);
	    
        int result = 0;
        if(!selectRows.equals(null))
            for(String id : selectRows)
            {
                result = result + maEqMstrListDAO.insertQrCode(id,fileName,loginUser);
            }
        return result;
    }
	public int insertListQrCode(MaEqMstrCommonDTO maEqMstrCommonDTO, String fileName, User loginUser) throws Exception{
		//일단 지운뒤에 insert하여 이전의  출력 데이터를 삭제한다.
		maEqMstrListDAO.deleteQrCode(loginUser, fileName);
		return maEqMstrListDAO.insertListQrCode(maEqMstrCommonDTO,fileName,loginUser);
	}
	
	public List copyEquipment(String[] selectRows, User loginUser) throws Exception{
        int result = 0;
        List resultList = new ArrayList();
        Map map =  null;
        CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
        
        if(!selectRows.equals(null)){
            for(String oldId : selectRows)
            {
            	//새로운 시퀀스 받기.
        		String newSeq = maEqMstrListDAO.getNextSequence("SQAEQUIP_ID");
        		String revhistId = maEqMstrListDAO.getNextSequence("SQAREVISIONHIST_ID");
        		
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
        		
        		map = new HashMap();
        		map.put("id", newSeq);
        		resultList.add(map);
        		//설비 데이터 복사
                result = result + insertCopyMstr(newSeq, oldId, revhistId, revisionStatus, loginUser, "Y");
            }
        }
        return resultList;
    }
	
	public int insertCopyMstr(String newSeq, String oldSeq, String revisionHistId, String revisionStatus, User loginUser, String isCopy) throws Exception{
		int result = 0;
		
		result = result + maEqMstrListDAO.insertCopyDetail(newSeq, oldSeq, revisionHistId, revisionStatus, loginUser, isCopy);
        result = result + maEqMstrListDAO.insertCopyEqAsmb(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqMold(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqTool(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqBuilding(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqSpec(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqPart(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqAsset(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqItems(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqVendor(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqDevice(newSeq, oldSeq, loginUser);
        result = result + maEqMstrListDAO.insertCopyEqHist(newSeq, oldSeq, loginUser);
        
        maEqMstrListDAO.SP_IF_UPD_TXEQUIPMENT(newSeq,loginUser);
		
		return result;
	}

	public List findEqPartMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
		return maEqMstrListDAO.findEqPartMstrList(maEqMstrCommonDTO, user);
	}
	
	public String findPtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findPtTotalCount(maEqMstrCommonDTO, user);
	}
	
	public String findMcTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findMcTotalCount(maEqMstrCommonDTO, user);
	}
	
	public String findUtTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findUtTotalCount(maEqMstrCommonDTO, user);
	}
	
	public String findBdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findBdTotalCount(maEqMstrCommonDTO, user);
	}
	
	public String findTlTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findTlTotalCount(maEqMstrCommonDTO, user);
	}
	
	public String findMdTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findMdTotalCount(maEqMstrCommonDTO, user);
	}

	public List findEqITList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
		return maEqMstrListDAO.findEqITList(maEqMstrCommonDTO, user);
	}
	
	public String findITTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findITTotalCount(maEqMstrCommonDTO, user);
	}
	
	public List findEqGNList(MaEqMstrCommonDTO maEqMstrCommonDTO, User user) {
		return maEqMstrListDAO.findEqGNList(maEqMstrCommonDTO, user);
	}
	
	public String findGNTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		return maEqMstrListDAO.findGNTotalCount(maEqMstrCommonDTO, user);
	}
	
	public String getData(MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		if ("" != maEqMstrCommonDTO.getEqCtgType() && maEqMstrCommonDTO.getEqCtgType() != null) {
			switch (maEqMstrCommonDTO.getEqCtgType()) {
			case "MC": maEqMstrCommonDTO.setExceltabNo("EQMACHINE"); break;
			case "TL": maEqMstrCommonDTO.setExceltabNo("EQTOOL"); break;
			case "IT": maEqMstrCommonDTO.setExceltabNo("EQIT"); break;
			case "GN": maEqMstrCommonDTO.setExceltabNo("EQGEN"); break;
			default: break;
			}
		}
		return maEqMstrListDAO.getData(maEqMstrCommonDTO, user);
	}
}

