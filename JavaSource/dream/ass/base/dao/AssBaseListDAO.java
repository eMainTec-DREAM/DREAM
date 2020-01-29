package dream.ass.base.dao;

import java.util.List;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;

/**
 * 설비등급 평가기준 - List DAO
 * @author kim21017
 * @version $Id: AssBaseListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface AssBaseListDAO
{
	/**
	 * FIND LIST
	 * @param assBaseCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception;
    
    /**
     * DELETE HDR LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE GRADE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteGradeList(String id, User user) throws Exception;
    /**
     * DELETE POINT LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePointList(String id, User user) throws Exception;
    /**
     * DELETE VALUE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteValList(String id, User user) throws Exception;

	/**
	 * FIND LIST COUNT
	 * @param assBaseCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception;
}