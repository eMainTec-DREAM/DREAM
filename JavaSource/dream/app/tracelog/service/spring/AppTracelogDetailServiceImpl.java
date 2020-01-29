package dream.app.tracelog.service.spring;

import common.bean.User;
import dream.app.tracelog.dao.AppTracelogDetailDAO;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
import dream.app.tracelog.dto.AppTracelogDetailDTO;
import dream.app.tracelog.service.AppTracelogDetailService;

/**
 * TraceLog - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="appTracelogDetailServiceTarget"
 * @spring.txbn id="appTracelogDetailService"
 * @spring.property name="appTracelogDetailDAO" ref="appTracelogDetailDAO"
 */
public class AppTracelogDetailServiceImpl implements AppTracelogDetailService
{
    private AppTracelogDetailDAO appTracelogDetailDAO = null;
    
    public AppTracelogDetailDTO findCompTracelogDetail(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception
    {
    	return appTracelogDetailDAO.findCompTracelogDetail(appTracelogCommonDTO, user);
    }
    
    public int insertCompTracelogDetail(AppTracelogDetailDTO appTracelogDetailDTO, User user) throws Exception
    {
    	return appTracelogDetailDAO.insertCompTracelogDetail(appTracelogDetailDTO, user);
    }
    
    public int updateCompTracelogDetail(AppTracelogDetailDTO appTracelogDetailDTO, User user) throws Exception
    {
    	 return appTracelogDetailDAO.updateCompTracelogDetail(appTracelogDetailDTO, user);
    }

	public AppTracelogDetailDAO getAppTracelogDetailDAO() {
		return appTracelogDetailDAO;
	}

	public void setAppTracelogDetailDAO(AppTracelogDetailDAO appTracelogDetailDAO) {
		this.appTracelogDetailDAO = appTracelogDetailDAO;
	}
}
