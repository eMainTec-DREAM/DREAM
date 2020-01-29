package dream.ass.base.dao;

import java.util.List;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValListDTO;

/**
 * 평가기준 - List DAO
 * @author kim21017
 * @version $Id: AssBasePointValListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface AssBasePointValListDAO
{
	/**
	 * FIND LIST
	 * @param assBaseCommonDTO
	 * @param assBasePointListDTO
	 * @param assBasePointValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param assBaseCommonDTO
	 * @param assBasePointListDTO
	 * @param assBasePointValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception;
    
}