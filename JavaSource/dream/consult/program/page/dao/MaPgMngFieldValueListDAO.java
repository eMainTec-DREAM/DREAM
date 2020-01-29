package dream.consult.program.page.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;

/**
 * 화면별 필드 기본값 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngFieldValueListDAO
{
    public List findList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user);
    public int deleteList(String id, User user);
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO, MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngFieldValueListDTO maPgMngFieldValueListDTO, User user) throws Exception;
}