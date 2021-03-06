package dream.ass.base.service;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValDetailDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
/**
 * ���׸� - Detail Service
 * @author kim21017
 * @version $Id: AssBasePointValDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface AssBasePointValDetailService
{    
	/**
	 * FIND DETAIL
	 * @param assBaseCommonDTO
	 * @param assBasePointListDTO
	 * @param assBasePointValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AssBasePointValDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception;
	/**
     * INSERT DETAIL
     * @param assBaseCommonDTO
     * @param assBaseDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO,AssBasePointValDetailDTO assBasePointValDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param assBaseCommonDTO
     * @param assBaseDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO,AssBasePointValDetailDTO assBasePointValDetailDTO, User user) throws Exception;
    
}
