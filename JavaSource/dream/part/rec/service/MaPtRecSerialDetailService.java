package dream.part.rec.service;

import dream.part.rec.dto.MaPtRecSerialDetailDTO;
import common.bean.User;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 구매입고item - detail
 * @author  kim21017
 * @version $Id: MaPtRecSerialDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPtRecSerialDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialListDTO
     * @param maPtRecCommonDTO
     * @return
     * @throws Exception
     */
    public MaPtRecSerialDetailDTO findDetail(MaPtRecSerialListDTO maPtRecSerialListDTO, MaPtRecCommonDTO maPtRecCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailDTO
     * @param maPtRecCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailDTO
     * @param maPtRecCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO) throws Exception;
    
    /**
     * 시리얼중복검사
     */
    public String validSerial(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO,User loginUser);
}
