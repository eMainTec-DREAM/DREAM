package dream.mgr.cal.dao;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetDetailDTO;

/**
 * 휴무요일설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 */
public interface MgrCalCompWkrcalDowsetDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrCalCompWkrcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang);

    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailDTO
     * @return
     */
    public int insertDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user);

    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailDTO
     * @return
     */
    public int updateDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO);
}