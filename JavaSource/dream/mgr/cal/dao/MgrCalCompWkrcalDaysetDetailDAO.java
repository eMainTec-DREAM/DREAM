package dream.mgr.cal.dao;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetDetailDTO;

/**
 * 휴무일 설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 */
public interface MgrCalCompWkrcalDaysetDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrCalCompWkrcalDaysetDetailDTO findDetail(String wrkcalDaysetId);

    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailDTO
     * @return
     */
    public int insertDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user);

    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailDTO
     * @return
     */
    public int updateDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO);
}