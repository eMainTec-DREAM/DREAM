package dream.ass.base.service;

import java.util.List;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
/**
 * 평가기준 - List Service
 * @author kim21017
 * @version $Id: AssBasePointValListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface AssBasePointValListService {
	/**
	 * FIND LIST
	 * @param assBaseCommonDTO
	 * @param assBasePointListDTO
	 * @param assBasePointValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception;
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
	 * @param assBasePointValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception;
}
