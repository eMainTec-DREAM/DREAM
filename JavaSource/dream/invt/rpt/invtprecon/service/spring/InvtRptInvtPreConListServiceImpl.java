package dream.invt.rpt.invtprecon.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtprecon.dao.InvtRptInvtPreConListDAO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.service.InvtRptInvtPreConListService;

/**
 * InvtRptInvtPreCon Page - List Service implements
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="invtRptInvtPreConListServiceTarget"
 * @spring.txbn id="invtRptInvtPreConListService"
 * @spring.property name="invtRptInvtPreConListDAO" ref="invtRptInvtPreConListDAO"
 */
public class InvtRptInvtPreConListServiceImpl implements InvtRptInvtPreConListService
{
    private InvtRptInvtPreConListDAO invtRptInvtPreConListDAO = null;

    public List findList(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) throws Exception
    {      
        return invtRptInvtPreConListDAO.findList(invtRptInvtPreConCommonDTO,user);
    }

    public InvtRptInvtPreConListDAO getInvtRptInvtPreConListDAO() {
        return invtRptInvtPreConListDAO;
    }

    public void setInvtRptInvtPreConListDAO(InvtRptInvtPreConListDAO invtRptInvtPreConListDAO) {
        this.invtRptInvtPreConListDAO = invtRptInvtPreConListDAO;
    }    
    
    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO,User user)  throws Exception
    {
        return invtRptInvtPreConListDAO.findTotalCount(invtRptInvtPreConCommonDTO, user);
    }
}
