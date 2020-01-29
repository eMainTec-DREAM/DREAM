package dream.invt.rpt.invtprecon.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.invtprecon.dto.InvtItemListDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtItem Page - List DAO
 * @author youngjoo38
 * @version $Id: InvtItemListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface InvtItemListDAO
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
     * DELETE LIST
     * @param invtItemListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, InvtItemListDTO invtItemListDTO, User user) throws Exception;
    
}
