package dream.ass.base.service;

import java.util.List;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
/**
 * 평가항목 - List Service
 * @author kim21017
 * @version $Id: AssBasePointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface AssBasePointListService {
	/**
	 * FIND LIST
	 * @param assBaseCommonDTO
	 * @param assBasePointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, User user) throws Exception;
	/**
	 * DELETE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param assBaseCommonDTO
	 * @param assBasePointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO, User user) throws Exception;
}
