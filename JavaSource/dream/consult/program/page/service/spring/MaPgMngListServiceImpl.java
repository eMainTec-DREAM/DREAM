package dream.consult.program.page.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dao.MaPgMngListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.service.MaPgMngListService;

/**
 * 화면 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaPgMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngListServiceTarget"
 * @spring.txbn id="maPgMngListService"
 * @spring.property name="maPgMngListDAO" ref="maPgMngListDAO"
 */
public class MaPgMngListServiceImpl implements MaPgMngListService
{
    private MaPgMngListDAO maPgMngListDAO = null;

    public MaPgMngListDAO getMaPgMngListDAO() {
		return maPgMngListDAO;
	}

	public void setMaPgMngListDAO(MaPgMngListDAO maPgMngListDAO) {
		this.maPgMngListDAO = maPgMngListDAO;
	}

	public List findPgList(MaPgMngCommonDTO maPgMngCommonDTO, User user)
    {      
        return maPgMngListDAO.findPgList(maPgMngCommonDTO,user);
    }
	
	public int deletePage(String[] deleteRows) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPgMngListDAO.deletePage(id);
            }
        return result;
    }
	
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO,User user)  throws Exception
	{
	    return maPgMngListDAO.findTotalCount(maPgMngCommonDTO, user);
	}
	
}

