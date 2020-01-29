package dream.work.close.check.service;

import common.bean.User;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckDetailDTO;

/**
 * MgrWorkCloseCheck Page - Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrWorkCloseCheckDetailService
{
    /**
     * FIND DETAIL
     * @param mgrWorkCloseCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrWorkCloseCheckDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception;
    /**
     * INSERT DETAIL 
     * @param mgrWorkCloseCheckDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL 
     * @param mgrWorkCloseCheckDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    
    public int confirmDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
    
    public int insertRevisionHistAndUpdateMstr(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception;
}
