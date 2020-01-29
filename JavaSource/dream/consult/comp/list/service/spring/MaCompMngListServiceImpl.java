package dream.consult.comp.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.list.dao.MaCompMngListDAO;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.service.MaCompMngListService;

/**
 * 회사설정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaCompMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maCompMngListServiceTarget"
 * @spring.txbn id="maCompMngListService"
 * @spring.property name="maCompMngListDAO" ref="maCompMngListDAO"
 */
public class MaCompMngListServiceImpl implements MaCompMngListService
{
    private MaCompMngListDAO maCompMngListDAO = null;

    public MaCompMngListDAO getMaCompMngListDAO() {
		return maCompMngListDAO;
	}

	public void setMaCompMngListDAO(MaCompMngListDAO maCompMngListDAO) {
		this.maCompMngListDAO = maCompMngListDAO;
	}

	public List findCompList(MaCompMngCommonDTO maCompMngCommonDTO, User user)
    {      
        return maCompMngListDAO.findCompList(maCompMngCommonDTO,user);
    }
	
	public int deleteComp(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maCompMngListDAO.deleteComp(id);
            }
        return result;
    }
	
	public String findTotalCount(MaCompMngCommonDTO maCompMngCommonDTO, User user)
	{
		return maCompMngListDAO.findTotalCount(maCompMngCommonDTO, user);
	}
}

