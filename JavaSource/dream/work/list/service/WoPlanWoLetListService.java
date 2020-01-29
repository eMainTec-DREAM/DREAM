package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;

/**
 * 작업계획목록 - 안전작업  목록
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanWoLetListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanWoLetListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findWoLetList(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser);
    /**
     *  delete
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteWoLetList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User user) throws Exception;
    public int inputWoLetList(WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, WoPlanCommonDTO woPlanCommonDTO,User user) throws Exception;


}
