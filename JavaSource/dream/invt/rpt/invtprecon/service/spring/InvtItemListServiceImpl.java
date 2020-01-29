package dream.invt.rpt.invtprecon.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtprecon.dao.InvtItemListDAO;
import dream.invt.rpt.invtprecon.dto.InvtItemListDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.service.InvtItemListService;

/**
 * InvtItem Page - List Service implements
 * @author youngjoo38
 * @version $Id: InvtItemListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="invtItemListServiceTarget"
 * @spring.txbn id="invtItemListService"
 * @spring.property name="invtItemListDAO" ref="invtItemListDAO"
 */
public class InvtItemListServiceImpl implements InvtItemListService
{
    private InvtItemListDAO invtItemListDAO = null;

    public List findList(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, InvtItemListDTO invtItemListDTO, User user) throws Exception
    {      
        return invtItemListDAO.findList(invtRptInvtPreConCommonDTO,invtItemListDTO,user);
    }

    public InvtItemListDAO getInvtItemListDAO() {
        return invtItemListDAO;
    }

    public void setInvtItemListDAO(InvtItemListDAO invtItemListDAO) {
        this.invtItemListDAO = invtItemListDAO;
    }    
    
    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, InvtItemListDTO invtItemListDTO, User user)  throws Exception
    {
        return invtItemListDAO.findTotalCount(invtRptInvtPreConCommonDTO, invtItemListDTO, user);
    }
}
