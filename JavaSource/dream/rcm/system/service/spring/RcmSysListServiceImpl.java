package dream.rcm.system.service.spring;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.rcm.system.dao.RcmSysListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysListService;

/**
 * 사원 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysListServiceTarget"
 * @spring.txbn id="rcmSysListService"
 * @spring.property name="rcmSysListDAO" ref="rcmSysListDAO"
 */
public class RcmSysListServiceImpl implements RcmSysListService
{
    private RcmSysListDAO rcmSysListDAO = null;

    public RcmSysListDAO getRcmSysListDAO() 
    {
		return rcmSysListDAO;
	}

	public void setRcmSysListDAO(RcmSysListDAO rcmSysListDAO) 
	{
		this.rcmSysListDAO = rcmSysListDAO;
	}

	public List findList(RcmSysCommonDTO rcmSysCommonDTO, User user) throws IOException
    {      
        return rcmSysListDAO.findList(rcmSysCommonDTO, user);
    }

    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmSysListDAO.deleteList(compNo, id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, User user) {
		return rcmSysListDAO.findTotalCount(rcmSysCommonDTO, user);
	}
}

