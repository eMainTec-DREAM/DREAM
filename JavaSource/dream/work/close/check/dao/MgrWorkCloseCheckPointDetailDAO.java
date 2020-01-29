package dream.work.close.check.dao;

import common.bean.User;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointDetailDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 */
public interface MgrWorkCloseCheckPointDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @param loginUser
     * @return
     */
    public MgrWorkCloseCheckPointDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailDTO
     * @return
     */
    public int insertDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO, MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailDTO
     * @return
     */
    public int updateDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO,MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser);
}