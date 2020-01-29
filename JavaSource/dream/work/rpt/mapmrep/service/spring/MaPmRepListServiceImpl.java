package dream.work.rpt.mapmrep.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmrep.dao.MaPmRepListDAO;
import dream.work.rpt.mapmrep.dto.MaPmRepListDTO;
import dream.work.rpt.mapmrep.service.MaPmRepListService;

/**
 * 예방수리내역serviceimpl
 * @author kim21017
 * @version $Id: MaPmRepListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmRepListServiceTarget"
 * @spring.txbn id="maPmRepListService"
 * @spring.property name="maPmRepListDAO" ref="maPmRepListDAO"
 */
public class MaPmRepListServiceImpl implements MaPmRepListService
{
    private MaPmRepListDAO maPmRepListDAO = null;

    public MaPmRepListDAO getMaPmRepListDAO() {
		return maPmRepListDAO;
	}

	public void setMaPmRepListDAO(MaPmRepListDAO maPmRepListDAO) {
		this.maPmRepListDAO = maPmRepListDAO;
	}
	
	public List findPmRepList(MaPmRepListDTO maPmRepListDTO, User user)
    {      
        return maPmRepListDAO.findPmRepList(maPmRepListDTO, user);
    }

	@Override
	public String findTotalCount(MaPmRepListDTO maPmRepListDTO, User user)
	{
		return maPmRepListDAO.findTotalCount(maPmRepListDTO, user);
	}
}