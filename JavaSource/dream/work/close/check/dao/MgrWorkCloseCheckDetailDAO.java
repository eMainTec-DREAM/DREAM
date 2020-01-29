package dream.work.close.check.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckDetailDTO;

/**
 * MgrWorkCloseCheck Page - Detail DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrWorkCloseCheckDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param mgrWorkCloseCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrWorkCloseCheckDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception;
    
    public int insertDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    public int insertWorkDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param mgrWorkCloseCheckDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    public int updateWorkDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    
    public int confirmDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    
    public int insertRevisionHist(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user, String histId);
	public int updateStdPointMstrLastVersion(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user, String histId);
	
}
