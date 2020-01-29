package dream.part.rec.dao;

import java.util.List;

import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;

/**
 * 구매입고 item 목록 dao
 * @author  kim21017
 * @version $Id: MaPtRecSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtRecSerialListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPtRecSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecCommonDTO
     * @param maPtRecSerialListDTO
     * @return List
     */
    public List findItemList(MaPtRecCommonDTO maPtRecCommonDTO, MaPtRecSerialListDTO maPtRecSerialListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPtRecSerialListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow, String deleteRowsExt);
}