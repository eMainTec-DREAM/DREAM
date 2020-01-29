package dream.mgr.cal.service;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetDetailDTO;

/**
 * 휴무일 설정 - 상세 service
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 */
public interface MgrCalCompWkrcalDaysetDetailService
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param wrkcalDaysetId
     * @param lang
     * @return
     * @throws Exception
     */
    public MgrCalCompWkrcalDaysetDetailDTO findDetail(String wrkcalDaysetId)throws Exception;
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailDTO
     * @param mgrCalCompWkrcalCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user) throws Exception;
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailDTO
     * @param mgrCalCompWkrcalCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO) throws Exception;
}
