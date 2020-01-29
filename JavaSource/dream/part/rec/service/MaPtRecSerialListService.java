package dream.part.rec.service;

import java.util.List;

import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;

/**
 * 구매입고item 목록
 * @author  kim21017
 * @version $Id: MaPtRecSerialListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtRecSerialListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPtRecSerialListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecCommonDTO
     * @param maPtRecSerialListDTO
     * @throws Exception
     */
    public List findItemList(MaPtRecCommonDTO maPtRecCommonDTO, MaPtRecSerialListDTO maPtRecSerialListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaPtRecSerialListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] m_id, String[] d_id) throws Exception;

}
