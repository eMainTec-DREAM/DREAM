package dream.consult.comp.time.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.time.dao.MaLineTimeListDAO;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.service.MaLineTimeListService;

/**
 * 가동시간설정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaLineTimeListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maLineTimeListServiceTarget"
 * @spring.txbn id="maLineTimeListService"
 * @spring.property name="maLineTimeListDAO" ref="maLineTimeListDAO"
 */
public class MaLineTimeListServiceImpl implements MaLineTimeListService
{
    private MaLineTimeListDAO maLineTimeListDAO = null;

    public MaLineTimeListDAO getMaLineTimeListDAO() {
		return maLineTimeListDAO;
	}

	public void setMaLineTimeListDAO(MaLineTimeListDAO maLineTimeListDAO) {
		this.maLineTimeListDAO = maLineTimeListDAO;
	}

	public List findEqLocList(MaLineTimeCommonDTO maLineTimeCommonDTO, User user)
    {      
        return maLineTimeListDAO.findEqLocList(maLineTimeCommonDTO, user);
    }
	
    @Override
    public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, User user)
    {
        return maLineTimeListDAO.findTotalCount(maLineTimeCommonDTO, user);
    }
    
    public int deleteList(String[] deleteRows, String[] deleteRowsExt) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && !deleteRowsExt.equals(null))
            for(int i=0; i<deleteRows.length; i++)
            {
                result = result + maLineTimeListDAO.deleteList(deleteRows[i], deleteRowsExt[i]);
            }
        
        return result;
    }
}

