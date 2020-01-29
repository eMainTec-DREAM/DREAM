package dream.invt.rpt.invtprecon.service.spring;

import common.bean.User;
import dream.invt.rpt.invtprecon.dao.InvtRptInvtPreConDetailDAO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConDetailDTO;
import dream.invt.rpt.invtprecon.service.InvtRptInvtPreConDetailService;

/**
 * InvtRptInvtPreCon Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="invtRptInvtPreConDetailServiceTarget"
 * @spring.txbn id="invtRptInvtPreConDetailService"
 * @spring.property name="invtRptInvtPreConDetailDAO" ref="invtRptInvtPreConDetailDAO"
 */
public class InvtRptInvtPreConDetailServiceImpl implements InvtRptInvtPreConDetailService
{
    private InvtRptInvtPreConDetailDAO invtRptInvtPreConDetailDAO = null;
    
    public InvtRptInvtPreConDetailDTO findDetail(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) throws Exception
    {
        return invtRptInvtPreConDetailDAO.findDetail(invtRptInvtPreConCommonDTO, user);
    }
    
    public InvtRptInvtPreConDetailDAO getInvtRptInvtPreConDetailDAO() {
        return invtRptInvtPreConDetailDAO;
    }

    public void setInvtRptInvtPreConDetailDAO(InvtRptInvtPreConDetailDAO invtRptInvtPreConDetailDAO) {
        this.invtRptInvtPreConDetailDAO = invtRptInvtPreConDetailDAO;
    }
}
