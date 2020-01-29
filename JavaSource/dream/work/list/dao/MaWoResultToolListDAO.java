package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolListDTO;

/**
 * 작업결과 투입공기구 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultToolListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultToolListDAO extends BaseJdbcDaoSupportIntf
{
	//조회
    public List findToolList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User loginUser);
    //삭제
    public int deleteToolList(String id, String compNo);
	//건수조회
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User user) throws Exception;
}