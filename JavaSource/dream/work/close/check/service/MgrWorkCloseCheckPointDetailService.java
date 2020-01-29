package dream.work.close.check.service;

import common.bean.User;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointDetailDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * 표준항목 리스트 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MgrWorkCloseCheckPointDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MgrWorkCloseCheckPointDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailDTO
     * @param mgrWorkCloseCheckCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO,MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailDTO
     * @param mgrWorkCloseCheckCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO, MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser) throws Exception;
}
