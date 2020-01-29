package dream.ass.base.service;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeDetailDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;
/**
 * 등급기준 - Detail Service
 * @author kim21017
 * @version $Id: AssBaseGradeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface AssBaseGradeDetailService
{    
	/**
	 * FIND DETAIL
	 * @param assBaseCommonDTO
	 * @param assBaseGradeListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AssBaseGradeDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeListDTO assBaseGradeListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param assBaseCommonDTO
	 * @param assBaseGradeDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param assBaseCommonDTO
     * @param assBaseGradeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception;
    
    /**
     * GRADE VALID CHECK
     * @param assBaseCommonDTO
     * @param assBaseGradeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String validGrade(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception;
    
    /**
     * FROM TO VALID CHECK
     * @param assBaseCommonDTO
     * @param assBaseGradeDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String validFromTo(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception;
}
