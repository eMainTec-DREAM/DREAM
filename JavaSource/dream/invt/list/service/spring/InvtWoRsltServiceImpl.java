package dream.invt.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import dream.invt.list.dao.InvtWoRsltDAO;
import dream.invt.list.dto.InvtWoRsltDTO;
import dream.invt.list.service.InvtWoRsltService;

/**
 * @author ghlee
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="invtWoRsltServiceTarget"
 * @spring.txbn id="invtWoRsltService"
 * @spring.property name="invtWoRsltDAO" ref="invtWoRsltDAO"
 */
public class InvtWoRsltServiceImpl implements InvtWoRsltService
{
    private InvtWoRsltDAO invtWoRsltDAO = null;

    public InvtWoRsltDAO getInvtWoRsltDAO() {
		return invtWoRsltDAO;
	}
	public void setInvtWoRsltDAO(InvtWoRsltDAO invtWoRsltDAO) {
		this.invtWoRsltDAO = invtWoRsltDAO;
	}
	
	@Override
	public List findList(InvtWoRsltDTO invtWoRsltDTO, User user)
    {
	    if("".equals(invtWoRsltDTO.getInvtworkMethod())) invtWoRsltDTO.setInvtworkMethod("RSLT");
        return invtWoRsltDAO.findList(invtWoRsltDTO, user);
    }
	
	@Override
	public int delete(String[] deleteRows, User user) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            //설비 삭제
            result = result + invtWoRsltDAO.deleteList(deleteRows[i], user);
        }
        
        return result;
    }
	
	@Override
	public String findTotalCount(InvtWoRsltDTO invtWoRsltDTO, User user)  throws Exception
    {
        return invtWoRsltDAO.findTotalCount(invtWoRsltDTO, user);
    }

	@Override
	public int insert(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception 
	{
	    List list = new ArrayList();
	    list.add(invtWoRsltDTO);
		return this.insert(list, user);
	}
	
	@Override
	public int insert(List<InvtWoRsltDTO> list, User user) throws Exception 
	{
	    return invtWoRsltDAO.insertList(list, user).length;
	}
	
	@Override
	public int linkList(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception 
	{
		List list = new ArrayList();
        
		if("".equals(invtWoRsltDTO.getInvtworkMethod())) invtWoRsltDTO.setInvtworkMethod("RSLT");
		
		String[] multiKey = invtWoRsltDTO.getWkorId().split("\\+");
		
        for(int i=0; i < multiKey.length; i++)
        {
            invtWoRsltDTO.setWkorId(multiKey[i]);
            
            if(this.isValid(invtWoRsltDTO, user))
            {
                invtWoRsltDTO.setInvtworkId(invtWoRsltDAO.getNextSequence("SQAINVTWORK_ID"));
                list.add(BeanUtils.cloneBean(invtWoRsltDTO));
            }
        }
        
		return this.insert(list, user);
	}

	private boolean isValid(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception 
	{
		return "0".equals(this.findTotalCount(invtWoRsltDTO, user));
	}
}

