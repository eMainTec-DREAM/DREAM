package dream.part.pur.po.service;

import java.util.List;

import common.bean.User;
import dream.part.pur.po.dto.MaPtPoCommonDTO;
import dream.part.pur.po.form.MaPtPoListForm;

/**
 * 발주이력 - 목록 service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtPoListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maPtPoCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtPoCommonDTO maPtPoCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
    public int recList(MaPtPoListForm maPtPoListForm, User user) throws Exception;
    
    public String findTotalCount(MaPtPoCommonDTO maPtPoCommonDTO, User user);

}
