package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanCraftListDTO;

/**
 * 작업계획목록 - 작업자  목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanCraftListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanCraftListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findCraftList(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User loginUser);
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
    public int deleteCraftList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User user) throws Exception;
    public int inputCraftList(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO,User user) throws Exception;


}
