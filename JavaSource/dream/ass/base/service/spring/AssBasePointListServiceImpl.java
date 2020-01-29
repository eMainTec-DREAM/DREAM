package dream.ass.base.service.spring;

import java.util.List;

import common.bean.User;
import dream.ass.base.dao.AssBasePointListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.service.AssBasePointListService;

/**
 * 평가항목 - List Service implements
 * @author kim21017
 * @version $Id: AssBasePointListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBasePointListServiceTarget"
 * @spring.txbn id="assBasePointListService"
 * @spring.property name="assBasePointListDAO" ref="assBasePointListDAO"
 */
public class AssBasePointListServiceImpl implements AssBasePointListService
{
	private AssBasePointListDAO assBasePointListDAO = null;

	public List findList(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO, User user) throws Exception
    {      
        return assBasePointListDAO.findList(assBaseCommonDTO,assBasePointListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assBasePointListDAO.deleteList(id, user);
                result = result + assBasePointListDAO.deleteValList(id, user);
            }
        return result;
    }
	public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO, User user) throws Exception
    {      
        return assBasePointListDAO.findTotalCount(assBaseCommonDTO,assBasePointListDTO,user);
    }
	public AssBasePointListDAO getAssBasePointListDAO() {
		return assBasePointListDAO;
	}

	public void setAssBasePointListDAO(AssBasePointListDAO assBasePointListDAO) {
		this.assBasePointListDAO = assBasePointListDAO;
	}

}

