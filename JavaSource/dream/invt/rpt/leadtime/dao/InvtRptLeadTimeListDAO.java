package dream.invt.rpt.leadtime.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.leadtime.dto.InvtRptLeadTimeCommonDTO;

/**
 * InvtRptLeadTime Page - List DAO
 * @author cjscjs9
 * @version $Id: InvtRptLeadTimeListDAO.java,v 1.0 2017/08/22 15:19:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public interface InvtRptLeadTimeListDAO
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
     * FIND TOTAL LIST
     * @param invtRptLeadTimeCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User user) throws Exception;
    
    public List findChart(InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO, User loginUser);

}
