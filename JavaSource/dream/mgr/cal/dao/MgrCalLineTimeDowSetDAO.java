package dream.mgr.cal.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.cal.dto.MgrCalLineTimeDowSetDTO;

/**
 * 요일별 설정 목록 dao
 * @author  euna0207
 * @version $Id: MgrCalLineTimeDowSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalLineTimeDowSetDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @param mgrCalLineTimeDowSetDTO
     * @param loginUser
     * @return List
     */
//    public List findDowList(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User loginUser);
    public List findDowList(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User loginUser);

    public String findTotalCount(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);

    public int[] updateDetail(final List<MgrCalLineTimeDowSetDTO> list, User user) throws Exception;
    
    public int[] insertDetail(final List<MgrCalLineTimeDowSetDTO> list, User user) throws Exception;
    
    public int[] deleteDowList(final List<MgrCalLineTimeDowSetDTO> list, final User user) throws Exception;

    public String getColums(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);
    
    public String getTables(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);
    
    public String getOrderBy(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);
    
    public String getWhere(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);
    /**
     * 요일 중복 검사
     */
    public String validDay(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);
    public int deleteWrkTime(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);
    public int execRunTime(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user);
}