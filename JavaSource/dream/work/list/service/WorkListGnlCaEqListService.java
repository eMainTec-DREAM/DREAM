package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���  - �˱��� - ǥ�ر� ���
 * @author  kim21017
 * @version $Id: WorkListGnlCaEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListGnlCaEqListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkListGnlCaEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListGnlCaEqListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: WorkListGnlCaEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteCavalList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)throws Exception;
}
