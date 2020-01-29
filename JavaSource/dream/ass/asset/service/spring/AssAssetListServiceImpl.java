package dream.ass.asset.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.ass.asset.dao.AssAssetListDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.service.AssAssetListService;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dto.InvtDetailDTO;

/**
 * AssAsset Page - List Service implements
 * @author youngjoo38
 * @version $Id: AssAssetListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="assAssetListServiceTarget"
 * @spring.txbn id="assAssetListService"
 * @spring.property name="assAssetListDAO" ref="assAssetListDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 */
public class AssAssetListServiceImpl implements AssAssetListService
{
    private AssAssetListDAO assAssetListDAO = null;
    
    private InvtDetailDAO invtDetailDAO = null;

    public List findList(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception
    {      
        return assAssetListDAO.findList(assAssetCommonDTO,user);
    }

    public InvtDetailDAO getInvtDetailDAO() {
		return invtDetailDAO;
	}

	public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO) {
		this.invtDetailDAO = invtDetailDAO;
	}

	public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                String chkRows = assAssetListDAO.chkDelList(id, user);
                if("0".equals(chkRows)) {
                    result = result + assAssetListDAO.deleteList(id, user);
                } else {
                    result = 0;
                }
            }
        return result;
    }
    
    public int createInvtList( String[] selectRows, AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception
    {
    	int result = 0;
    	
    	if(!selectRows.equals(null))
    		for(String id : selectRows)
    		{
    			
    			InvtDetailDTO invtDetailDTO = new InvtDetailDTO();

    			String prctpId = this.findDefaultPrctpId(user);
    			// ���� ���� ��������
    			String[] arrEqList = this.findEquipDesc(id,user).split("\\+");
    			String equipDesc = arrEqList[0];
    			if(arrEqList.length>1) invtDetailDTO.setEqlocId(arrEqList[1]);
    			if(arrEqList.length>2) invtDetailDTO.setEqctgId(arrEqList[2]);
    			
    			// ID ����
    			String invtListId = invtDetailDAO.getNextSequence("SQAINVTLIST_ID");
    			invtDetailDTO.setInvtlistId(invtListId);
    			invtDetailDTO.setInvtlistNo(invtListId);
    			
    			// ���� ID ����
    			invtDetailDTO.setEquipId(id);
    			// ���ڱ��� ���� (����)
    			invtDetailDTO.setInvtCateg("ETC");
    			// ���ںз� ���� (���׷��̵�)
    			invtDetailDTO.setInvtTypeId("EQ");
    			// �������� ���� (Default ��)
    			invtDetailDTO.setInvtprctpId(prctpId);
    			// ����� ����
    			invtDetailDTO.setEmpId(user.getEmpId());
    			// ���ںμ� ����
    			invtDetailDTO.setDeptId(user.getDeptId());
    			// ���ڽñ� ����
    			invtDetailDTO.setPlanSdate(CommonUtil.convertDate(DateUtil.getDate()));
    			// ���ڸ� ���� (�����+�����)
    			invtDetailDTO.setDescription(equipDesc+" �����");
    			// �������� ���� (��ȹ)
    			invtDetailDTO.setInvtKindId("PLN");
    			// ���� ���� ���� (�ۼ���)
    			invtDetailDTO.setInvtlistStatus("W");
    			if(!"null".equals(user.getPlant())) invtDetailDTO.setPlantId(user.getPlant());
    			
    			invtDetailDAO.insertPhase(invtDetailDTO, user);
				invtDetailDAO.insertDetail(invtDetailDTO, user);
				
				//�������� �Է� �ʿ� 
				
    		}
    	return result;
    }

    public String findDefaultPrctpId(User user) throws Exception
    {
    	return assAssetListDAO.findDefaultPrctpId(user);
    }
    
    public String findEquipDesc(String id, User user) throws Exception
    {
    	return assAssetListDAO.findEquipDesc(id, user);
    }
    
    public AssAssetListDAO getAssAssetListDAO() {
        return assAssetListDAO;
    }

    public void setAssAssetListDAO(AssAssetListDAO assAssetListDAO) {
        this.assAssetListDAO = assAssetListDAO;
    }    
    
    public String findTotalCount(AssAssetCommonDTO assAssetCommonDTO,User user)  throws Exception
    {
        return assAssetListDAO.findTotalCount(assAssetCommonDTO, user);
    }
}
