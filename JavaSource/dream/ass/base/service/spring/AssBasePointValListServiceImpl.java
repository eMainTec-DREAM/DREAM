package dream.ass.base.service.spring;

import java.util.List;

import common.bean.User;
import dream.ass.base.dao.AssBasePointValListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
import dream.ass.base.service.AssBasePointValListService;

/**
 * 평가기준 - List Service implements
 * @author kim21017
 * @version $Id: AssBasePointValListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBasePointValListServiceTarget"
 * @spring.txbn id="assBasePointValListService"
 * @spring.property name="assBasePointValListDAO" ref="assBasePointValListDAO"
 */
public class AssBasePointValListServiceImpl implements AssBasePointValListService
{
	private AssBasePointValListDAO assBasePointValListDAO = null;

	public List findList(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception
    {      
        return assBasePointValListDAO.findList(assBaseCommonDTO,assBasePointListDTO,assBasePointValListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assBasePointValListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception
    {      
        return assBasePointValListDAO.findTotalCount(assBaseCommonDTO,assBasePointListDTO,assBasePointValListDTO,user);
    }
	public AssBasePointValListDAO getAssBasePointValListDAO() {
		return assBasePointValListDAO;
	}

	public void setAssBasePointValListDAO(AssBasePointValListDAO assBasePointValListDAO) {
		this.assBasePointValListDAO = assBasePointValListDAO;
	}

}

