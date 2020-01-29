package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.MaPmEqClnListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.service.MaPmEqClnListService;

/**
 * 예방설비 목록
 * @author kim21017
 * @version $Id: MaPmEqClnListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmEqClnListServiceTarget"
 * @spring.txbn id="maPmEqClnListService"
 * @spring.property name="maPmEqClnListDAO" ref="maPmEqClnListDAO"
 */
public class MaPmEqClnListServiceImpl implements MaPmEqClnListService
{
    private MaPmEqClnListDAO maPmEqClnListDAO = null;


	public List findClnList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {      
        return maPmEqClnListDAO.findClnList(maPmMstrCommonDTO, loginUser);
    }

	public MaPmEqClnListDAO getMaPmEqClnListDAO() {
		return maPmEqClnListDAO;
	}

	public void setMaPmEqClnListDAO(MaPmEqClnListDAO maPmEqClnListDAO) {
		this.maPmEqClnListDAO = maPmEqClnListDAO;
	}
	
	public int deleteClnList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPmEqClnListDAO.deleteClnList(id, compNo);
            }
        
        return result;
    }

	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception 
	{
		return maPmEqClnListDAO.findTotalCount(maPmMstrCommonDTO, user);
	}
}

