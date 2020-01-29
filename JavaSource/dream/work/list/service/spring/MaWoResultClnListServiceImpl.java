package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.MaWoResultClnListDAO;
import dream.work.list.dto.MaWoResultClnListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.MaWoResultClnListService;

/**
 * 작업결과 작업설비 목록
 * @author kim21017
 * @version $Id: MaWoResultClnListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultClnListServiceTarget"
 * @spring.txbn id="maWoResultClnListService"
 * @spring.property name="maWoResultClnListDAO" ref="maWoResultClnListDAO"
 */
public class MaWoResultClnListServiceImpl implements MaWoResultClnListService
{
    private MaWoResultClnListDAO maWoResultClnListDAO = null;


	public List findClnList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser)
    {      
        return maWoResultClnListDAO.findClnList(maWoResultMstrCommonDTO, maWoResultClnListDTO, loginUser);
    }

	public MaWoResultClnListDAO getMaWoResultClnListDAO() {
		return maWoResultClnListDAO;
	}

	public void setMaWoResultClnListDAO(MaWoResultClnListDAO maWoResultClnListDAO) {
		this.maWoResultClnListDAO = maWoResultClnListDAO;
	}
	
	public int deleteClnList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoResultClnListDAO.deleteClnList(id, compNo);
            }
        
        return result;
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser) throws Exception 
	{
		return maWoResultClnListDAO.findTotalCount(maWoResultMstrCommonDTO, maWoResultClnListDTO, loginUser);
	}
}

