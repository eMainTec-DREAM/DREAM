package dream.consult.program.page.service;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;

/**
 * 화면별 필드 기본값 상세
 * @author  kim210117
 * @version $Id: MaPgMngFieldValueDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPgMngFieldValueDetailService
{    
	public MaPgMngFieldValueDetailDTO findDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user);
    public int updateDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) throws Exception;
    public int insertDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) throws Exception;
    public String validCompNo(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user) throws Exception;
}
