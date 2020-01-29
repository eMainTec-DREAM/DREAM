package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgAsmbListDTO;
import dream.asset.categ.list.form.LovEqCtgAsmbListForm;

/**
 * 설비종류 작업부위팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEqCtgAsmbListService
{

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovEqCtgAsmbListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgAsmbList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser)throws Exception;

    public List findEqCtgAsmbACList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User user, LovEqCtgAsmbListForm lovEqCtgAsmbListForm)throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgAsmbListDTO
     * @return
     */
    public String findTotalCount(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User user) throws Exception;
}