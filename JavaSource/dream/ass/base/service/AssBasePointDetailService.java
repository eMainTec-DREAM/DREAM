package dream.ass.base.service;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointDetailDTO;
import dream.ass.base.dto.AssBasePointListDTO;
/**
 * 평가항목 - Detail Service
 * @author kim21017
 * @version $Id: AssBasePointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface AssBasePointDetailService
{    
	/**
	 * FIND DETAIL
	 * @param assBaseCommonDTO
	 * @param assBasePointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AssBasePointDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param assBaseCommonDTO
	 * @param assBasePointDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointDetailDTO assBasePointDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param assBaseCommonDTO
     * @param assBasePointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointDetailDTO assBasePointDetailDTO, User user) throws Exception;
}
