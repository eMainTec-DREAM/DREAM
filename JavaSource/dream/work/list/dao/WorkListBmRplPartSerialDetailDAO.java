package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.WorkListBmRplPartSerialDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과-부품Serial 상세 dao
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListBmRplPartSerialDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListBmRplPartSerialListDTO
     * @param compNo
     * @return
     */
    public WorkListBmRplPartSerialDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    /**
     * 재고확인
     */
    public String validSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);

	/**
	 * Delete Garbage Serial
	 * @param workListBmRplPartSerialDetailDTO
	 * @param maWoResultMstrCommonDTO
	 */
	public void deleteGarbageSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO,
			MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
}