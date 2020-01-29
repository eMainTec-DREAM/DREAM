package dream.invt.rpt.invtprecon.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtprecon.dto.InvtItemListDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtItem Page - List Service
 * @author youngjoo38
 * @version $Id: InvtItemListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface InvtItemListService
{
    /**
     * FIND LIST
     * @param invtItemListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, InvtItemListDTO invtItemListDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtItemListDTO
     * @return
     */
    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, InvtItemListDTO invtItemListDTO, User user) throws Exception;
}
