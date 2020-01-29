package dream.mgr.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;

/**
 * 근무일달력 - 목록 service
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalCompWkrcalListService
{
    /**
     *  grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @param mgrCalCompWkrcalCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findWrkcalList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user);
    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteWrkcal(String[] deleteRows , String[] deleteRowsExt, User user) throws Exception;
    
    public String findTotalCount( MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user);
}
