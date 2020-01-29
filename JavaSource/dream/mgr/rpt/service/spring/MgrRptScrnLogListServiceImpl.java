package dream.mgr.rpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.dao.MgrRptScrnLogListDAO;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.dto.MgrRptScrnLogCommonDTO;
import dream.mgr.rpt.service.MgrRptScrnLogListService;

/**
 * 화면접속로그 리스트 Page - List Service implements
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrRptScrnLogListServiceTarget"
 * @spring.txbn id="mgrRptScrnLogListService"
 * @spring.property name="mgrRptScrnLogListDAO" ref="mgrRptScrnLogListDAO"
 */
public class MgrRptScrnLogListServiceImpl implements MgrRptScrnLogListService
{
    private MgrRptScrnLogListDAO mgrRptScrnLogListDAO = null;

    public List findList(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception
    {      
        return mgrRptScrnLogListDAO.findList(mgrRptScrnLogCommonDTO, mgrRptLoginLogCommonDTO, user);
    }

    public MgrRptScrnLogListDAO getMgrRptScrnLogListDAO() {
        return mgrRptScrnLogListDAO;
    }

    public void setMgrRptScrnLogListDAO(MgrRptScrnLogListDAO mgrRptScrnLogListDAO) {
        this.mgrRptScrnLogListDAO = mgrRptScrnLogListDAO;
    }    
    
    public String findTotalCount(MgrRptScrnLogCommonDTO mgrRptScrnLogCommonDTO, MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user)  throws Exception
    {
        return mgrRptScrnLogListDAO.findTotalCount(mgrRptScrnLogCommonDTO, mgrRptLoginLogCommonDTO, user);
    }

}
