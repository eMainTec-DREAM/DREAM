package dream.mgr.cal.service;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetDetailDTO;

/**
 * 휴무요일설정 - 상세 service
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 */
public interface MgrCalCompWkrcalDowsetDetailService
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param wrkcalDowsetId
     * @param lang
     * @return
     * @throws Exception
     */
    public MgrCalCompWkrcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang)throws Exception;
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailDTO
     * @param mgrCalCompWkrcalCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user) throws Exception;
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailDTO
     * @param mgrCalCompWkrcalCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO) throws Exception;
}
