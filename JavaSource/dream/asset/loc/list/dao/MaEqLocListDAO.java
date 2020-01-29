package dream.asset.loc.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.loc.list.dto.MaEqLocCommonDTO;

/**
 * 설비위치 - 목록 
 * @author  kim21017
 * @version $Id: MaEqLocListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqLocListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqLocListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocCommonDTO
     * @return List
     */
    public List findEqLocList(MaEqLocCommonDTO maEqLocCommonDTO,User user);
    

    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqLocListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqLocCommonDTO
     * @return
     */
    public int deleteEqLoc(String id, MaEqLocCommonDTO maEqLocCommonDTO, User user);
    

    public int resetFullDesc(MaEqLocCommonDTO maEqLocCommonDTO, User user);
}