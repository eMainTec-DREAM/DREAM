package dream.ass.base.dao;

import java.util.List;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;

/**
 * 등급기준 - List DAO
 * @author kim21017
 * @version $Id: AssBaseGradeListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface AssBaseGradeListDAO
{
	/**
	 * FIND LIST
	 * @param assBaseCommonDTO
	 * @param assBaseGradeListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeListDTO assBaseGradeListDTO, User user) throws Exception;
    
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
	 * @param assBaseGradeListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeListDTO assBaseGradeListDTO, User user) throws Exception;
    
}