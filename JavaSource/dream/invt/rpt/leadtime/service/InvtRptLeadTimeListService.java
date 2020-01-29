package dream.invt.rpt.leadtime.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.leadtime.dto.InvtRptLeadTimeCommonDTO;
import dream.invt.rpt.leadtime.form.InvtRptLeadTimeListForm;
/**
 * InvtRptLeadTime Page - List Service
 * @author cjscjs9
 * @version $Id: InvtRptLeadTimeListService.java,v 1.0 2017/08/22 15:19:40 cjscjs9 Exp $
 * @since 1.0
 */
public interface InvtRptLeadTimeListService
{
    /**
     * FIND LIST
     * @param invtRptLeadTimeCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser) throws Exception;
    
    /**
     * find Total Count
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptLeadTimeCommonDTO
     * @return
     */
    public String findTotalCount(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User user) throws Exception;
    public List findChart(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser);
}
