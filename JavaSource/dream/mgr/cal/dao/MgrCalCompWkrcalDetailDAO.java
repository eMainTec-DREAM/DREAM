package dream.mgr.cal.dao;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDetailDTO;

/**
 * 회사설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 */
public interface MgrCalCompWkrcalDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrCalCompWkrcalDetailDTO findDetail(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user);

    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailDTO
     * @return
     */
    public int insertDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user);

    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailDTO
     * @return
     */
    public int updateDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user);

    public void SP_SETDEFAULT_WRKCAL_BYONE(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user) throws Exception;
}