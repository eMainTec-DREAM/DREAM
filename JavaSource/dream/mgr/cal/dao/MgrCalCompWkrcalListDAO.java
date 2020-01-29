package dream.mgr.cal.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;

/**
 * 근무일달력 - 목록 dao
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalCompWkrcalListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalCommonDTO
     * @return List
     */
    public List findWrkcalList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user);

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String wrkCalListId, User user);

    public String findTotalCount(
            MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user);
}