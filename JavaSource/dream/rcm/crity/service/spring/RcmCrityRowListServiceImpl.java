package dream.rcm.crity.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.crity.dao.RcmCrityRowListDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;
import dream.rcm.crity.service.RcmCrityRowListService;

/**
 * Criticality Matrix Row Page - List Service implements
 * @author kim21017
 * @version $Id: RcmCrityRowListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmCrityRowListServiceTarget"
 * @spring.txbn id="rcmCrityRowListService"
 * @spring.property name="rcmCrityRowListDAO" ref="rcmCrityRowListDAO"
 */
public class RcmCrityRowListServiceImpl implements RcmCrityRowListService
{
	private RcmCrityRowListDAO rcmCrityRowListDAO = null;

	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowListDTO rcmCrityRowListDTO, User user) throws Exception
    {      
        return rcmCrityRowListDAO.findList(rcmCrityCommonDTO,rcmCrityRowListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + rcmCrityRowListDAO.deleteList(id, user);
                result = result + rcmCrityRowListDAO.deleteValList(id, user);
            }
        return result;
    }

	public RcmCrityRowListDAO getRcmCrityRowListDAO() {
		return rcmCrityRowListDAO;
	}

	public void setRcmCrityRowListDAO(RcmCrityRowListDAO rcmCrityRowListDAO) {
		this.rcmCrityRowListDAO = rcmCrityRowListDAO;
	}

	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityRowListDTO rcmCrityRowListDTO,
			User user) {
		 return rcmCrityRowListDAO.findTotalCount(rcmCrityCommonDTO, rcmCrityRowListDTO, user);
			
	}

}

