package dream.invt.rpt.invtprecon.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtRptInvtPreCon Page - List Service
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface InvtRptInvtPreConListService
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
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptInvtPreConCommonDTO
     * @return
     */
    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) throws Exception;
}
