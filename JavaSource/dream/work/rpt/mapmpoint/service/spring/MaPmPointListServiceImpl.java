package dream.work.rpt.mapmpoint.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmpoint.dao.MaPmPointListDAO;
import dream.work.rpt.mapmpoint.dto.MaPmPointListDTO;
import dream.work.rpt.mapmpoint.service.MaPmPointListService;

/**
 * 예방점검내역 serviceimpl
 * @author kim21017
 * @version $Id: MaPmPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmPointListServiceTarget"
 * @spring.txbn id="maPmPointListService"
 * @spring.property name="maPmPointListDAO" ref="maPmPointListDAO"
 */
public class MaPmPointListServiceImpl implements MaPmPointListService
{
    private MaPmPointListDAO maPmPointListDAO = null;

    public MaPmPointListDAO getMaPmPointListDAO() {
		return maPmPointListDAO;
	}

	public void setMaPmPointListDAO(MaPmPointListDAO maPmPointListDAO) {
		this.maPmPointListDAO = maPmPointListDAO;
	}
	
	public List findPmPointList(MaPmPointListDTO maPmPointListDTO, User user)
    {      
        return maPmPointListDAO.findPmPointList(maPmPointListDTO, user);
    }

	@Override
	public String findTotalCount(MaPmPointListDTO maPmPointListDTO, User user)
	{
		return maPmPointListDAO.findTotalCount(maPmPointListDTO, user);
	}
}