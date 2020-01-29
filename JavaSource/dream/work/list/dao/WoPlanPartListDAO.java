package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartListDTO;

/**
 * 작업계획목록 - 투입부품 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanPartListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanPartListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User loginUser);
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo);

	public int updateEmgPart(String woPartId, String compNo);
	
    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, User user) throws Exception;

    public String validWoPart(WoPlanCommonDTO woPlanCommonDTO, String multiKey) throws Exception;

    public int inputPartList(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, String multiKey) throws Exception;

    public int updateEmgPart(WoPlanCommonDTO woPlanCommonDTO, WoPlanPartListDTO woPlanPartListDTO, String multiKey) throws Exception;

}