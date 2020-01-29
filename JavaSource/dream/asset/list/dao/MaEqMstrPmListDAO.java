package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmListDTO;

/**
 * 설비 점검 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 *@since   1.0
 */
public interface MaEqMstrPmListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmListDTO
     * @param loginUser
     * @return List
     */
    public List findInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);
    public List findRplList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);
    public String findInsTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);
    public String findRplTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser);
}