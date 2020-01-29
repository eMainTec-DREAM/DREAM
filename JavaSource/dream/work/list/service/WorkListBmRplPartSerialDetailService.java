package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WorkListBmRplPartSerialDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 자재Serial 상세
 * @author  kim210117
 * @version $Id: WorkListBmRplPartSerialDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface WorkListBmRplPartSerialDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListBmRplPartSerialListDTO
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkListBmRplPartSerialDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;

    /**
     * Serial중복검사
     */
    public String validSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser);
}
