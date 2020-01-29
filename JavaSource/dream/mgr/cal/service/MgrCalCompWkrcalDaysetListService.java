package dream.mgr.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetListDTO;

/**
 * 휴무일 설정  - 목록 service
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDaysetListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalCompWkrcalDaysetListService
{
    /**
     *  grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @param mgrCalCompWkrcalCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findDaysetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO);
    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteWrkcal(String[] deleteRows) throws Exception;
    
    public String findTotalCount(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO);
}
