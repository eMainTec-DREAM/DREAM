package dream.mgr.rpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.rpt.dao.MgrRptLoginLogListDAO;
import dream.mgr.rpt.dto.MgrRptLoginLogCommonDTO;
import dream.mgr.rpt.service.MgrRptLoginLogListService;

/**
 * 로그인 로그 리스트 Page - List Service implements
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrRptLoginLogListServiceTarget"
 * @spring.txbn id="mgrRptLoginLogListService"
 * @spring.property name="mgrRptLoginLogListDAO" ref="mgrRptLoginLogListDAO"
 */
public class MgrRptLoginLogListServiceImpl implements MgrRptLoginLogListService
{
    private MgrRptLoginLogListDAO mgrRptLoginLogListDAO = null;

    public List findList(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO, User user) throws Exception
    {      
        return mgrRptLoginLogListDAO.findList(mgrRptLoginLogCommonDTO,user);
    }

    public MgrRptLoginLogListDAO getMgrRptLoginLogListDAO() {
        return mgrRptLoginLogListDAO;
    }

    public void setMgrRptLoginLogListDAO(MgrRptLoginLogListDAO mgrRptLoginLogListDAO) {
        this.mgrRptLoginLogListDAO = mgrRptLoginLogListDAO;
    }    
    
    public String findTotalCount(MgrRptLoginLogCommonDTO mgrRptLoginLogCommonDTO,User user)  throws Exception
    {
        return mgrRptLoginLogListDAO.findTotalCount(mgrRptLoginLogCommonDTO, user);
    }

}
