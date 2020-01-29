package dream.invt.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtEquipListDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.service.InvtEquipListService;

/**
 * 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="invtEquipListServiceTarget"
 * @spring.txbn id="invtEquipListService"
 * @spring.property name="invtEquipListDAO" ref="invtEquipListDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 */
public class InvtEquipListServiceImpl implements InvtEquipListService
{
    private InvtEquipListDAO invtEquipListDAO = null;
    private InvtDetailDAO invtDetailDAO = null;

    public InvtEquipListDAO getInvtEquipListDAO() {
		return invtEquipListDAO;
	}
	public void setInvtEquipListDAO(InvtEquipListDAO invtEquipListDAO) {
		this.invtEquipListDAO = invtEquipListDAO;
	}
	public InvtDetailDAO getInvtDetailDAO()
    {
        return invtDetailDAO;
    }
    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO)
    {
        this.invtDetailDAO = invtDetailDAO;
    }
	
	public List findList(InvtCommonDTO invtCommonDTO, User user)
    {      
        return invtEquipListDAO.findList(invtCommonDTO, user);
    }
	
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            String invtListId = invtDetailDAO.getInvtListId(deleteRows[i], user);
            
            //설비 삭제
            result = result + invtEquipListDAO.deleteList(deleteRows[i], user);
        }
        
        return result;
    }
	public String findTotalCount(InvtCommonDTO invtCommonDTO,User user)  throws Exception
    {
        return invtEquipListDAO.findTotalCount(invtCommonDTO, user);
    }

	public int insertNewEqList(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
		return invtEquipListDAO.insertNewEqList(invtCommonDTO, user);
	}
	
	public int insertEqList(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
		int result = 0;
        
		String[] multiKey = invtCommonDTO.getEquipId().split("\\+");
		
        for(int i=0; i < multiKey.length; i++)
        {
            invtCommonDTO.setEquipId(multiKey[i]);

            String cnt = this.validEquip(invtCommonDTO, user);
            
            if("0".equals(cnt))
            {
            	invtCommonDTO.setInvtEquipId(invtEquipListDAO.getNextSequence("SQAINVTEQUIP_ID"));
            	this.insertNewEqList(invtCommonDTO, user);
            }
            
        }
		
		return result;
	}

	public String validEquip(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
		return invtEquipListDAO.validEquip(invtCommonDTO, user);
	}
}

