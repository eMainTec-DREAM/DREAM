package dream.invt.prc.dao;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;

/**
 * 구매절차 dao
 * @author  kim21017
 * @version $Id: InvtPrcTpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface InvtPrcTpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: InvtPrcTpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @return List
     */
    public List findInvtPrcTpList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user);
    
    public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user);

    
    /**
     * delete
     * @author kim21017
     * @version $Id: InvtPrcTpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user);
    public int deleteTpItem(String id, User user);
    public int deleteObjDoc(String id, User user);
    public int deleteDoc(String id, User user);
}