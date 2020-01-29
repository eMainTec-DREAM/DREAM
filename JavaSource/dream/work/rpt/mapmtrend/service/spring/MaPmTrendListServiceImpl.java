package dream.work.rpt.mapmtrend.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dao.MaPmTrendListDAO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.service.MaPmTrendListService;

/**
 * ¸ñ·Ï
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPmTrendListServiceTarget"
 * @spring.txbn id="maPmTrendListService"
 * @spring.property name="maPmTrendListDAO" ref="maPmTrendListDAO"
 */
public class MaPmTrendListServiceImpl implements MaPmTrendListService
{
    private MaPmTrendListDAO maPmTrendListDAO = null;
    
	public MaPmTrendListDAO getMaPmTrendListDAO()
    {
        return maPmTrendListDAO;
    }
	
    public void setMaPmTrendListDAO(
            MaPmTrendListDAO maPmTrendListDAO)
    {
        this.maPmTrendListDAO = maPmTrendListDAO;
    }
    
    public List findList(MaPmTrendCommonDTO maPmTrendCommonDTO, User loginUser)
    {
        return maPmTrendListDAO.findList(maPmTrendCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(MaPmTrendCommonDTO maPmTrendCommonDTO, User user)
    {
        return maPmTrendListDAO.findTotalCount(maPmTrendCommonDTO, user);
    }
	
}

