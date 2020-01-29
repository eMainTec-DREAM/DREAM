package dream.part.rec.dao;

import dream.part.rec.dto.MaPtRecSerialDetailDTO;
import common.bean.User;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 구매입고 item 상세 dao
 * @author  kim21017
 * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtRecSerialDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialListDTO
     * @param maPtRecCommonDTO
     * @return
     */
    public MaPtRecSerialDetailDTO findDetail(MaPtRecSerialListDTO maPtRecSerialListDTO, MaPtRecCommonDTO maPtRecCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailDTO
     * @param maPtRecCommonDTO
     * @return
     */
    public int updateDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailDTO
     * @param maPtRecCommonDTO
     * @return
     */
    public int insertDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO);
    
    /**
     * 재고확인
     */
    public String validSerial(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO, User loginUser);
}