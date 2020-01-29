package dream.mgr.cal.service;

import java.util.List;

import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;

/**
 * 휴무요일설정 설정  - 목록 service
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalCompWkrcalDowsetListService
{
    /**
     *  grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @param mgrCalCompWkrcalCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findDowsetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO);
    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteWrkcal(String[] deleteRows) throws Exception;
}
