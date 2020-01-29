package dream.work.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.dto.MaWoResultToolListDTO;

/**
 * 작업결과 투입공기구  목록
 * @author  kim21017
 * @version $Id: MaWoResultToolListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultToolListService
{
	//조회
    public List findToolList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User loginUser);
    //삭제
    public int deleteToolList(String[] deleteRows, String compNo) throws Exception;
    //건수조회
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User user) throws Exception;
    //생성
    public int inputToolList(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    //Edit시 저장
    public void saveList(List<Map> gridList, User user) throws Exception;
}
