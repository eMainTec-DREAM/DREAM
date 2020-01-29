package dream.invt.rpt.invtprecon.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtRptInvtPreCon Page - List DAO
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface InvtRptInvtPreConListDAO
{
    /**
     * FIND LIST
     * @param invtRptInvtPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param invtRptInvtPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) throws Exception;
    
}
