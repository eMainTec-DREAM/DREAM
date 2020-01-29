package dream.consult.program.page.dao;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;

/**
 * 화면별 필드 기본값 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngFieldValueDetailDAO
{
    public MaPgMngFieldValueDetailDTO findDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user);
    public int updateDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user);
    public int insertDetail(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user);
    public String validCompNo(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO, User user);
}