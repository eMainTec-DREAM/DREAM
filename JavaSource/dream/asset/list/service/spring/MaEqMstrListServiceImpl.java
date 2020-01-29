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
 * ���񸶽��� - ��� serviceimpl
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
	
    /**���꼳��FIND 
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
	/**����,�ǹ� Find*/
    public List findEqBuildMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)
    {
        return maEqMstrListDAO.findEqBuildMstrList(maEqMstrCommonDTO, user);
    }
    /**������ Find*/
    public List findEqToolMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)
    {
        return maEqMstrListDAO.findEqToolMstrList(maEqMstrCommonDTO, user);
    }
    /**���� Find*/
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
            	//�簳�� ������� �ʴ� �����۾� ���� ����
            	if("N".equals(MwareConfig.getIsUseAssetRevision()))
            	{
                    result = result + maEqMstrListDAO.updateDeleteTag(id,loginUser);
                    
                    MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
                    maEqMstrDetailDTO.setCompNo(loginUser.getCompNo());
                    maEqMstrDetailDTO.setEquipId(id);
                    //�����۾������� ��ü �����ؾ� ��.
                    maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
                    //������ �� ���� �ؾ� ��.
                    maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);
        		}
            	//�簳�� ����ϴ� �����۾� ���� ����
            	else
            	{
            		// �ֽŹ������� Ȯ���� ���� ���� ��ȸ
            		MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)CommonUtil.getBean("maEqMstrDetailService", loginUser);
            		MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            		maEqMstrCommonDTO.setEquipId(id);
            		MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailService.findDetail(maEqMstrCommonDTO, loginUser);
        				
    				// ������ ��������
    				String isLastVersion = maEqMstrDetailDTO.getIsLastVersion();
    				String equipId = maEqMstrDetailDTO.getEquipId();
    				
    				// �ش� Item ��� ���� ��ȸ(List)
    				maEqMstrCommonDTO.setEquipId("");
    				maEqMstrCommonDTO.setFilterEquipNo(maEqMstrDetailDTO.getItemNo());
    				List eqItemList = maEqMstrListDAO.findEqMstrList(maEqMstrCommonDTO, loginUser);
    				
    				// �ֽſ��� Y�ΰ��
    				// Item ������ �ش� Item �� �ƴ϶� �ֽŹ������ΰ� "N"�� ��� History Item�� ���� Flag �߰�
    				if("Y".equals(isLastVersion))
    				{
    					// Item ������ �ش� Item, �ֽŹ������ΰ� "N"�� ��� History Item�� ���� Flag �߰�
    					if(eqItemList.size() != 0)
    					{
    						for (int j = 0; j < eqItemList.size(); j++)
    						{
    							Map mapDto = (Map)eqItemList.get(j);
    							MaEqMstrDetailDTO eqMstrDetailDTO = (MaEqMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaEqMstrDetailDTO.class);
    							
    							// �ֽſ��� Y�� ���� ���� Flag �߰� �� �ش� Item, �ֽŹ������ΰ� "N"�� ��� History Item�� ���� Flag �߰�
								result = result + maEqMstrListDAO.updateDeleteTag(eqMstrDetailDTO.getEquipId(), loginUser);
            					
            					maEqMstrDetailDTO.setCompNo(loginUser.getCompNo());
            					maEqMstrDetailDTO.setEquipId(eqMstrDetailDTO.getEquipId());
            					//�����۾������� ��ü �����ؾ� ��.
            					maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
            					//������ �� ���� �ؾ� ��.
            					maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);
    						}
    					}
    				}
    				// �ֽſ��� N�ΰ�� Item ������ ���θ� ���� Flag �߰� 
    				// (�ش� Item�� �׸� �� ������ �̸鼭 �ֽŹ������� "Y"�� Item�� ���¸� "�簳���Ϸ�"�� ���� / �������� �׸��� ������ �ֽŹ������� "Y"�� Item ���¸� �׳� �д� )
    				else if("N".equals(isLastVersion))
    				{
    					// �ֽſ��� N�ΰ�� ���� ���� Flag �߰� 
    					result = result + maEqMstrListDAO.updateDeleteTag(equipId, loginUser);
    					
    					maEqMstrDetailDTO.setCompNo(loginUser.getCompNo());
    					maEqMstrDetailDTO.setEquipId(equipId);
    					//�����۾������� ��ü �����ؾ� ��.
    					maEqMstrDetailDAO.updatePmActive(maEqMstrDetailDTO);
    					//������ �� ���� �ؾ� ��.
    					maEqMstrDetailDAO.deleteSchedAllPmEquip(maEqMstrDetailDTO);

    					// �ش� Item �� �ֽſ��� "N"�̸鼭 �������� �׸��� �����Ѵٸ� �۾�����(���� �۾��� �������� ����)
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
    							
    							// ������ �ش� Item�� ������ Item ��
    							// �������̸鼭 �ֽŹ������� "N"�� Item�� �����ϴ��� Ȯ��
    							if(!equipId.equals(eqId) && "P".equals(revisionStatusId) && "N".equals(isLastVer))
    							{
    								isFlag = true;
    								break;
    							}
    						}
    						// �ش� Item �� �ֽſ��� "N"�̸鼭 �������� �׸��� �������� �����鼭
    						// �ֽŹ������� "Y"�� Item�� ���¸� "�簳���Ϸ�"�� ����
    						if(!isFlag)
    						{
        						for (int j = 0; j < eqItemList.size(); j++) 
        						{
        							Map mapDto = (Map)eqItemList.get(j);
        							MaEqMstrDetailDTO eqMstrDetailDTO = (MaEqMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaEqMstrDetailDTO.class);
        							
        							String eqId = eqMstrDetailDTO.getEquipId();
        							String isLastVer = eqMstrDetailDTO.getIsLastVersion();
        							
        							// ������ �ش� Item�� ������ Item �� �ֽŹ������� "Y"�� Item�� ���¸� "�簳���Ϸ�"�� ����
        							if(!equipId.equals(eqId) && "Y".equals(isLastVer))
        							{
        								String revisionhistId = eqMstrDetailDTO.getRevisionhistId();
        								String eqItemNo = eqMstrDetailDTO.getItemNo();
        								
        								// ������ �Ϸ�ó�� �ش� equip_id
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
	
		//�ϴ� ����ڿ� insert�Ͽ� ������  ��� �����͸� �����Ѵ�.
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
		//�ϴ� ����ڿ� insert�Ͽ� ������  ��� �����͸� �����Ѵ�.
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
            	//���ο� ������ �ޱ�.
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
        		//���� ������ ����
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

