package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartListDTO;

/**
 * 작업계획목록 - 투입부품  목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanPartListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanPartListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User loginUser);
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deletePartList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User user) throws Exception;
    public int inputPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO) throws Exception;

}
