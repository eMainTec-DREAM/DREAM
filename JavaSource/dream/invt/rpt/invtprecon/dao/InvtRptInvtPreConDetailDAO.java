package dream.invt.rpt.invtprecon.dao;

import common.bean.User;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConDetailDTO;

/**
 * InvtRptInvtPreCon Page - Detail DAO
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface InvtRptInvtPreConDetailDAO
{
    /**
     * FIND DETAIL
     * @param invtRptInvtPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public InvtRptInvtPreConDetailDTO findDetail(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) throws Exception;
}
