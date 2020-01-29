package dream.app.tracelog.service.spring;

import java.util.List;

import common.bean.User;
import dream.app.tracelog.dao.AppTracelogListDAO;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
import dream.app.tracelog.service.AppTracelogListService;

/**
 * TraceLog - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="appTracelogListServiceTarget"
 * @spring.txbn id="appTracelogListService"
 * @spring.property name="appTracelogListDAO" ref="appTracelogListDAO"
 */
public class AppTracelogListServiceImpl implements AppTracelogListService
{
	private AppTracelogListDAO appTracelogListDAO = null;

	public List findCompTracelogList(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception
    {      
        return appTracelogListDAO.findCompTracelogList(appTracelogCommonDTO,user);
    }

	public int deleteCompTracelogList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + appTracelogListDAO.deleteCompTracelogList(id, user);
            }
        return result;
    }

	public AppTracelogListDAO getAppTracelogListDAO() {
		return appTracelogListDAO;
	}

	public void setAppTracelogListDAO(AppTracelogListDAO appTracelogListDAO) {
		this.appTracelogListDAO = appTracelogListDAO;
	}
	public String findTotalCount(AppTracelogCommonDTO appTracelogCommonDTO,User user) throws Exception
    {
        return appTracelogListDAO.findTotalCount(appTracelogCommonDTO, user);
    }
}

