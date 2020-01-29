package dream.mgr.cal.dao;

import java.util.List;

import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetListDTO;

/**
 * 휴무일 설정  - 목록 dao
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDaysetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalCompWkrcalDaysetListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalCommonDTO
     * @return List
     */
    public List findDaysetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO);

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String id);

    public String findTotalCount(  MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO);
}