package dream.ass.base.service.spring;

import java.util.List;

import common.bean.User;
import dream.ass.base.dao.AssBaseListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.service.AssBaseListService;

/**
 * 설비등급 평가기준 - List Service implements
 * @author kim21017
 * @version $Id: AssBaseListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBaseListServiceTarget"
 * @spring.txbn id="assBaseListService"
 * @spring.property name="assBaseListDAO" ref="assBaseListDAO"
 */
public class AssBaseListServiceImpl implements AssBaseListService
{
	private AssBaseListDAO assBaseListDAO = null;

	public List findList(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception
    {      
        return assBaseListDAO.findList(assBaseCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assBaseListDAO.deleteList(id, user);
                result = result + assBaseListDAO.deleteGradeList(id, user);
                result = result + assBaseListDAO.deletePointList(id, user);
                result = result + assBaseListDAO.deleteValList(id, user);
            }
        return result;
    }
	public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception
    {      
        return assBaseListDAO.findTotalCount(assBaseCommonDTO,user);
    }
	public AssBaseListDAO getAssBaseListDAO() {
		return assBaseListDAO;
	}

	public void setAssBaseListDAO(AssBaseListDAO assBaseListDAO) {
		this.assBaseListDAO = assBaseListDAO;
	}
}

