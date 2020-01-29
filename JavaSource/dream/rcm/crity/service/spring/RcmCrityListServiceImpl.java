package dream.rcm.crity.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityListDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.service.RcmCrityListService;

/**
 * Criticality Matrix Page - List Service implements
 * @author kim21017
 * @version $Id: RcmCrityListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityListServiceTarget"
 * @spring.txbn id="rcmCrityListService"
 * @spring.property name="rcmCrityListDAO" ref="rcmCrityListDAO"
 */
public class RcmCrityListServiceImpl implements RcmCrityListService
{
	private RcmCrityListDAO rcmCrityListDAO = null;

	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, User user) throws Exception
    {      
        return rcmCrityListDAO.findList(rcmCrityCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmCrityListDAO.deleteCrityList(id, user);
                result = result + rcmCrityListDAO.deleteCrityColList(id, user);
                result = result + rcmCrityListDAO.deleteCrityRowList(id, user);
                result = result + rcmCrityListDAO.deleteCrityValueList(id, user);
            }
        return result;
    }

	public RcmCrityListDAO getRcmCrityListDAO() {
		return rcmCrityListDAO;
	}

	public void setRcmCrityListDAO(RcmCrityListDAO rcmCrityListDAO) {
		this.rcmCrityListDAO = rcmCrityListDAO;
	}

	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, User user) {
		 return rcmCrityListDAO.findTotalCount(rcmCrityCommonDTO, user);
			
	}
}

