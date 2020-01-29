package dream.mgr.cal.dao;

import java.util.List;

import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;

/**
 * 휴무요일 설정  - 목록 dao
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalCompWkrcalDowsetListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalCommonDTO
     * @return List
     */
    public List findDowsetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO);

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String id);
}