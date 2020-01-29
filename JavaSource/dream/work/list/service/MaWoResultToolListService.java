package dream.work.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.dto.MaWoResultToolListDTO;

/**
 * �۾���� ���԰��ⱸ  ���
 * @author  kim21017
 * @version $Id: MaWoResultToolListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultToolListService
{
	//��ȸ
    public List findToolList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User loginUser);
    //����
    public int deleteToolList(String[] deleteRows, String compNo) throws Exception;
    //�Ǽ���ȸ
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User user) throws Exception;
    //����
    public int inputToolList(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    //Edit�� ����
    public void saveList(List<Map> gridList, User user) throws Exception;
}
