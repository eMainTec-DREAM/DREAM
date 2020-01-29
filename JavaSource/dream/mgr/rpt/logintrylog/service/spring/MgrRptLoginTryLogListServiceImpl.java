package dream.mgr.rpt.logintrylog.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.logintrylog.dao.MgrRptLoginTryLogListDAO;
import dream.mgr.rpt.logintrylog.dto.MgrRptLoginTryLogCommonDTO;
import dream.mgr.rpt.logintrylog.service.MgrRptLoginTryLogListService;

/**
 * 로그인 시도 로그 리스트 Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrRptLoginTryLogListServiceTarget"
 * @spring.txbn id="mgrRptLoginTryLogListService"
 * @spring.property name="mgrRptLoginTryLogListDAO" ref="mgrRptLoginTryLogListDAO"
 */
public class MgrRptLoginTryLogListServiceImpl implements MgrRptLoginTryLogListService
{
    private MgrRptLoginTryLogListDAO mgrRptLoginTryLogListDAO = null;

    public List findList(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO, User user) throws Exception
    {      
        return mgrRptLoginTryLogListDAO.findList(mgrRptLoginTryLogCommonDTO,user);
    }

    public MgrRptLoginTryLogListDAO getMgrRptLoginTryLogListDAO() {
        return mgrRptLoginTryLogListDAO;
    }

    public void setMgrRptLoginTryLogListDAO(MgrRptLoginTryLogListDAO mgrRptLoginTryLogListDAO) {
        this.mgrRptLoginTryLogListDAO = mgrRptLoginTryLogListDAO;
    }    
    
    public String findTotalCount(MgrRptLoginTryLogCommonDTO mgrRptLoginTryLogCommonDTO,User user)  throws Exception
    {
        return mgrRptLoginTryLogListDAO.findTotalCount(mgrRptLoginTryLogCommonDTO, user);
    }

}
