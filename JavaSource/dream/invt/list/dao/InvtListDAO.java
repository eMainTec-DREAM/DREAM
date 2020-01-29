package dream.invt.list.dao;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;

/**
 *  dao
 * @author  kim21017
 * @version $Id: InvtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface InvtListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: InvtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param user 
     * @return List
     * @throws IOException 
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user) throws IOException;

	public int deletePhase(String id, User loginUser);

	public int deleteInvtList(String id, User loginUser);
	
    public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;
    
    public String getData(User user);
}