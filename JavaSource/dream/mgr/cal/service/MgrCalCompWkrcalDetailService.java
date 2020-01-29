package dream.mgr.cal.service;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDetailDTO;

/**
 * 회사설정 - 상세 service
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 */
public interface MgrCalCompWkrcalDetailService
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     * @throws Exception
     */
    public MgrCalCompWkrcalDetailDTO findDetail(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user) throws Exception;
}
