package dream.invt.rpt.leadtime.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.leadtime.dao.InvtRptLeadTimeListDAO;
import dream.invt.rpt.leadtime.dto.InvtRptLeadTimeCommonDTO;
import dream.invt.rpt.leadtime.service.InvtRptLeadTimeListService;
import dream.invt.rpt.leadtime.form.InvtRptLeadTimeListForm;

/**
 * InvtRptLeadTime Page - List Service implements
 * @author cjscjs9
 * @version $Id: InvtRptLeadTimeListServiceImpl.java,v 1.0 2017/08/22 15:19:40 cjscjs9 Exp $
 * @since 1.0
 * @spring.bean id="invtRptLeadTimeListServiceTarget"
 * @spring.txbn id="invtRptLeadTimeListService"
 * @spring.property name="invtRptLeadTimeListDAO" ref="invtRptLeadTimeListDAO"
 */
public class InvtRptLeadTimeListServiceImpl implements InvtRptLeadTimeListService
{
    private InvtRptLeadTimeListDAO invtRptLeadTimeListDAO = null;

    public List findList(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser) throws Exception
    {      
        return invtRptLeadTimeListDAO.findList(invtRptLeadTimeCommonDTO,loginUser);
    }

    public InvtRptLeadTimeListDAO getInvtRptLeadTimeListDAO() {
        return invtRptLeadTimeListDAO;
    }

    public void setInvtRptLeadTimeListDAO(InvtRptLeadTimeListDAO invtRptLeadTimeListDAO) {
        this.invtRptLeadTimeListDAO = invtRptLeadTimeListDAO;
    }    
    
    public String findTotalCount(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO,User user)  throws Exception
    {
        return invtRptLeadTimeListDAO.findTotalCount(invtRptLeadTimeCommonDTO, user);
    }
    public List findChart(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser)
    {
    	return invtRptLeadTimeListDAO.findChart(invtRptLeadTimeCommonDTO, loginUser);
    }
}
