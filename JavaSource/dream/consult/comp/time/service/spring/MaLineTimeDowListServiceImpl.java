package dream.consult.comp.time.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.time.dao.MaLineTimeDowListDAO;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;
import dream.consult.comp.time.service.MaLineTimeDowListService;

/**
 * 요일별 설정  목록
 * @author kim21017
 * @version $Id: MaLineTimeDowListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maLineTimeDowListServiceTarget"
 * @spring.txbn id="maLineTimeDowListService"
 * @spring.property name="maLineTimeDowListDAO" ref="maLineTimeDowListDAO"
 */
public class MaLineTimeDowListServiceImpl implements MaLineTimeDowListService
{
    private MaLineTimeDowListDAO maLineTimeDowListDAO = null;


	public List findDowList(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser)
    {      
        return maLineTimeDowListDAO.findDowList(maLineTimeCommonDTO, maLineTimeDowListDTO, loginUser);
    }
	public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser)
	{      
		return maLineTimeDowListDAO.findTotalCount(maLineTimeCommonDTO, maLineTimeDowListDTO, loginUser);
	}

	public MaLineTimeDowListDAO getMaLineTimeDowListDAO() {
		return maLineTimeDowListDAO;
	}

	public void setMaLineTimeDowListDAO(MaLineTimeDowListDAO maLineTimeDowListDAO) {
		this.maLineTimeDowListDAO = maLineTimeDowListDAO;
	}
	
	public int deleteDowList(String[] deleteRows, String[] deleteRowsExt) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && !deleteRowsExt.equals(null))
            for(int i=0; i<deleteRows.length; i++)
            {
                result = result + maLineTimeDowListDAO.deleteDowList(deleteRows[i], deleteRowsExt[i]);
            }
        
        return result;
    }
}

