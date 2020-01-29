package dream.rcm.crity.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityColListDAO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.service.RcmCrityColListService;

/**
 * Criticality Matrix Col Page - List Service implements
 * @author kim21017
 * @version $Id: RcmCrityColListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityColListServiceTarget"
 * @spring.txbn id="rcmCrityColListService"
 * @spring.property name="rcmCrityColListDAO" ref="rcmCrityColListDAO"
 */
public class RcmCrityColListServiceImpl implements RcmCrityColListService
{
	private RcmCrityColListDAO rcmCrityColListDAO = null;

	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColListDTO rcmCrityColListDTO, User user) throws Exception
    {      
        return rcmCrityColListDAO.findList(rcmCrityCommonDTO,rcmCrityColListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmCrityColListDAO.deleteList(id, user);
                result = result + rcmCrityColListDAO.deleteValList(id, user);
            }
        return result;
    }

	public RcmCrityColListDAO getRcmCrityColListDAO() {
		return rcmCrityColListDAO;
	}

	public void setRcmCrityColListDAO(RcmCrityColListDAO rcmCrityColListDAO) {
		this.rcmCrityColListDAO = rcmCrityColListDAO;
	}

	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityColListDTO rcmCrityColListDTO,
			User user) {
		return rcmCrityColListDAO.findTotalCount(rcmCrityCommonDTO, rcmCrityColListDTO, user);
	}
}

